package com.example.NoSound;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.apache.poi.xwpf.usermodel.XWPFDocument; // tycks inte existera...
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.FileOutputStream;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OrderAlternative#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrderAlternative extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public OrderAlternative() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OrderAlternativ.
     */
    // TODO: Rename and change types and number of parameters
    public static OrderAlternative newInstance(String param1, String param2) {
        OrderAlternative fragment = new OrderAlternative();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order_alternativ, container, false);
    }

    public void ButtonCreated (View view){

        ((MainActivity) requireActivity()).generateDocx();
        try {
            XWPFDocument xwpfDocument = new XWPFDocument();
            XWPFParagraph xwpfParagraph = xwpfDocument.createParagraph();
            XWPFRun xwpfRun = xwpfParagraph.createRun();

            xwpfRun.setText("hej detta är ett test");
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
}