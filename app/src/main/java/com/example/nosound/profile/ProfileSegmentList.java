package com.example.nosound.profile;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.example.nosound.R;

import java.util.ArrayList;

public class ProfileSegmentList extends ListActivity {

    ArrayList<ProfileFirst> segment = new ArrayList<>();
    ArrayAdapter<ProfileFirst> segmentAdapter;
    int clickCounter = 0;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_main);
        segmentAdapter=new ArrayAdapter<String>(this, R.layout.profile_first, segment);
        setListAdapter(segmentAdapter);
    }
}
