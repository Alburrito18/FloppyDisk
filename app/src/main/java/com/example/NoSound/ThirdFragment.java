package com.example.NoSound;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ThirdFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThirdFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ThirdFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Vy3.
     */
    // TODO: Rename and change types and number of parameters
    public static ThirdFragment newInstance(String param1, String param2) {
        ThirdFragment fragment = new ThirdFragment();
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
        Log.d("codeTag", generateFilterCode("HN1 - Svart", true, true, false, true, "Blå", "Blå", true));
        return inflater.inflate(R.layout.fragment_third, container, false);
    }

    private String generateFilterCode(String filter, boolean cord, boolean rightConch,
                                      boolean leftConch, boolean detect, String rightColour,
                                      String leftColour, boolean triple) {
        String output = "";

        //Add filter to output
        if(filter.equals("HN0 - Magenta") || filter.equals("HN1 - Svart") ||
           filter.equals("HN2 - Grön") || filter.equals("HN3 - Guld") ||
           filter.equals("HN4 - Grå") || filter.equals("HN5 - Orange") ||
           filter.equals("HN6 - Gul") || filter.equals("HN7 - Vit")) {
            output = output + filter.substring(0, 3);
        } else {
            return "Illegal filter choice";
        }

        //Add cord to output
        if(!triple) {
            //Weird if statement since this letter is omitted if both sides are Conch
            if(cord && !(rightConch && leftConch)) {
                output = output + "M";
            } else if(!(rightConch && leftConch)) {
                output = output + "Z";
            }
        } else {
            if(cord) {
                output = output + "M";
            } else {
                output = output + "Z";
            }
        }

        //Add conch to output
        if(!triple) {
            if(rightConch && leftConch) {
                output = output + "SNT";
            } else if(rightConch && !leftConch) {
                output = output + "RNT";
            } else if(leftConch) {
                output = output + "LNT";
            }
        }

        //Add detect to output
        if(detect) {
            output = output + "D";
        }

        //Add colour to output
        if(!(leftColour.equals("Transparent") && rightColour.equals("Transparent"))) {
            output = output + colourNameToCode(rightColour);
            if(!leftColour.equals(rightColour)) {
                output = output + "+" + colourNameToCode(leftColour);
            }
        }

        if(triple) {
            //Add + sign and filter again
            output = output + "+" + output.substring(0, 3);

            //Add which side has Conch
            if(leftConch && !rightConch) {
                output = output + "LNT";
            } else if (rightConch && !leftConch) {
                output = output + "RNT";
            } else {
                return "Illegal combination of conch/triple";
            }

            if(detect) {
                output = output + "D";
            }

            //Add correct colour for the Conch
            if(leftConch && !leftColour.equals("Transparent")) {
                output = output + colourNameToCode(leftColour);
            } else if(rightConch && !rightColour.equals("Transparent")) {
                output = output + colourNameToCode(rightColour);
            }
        }

        return output;
    }

    private String colourNameToCode(String colour) {
        switch (colour) {
            case "Transparent" :
                return "0";
            case "Blå" :
                return "1";
            case "Röd" :
                return "2";
            case "Gul" :
                return "3";
            case "Grön" :
                return "4";
            case "Orange" :
                return "5";
            case "Lila" :
                return "6";
            default :
                return "Illegal colour choice";
        }
    }
}