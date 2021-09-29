package ru.simbial.shoppingapp.entity;

import org.springframework.security.core.GrantedAuthority;

import java.util.Arrays;
import java.util.List;

public enum Role implements GrantedAuthority {
    USER,
    ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }

    public static List<Role> getRoles() {
        return Arrays.asList(Role.values());
    }
}