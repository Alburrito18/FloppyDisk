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
import java.util.List;

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

        mListView = view.findViewById(R.id.ListViewProfile);

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

    /**
     * This method updates the ListView with the new Order given.
     * @param orderView the order that needs to added to the listview.
     */
    public void updateOrderView(OrderView orderView) {
        orderViewList.add(orderView);
        OrderViewListAdapter adapter = new OrderViewListAdapter(getContext(), R.layout.profile_first, orderViewList);
        mListView.setAdapter(adapter);
    }
}