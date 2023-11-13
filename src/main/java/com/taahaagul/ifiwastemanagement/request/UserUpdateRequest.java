package com.taahaagul.ifiwastemanagement.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserUpdateRequest {

    @NotNull(message = "Id cannot be null")
    private Long id;
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
