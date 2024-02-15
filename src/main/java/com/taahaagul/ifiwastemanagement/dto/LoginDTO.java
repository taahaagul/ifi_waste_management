package com.taahaagul.ifiwastemanagement.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginDTO {

    @NotBlank(message = "Email can not be a null or empty")
    private String email;
    @NotBlank(message = "Password can not be a null or empty")
    private String password;
}
