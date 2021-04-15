package com.example.intehora;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class ProfileFirst extends Fragment {
    private TextView DagensDatumProfil;
    private TextView KundIDProfil;
    private TextView NamnProfil;

    private Button InfoKnappProfil;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.profile_first, container, false);
    }
}
