package com.taahaagul.ifiwastemanagement.controller;

import com.taahaagul.ifiwastemanagement.request.RegisterRequest;
import com.taahaagul.ifiwastemanagement.request.UserChangePaswRequest;
import com.taahaagul.ifiwastemanagement.response.UserResponse;
import com.taahaagul.ifiwastemanagement.service.AuthenticationService;
import com.taahaagul.ifiwastemanagement.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/TG/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthenticationService authenticationService;

    @GetMapping
    public ResponseEntity<UserResponse> getCurrentUser() {
        return ResponseEntity.status(OK)
                .body(userService.getCurrentUser());
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.status(OK)
                .body(userService.getAllUsers());
    }

    @PostMapping("/register")
    @PreAuthorize("hasAuthority('user:create')")
    public ResponseEntity<String> register(
            @Valid @RequestBody RegisterRequest request
    ) {
        authenticationService.register(request);
        return ResponseEntity.status(OK)
                .body("User Registiration Successfully");
    }

    @PutMapping("/change-password")
    public ResponseEntity<String> changePassword(
            @Valid @RequestBody UserChangePaswRequest request) {
        userService.changePassword(request);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body("Change Password Successfully");
    }
}
