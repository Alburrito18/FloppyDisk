package com.example.NoSound.OrderView;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.NoSound.BusinessView.BusinessData;
import com.example.NoSound.MainActivity;
import com.example.NoSound.R;
import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link edit_order_page#newInstance} factory method to
 * create an instance of this fragment.
 */
public class edit_order_page extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    BusinessData businessData;
    private TextInputEditText dateEditText;
    private TextInputEditText customerEditNameText;
    private TextInputEditText customerEditIDText;
    private TextInputEditText editHearNordicNRText;
    private TextInputEditText editCityText;
    private TextInputEditText editOrderIdText;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public edit_order_page() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment edit_order_page.
     */
    // TODO: Rename and change types and number of parameters
    public static edit_order_page newInstance(String param1, String param2) {
        edit_order_page fragment = new edit_order_page();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int latestOrderID = ((MainActivity) requireActivity()).getLatestOrderID();
        businessData = ((MainActivity) requireActivity()).getBusinessData(latestOrderID);
        ((MainActivity) requireActivity()).setOrder(businessData);
        ((TextView)view.findViewById(R.id.dateEditText)).setText(businessData.getDate());
        ((TextView)view.findViewById(R.id.customerEditNameText)).setText(businessData.getCustomerName());
        ((TextView)view.findViewById(R.id.customerEditIDText)).setText(businessData.getCustomerID());
        ((TextView)view.findViewById(R.id.editHearNordicNRtext)).setText(businessData.getHearNordicNr());
        ((TextView)view.findViewById(R.id.editCityText)).setText(businessData.getCity());
        ((TextView)view.findViewById(R.id.editOrderIdText)).setText(businessData.getOrderID());

        view.findViewById(R.id.button_edit_personel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                businessData.setDate(dateEditText.getText().toString());
                businessData.setCustomerID(customerEditIDText.getText().toString());
                businessData.setCity(editCityText.getText().toString());
                businessData.setCustomerName(customerEditNameText.getText().toString());
                businessData.setOrderID(editOrderIdText.getText().toString());
                businessData.setHearNordicNr(editHearNordicNRText.getText().toString());
                try {
                    saveMap();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                NavHostFragment.findNavController(edit_order_page.this).navigate(R.id.action_edit_order_page_to_personelListView);
            }
        });
        view.findViewById(R.id.button_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(edit_order_page.this)
                        .navigate(R.id.action_edit_order_page_to_orderAlternative);
            }
        });
    }
    private void saveMap() throws IOException, ClassNotFoundException {
        ((MainActivity) requireActivity()).saveMap();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        dateEditText = requireView().findViewById(R.id.dateEditText);
        customerEditNameText = requireView().findViewById(R.id.customerEditNameText);
        customerEditIDText = requireView().findViewById(R.id.customerEditIDText);
        editHearNordicNRText = requireView().findViewById(R.id.editHearNordicNRtext);
        editCityText = requireView().findViewById(R.id.editCityText);
        editOrderIdText = requireView().findViewById(R.id.editOrderIdText);
        dateEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                 if (!hasFocus) {
                    if (!checkDateFormat(dateEditText.getText().toString())) {
                        dateEditText.setBackgroundColor(0xBECC0000);
                    } else {
                        dateEditText.setBackgroundColor(0xBECECECE);
                    }
                }
            }
        });
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
        return inflater.inflate(R.layout.fragment_edit_order_page, container, false);
    }
    private boolean checkDateFormat(String date) {
        String[] dateArray = date.split("-");
        if (date.length() == 10 && dateArray.length == 3) {
            for (String sub : dateArray) {
                if (sub == null) return false;
            }
            boolean correctYear =  Integer.parseInt(dateArray[0]) >= 1990;
            boolean correctMonth = Integer.parseInt(dateArray[1]) <= 12 && Integer.parseInt(dateArray[1]) >= 1;
            //room for improvement here depending on month
            boolean correctDay = Integer.parseInt(dateArray[2]) <= 31 && Integer.parseInt(dateArray[2]) >= 1;

            return correctYear && correctMonth && correctDay;
        }
        return false;
    }
}