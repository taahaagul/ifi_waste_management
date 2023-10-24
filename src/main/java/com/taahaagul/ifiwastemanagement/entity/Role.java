package com.taahaagul.ifiwastemanagement.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.taahaagul.ifiwastemanagement.entity.Permission.*;

@RequiredArgsConstructor
public enum Role {

    SUPER_ADMIN(
            Set.of(
                    USER_CREATE,
                    USER_READ,
                    USER_UPDATE,
                    USER_DELETE,
                    ROLE_READ,
                    ROLE_CHANGE,
                    ENABLED_CHANGE
            )
    ),

    ADMIN(
            Set.of(
                    USER_READ,
                    USER_UPDATE,
                    ROLE_READ
            )
    ),

    USER(Collections.emptySet())
    ;

    @Getter
    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}