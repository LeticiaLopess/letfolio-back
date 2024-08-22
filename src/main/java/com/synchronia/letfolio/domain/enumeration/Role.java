package com.synchronia.letfolio.domain.enumeration;

import java.util.HashMap;
import java.util.Map;

public enum Role {

    ADMIN(1),
    USER( 2),
    GUEST(3);

    private final String name;
    private final String description;
    private final int code;

    private static final Map<Integer, String> codeToName = new HashMap<>();
    private static final Map<Integer, String> codeToDescription = new HashMap<>();

    static {
        codeToName.put(1, "ADMIN");
        codeToName.put(2, "USER");
        codeToName.put(3, "GUEST");

        codeToDescription.put(1, "Administrator");
        codeToDescription.put(2, "User");
        codeToDescription.put(3, "Guest");
    }

    Role(int code) {
        this.code = code;
        this.name = initializeName(code);
        this.description = initializeDescription(code);
    }

    private static String initializeName(int code) {
        return codeToName.get(code);
    }

    private static String initializeDescription(int code) {
        return codeToDescription.get(code);
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
