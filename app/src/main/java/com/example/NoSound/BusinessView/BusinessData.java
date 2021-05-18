package com.example.NoSound.BusinessView;

import com.example.NoSound.Employee;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BusinessData implements Serializable {
    private String customerName;
    private String customerID;
    private String date;
    private String hearNordicNr;
    private String city;
    private List<Employee> employees = new ArrayList<>();
    private String cityCode;

    public BusinessData(String customerName, String customerID, String date, String hearNordicNr, String city) {
        this.customerName = customerName;
        this.customerID = customerID;
        this.date = date;
        this.hearNordicNr = hearNordicNr;
        this.city = city;
    }
    public List<Employee> getEmployees(){
        return new ArrayList<>(employees);
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

    public String getCustomerName() {
        return customerName;
    }

    public String getDate() {
        return date;
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
        sb.append("Anställda: Förnamn, Efternamn, Avdelning, Personummer, Anmärkning, Filterkod").append("\n");
        for (int i = 0; i<employees.size(); i++ ){
            sb.append(employees.get(i).toString()).append("\n");
        }
        return sb.toString();
    }
    public void setCityCode(String city){
        cityCode = city;
    }
    public String getCityCode(){
        return cityCode;
    }
}
