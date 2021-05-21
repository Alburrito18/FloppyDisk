package com.example.NoSound;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

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
    private EditText commentText;
    private String filterCode;
    private TextView filterCodeTextView;

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
        //Log.d("codeTag", generateFilterCode("HN1 - Svart", true, true, false, true, "Blå", "Blå", true));
        return inflater.inflate(R.layout.fragment_earpiece, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        Employee employee = ((MainActivity) requireActivity()).getEditEmployee();
        if (employee!=null){
            ((Switch)requireView().findViewById(R.id.stringAttachment)).setChecked(employee.isStringAttachment());
            setSpinnerColor(employee.getLeftSideColor(),(Spinner)requireView().findViewById(R.id.ColorLeft));
            setSpinnerColor(employee.getRightSideColor(),(Spinner)requireView().findViewById(R.id.ColorRight));
            setSpinnerConcha(conchaString(employee.isLeftSideConcha(),employee.isRightSideConcha()),(Spinner)requireView().findViewById(R.id.Concha));
            ((Switch)requireView().findViewById(R.id.detect)).setChecked(employee.isDetect());
        }
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
                employee1.setComment(commentText.getText().toString());
                employee1.setFilterCode(filterCode);
                saveInfo(view);
                NavHostFragment.findNavController(EarPieceForm.this).navigate(R.id.action_earPieceForm_to_personelListView);
            }
        });
        view.findViewById(R.id.stringAttachment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               updateFilterCode();
            }
        });
        view.findViewById(R.id.detect).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateFilterCode();
            }
        });
        view.findViewById(R.id.tripleset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateFilterCode();
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
        commentText = requireView().findViewById(R.id.commentText);
        filterCodeTextView = requireView().findViewById(R.id.filterCodeTextView);
        updateFilterCode();
        colorLeft.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateFilterCode();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        colorRight.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateFilterCode();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        concha.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateFilterCode();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        filterChoice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateFilterCode();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private boolean leftSideConchaChoice() {
        return concha.getSelectedItem().toString().equals("Vänster") || concha.getSelectedItem().toString().equals("Båda");
    }
    private boolean rightSideConchaChoice() {
        return concha.getSelectedItem().toString().equals("Höger") || concha.getSelectedItem().toString().equals("Båda");
    }
    private String conchaString(boolean leftSide, boolean rightside){
        if (leftSide && rightside){
            return "Båda";
        }
        else if (leftSide){
            return "Vänster";
        }
        else if (rightside){
            return "Höger";
        }
        return "Ingen";
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
            case "Gul*" :
                return "3";
            case "Grön*" :
                return "4";
            case "Orange*" :
                return "5";
            case "Lila*" :
                return "6";
            default :
                return "Illegal colour choice";
        }
    }
    private void updateFilterCode(){
        filterCode = generateFilterCode(filterChoice.getSelectedItem().toString(),stringAttachment.isChecked(),
                rightSideConchaChoice(),leftSideConchaChoice(),detect.isChecked(),colorRight.getSelectedItem().toString(),
                colorLeft.getSelectedItem().toString(),tripleset.isChecked());
        filterCodeTextView.setText(filterCode);
    }
    private void setSpinnerColor(String compareValue,Spinner mSpinner){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.ColourChoices, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);
        if (compareValue != null) {
            int spinnerPosition = adapter.getPosition(compareValue);
            mSpinner.setSelection(spinnerPosition);
        }
    }
    private void setSpinnerConcha(String compareValue,Spinner mSpinner){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.ConchaChoices, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);
        if (compareValue != null) {
            int spinnerPosition = adapter.getPosition(compareValue);
            mSpinner.setSelection(spinnerPosition);
        }
    }
}