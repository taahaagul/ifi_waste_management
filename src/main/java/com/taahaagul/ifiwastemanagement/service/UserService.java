package com.taahaagul.ifiwastemanagement.service;

import com.taahaagul.ifiwastemanagement.entity.Role;
import com.taahaagul.ifiwastemanagement.entity.User;
import com.taahaagul.ifiwastemanagement.exception.UserNotFoundException;
import com.taahaagul.ifiwastemanagement.repository.UserRepository;
import com.taahaagul.ifiwastemanagement.request.UserChangePaswRequest;
import com.taahaagul.ifiwastemanagement.request.UserUpdateRequest;
import com.taahaagul.ifiwastemanagement.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AuthenticationService authenticationService;
    private final PasswordEncoder passwordEncoder;

    public UserResponse getCurrentUser() {
        return new UserResponse(authenticationService.getCurrentUser());
    }

    public List<UserResponse> getAllUsers() {
        List<User> list = userRepository.findAll();
        return list.stream()
                .map(user -> new UserResponse(user))
                .collect(Collectors.toList());
    }

    public void changePassword(UserChangePaswRequest request) {
        User user = authenticationService.getCurrentUser();
        if(passwordEncoder.matches(request.getOldPasw(), user.getPassword())) {
            user.setPassword(passwordEncoder.encode(request.getNewPasw()));
            userRepository.save(user);
        } else
            throw new UserNotFoundException("Password unmatched!");
    }

    public UserResponse updateUser(UserUpdateRequest request) {
        User findedUser = userRepository.findById(request.getId())
                .orElseThrow(()-> new UserNotFoundException("User is not founded!"));

        findedUser.setFirstName(request.getFirstName());
        findedUser.setLastName(request.getLastName());
        findedUser.setUserName(request.getUserName());
        findedUser.setEmail(request.getEmail());

        userRepository.save(findedUser);
        return new UserResponse(findedUser);
    }

    public void deleteUser(Long userId) {
        User foundUser = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User is not founded"));

        if(foundUser.getRole().equals(Role.SUPER_ADMIN)) {
            throw new UserNotFoundException("SUPER_ADMIN can not be deleted");
        }

        userRepository.delete(foundUser);
    }

    public List<Role> getAllRole() {
        return Arrays.asList(Role.values());
    }

    public void updateUserRole(Long userId, String userRole) {
        User foundUser = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User is not founded"));

        if(foundUser.getRole().equals(Role.SUPER_ADMIN)) {
            throw new UserNotFoundException("SUPER_ADMIN can not change role");
        }

        foundUser.setRole(Role.valueOf(userRole));
        userRepository.save(foundUser);
    }

    public void changeUserEnabled(Long userId) {
        User foundUser = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User is not founded"));

        if(foundUser.getRole().equals(Role.SUPER_ADMIN)) {
            throw new UserNotFoundException("SUPER_ADMIN can not change enabled");
        }

        if(foundUser.isEnabled())
            foundUser.setEnabled(false);
        else
            foundUser.setEnabled(true);

        userRepository.save(foundUser);
    }
}

