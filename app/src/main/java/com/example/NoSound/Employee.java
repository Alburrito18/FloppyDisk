package com.example.NoSound;

import android.os.Environment;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
    private File file;
    private String couponNumber = null;

    public Employee(String firstName, String surName, String department, String personalNumber) throws IOException {
        this.firstName = firstName;
        this.surName = surName;
        this.department = department;
        this.personalNumber = personalNumber;
    }

    public void setCouponNumber(String prefix) throws IOException {
        couponNumber = generateCouponID(prefix);
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setPersonalNumber(String personalNumber) {
        this.personalNumber = personalNumber;
    }

    private String generateCouponID(String prefix) throws IOException {
        int orderID;
        orderID = retrieveOrderID() + 1;
        saveID(orderID);
        return prefix + orderID;
    }

    private void saveID(int orderID) throws IOException {
        DataOutputStream dos = new DataOutputStream(new FileOutputStream(internalOrderFile()));
        dos.writeInt(orderID);
        dos.close();
    }
    private File internalOrderFile(){
        // getExternalStoragePublicDirectory() represents root of external storage, we are using DOWNLOADS
        // We can use following directories: MUSIC, PODCASTS, ALARMS, RINGTONES, NOTIFICATIONS, PICTURES, MOVIES
        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);

        // Storing the data in file with name as geeksData.txt
        file = new File(folder, "couponID.txt");
        return file;
    }
    private int retrieveOrderID() throws IOException {
        try {
            DataInputStream dis = new DataInputStream(new FileInputStream(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + "/couponID.txt"));
            return dis.readInt();
        }
        catch (FileNotFoundException e){
            return 0;
        }
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
        return "        "+firstName + "," +
                surName + "," +
                department  +"," +
                personalNumber +"," +
                comment +"," +
                filterCode+".";
    }

    public String toCouponString(String date, String customerID, String customerName,String city){
        return "PH_LOGGA" + '\n' +
                "                                                 " + "Datum:  " + date + '\n'+
                "                                                 " + "Kupong: " + couponNumber + '\n' +
                "Med snöre:     " + stringAttachment + '\n' +
                "Färg vänster:  " + leftSideColor + '\n' +
                "Färg höger:    " + rightSideColor + '\n' +
                "Telefonsnäcka vänster: " + leftSideConcha + '\n' +
                "Telefonsnäcka höger:   " + rightSideConcha + '\n' +
                "Filter: " + filterChoice + '\n' +
                "KundNr: " + customerID + '\n' + '\n' +
                "Företag: " + customerName + '\n' +
                "Ort: " + city + '\n' +
                "Namn: " + firstName + " " + surName + '\n' +
                "FödelseNr: " + personalNumber +'\n' + '\n' +
                "Härmed godkänner jag att mina ögonavtryck och personuppgifter spara för ny och efterbeställning av formgjutna produkter från HEAR Nordic: " + '\n' +
                "Godkännande: " + true;
    }

    public boolean isStringAttachment() {
        return stringAttachment;
    }

    public String getFilterCode() {
        return filterCode;
    }

    public String getLeftSideColor() {
        return leftSideColor;
    }

    public String getRightSideColor() {
        return rightSideColor;
    }

    public String getComment() {
        return comment;
    }

    public String getCouponNumber() {
        return couponNumber;
    }

    public boolean isLeftSideConcha() {
        return leftSideConcha;
    }

    public boolean isRightSideConcha() {
        return rightSideConcha;
    }

    public boolean isDetect() {
        return detect;
    }

    public boolean isTripleset() {
        return tripleset;
    }
}
