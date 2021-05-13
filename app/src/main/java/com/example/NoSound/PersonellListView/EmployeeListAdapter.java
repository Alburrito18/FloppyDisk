package com.example.NoSound.PersonellListView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.NoSound.Employee;
import com.example.NoSound.R;

import java.util.ArrayList;

public class EmployeeListAdapter extends ArrayAdapter<Employee> {

    private Context mContext;
    private int mResource;

    public EmployeeListAdapter(Context context, int resource, ArrayList<Employee> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //hämta informationen
        String PersSurName = getItem(position).getSurName();
        String PersName = getItem(position).getFirstName();
        String PersNr = getItem(position).getPersonalNumber();
        String Filterkod = getItem(position).getFilterChoice();

        //Parametrarna neden existerar inte just nu och behöver skapas innan de kan införas.
        //String HögRec = getItem(position).get...
        //String VänLink = getItem(position).get...
        //String AnmOpm = getItem(position).get...
        //String KupNr = getItem(position).get...

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvSurName = (TextView) convertView.findViewById(R.id.text1);
        TextView tvName = (TextView) convertView.findViewById(R.id.text3);
        TextView tvID = (TextView) convertView.findViewById(R.id.text4);
        TextView tvFilterChoice = (TextView) convertView.findViewById(R.id.text5);
        //TextView tvHögRec = (TextView) convertView.findViewById(R.id.NameProfile);
        //TextView tvVänLink = (TextView) convertView.findViewById(R.id.NameProfile);
        //TextView tvAnmOpm = (TextView) convertView.findViewById(R.id.NameProfile);
        //TextView tvKupNr = (TextView) convertView.findViewById(R.id.NameProfile);

        tvSurName.setText(PersSurName);
        tvName.setText(PersName);
        tvID.setText(PersNr);
        tvFilterChoice.setText(Filterkod);

        return convertView;
    }
}