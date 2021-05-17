package com.example.NoSound;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.NoSound.BusinessView.BusinessData;
import com.example.NoSound.PersonellListView.PersonellListView;

import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.FileOutputStream;
import java.util.ArrayList;

public class OrderAlternative extends Fragment {
    private OnDataPass dataPasser;

    private BusinessData businessData;

    public OrderAlternative() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order_alternativ, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String latestOrderID = ((MainActivity) requireActivity()).getLatestOrderID();
        businessData = ((MainActivity) requireActivity()).getBusinessData(latestOrderID);
        passData(businessData,latestOrderID);
        ((TextView)view.findViewById(R.id.companyName)).setText(businessData.getCustomerName());
        ((TextView)view.findViewById(R.id.date)).setText(businessData.getDate());
        ((TextView)view.findViewById(R.id.orderNum)).setText("Ordernr: " + latestOrderID);
        view.findViewById(R.id.wordButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDocx(businessData);
            }
        });
        view.findViewById(R.id.orderButton).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
                NavHostFragment.findNavController(OrderAlternative.this).navigate(R.id.action_orderAlternative_to_personellList);
                ((MainActivity) requireActivity()).loadEmployeeList((ArrayList<Employee>) businessData.getEmployees());
        }
    });
}

    private void createDocx(BusinessData businessData){

        ((MainActivity) requireActivity()).generateDocx();
        try {
            XWPFDocument xwpfDocument = new XWPFDocument();
            XWPFParagraph xwpfParagraph = xwpfDocument.createParagraph();
            XWPFRun xwpfRun = xwpfParagraph.createRun();
            Log.d("BusineddDataToString",businessData.toString());
            String data = businessData.toString();
            if (data.contains("\n")) {
                String[] lines = data.split("\n");
                xwpfRun.setText(lines[0], 0); // set first line into XWPFRun
                for(int i=1;i<lines.length;i++){
                    // add break and insert new text
                    xwpfRun.addBreak();
                    xwpfRun.setText(lines[i]);
                }
            } else {
                xwpfRun.setText(data, 0);
            }
            xwpfRun.setFontSize(12);
            FileOutputStream fileOutputStream = new FileOutputStream(((MainActivity) requireActivity()).fileGetter());
            xwpfDocument.write(fileOutputStream);

            if (fileOutputStream != null){
                fileOutputStream.flush();
                fileOutputStream.close();
            }

            xwpfDocument.close();

        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        dataPasser = (OnDataPass) context;
    }
    private void passData(BusinessData businessData,String latestorderID){
        dataPasser.onDataPass(businessData,latestorderID);
    }
}