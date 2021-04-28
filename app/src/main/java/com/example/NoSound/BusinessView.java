package com.example.NoSound;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BusinessView#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BusinessView extends Fragment {
    private OnDataPass dataPasser;
    private TextInputEditText customerIDText;
    private TextInputEditText customerNameText;
    private TextInputEditText dateEditText1;

    public BusinessView() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment BusinessView.
     */
    // TODO: Rename and change types and number of parameters
    public static BusinessView newInstance() {
        BusinessView fragment = new BusinessView();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_business_view, container, false);
    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.button_personel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String customerName = customerIDText.getText().toString();
                String customerID = customerNameText.getText().toString();
                passCustomerData(customerName,customerID);
                saveInfo(view);
                NavHostFragment.findNavController(BusinessView.this)
                        .navigate(R.id.action_businessView_to_personalInfo);

            }
        });
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        customerIDText = requireView().findViewById(R.id.customerIDText);
        customerNameText = requireView().findViewById(R.id.customerNameText);
        dateEditText1 = requireView().findViewById(R.id.dateEditText1);
        dateEditText1.setText(getDate());
    }

    /**
     * The method called when information needs to be stored
     * @param v The view which needs to be saved
     */
    private void saveInfo(View v){
        ((MainActivity) requireActivity()).savePublicly(v);
    }

    /**
     * The method that passes the data that needs to be stored
     * @param customerName a String representing the customers company name
     * @param customerID a String representing the customers unique ID
     */
    private void passCustomerData(String customerName,String customerID) {
        dataPasser.onDataPass(customerName,customerID);
    }
    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        dataPasser = (OnDataPass) context;
    }

    private String getDate() {
        Date date = Calendar.getInstance().getTime();
        String formattedDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
        //Log.d("testTag", formattedDate);
        return formattedDate;
    }
}