package com.example.NoSound;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EarPieceForm#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EarPieceForm extends Fragment {

    private Spinner colorLeft;
    private Spinner colorRight;
    private Spinner concha;
    private Spinner filterChoice;
    private Switch detect;
    private Switch tripleset;
    private Switch stringAttachment;
    private Button saveButton;

    public EarPieceForm() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static EarPieceForm newInstance() {
        EarPieceForm fragment = new EarPieceForm();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d("codeTag", generateFilterCode("HN1 - Svart", true, true, false, true, "Blå", "Blå", true));
        return inflater.inflate(R.layout.fragment_earpiece, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.saveButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Employee employee1 = ((MainActivity) requireActivity()).getEmployee();
                employee1.setStringAttachment(stringAttachment.isChecked());
                employee1.setRightSideColor(colorRight.getSelectedItem().toString());
                employee1.setLeftSideColor(colorLeft.getSelectedItem().toString());
                employee1.setDetect(detect.isChecked());
                employee1.setTripleset(tripleset.isChecked());
                employee1.setFilterChoice(filterChoice.getSelectedItem().toString());
                employee1.setLeftSideConcha(leftSideConchaChoice());
                employee1.setRightSideConcha(rightSideConchaChoice());
                saveInfo(view);
                NavHostFragment.findNavController(EarPieceForm.this).navigate(R.id.action_thirdfragment_to_FirstFragment);
            }
        });
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        colorLeft = requireView().findViewById(R.id.ColorLeft);
        colorRight = requireView().findViewById(R.id.ColorRight);
        concha = requireView().findViewById(R.id.Concha);
        filterChoice = requireView().findViewById(R.id.filterChoice);
        detect = requireView().findViewById(R.id.detect);
        tripleset = requireView().findViewById(R.id.tripleset);
        stringAttachment = requireView().findViewById(R.id.stringAttachment);
    }
    private boolean leftSideConchaChoice() {
        return concha.getSelectedItem().toString().equals("Vänster") || concha.getSelectedItem().toString().equals("Båda");
    }
    private boolean rightSideConchaChoice() {
        return concha.getSelectedItem().toString().equals("Höger") || concha.getSelectedItem().toString().equals("Båda");
    }
    private void saveInfo(View v) {
        ((MainActivity) requireActivity()).saveEmployeePublicly(v);
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