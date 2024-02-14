package com.taahaagul.ifiwastemanagement.service;

import com.taahaagul.ifiwastemanagement.dto.UserDTO;
import com.taahaagul.ifiwastemanagement.entity.Car;
import com.taahaagul.ifiwastemanagement.entity.Role;
import com.taahaagul.ifiwastemanagement.entity.User;
import com.taahaagul.ifiwastemanagement.exception.IncorrectValueException;
import com.taahaagul.ifiwastemanagement.exception.ResourceNotFoundException;
import com.taahaagul.ifiwastemanagement.exception.RoleUnmathcedException;
import com.taahaagul.ifiwastemanagement.mapper.UserDTOMapper;
import com.taahaagul.ifiwastemanagement.repository.CarRepository;
import com.taahaagul.ifiwastemanagement.repository.UserRepository;
import com.taahaagul.ifiwastemanagement.request.UserChangePaswRequest;
import com.taahaagul.ifiwastemanagement.request.UserUpdateRequest;
import com.taahaagul.ifiwastemanagement.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserDTOMapper userDTOMapper;
    private final AuthenticationService authenticationService;
    private final PasswordEncoder passwordEncoder;
    private final CarRepository carRepository;

    public UserDTO getCurrentUser() {
        return userDTOMapper.apply(authenticationService.getCurrentUser());
    }

    @Transactional(readOnly = true)
    public List<UserDTO> getAllUsers() {
        List<User> list = userRepository.findAll();
        return list.stream()
                .map(userDTOMapper)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UserResponse getAnyUser(Long userId) {
        User foundUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId.toString()));;

        return new UserResponse(foundUser);
    }

    public void changePassword(UserChangePaswRequest request) {
        User user = authenticationService.getCurrentUser();
        if(passwordEncoder.matches(request.getOldPasw(), user.getPassword())) {
            user.setPassword(passwordEncoder.encode(request.getNewPasw()));
            userRepository.save(user);
        } else
            throw new IncorrectValueException("Old Password is incorrect!");
    }

    public UserResponse updateUser(Long userId, UserUpdateRequest request) {
        User foundUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId.toString()));

        if(foundUser.getRole().equals(Role.SUPER_ADMIN)) {
            throw new RoleUnmathcedException(foundUser.getRole(), "Untouchable!");
        }

        foundUser.setFirstName(request.getFirstName());
        foundUser.setLastName(request.getLastName());
        foundUser.setUserName(request.getUserName());
        foundUser.setEmail(request.getEmail());

        return new UserResponse(userRepository.save(foundUser));
    }

    public void deleteUser(Long userId) {
        User foundUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId.toString()));

        if(foundUser.getRole().equals(Role.SUPER_ADMIN)) {
            throw new RoleUnmathcedException(foundUser.getRole(), "Untouchable!");
        }

        userRepository.delete(foundUser);
    }

    public List<Role> getAllRole() {
        return Arrays.asList(Role.values());
    }

    public void updateUserRole(Long userId, String userRole) {
        User foundUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId.toString()));

        if(foundUser.getRole().equals(Role.SUPER_ADMIN)) {
            throw new RoleUnmathcedException(foundUser.getRole(), "Untouchable!");
        }

        if(userRole.equals("SUPER_ADMIN"))
            throw new RoleUnmathcedException(Role.SUPER_ADMIN, "Not Adjustable!");

        foundUser.setRole(Role.valueOf(userRole));
        userRepository.save(foundUser);
    }

    public void changeUserEnabled(Long userId) {
        User foundUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId.toString()));

        if(foundUser.getRole().equals(Role.SUPER_ADMIN)) {
            throw new RoleUnmathcedException(foundUser.getRole(), "Not Adjustable!");
        }

        if(foundUser.isEnabled())
            foundUser.setEnabled(false);
        else
            foundUser.setEnabled(true);

        userRepository.save(foundUser);
    }


    public void assignUserCar(Long userId, Long carId) {
        User foundUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId.toString()));

        Car foundCar = carRepository.findById(carId)
                .orElseThrow(() -> new ResourceNotFoundException("Car", "carId", carId.toString()));

        foundUser.setCar(foundCar);
        userRepository.save(foundUser);
    }
}