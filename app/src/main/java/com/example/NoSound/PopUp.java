package com.example.NoSound;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.SurfaceControl;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.navigation.fragment.NavHostFragment;

import com.example.NoSound.PersonelListView.PersonelListView;

public class PopUp extends Activity {

    TextView firstNameTextView;
    TextView surNameTextView;
    Button editPerson;
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
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;
        getWindow().setAttributes(params);
        firstNameTextView = findViewById(R.id.firstNameTextView);
        surNameTextView = findViewById(R.id.surNameTextView);
        editPerson = findViewById(R.id.editPerson);
        findViewById(R.id.editPerson).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),MainActivity.class);
                intent.putExtra("Redigera",employee);
                setResult(Activity.RESULT_OK,intent);
                finish();
            }
        });
        findViewById(R.id.deletePerson).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),MainActivity.class);
                intent.putExtra("Radera",employee);
                setResult(Activity.RESULT_OK,intent);
                finish();
            }
        });
    }

    protected void onStart() {
        super.onStart();
        employee = (Employee) getIntent().getSerializableExtra("EXTRA_SESSION_ID");
        firstNameTextView.setText(employee.getFirstName());
        surNameTextView.setText(employee.getSurName());
    }

}
