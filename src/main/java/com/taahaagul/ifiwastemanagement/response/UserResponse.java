package com.taahaagul.ifiwastemanagement.response;

import com.taahaagul.ifiwastemanagement.entity.Role;
import com.taahaagul.ifiwastemanagement.entity.User;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private LocalDate memberSince;
    private boolean enabled;
    private Role role;

    public UserResponse(User entity) {
        this.id = entity.getId();
        this.firstName = entity.getFirstName();
        this.lastName = entity.getLastName();
        this.userName = entity.getUserName();
        this.email = entity.getEmail();
        this.memberSince = entity.getMemberSince();
        this.enabled = entity.isEnabled();
        this.role = entity.getRole();
    }
}
