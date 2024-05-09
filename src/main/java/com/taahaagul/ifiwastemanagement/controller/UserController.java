package com.taahaagul.ifiwastemanagement.controller;

import com.taahaagul.ifiwastemanagement.dto.*;
import com.taahaagul.ifiwastemanagement.entity.Role;
import com.taahaagul.ifiwastemanagement.service.AuthenticationService;
import com.taahaagul.ifiwastemanagement.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/TG/user")
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserService userService;
    private final AuthenticationService authenticationService;

    @GetMapping("/current")
    public ResponseEntity<UserDTO> getCurrentUser() {
        return ResponseEntity.status(OK)
                .body(userService.getCurrentUser());
    }

    @PostMapping("/all")
    public ResponseEntity<Page<UserDTO>> getAllUsers(
            @RequestBody RequestDTO requestDTO) {

        return ResponseEntity.status(OK)
                .body(userService.getAllUsers(requestDTO));
    }

    @GetMapping("/byId/{userId}")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<UserDTO> getAnyUser(@PathVariable Long userId) {
        return ResponseEntity.status(OK)
                .body(userService.getAnyUser(userId));
    }


    @PostMapping("/register")
    @PreAuthorize("hasAuthority('user:create')")
    public ResponseEntity<String> register(
            @Valid @RequestBody RegisterDTO request
    ) {
        authenticationService.register(request);
        return ResponseEntity.status(OK)
                .body("User Registiration Successfully");
    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('user:update')")
    public ResponseEntity<UserDTO> updateUser(
            @Valid @RequestBody UserDTO userDTO) {
        return ResponseEntity.status(OK)
                .body(userService.updateUser(userDTO));
    }

    @DeleteMapping("/delete/{userId}")
    @PreAuthorize("hasAuthority('user:delete')")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.status(OK)
                .body("User deleted successfully");
    }

    @PutMapping("/change-password")
    public ResponseEntity<String> changePassword(
            @Valid @RequestBody ChangePasswordDTO request) {
        userService.changePassword(request);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body("Change Password Successfully");
    }

    @PutMapping("/change-password/{userId}")
    @PreAuthorize("hasAuthority('password:change')")
    public ResponseEntity<String> changePasswordByAdmin(
            @PathVariable Long userId,
            @Valid @RequestBody ChangePasswordDTO request) {
        userService.changePasswordByAdmin(userId, request);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body("Change Password Successfully");
    }

    @GetMapping("/role")
    @PreAuthorize("hasAuthority('role:read')")
    public ResponseEntity<List<Role>> getAllRole() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.getAllRole());
    }

    @PutMapping("/update-role/{userId}/{userRole}")
    @PreAuthorize("hasAuthority('role:change')")
    public ResponseEntity<String> updateUserRole(
            @PathVariable Long userId,
            @PathVariable String userRole
    ) {
        userService.updateUserRole(userId, userRole);
        return ResponseEntity.status(OK)
                .body("User role changed succsessfully");
    }

    @PutMapping("/change-enabled/{userId}")
    @PreAuthorize("hasAuthority('enabled:change')")
    public ResponseEntity<String> changeUserEnabled(@PathVariable Long userId) {
        userService.changeUserEnabled(userId);
        return ResponseEntity.status(OK)
                .body("User enabled status changed");
    }


    @PutMapping("/{userId}/car/{carId}")
    public ResponseEntity<String> assignUserCar(
            @PathVariable Long userId,
            @PathVariable Long carId) {

        userService.assignUserCar(userId, carId);
        return ResponseEntity.status(HttpStatus.OK)
                .body("User car assigned successfully");
    }
}