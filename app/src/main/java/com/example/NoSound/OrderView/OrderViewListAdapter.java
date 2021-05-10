package com.example.NoSound.OrderView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.NoSound.FirstFragment;
import com.example.NoSound.MainActivity;
import com.example.NoSound.OrderAlternative;
import com.example.NoSound.R;

import java.util.ArrayList;

public class OrderViewListAdapter extends ArrayAdapter<OrderView> {
    private static final String TAG = "ProfileListAdapter";

    private Context mContext;
    private int mResource;
    private Fragment firstFragment;



    public OrderViewListAdapter(Context context, int resource, ArrayList<OrderView> objects, Fragment firstFragment) {
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

        convertView.findViewById(R.id.InfoButtonProfile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) firstFragment.requireActivity()).setLatestorderID(CustomerIDProfile);
                NavHostFragment.findNavController(firstFragment)
                        .navigate(R.id.action_FirstFragment_to_orderAlternative);
            }
        });
        return convertView;
    }

}
