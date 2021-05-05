package com.example.NoSound;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ThirdFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThirdFragment extends Fragment {

    private Spinner colorLeft;
    private Spinner colorRight;
    private Spinner concha;
    private Spinner filterChoice;
    private Switch detect;
    private Switch tripleset;
    private Switch stringAttachment;
    private Button saveButton;

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
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.saveButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Employee employee1 = ((MainActivity) requireActivity()).getEmployee();
                employee1.setStringAttachment(stringAttachment.isChecked());
                employee1.setRightSideColor(colorRight.getSelectedItem().toString());
                employee1.setLeftSideColor(colorLeft.getSelectedItem().toString());
                employee1.setDetect(detect.isChecked());
                employee1.setTripleset(tripleset.isChecked());
                employee1.setFilterChoice(filterChoice.getSelectedItem().toString());
                employee1.setLeftSideConcha(leftSideConchaChoice());
                employee1.setRightSideConcha(rightSideConchaChoice());
                saveInfo(view);
                NavHostFragment.findNavController(ThirdFragment.this).navigate(R.id.action_thirdfragment_to_FirstFragment);
            }
        });
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        colorLeft = requireView().findViewById(R.id.ColorLeft);
        colorRight = requireView().findViewById(R.id.ColorRight);
        concha = requireView().findViewById(R.id.Concha);
        filterChoice = requireView().findViewById(R.id.filterChoice);
        detect = requireView().findViewById(R.id.detect);
        tripleset = requireView().findViewById(R.id.tripleset);
        stringAttachment = requireView().findViewById(R.id.stringAttachment);
    }
    private boolean leftSideConchaChoice() {
        return concha.getSelectedItem().toString().equals("Vänster") || concha.getSelectedItem().toString().equals("Båda");
    }
    private boolean rightSideConchaChoice() {
        return concha.getSelectedItem().toString().equals("Höger") || concha.getSelectedItem().toString().equals("Båda");
    }
    private void saveInfo(View v){
        ((MainActivity) requireActivity()).saveEmployeePublicly(v);
    }
}