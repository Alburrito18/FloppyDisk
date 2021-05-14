package com.example.NoSound.BusinessView;

import android.Manifest;
import android.os.Environment;

import androidx.core.app.ActivityCompat;

import com.example.NoSound.Employee;
import com.example.NoSound.FirstFragment;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.io.Writer;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.List;

public class BusinessData implements Serializable {
    private String customerName;
    private String customerID;
    private String date;
    private String hearNordicNr;
    private String city;
    private String orderID;

    public int getInternalOrderID() {
        return internalOrderID;
    }

    private int internalOrderID;
    private List<Employee> employees = new ArrayList<>();
    File file;

    public BusinessData(String customerName, String customerID, String date, String hearNordicNr, String city, String orderID) throws IOException {
        this.customerName = customerName;
        this.customerID = customerID;
        this.date = date;
        this.hearNordicNr = hearNordicNr;
        this.city = city;
        this.internalOrderID = generateOrderID();
        this.orderID = orderID;
    }

    private int generateOrderID() throws IOException {
        int orderID;
        orderID = retreiveOrderID() + 1;
        saveID(orderID);
        return orderID;
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
        file = new File(folder, "internalOrderID.txt");
        return file;
    }
    /**
     * Adds an employee to the list of employees
     * @param employee the employee to be added to the list
     */
    public void addEmployee(Employee employee){
        employees.add(employee);
    }

    /**
     * Method to get an employee from the list of employees in the order
     * @param index the index of the employee in the list
     * @return an employee
     */
    public Employee getEmployee(int index){
        return employees.get(index);
    }

    public String getOrderID() {
        return orderID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getDate() {
        return date;
    }
    private int retreiveOrderID() throws IOException {
        try {
            DataInputStream dis = new DataInputStream(new FileInputStream(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + "/internalOrderID.txt"));
            return dis.readInt();
        }
        catch (FileNotFoundException e){
            return 0;
        }

    }


    @Override
    public String toString() {
        return "Orderinformation:" + "\n" +
                "Företag: " + customerName +"," + "\n" +
                "Kundnummer: " + customerID +"," +"\n" +
                "Datum: " + date +","  +"\n" +
                "hearNordicNr: " + hearNordicNr +","  +"\n" +
                "Ort: " + city + "\n" +
                employeesToString()+ "\n";
    }
    private String employeesToString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Anställda enligt: Förnamn, Efternamn, Avdelning, Personummer, Anmärkning, Filterkod").append("\n");
        for (int i = 0; i<employees.size(); i++ ){
            sb.append(employees.get(i).toString()).append("\n");
        }
        return sb.toString();
    }
}
