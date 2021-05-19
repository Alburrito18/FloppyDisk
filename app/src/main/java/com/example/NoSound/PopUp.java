package com.example.NoSound;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

public class PopUp extends Activity {

    TextView firstNameTextView;
    TextView surNameTextView;
    Employee employee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popwindow);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width*0.8),(int) (height*0.8));
        firstNameTextView = findViewById(R.id.firstNameTextView);
        surNameTextView = findViewById(R.id.surNameTextView);
    }

    protected void onStart() {
        super.onStart();
        employee = (Employee) getIntent().getSerializableExtra("EXTRA_SESSION_ID");
        firstNameTextView.setText(employee.getFirstName());
        surNameTextView.setText(employee.getSurName());
    }

}
