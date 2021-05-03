package com.example.NoSound.OrderView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.NoSound.R;

import java.util.ArrayList;

public class OrderViewListAdapter extends ArrayAdapter<OrderView> {
    private static final String TAG = "ProfileListAdapter";

    private Context mContext;
    private int mResource;



    public OrderViewListAdapter(Context context, int resource, ArrayList<OrderView> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;

    }
    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //hämta informationen
        String CustomerIDProfile = getItem(position).getCustomerIDProfile();
        String NameProfile = getItem(position).getNameProfile();
        String DateProfile = getItem(position).getDateProfile();

        //skapa objekt med informationen
        OrderView orderView = new OrderView(CustomerIDProfile, NameProfile, DateProfile);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvName = (TextView) convertView.findViewById(R.id.NameProfile);
        TextView tvCustomerID = (TextView) convertView.findViewById(R.id.CustomerIDProfile);
        TextView tvDate = (TextView) convertView.findViewById(R.id.DateProfile);

        tvName.setText(NameProfile);
        tvCustomerID.setText(CustomerIDProfile);
        tvDate.setText(DateProfile);

        return convertView;
    }

}
