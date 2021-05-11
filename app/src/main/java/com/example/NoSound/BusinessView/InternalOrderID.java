package com.example.NoSound.BusinessView;

import android.os.Environment;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class InternalOrderID {
    private int internalOrderID;
    File file;

    public InternalOrderID(int internalOrderID) throws IOException {
        this.internalOrderID = generateOrderID();
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
    private int retreiveOrderID() throws IOException {
        try {
            DataInputStream dis = new DataInputStream(new FileInputStream(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + "/internalOrderID.txt"));
            return dis.readInt();
        }
        catch (FileNotFoundException e){
            return 0;
        }

    }
}
