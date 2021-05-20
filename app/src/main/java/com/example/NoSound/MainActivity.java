package com.example.NoSound;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.NoSound.BusinessView.BusinessData;
import com.example.NoSound.OrderView.OrderViewListAdapter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements OnDataPass {

    FirstFragment firstFragment;// = (FirstFragment) getSupportFragmentManager().findFragmentById(R.id.FirstFragment);
    private BusinessData order;
    private String orderID;
    private int internalOrderID;
    private File file;
    private int latestOrderID; // disgusting way to update orderalternative fragment
    private Employee employee;
    private File filePath = null;
    private String cityCode;
    private Employee editEmployee = null;

    private static final int EXTERNAL_STORAGE_PERMISSION_CODE = 23;
    private HashMap<Integer, BusinessData> customerInfo = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        try {
            customerInfo = getOrdersFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

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
     *
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
        file = new File(folder, "Customerinfo.txt");
        writeTextData(file, internalOrderID, order);
    }

    /**
     * The method puts ID coupled with Name into a map then makes sure that it is saved on the file
     * that it takes as an argument.
     *
     * @param file    a File that the map will be stored in.
     * @param internalOrderID a String representing the ID of the order.
     * @param order   a BusinessData variable conating information about the order.
     */
    private void writeTextData(File file, int internalOrderID, BusinessData order) {
        customerInfo.put(internalOrderID, order);
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream;
        try {
            fileOutputStream = new FileOutputStream(file);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(customerInfo);
            objectOutputStream.close();
            updateOrderView(internalOrderID);
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
     *
     * @param v
     */
    public void saveEmployeePublicly(View v) {
        // Requesting Permission to access External Storage
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                EXTERNAL_STORAGE_PERMISSION_CODE);

        // Storing the data in file with name as geeksData.txt
        writeEmployeeData(file, employee);
    }

    /**
     * This method takes an employee an stores the employee in a file. Similarly to the previous method WriteTextData.
     *
     * @param file     a File that the map will be stored in.
     * @param employee a String representing the businesses name .
     */
    private void writeEmployeeData(File file, Employee employee) {
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream;
        try {
            fileOutputStream = new FileOutputStream(file, true);
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

    public void setFirstFragment(FirstFragment firstFragment) {
        this.firstFragment = firstFragment;
    }


    /**
     * adds one order to the view of the list of orders.
     *
     * @param internalOrderID the specific order by it's internal id.
     */
    public void updateOrderView(int internalOrderID) {
        firstFragment.updateOrderView(customerInfo.get(internalOrderID));
    }

    private HashMap<Integer, BusinessData> getOrdersFromFile() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + "/Customerinfo.txt"));
        HashMap<Integer, BusinessData> customerInfo = (HashMap<Integer, BusinessData>) ois.readObject();
        ois.close();
        return customerInfo;
    }

    /**
     * loads orders to the view, but first sorts the elements so that they come in reverse size order.
     */
    public void loadOrderViews() {
        List<Integer> orderByKey = new ArrayList<>(customerInfo.keySet());
        Collections.sort(orderByKey);
        Collections.reverse(orderByKey);
        for (int id : orderByKey) {
            updateOrderView(id);
        }
    }

    @Override
    public void onDataPass(BusinessData order) {
        order.setCityCode(cityCode);
        this.order = order;
        this.internalOrderID = order.getInternalOrderID();
        this.latestOrderID = internalOrderID;
    }

    @Override
    public void onEmployeePass(Employee employee) throws IOException {
        employee.setCouponNumber(order.getCityCode());
        order.addEmployee(employee);
        this.employee = employee;
    }

    @Override
    public void onPrefixPass(String city) {
        if (city.equals("Hässleholm")){
            cityCode = "HSLM";
        }
        else if (city.equals("Ludvika")){
            cityCode = "LVKA";
        }
        else if (city.equals("Jönköping")) {
            cityCode = "JKPG";
        }
        else if (city.equals("Solna")) {
            cityCode = "SLNA";
        }
    }

    Employee getEmployee() {
        return employee;
    }
    public int getLatestOrderID() {
        return latestOrderID;
    }

    public void setLatestorderID(int latestOrderID) {
        this.latestOrderID = latestOrderID;
    }

    public BusinessData getBusinessData(int internalOrderID){
        return customerInfo.get(internalOrderID);
    }
    public void generateDocx(String name){
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE},
                PackageManager.PERMISSION_GRANTED);

        filePath = new File(getExternalFilesDir(null), name + ".docx");

        try {
            if(!filePath.exists()){
                filePath.createNewFile();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10 && resultCode == RESULT_OK){
            editEmployee = (Employee) data.getSerializableExtra("Redigera");
            NavHostFragment.findNavController(firstFragment).navigate(R.id.action_personelListView_to_personelInfo);
        }
    }

    public Employee getEditEmployee(){
        return editEmployee;
    }
    public void resetEditEmployee(){
        editEmployee = null;
    }
    public File fileGetter(){
        return filePath;
    }
}