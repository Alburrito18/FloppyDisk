package com.example.NoSound.PersonelListView;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.example.NoSound.Employee;
import com.example.NoSound.MainActivity;
import com.example.NoSound.R;

import java.util.ArrayList;

public class PersonelListView extends Fragment {

    private ArrayList<Employee> personellList = new ArrayList<>();
    private ListView mListView;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((MainActivity) requireActivity()).resetEditEmployee();
        mListView = view.findViewById(R.id.personellList);
        int latestOrderID = ((MainActivity) requireActivity()).getLatestOrderID();

        personellList.clear();
        personellList = (ArrayList<Employee>) ((MainActivity) requireActivity()).getBusinessData(latestOrderID).getEmployees();
        updateOrderView();
        view.findViewById(R.id.addButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(PersonelListView.this).navigate(R.id.action_personelListView_to_personelInfo);
            }
        });
        view.findViewById(R.id.savePersonellButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(PersonelListView.this).navigate(R.id.action_personelListView_to_FirstFragment);
            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_personell_list, container, false);
    }

    public void updateOrderView() {
        EmployeeListAdapter adapter = new EmployeeListAdapter(getContext(), R.layout.personell_segment, personellList,this);
        mListView.setAdapter(adapter);
    }
    public Employee getEmployee(String firstname){
        for (int i = 0; i<personellList.size(); i++){
            if (personellList.get(i).getFirstName().equals(firstname)){
                return personellList.get(i);
            }
        }
        return null;
    }
    public void onResume() {
        super.onResume();
        if (getArguments()!=null) {
            Employee employee = (Employee) getArguments().getSerializable("Redigera");
            if (employee != null) {
                NavHostFragment.findNavController(PersonelListView.this).navigate(R.id.action_personelListView_to_personelInfo);
            }
        }
    }
}

