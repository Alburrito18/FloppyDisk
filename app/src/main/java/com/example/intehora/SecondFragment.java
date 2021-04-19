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

public class SecondFragment extends Fragment {
    String customerName;
    String customerID;
    OnDataPass dataPasser;
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
                TextInputEditText customerIDText = view.findViewById(R.id.customerIDText) ;
                customerName = customerIDText.getText().toString();
                TextInputEditText customerNameText = view.findViewById(R.id.customerNameText) ;
                customerID = customerNameText.getText().toString();
                passCustomerData(customerName,customerID);
                saveInfo(view);
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_personalInfo);

            }
        });

    }
    public void saveInfo(View v){
        ((MainActivity)getActivity()).savePublicly(v);
    }
    public void passCustomerData(String customerName,String customerID) {
        dataPasser.onDataPass(customerName,customerID);
    }
    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        dataPasser = (OnDataPass) context;
    }
}