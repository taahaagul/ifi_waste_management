package com.taahaagul.ifiwastemanagement.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UserUpdateRequest {

    @NotEmpty(message = "First name cannot be empty")
    private String firstName;
    @NotEmpty(message = "Last name cannot be empty")
    private String lastName;
    @NotEmpty(message = "Username cannot be empty")
    private String userName;
    @Email
    @NotEmpty(message = "email cannot be empty")
    private String email;
}