package com.example.NoSound.PersonellListView;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.NoSound.EarPieceForm;
import com.example.NoSound.Employee;
import com.example.NoSound.MainActivity;
import com.example.NoSound.OrderView.OrderView;
import com.example.NoSound.OrderView.OrderViewListAdapter;
import com.example.NoSound.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class PersonellListView extends Fragment {

    private ArrayList<Employee> personellList = new ArrayList<>();
    private ListView mListView;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainActivity) requireActivity()).setPersonellListView(this);
        }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mListView = view.findViewById(R.id.personellList);


        EmployeeListAdapter adapter = new EmployeeListAdapter(getContext(), R.layout.personell_segment, personellList,this);
        mListView.setAdapter(adapter);
        ((MainActivity) requireActivity()).setPersonellListView(this);
        personellList.clear();
        ((MainActivity) requireActivity()).loadEmployeeList();
        view.findViewById(R.id.addButton).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(PersonellListView.this).navigate(R.id.action_personellList_to_personelInfo);
            }
        });
        view.findViewById(R.id.savePersonellButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(PersonellListView.this).navigate(R.id.action_personellList_to_FirstFragment);
            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_personell_list, container, false);
    }

    public void updateOrderView(ArrayList<Employee> employeeList) {
        personellList = employeeList;
        EmployeeListAdapter adapter = new EmployeeListAdapter(getContext(), R.layout.personell_segment, personellList,this);
        mListView.setAdapter(adapter);
    }
}