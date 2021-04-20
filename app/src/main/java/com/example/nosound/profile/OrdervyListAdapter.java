package com.example.nosound.profile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.nosound.R;

import java.util.ArrayList;

public class OrdervyListAdapter extends ArrayAdapter<Ordervy> {
    private static final String TAG = "ProfileListAdapter";

    private Context mContext;
    int mResource;



    public OrdervyListAdapter(Context context, int resource, ArrayList<Ordervy> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;

    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
       //hämta informationen
        String KundIDProfil = getItem(position).getKundIDProfil();
       String NamnProfil = getItem(position).getNamnProfil();
       String DagensDatumProfil = getItem(position).getDagensDatumProfil();

       //skapa objekt med informationen
        Ordervy ordervy = new Ordervy(KundIDProfil, NamnProfil, DagensDatumProfil);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvNamn = (TextView) convertView.findViewById(R.id.NamnProfil);
        TextView tvKundID = (TextView) convertView.findViewById(R.id.KundIDProfil);
        TextView tvDatum = (TextView) convertView.findViewById(R.id.DagensDatumProfil);

        tvNamn.setText(NamnProfil);
        tvKundID.setText(KundIDProfil);
        tvDatum.setText(DagensDatumProfil);

        return convertView;
    }

}
