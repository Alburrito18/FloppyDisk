package com.example.NoSound;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.Switch;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ThirdFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThirdFragment extends Fragment {

    private Employee employee;
    private Spinner colorLeft;
    private Spinner colorRight;
    private Spinner concha;
    private Spinner filterChoice;
    private Switch detect;
    private Switch tripleset;
    private Switch stringAttachment;

    public ThirdFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ThirdFragment newInstance() {
        ThirdFragment fragment = new ThirdFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_third, container, false);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        colorLeft = requireView().findViewById(R.id.ColorLeft);
        colorRight = requireView().findViewById(R.id.ColorRight);
        concha = requireView().findViewById(R.id.Concha);
        filterChoice = requireView().findViewById(R.id.filterChoice);
    }
}