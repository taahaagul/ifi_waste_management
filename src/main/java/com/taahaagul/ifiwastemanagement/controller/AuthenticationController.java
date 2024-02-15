package com.taahaagul.ifiwastemanagement.controller;

import com.taahaagul.ifiwastemanagement.dto.ForgetPasswordDTO;
import com.taahaagul.ifiwastemanagement.dto.LoginDTO;
import com.taahaagul.ifiwastemanagement.dto.AuthenticationDTO;
import com.taahaagul.ifiwastemanagement.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Validated
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationDTO> authenticate(
            @Valid @RequestBody LoginDTO request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PostMapping("/refresh/token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        service.refreshToken(request, response);
    }

    @PostMapping("/forgetMyPassword/{email}")
    public ResponseEntity<String> forgetMyPassword(@PathVariable String email) {
        service.forgetMyPaswToken(email);
        return new ResponseEntity<>("Token is delivered", OK);
    }

    @PutMapping("/forgetMyPassword/newPasw")
    public ResponseEntity<String> forgetChangePasword(
            @Valid @RequestBody ForgetPasswordDTO forgetPasswordDTO) {
        service.forgetChangePasw(forgetPasswordDTO);
        return new ResponseEntity<>("New Password is determined", OK);
    }
}