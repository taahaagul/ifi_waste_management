package com.taahaagul.ifiwastemanagement.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {

    @NotBlank(message = "Email can not be a null or empty")
    private String email;
    @NotBlank(message = "Password can not be a null or empty")
    private String password;
}
