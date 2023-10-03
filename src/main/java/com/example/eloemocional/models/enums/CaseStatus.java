package com.example.eloemocional.models.enums;

public enum CaseStatus {
    OPEN(0, "Open"),
    IN_PROGRESS(1, "In Progress"),
    CLOSED(2, "Closed");

    private Integer code;
    private String description;

    CaseStatus(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }


    public static CaseStatus toEnumStatus(Integer code) {
        if (code == null) {
            return null;
        }
        for (CaseStatus caseStatus : CaseStatus.values()) {
            if (code.equals(caseStatus.getCode())) {
                return caseStatus;
            }
        }
        throw new IllegalArgumentException("caseStatus Invalid");
    }
}

