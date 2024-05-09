package com.taahaagul.ifiwastemanagement.service;

import com.taahaagul.ifiwastemanagement.dto.*;
import com.taahaagul.ifiwastemanagement.entity.Car;
import com.taahaagul.ifiwastemanagement.entity.Role;
import com.taahaagul.ifiwastemanagement.entity.User;
import com.taahaagul.ifiwastemanagement.exception.IncorrectValueException;
import com.taahaagul.ifiwastemanagement.exception.ResourceNotFoundException;
import com.taahaagul.ifiwastemanagement.exception.RoleUnmathcedException;
import com.taahaagul.ifiwastemanagement.mapper.UserMapper;
import com.taahaagul.ifiwastemanagement.repository.CarRepository;
import com.taahaagul.ifiwastemanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final FilterSpecificationService<User> userFilterSpecificationService;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AuthenticationService authenticationService;
    private final PasswordEncoder passwordEncoder;
    private final CarRepository carRepository;

    public UserDTO getCurrentUser() {
        return userMapper.mapToUserDTO(authenticationService.getCurrentUser());
    }

    public Page<UserDTO> getAllUsers(RequestDTO requestDTO) {
        Specification<User> searchSpecification =
                userFilterSpecificationService.getSearchSpecification(requestDTO.getSearchRequestDto(), requestDTO.getGlobalOperator());

        Pageable pageable = new PageRequestDTO().getPageable(requestDTO.getPageRequestDto());

        Page<UserDTO> userPage = userRepository.findAll(searchSpecification, pageable)
                .map(userMapper::mapToUserDTO);

        Long totalActiveUsers = userRepository.countByEnabled(true);
        Long totalPassiveUsers = userRepository.countByEnabled(false);

        CustomPage customPage = new CustomPage(
                userPage.getContent(), totalActiveUsers, totalPassiveUsers);

        return customPage;
    }

    public UserDTO getAnyUser(Long userId) {
        User foundUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId.toString()));;

        return userMapper.mapToUserDTO(foundUser);
    }

    public void changePassword(ChangePasswordDTO request) {
        User user = authenticationService.getCurrentUser();
        if(passwordEncoder.matches(request.getOldPasw(), user.getPassword())) {
            user.setPassword(passwordEncoder.encode(request.getNewPasw()));
            userRepository.save(user);
        } else
            throw new IncorrectValueException("Old Password is incorrect!");
    }

    public UserDTO updateUser(UserDTO userDTO) {
        User foundUser = userRepository.findById(userDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "userId", userDTO.getId().toString()));

        if(foundUser.getRole().equals(Role.SUPER_ADMIN)) {
            throw new RoleUnmathcedException(foundUser.getRole(), "Untouchable!");
        }

        userMapper.mapToUser(userDTO, foundUser);

        return userMapper.mapToUserDTO(userRepository.save(foundUser));
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

        if (foundUser.isEnabled())
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

    public void changePasswordByAdmin(Long userId, ChangePasswordDTO request) {
        User foundUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId.toString()));

        foundUser.setPassword(passwordEncoder.encode(request.getNewPasw()));
        userRepository.save(foundUser);
    }
}
