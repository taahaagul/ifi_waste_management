package com.taahaagul.ifiwastemanagement.dto;

import com.taahaagul.ifiwastemanagement.entity.Role;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter @Setter @ToString
@Builder
public class UserDTO {

    private Long id;

    @NotEmpty(message = "firstName cannot be null or empty")
    private String firstName;
    @NotEmpty(message = "lastName cannot be null or empty")
    private String lastName;
    @NotEmpty(message = "userName cannot be null or empty")
    private String userName;
    @NotEmpty(message = "email cannot be null or empty")
    private String email;

    private boolean enabled;
    private Role role;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
}