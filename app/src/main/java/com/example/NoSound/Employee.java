package com.example.NoSound;

import java.io.Serializable;

public class Employee implements Serializable {
    private String firstName;
    private String surName;
    private String department;
    private String personalNumber;
    private boolean stringAttachment;
    private String leftSideColor;
    private String rightSideColor;
    private boolean detect;
    private boolean tripleset;
    private String filterChoice;
    private boolean leftSideConcha;
    private boolean rightSideConcha;

    public Employee(String firstName, String surName, String department, String personalNumber) {
        this.firstName = firstName;
        this.surName = surName;
        this.department = department;
        this.personalNumber = personalNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurName() {
        return surName;
    }

    public String getDepartment() {
        return department;
    }

    public String getPersonalNumber() {
        return personalNumber;
    }




}
