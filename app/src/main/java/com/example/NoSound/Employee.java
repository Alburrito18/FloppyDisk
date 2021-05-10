package com.example.NoSound;

import java.io.Serializable;

/**
 * In this class we set the parameters for the employee, including different choices for the earpieces.
 */
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
    private String comment;
    private String filterCode;

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

    /**
     * Looks if the user want a string fo their earpiece
     * @param stringAttachment a parameter that can be true if they want a an attachment or false if they don't.
     */
    public void setStringAttachment(boolean stringAttachment) {
        this.stringAttachment = stringAttachment;
    }

    /**
     * A setter for what color the employee wants it's left earpiece to be
     * @param leftSideColor A string telling what colour they want
     */
    public void setLeftSideColor(String leftSideColor) {
        this.leftSideColor = leftSideColor;
    }

    /**
     * A setter for what color the employee wants it's right earpiece to be
     * @param rightSideColor a string telling what color they want
     */
    public void setRightSideColor(String rightSideColor) {
        this.rightSideColor = rightSideColor;
    }

    /**
     * A setter that checks if the employee wants detect
     * @param detect boolean that tells if the employee needs detect
     */
    public void setDetect(boolean detect) {
        this.detect = detect;
    }

    /**
     * A setter that checks if the employee wants a triple set
     * @param tripleset boolean tells if the employee wants a triple set
     */
    public void setTripleset(boolean tripleset) {
        this.tripleset = tripleset;
    }

    /**
     * A setter for the employees choice of filter
     * @param filterChoice A string representing the choice of filter
     */
    public void setFilterChoice(String filterChoice) {
        this.filterChoice = filterChoice;
    }

    /**
     * A setter for the employees choice of a concha in the left ear
     * @param leftSideConcha a boolean, true if the employee wants concha, otherwise false
     */
    public void setLeftSideConcha(boolean leftSideConcha) {
        this.leftSideConcha = leftSideConcha;
    }

    /**
     *   * A setter for the employees choice of a concha in the right ear
     * @param rightSideConcha a boolean, true if the employee wants concha in the right ear, otherwise false
     */
    public void setRightSideConcha(boolean rightSideConcha) {
        this.rightSideConcha = rightSideConcha;
    }

    /**
     * A setter for the employees comment
     * @param comment the string that is the comment
     */
    public void setComment(String comment){
        this.comment = comment;
    }

    /**
     * A setter for the employees filtercode
     * @param filterCode the String representing the users filtercode, which is dependent on its choices
     */
    public void setFilterCode(String filterCode){
        this.filterCode = filterCode;
    }

    @Override
    public String toString() {
        return "Förnamn: " + firstName +
                ", Efternamn: " + surName +
                ", Avdelning: " + department  +
                ", Personnummer: " + personalNumber +
                ", Anmärkning: " + comment +
                ", Filterkod: " + filterCode;
    }
}
