package com.taahaagul.ifiwastemanagement.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LoginRequest {

    @NotEmpty(message = "Email can not be a null or empty")
    private String email;
    @NotEmpty(message = "Password can not be a null or empty")
    private String password;
}
