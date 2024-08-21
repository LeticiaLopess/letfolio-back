package com.synchronia.letfolio.domain.enumeration;

public enum Role {

    ADMIN("Administrator", 1),
    USER("User", 2),
    GUEST("Guest", 3);

    private final String description;
    private final int code;

    Role(String description, int code) {
        this.description = description;
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public int getCode() {
        return code;
    }

    public static Role fromCode(int code) {
        for (Role role : Role.values()) {
            if (role.getCode() == code) {
                return role;
            }
        }
        throw new IllegalArgumentException("Invalid code: " + code);
    }
}
