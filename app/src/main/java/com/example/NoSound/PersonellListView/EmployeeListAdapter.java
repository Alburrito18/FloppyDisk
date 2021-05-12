package com.example.NoSound.PersonellListView;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import com.example.NoSound.Employee;
import com.example.NoSound.OrderView.OrderView;

import java.util.List;

public class EmployeeListAdapter extends ArrayAdapter<Employee> {

    public EmployeeListAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    public EmployeeListAdapter(@NonNull Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    public EmployeeListAdapter(@NonNull Context context, int resource, @NonNull Employee[] objects) {
        super(context, resource, objects);
    }

    public EmployeeListAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull Employee[] objects) {
        super(context, resource, textViewResourceId, objects);
    }

    public EmployeeListAdapter(@NonNull Context context, int resource, @NonNull List<Employee> objects) {
        super(context, resource, objects);
    }

    public EmployeeListAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull List<Employee> objects) {
        super(context, resource, textViewResourceId, objects);
    }
}
