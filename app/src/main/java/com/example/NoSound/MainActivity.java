package com.example.NoSound;

import android.Manifest;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.NoSound.BusinessView.BusinessData;
import com.example.NoSound.OrderView.OrderView;
import com.example.NoSound.OrderView.OrderViewListAdapter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements OnDataPass {

    FirstFragment firstFragment;// = (FirstFragment) getSupportFragmentManager().findFragmentById(R.id.FirstFragment);
    private BusinessData order;
    private String orderID;
    private File file;

    private static final int EXTERNAL_STORAGE_PERMISSION_CODE = 23;
    private HashMap<String,BusinessData> customerInfo = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * This method creates a file and requests permission to store files in the external storage, which in this case is Documents.
     * It then calls upon the method that will store the file in the storage.
     * @param v
     */
    public void savePublicly(View v) {
        // Requesting Permission to access External Storage
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                EXTERNAL_STORAGE_PERMISSION_CODE);


        // getExternalStoragePublicDirectory() represents root of external storage, we are using DOWNLOADS
        // We can use following directories: MUSIC, PODCASTS, ALARMS, RINGTONES, NOTIFICATIONS, PICTURES, MOVIES
        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);

        // Storing the data in file with name as geeksData.txt
        file = new File(folder, orderID + "Customerinfo.txt");
        writeTextData(file,orderID, order);
    }

    /**
     * The method puts ID coupled with Name into a map then makes sure that it is saved on the file
     * that it takes as an argument.
     * @param file  a File that the map will be stored in.
     * @param orderID a String representing the ID of the order.
     * @param order a BusinessData variable conating information about the order.
     */
    private void writeTextData(File file, String orderID ,BusinessData order) {
        if (!(orderID == null || order == null)){
            customerInfo.put(orderID,order);
        }
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream;
        try {
            fileOutputStream = new FileOutputStream(file);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(customerInfo);
            objectOutputStream.close();
            Toast.makeText(this, "Done" + file.getAbsolutePath(), Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    /**
     * This method creates a file and requests permission to store files in the external storage, which in this case is Documents.
     * It then calls upon the method that will store the file in the storage. This method stores Employee info however.
     * @param v
     */
    public void saveEmployeePublicly(View v) {
        // Requesting Permission to access External Storage
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                EXTERNAL_STORAGE_PERMISSION_CODE);

        // Storing the data in file with name as geeksData.txt
        writeEmployeeData(file, order.getEmployee(0));
    } /**
     * This method takes an employee an stores the employee in a file. Similarly to the previous method WriteTextData.
     * @param file  a File that the map will be stored in.
     * @param employee a String representing the businesses name .
     */
    private void writeEmployeeData(File file, Employee employee){
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream;
        try {
            fileOutputStream = new FileOutputStream(file,true);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(employee);
            objectOutputStream.close();
            Toast.makeText(this, "Done" + file.getAbsolutePath(), Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    public void setFirstFragment(FirstFragment firstFragment){
        this.firstFragment = firstFragment;
    }
    public void printBusinessData(String orderID) throws IOException, ClassNotFoundException {
        System.out.println(Environment.getExternalStorageDirectory().getAbsolutePath());
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + "/" + orderID + "Customerinfo.txt"));
        HashMap<String,BusinessData> customerInfo = (HashMap<String, BusinessData>) ois.readObject();
        ois.close();
        OrderView newOrder = new OrderView(orderID,customerInfo.get(orderID).getCustomerName(),customerInfo.get(orderID).getDate());
        firstFragment.updateOrderView(newOrder);
        System.out.println(customerInfo.get(orderID).getCustomerName());
        System.out.println(customerInfo.get(orderID).getDate());
    }
    @Override
    public void onDataPass(BusinessData order,String orderID) {
        this.order = order;
        this.orderID = orderID;
    }

    @Override
    public void onEmployeePass(Employee employee) {
        order.addEmployee(employee);
    }

}