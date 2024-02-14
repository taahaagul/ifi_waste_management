package com.taahaagul.ifiwastemanagement.dto;

import com.taahaagul.ifiwastemanagement.entity.Role;

import java.time.LocalDateTime;

public record UserDTO(
        Long id,
        String firstName,
        String lastName,
        String userName,
        String email,
        boolean enabled,
        Role role,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime updatedAt,
        String updatedBy
) {
}
