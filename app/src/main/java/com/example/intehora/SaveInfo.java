package com.example.intehora;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class SaveInfo {
    HashMap customerInfo = new HashMap<String,String>();
    void setCustomerInfo(String name, String customerID){
        customerInfo.put(name,customerID);
    }
    public void save(HashMap<String,String> customerInfo, String file) throws IOException {
        try{
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(customerInfo);
            oos.flush();
            oos.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
