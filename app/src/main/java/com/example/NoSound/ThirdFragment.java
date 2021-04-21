package com.example.NoSound;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.NoSound.ordervy.Ordervy;
import com.example.NoSound.ordervy.OrdervyListAdapter;

import java.util.ArrayList;

public class ThirdFragment extends Fragment {

    private static final String TAG = "ThirdFragment";

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_third, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onCreate: Started.");
        ListView mListView = (ListView) view.findViewById(R.id.ListViewProfile);

        //Lite namn för test (tas bort senare)
        Ordervy MacDonalds = new Ordervy("112", "MacDonalds", "infoga dagens datum");
        Ordervy LegoLand = new Ordervy("113", "LegoLand", "infoga dagens datum");
        Ordervy kolmården = new Ordervy("114", "Kolmården", "infoga dagens datum");

        //En arrayList för dessa profiler, används inte efter ihopkoppling med Drill och Snows del?
        ArrayList<Ordervy> ordervyList = new ArrayList<>();
        ordervyList.add(MacDonalds);
        ordervyList.add(LegoLand);
        ordervyList.add(kolmården);

        OrdervyListAdapter adapter = new OrdervyListAdapter(getContext(), R.layout.ordervy_segment, ordervyList);
        mListView.setAdapter(adapter);

        }
    }

