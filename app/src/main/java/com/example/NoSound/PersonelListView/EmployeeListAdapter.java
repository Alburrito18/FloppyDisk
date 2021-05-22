package com.example.NoSound.PersonelListView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.NoSound.Employee;
import com.example.NoSound.MainActivity;
import com.example.NoSound.PopUp;
import com.example.NoSound.R;

import java.util.ArrayList;

public class EmployeeListAdapter extends ArrayAdapter<Employee> {

    private Context mContext;
    private int mResource;
    private PersonelListView personelListView;

    public EmployeeListAdapter(Context context, int resource, ArrayList<Employee> objects,PersonelListView personelListView) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
        this.personelListView = personelListView;
    }

    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //hämta informationen
        String PersSurName = getItem(position).getSurName();
        String PersName = getItem(position).getFirstName();
        String PersNr = getItem(position).getPersonalNumber();
        String Filterkod = getItem(position).getFilterCode();
        String HögRec = getItem(position).getRightSideColor();
        String VänLink = getItem(position).getLeftSideColor();
        String AnmOpm = getItem(position).getComment();
        String KupNr = getItem(position).getCouponNumber();

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvSurName = (TextView) convertView.findViewById(R.id.text1);
        TextView tvName = (TextView) convertView.findViewById(R.id.text3);
        TextView tvID = (TextView) convertView.findViewById(R.id.text4);
        TextView tvFilterCode = (TextView) convertView.findViewById(R.id.text5);
        TextView tvHögRec = (TextView) convertView.findViewById(R.id.text6);
        TextView tvVänLink = (TextView) convertView.findViewById(R.id.text7);
        TextView tvAnmOpm = (TextView) convertView.findViewById(R.id.text8);
        TextView tvKupNr = (TextView) convertView.findViewById(R.id.Text9);

        tvSurName.setText(PersSurName);
        tvName.setText(PersName);
        tvID.setText(PersNr);
        tvFilterCode.setText(Filterkod);
        tvHögRec.setText(HögRec);
        tvVänLink.setText(VänLink);
        tvAnmOpm.setText(AnmOpm);
        tvKupNr.setText(KupNr);


        convertView.findViewById(R.id.personell_segment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),PopUp.class);
                intent.putExtra("EXTRA_SESSION_ID",personelListView.getEmployee(PersName));
                ((MainActivity) personelListView.requireActivity()).startActivityForResult(intent,10);
            }
        });

        return convertView;
    }

}