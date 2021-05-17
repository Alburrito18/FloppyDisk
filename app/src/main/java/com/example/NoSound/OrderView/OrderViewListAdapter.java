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
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.NoSound.BusinessView.BusinessData;
import com.example.NoSound.MainActivity;
import com.example.NoSound.R;

import java.util.ArrayList;

public class OrderViewListAdapter extends ArrayAdapter<BusinessData> {
    private static final String TAG = "ProfileListAdapter";

    private Context mContext;
    private int mResource;
    private Fragment firstFragment;



    public OrderViewListAdapter(Context context, int resource, ArrayList<BusinessData> objects, Fragment firstFragment) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
        this.firstFragment = firstFragment;

    }
    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //hämta informationen
        String orderID = getItem(position).getOrderID();
        String name = getItem(position).getCustomerName();
        String date = getItem(position).getDate();
        int internalOrderID = getItem(position).getInternalOrderID();

        //skapa objekt med informationen
        //OrderView orderView = new OrderView(CustomerIDProfile, NameProfile, DateProfile);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvName = (TextView) convertView.findViewById(R.id.NameText);
        TextView tvCustomerID = (TextView) convertView.findViewById(R.id.OrderIDText);
        TextView tvDate = (TextView) convertView.findViewById(R.id.DateText);

        tvCustomerID.setText(orderID);
        tvName.setText(name);
        tvDate.setText(date);

        convertView.findViewById(R.id.InfoButtonProfile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((MainActivity) firstFragment.requireActivity()).setLatestorderID(internalOrderID);
                NavHostFragment.findNavController(firstFragment)
                        .navigate(R.id.action_FirstFragment_to_orderAlternative);
            }
        });
        return convertView;
    }

}
