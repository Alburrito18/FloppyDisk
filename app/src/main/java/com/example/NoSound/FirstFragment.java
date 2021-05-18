package com.example.NoSound;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.NoSound.OrderView.OrderView;
import com.example.NoSound.OrderView.OrderViewListAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class FirstFragment extends Fragment {

    private ArrayList<OrderView> orderViewList = new ArrayList<>();
    private ListView mListView;
    private static final String TAG = "FirstFragment";
    private OnDataPass dataPasser;
    private RadioGroup officeCities;
    private RadioButton hslmButton;
    private RadioButton ldvkButton;
    private RadioButton jkpgButton;
    private RadioButton slnaButton;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState

    ) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onCreate: Started.");

        mListView = view.findViewById(R.id.ListViewProfile);


        OrderViewListAdapter adapter = new OrderViewListAdapter(getContext(), R.layout.profile_first, orderViewList, this);
        mListView.setAdapter(adapter);

        ((MainActivity) requireActivity()).setFirstFragment(this);
        orderViewList.clear();
        ((MainActivity) requireActivity()).loadOrderViews();
        view.findViewById(R.id.button_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (officeCities.getCheckedRadioButtonId() != -1) {
                    String string = "hej";
                    if (slnaButton.isChecked()){
                        string = slnaButton.getText().toString();
                    }
                    else if (hslmButton.isChecked()){
                        string = hslmButton.getText().toString();
                    }
                    else if (jkpgButton.isChecked()){
                        string = jkpgButton.getText().toString();
                    }
                    else if (ldvkButton.isChecked()){
                        string = ldvkButton.getText().toString();
                    }
                    dataPasser.onPrefixPass(string);
                    NavHostFragment.findNavController(FirstFragment.this)
                            .navigate(R.id.action_FirstFragment_to_businessView);
                }
                else {
                    Toast.makeText(requireActivity(),"Kontor måste väljas",Toast.LENGTH_SHORT).show();
                }
             //   startActivity(new Intent(Intent.EXTRA_PROCESS_TEXT));
            }

        });
    }
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        officeCities = requireView().findViewById(R.id.officeCities);
        slnaButton = requireView().findViewById(R.id.slnaButton);
        hslmButton = requireView().findViewById(R.id.hslmButton);
        jkpgButton = requireView().findViewById(R.id.jkpgButton);
        ldvkButton = requireView().findViewById(R.id.ldvkButton);
    }

    /**
     * This method updates the ListView with the new Order given.
     * @param orderView the order that needs to added to the listview.
     */
    public void updateOrderView(OrderView orderView) {
        orderViewList.add(orderView);
        OrderViewListAdapter adapter = new OrderViewListAdapter(getContext(), R.layout.profile_first, orderViewList, this);
        mListView.setAdapter(adapter);
    }
    @Override
    public void onAttach(@NotNull Context context){
        super.onAttach(context);
        dataPasser = (OnDataPass) context;
    }
}