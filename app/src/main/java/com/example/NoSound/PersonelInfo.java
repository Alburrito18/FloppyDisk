package com.example.NoSound;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PersonelInfo#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PersonelInfo extends Fragment {
    private OnDataPass dataPasser;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "JKPG";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextInputEditText firstNameText;
    private TextInputEditText lastNameText;
    private TextInputEditText departmentText;
    private TextInputEditText birthNumberText;
    private Switch termsAgreementSwitch;

    public PersonelInfo() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PersonalInfo.
     */
    // TODO: Rename and change types and number of parameters
    public static PersonelInfo newInstance(String param1, String param2) {
        PersonelInfo fragment = new PersonelInfo();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        firstNameText= requireView().findViewById(R.id.firstName);
        lastNameText = requireView().findViewById(R.id.lastName);
        departmentText = requireView().findViewById(R.id.department);
        birthNumberText = requireView().findViewById(R.id.birthNumber);
        termsAgreementSwitch = requireView().findViewById(R.id.agreementToTerms);

        birthNumberText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (!bDayIsRightFormat(birthNumberText.getText().toString())) {
                        birthNumberText.setBackgroundColor(0xBECC0000); //Red
                    } else {
                        birthNumberText.setBackgroundColor(0xBECECECE); //Nuetral
                    }
                }
            }
        });
    }

    public boolean bDayIsRightFormat(String str) {
        if (!str.isEmpty() && str.length()== 6 && isDigit(str)) {
            return true;
        }else return false;
    }

    public boolean isDigit(String str) {
        char[] chars = str.toCharArray();
        for (char c : chars) {
            if(!Character.isDigit(c)) {
                return false;
            }
        } return true;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_personell_info, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.button_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (termsAgreementSwitch.isChecked() && bDayIsRightFormat(birthNumberText.getText().toString())) {
                    Employee employee = null;
                    try {
                        employee = new Employee(firstNameText.getText().toString(), lastNameText.getText().toString(), departmentText.getText().toString(), birthNumberText.getText().toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        passData(employee);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    NavHostFragment.findNavController(PersonelInfo.this)
                            .navigate(R.id.action_personalInfo_to_ThirdFragment);
                }
            }
        });
    }
    private void passData(Employee employee) throws IOException {
        dataPasser.onEmployeePass(employee);
    }
    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        dataPasser = (OnDataPass) context;
    }
}