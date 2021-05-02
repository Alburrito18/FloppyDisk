package com.example.NoSound.Business;

import com.example.NoSound.Employee;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BusinessData implements Serializable {
    private String customerName;
    private String customerID;
    private String date;
    private String hearNordicNr;
    private String city;
    private List<Employee> employees = new ArrayList<>();

    public BusinessData(String customerName, String customerID, String date, String hearNordicNr, String city) {
        this.customerName = customerName;
        this.customerID = customerID;
        this.date = date;
        this.hearNordicNr = hearNordicNr;
        this.city = city;
    }
}
