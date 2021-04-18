package com.example.nosound;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.nosound.profile.Profile;
import com.example.nosound.profile.ProfileListAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Log.d(TAG, "onCreate: Started.");
        ListView mListView = (ListView) findViewById(R.id.ListViewProfile);

        //Lite namn för test (tas bort senare)
        Profile MacDonalds = new Profile("112", "MacDonalds", "infoga dagens datum");
        Profile LegoLand = new Profile("113", "LegoLand", "infoga dagens datum");

        //En arrayList för dessa profiler, används inte efter ihopkoppling med Drill och Snows del?
        ArrayList<Profile> profileList = new ArrayList<>();
        profileList.add(MacDonalds);
        profileList.add(LegoLand);

        ProfileListAdapter adapter = new ProfileListAdapter(this, R.layout.profile_first, profileList);
        mListView.setAdapter(adapter);
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
}