package com.example.NoSound.BusinessView;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.NoSound.MainActivity;
import com.example.NoSound.OnDataPass;
import com.example.NoSound.OrderAlternative;
import com.example.NoSound.OrderView.OrderViewListAdapter;
import com.example.NoSound.R;
import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

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
    private TextInputEditText orderIDtext;
    private TextInputEditText hearNordicNrText;
    private TextInputEditText cityText;

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

                BusinessData order = null;
                try {
                    order = new BusinessData(customerNameText.getText().toString(),
                            customerIDText.getText().toString(), dateEditText1.getText().toString(),
                            hearNordicNrText.getText().toString(), cityText.getText().toString(),
                            orderIDtext.getText().toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                passCustomerData(order);
                try {
                    saveInfo(view);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                NavHostFragment.findNavController(BusinessView.this)
                        .navigate(R.id.action_businessView_to_personalInfo);

            }
        });
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        customerIDText = requireView().findViewById(R.id.customerIDText);
        customerNameText = requireView().findViewById(R.id.customerNameText);
        dateEditText1 = requireView().findViewById(R.id.dateEditText1);
        orderIDtext = requireView().findViewById(R.id.orderIDtext);
        hearNordicNrText = requireView().findViewById(R.id.hearNordicNrtext);
        cityText = requireView().findViewById(R.id.cityText);
        dateEditText1.setText(getDate());

        dateEditText1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus && Objects.requireNonNull(dateEditText1.getText()).toString().equals(getDate())) {
                    String fullDate = getDate();
                    String onlyYear = fullDate.substring(0, 5);
                    dateEditText1.setText(onlyYear);
                } else if (!hasFocus) {
                    if (dateEditText1.getText().toString().length() < 6) {
                        dateEditText1.setText(getDate());
                    }
                    if (!checkDateFormat(dateEditText1.getText().toString())) {
                        dateEditText1.setBackgroundColor(0xBECC0000);
                    } else {
                        dateEditText1.setBackgroundColor(0xBECECECE);
                    }
                }
            }
        });
    }

    /**
     * The method called when information needs to be stored
     *
     * @param v The view which needs to be saved
     */
    private void saveInfo(View v) throws IOException, ClassNotFoundException {
        ((MainActivity) requireActivity()).savePublicly(v);
    }


    private void passCustomerData(BusinessData businessData) {
        dataPasser.onDataPass(businessData);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        dataPasser = (OnDataPass) context;
    }

    private String getDate() {
        Date date = Calendar.getInstance().getTime();
        String formattedDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
        //Log.d("testTag", formattedDate);
        return formattedDate;
    }

    //Checks if the date is entered in the correct format and is valid
    private boolean checkDateFormat(String date) {
        String[] dateArray = date.split("-");
        if (date.length() == 10 && dateArray.length == 3) {
            for (String sub : dateArray) {
                if (sub == null) return false;
            }
            String[] controlArray = getDate().split("-");
            boolean correctYear = Integer.parseInt(dateArray[0]) <= Integer.parseInt(controlArray[0]) && Integer.parseInt(dateArray[0]) >= 1990;
            boolean correctMonth = Integer.parseInt(dateArray[1]) <= 12 && Integer.parseInt(dateArray[1]) >= 1;
            //room for improvement here depending on month
            boolean correctDay = Integer.parseInt(dateArray[2]) <= 31 && Integer.parseInt(dateArray[2]) >= 1;

            return correctYear && correctMonth && correctDay;
        }
        return false;
    }
}