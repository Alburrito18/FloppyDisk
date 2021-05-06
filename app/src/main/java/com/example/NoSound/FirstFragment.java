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

import com.example.NoSound.OrderView.OrderView;
import com.example.NoSound.OrderView.OrderViewListAdapter;

import java.util.ArrayList;

public class FirstFragment extends Fragment {

    private ArrayList<OrderView> orderViewList = new ArrayList<>();
    private ListView mListView;
    private static final String TAG = "FirstFragment";
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onCreate: Started.");
        mListView = (ListView) view.findViewById(R.id.ListViewProfile);

        //Lite namn för test (tas bort senare)
        OrderView MacDonalds = new OrderView("112", "MacDonalds", "infoga dagens datum");
        OrderView LegoLand = new OrderView("113", "LegoLand", "infoga dagens datum");
        OrderView kolmården = new OrderView("114", "Kolmården", "infoga dagens datum");

        //En arrayList för dessa profiler, används inte efter ihopkoppling med Drill och Snows del?
        orderViewList.add(MacDonalds);
        orderViewList.add(LegoLand);
        orderViewList.add(kolmården);

        OrderViewListAdapter adapter = new OrderViewListAdapter(getContext(), R.layout.profile_first, orderViewList);
        mListView.setAdapter(adapter);
        ((MainActivity) requireActivity()).setFirstFragment(this);
        view.findViewById(R.id.button_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_businessView);
             //   startActivity(new Intent(Intent.EXTRA_PROCESS_TEXT));
            }

        });
    }
    public ListView getmListView() {
        return mListView;
    }

    public void updateOrderView(OrderView orderView) {
        orderViewList.add(orderView);
        OrderViewListAdapter adapter = new OrderViewListAdapter(getContext(), R.layout.profile_first, orderViewList);
        mListView.invalidateViews();
        mListView.setAdapter(adapter);

    }
}