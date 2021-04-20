package com.example.intehora;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

/**
 * A fragment representing the screen where the customer puts in the information about the company ordering
 */
public class SecondFragment extends Fragment {
    private OnDataPass dataPasser;
    private TextInputEditText customerIDText;
    private TextInputEditText customerNameText;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.button_second).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String customerName = customerIDText.getText().toString();
                String customerID = customerNameText.getText().toString();
                passCustomerData(customerName,customerID);
                saveInfo(view);
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_personalInfo);

            }
        });

    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        customerIDText = requireView().findViewById(R.id.customerIDText);
        customerNameText = requireView().findViewById(R.id.customerNameText);
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
}