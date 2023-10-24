package com.taahaagul.ifiwastemanagement.request;

import com.taahaagul.ifiwastemanagement.entity.Role;
import lombok.Data;

@Data
public class UserUpdateRequest {

    private Long id;
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
}
