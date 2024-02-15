package com.taahaagul.ifiwastemanagement.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taahaagul.ifiwastemanagement.config.JwtService;
import com.taahaagul.ifiwastemanagement.entity.*;
import com.taahaagul.ifiwastemanagement.exception.IncorrectValueException;
import com.taahaagul.ifiwastemanagement.exception.ResourceNotFoundException;
import com.taahaagul.ifiwastemanagement.repository.UserRepository;
import com.taahaagul.ifiwastemanagement.repository.VerificationTokenRepository;
import com.taahaagul.ifiwastemanagement.dto.ForgetPasswordDTO;
import com.taahaagul.ifiwastemanagement.dto.LoginDTO;
import com.taahaagul.ifiwastemanagement.dto.RegisterDTO;
import com.taahaagul.ifiwastemanagement.dto.AuthenticationDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final VerificationTokenRepository verificationTokenRepository;
    private final MailService mailService;

    public void register(RegisterDTO request) {
        Optional<User> existingUserName = userRepository.findByUserName(request.getUserName());
        if(existingUserName.isPresent())
            throw new IncorrectValueException("Username already exist!");

        Optional<User> existingUser = userRepository.findByEmail(request.getEmail());
        if (existingUser.isPresent()) {
            throw new IncorrectValueException("Email already exist!");
        }

        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .userName(request.getUserName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .enabled(true)
                .build();
        userRepository.save(user);

        mailService.sendMail(new NotificationEmail("Ifi Waste Management Credentials",
                user.getEmail(), "Congratulations, you have been registered in the " +
                "IFI Waste Management System, \n" +
                "Your credentials are below : \n" +
                "Username : " + request.getEmail() + "\n" +
                "Password : " + request.getPassword()));
    }

    public AuthenticationDTO authenticate(LoginDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        return AuthenticationDTO.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    public String generateVerificationToken(User user) {
        if(user.isEnabled())
            revokeAllVerificationToken(user);

        String token = UUID.randomUUID().toString();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE, 4);
        Date expirationTime = calendar.getTime();

        VerificationToken verificationToken = VerificationToken.builder()
                .token(token)
                .user(user)
                .expirationTime(expirationTime)
                .build();

        verificationTokenRepository.save(verificationToken);
        return token;
    }

    private void revokeAllVerificationToken(User user) {
        var verificationTokens = verificationTokenRepository.findByUser(user);
        if (verificationTokens.isEmpty())
            return;
        verificationTokenRepository.deleteAll(verificationTokens);
    }


    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            var user = this.userRepository.findByEmail(userEmail)
                    .orElseThrow();
            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = jwtService.generateToken(user);
                var authResponse = AuthenticationDTO.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }

    public User getCurrentUser() {
        User user = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(() -> new ResourceNotFoundException("User", "userId", "email"));
        return user;
    }

    public void forgetMyPaswToken(String email) {
        User existingUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", email));

        String token = generateVerificationToken(existingUser);

        mailService.sendMail(new NotificationEmail("Forget My Password",
                existingUser.getEmail(), "Please copy this token = " + token));
    }

    public void forgetChangePasw(ForgetPasswordDTO forgetPasswordDTO) {

        VerificationToken verificationToken = verificationTokenRepository
                .findByToken(forgetPasswordDTO.getToken())
                .orElseThrow(() -> new ResourceNotFoundException("VerificationToken", "Token", forgetPasswordDTO.getToken()));

        Date now = new Date();

        if(verificationToken.getExpirationTime().after(now)) {
            User user = verificationToken.getUser();
            user.setPassword(passwordEncoder.encode(forgetPasswordDTO.getNewPasw()));
            userRepository.save(user);
        } else {
            throw new IncorrectValueException("Token is expired!");
        }
    }
}
