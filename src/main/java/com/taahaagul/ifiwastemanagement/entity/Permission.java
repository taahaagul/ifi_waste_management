package com.taahaagul.ifiwastemanagement.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    USER_CREATE("user:create"),
    USER_READ("user:read"),
    USER_UPDATE("user:update"),
    USER_DELETE("user:delete"),
    ROLE_READ("role:read"),
    ROLE_CHANGE("role:change"),
    ENABLED_CHANGE("enabled:change")
    ;

    @Getter
    private final String permission;
}