package com.taahaagul.ifiwastemanagement.exception;

import com.taahaagul.ifiwastemanagement.entity.Role;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class RoleUnmathcedException extends RuntimeException{

    public RoleUnmathcedException(Role role, String message) {
        super(message);
    }
}
