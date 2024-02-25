package com.login.login.with.jwt.domain.enums;

public enum Role {
    ROLE_USER("USER"),
    ROLE_ADMIN("ADMIN");

    private String value;

    Role(String value) {
        this.value = value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
