package com.example.eloemocional.models.enums;


public enum UserProfile {
    ADMIN(0, "Admin"),
    PATIENT(1, "Patient"),
    PSYCHOLOGIST(2, "Psychologist");

    private Integer code;
    private String description;

    UserProfile(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }


    public static UserProfile toEnumProfile(Integer code) {
        if (code == null) {
            return null;
        }
        for (UserProfile profile : UserProfile.values()) {
            if (code.equals(profile.getCode())) {
                return profile;
            }
        }
        throw new IllegalArgumentException("Profile Invalid");
    }

}
