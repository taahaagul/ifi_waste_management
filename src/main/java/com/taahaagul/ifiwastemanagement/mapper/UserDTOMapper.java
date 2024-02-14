package com.taahaagul.ifiwastemanagement.mapper;

import com.taahaagul.ifiwastemanagement.dto.UserDTO;
import com.taahaagul.ifiwastemanagement.entity.User;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class UserDTOMapper implements Function<User, UserDTO> {
    @Override
    public UserDTO apply(User user) {
        return new UserDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getUserName(),
                user.getEmail(),
                user.isEnabled(),
                user.getRole(),
                user.getCreatedAt(),
                user.getCreatedBy(),
                user.getUpdatedAt(),
                user.getUpdatedBy()
        );
    }
}
