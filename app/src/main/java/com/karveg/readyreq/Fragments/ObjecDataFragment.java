package com.karveg.readyreq.Fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.karveg.readyreq.Models.Objective;
import com.karveg.readyreq.R;

public class ObjecDataFragment extends Fragment {

    private static Objective objective;

    private static EditText editTextName;
    private static EditText editTextDesc;

    private static RadioButton radioButtonPVLow;
    private static RadioButton radioButtonPLow;
    private static RadioButton radioButtonPMed;
    private static RadioButton radioButtonPHigh;
    private static RadioButton radioButtonPVHigh;

    private static RadioButton radioButtonUVLow;
    private static RadioButton radioButtonULow;
    private static RadioButton radioButtonUMed;
    private static RadioButton radioButtonUHigh;
    private static RadioButton radioButtonUVHigh;

    private static RadioButton radioButtonEVLow;
    private static RadioButton radioButtonELow;
    private static RadioButton radioButtonEMed;
    private static RadioButton radioButtonEHigh;
    private static RadioButton radioButtonEVHigh;

    private static RadioButton radioButtonVer;
    private static RadioButton radioButtonNVer;

    private static Spinner spinnerCateg;
    private static EditText editTextComen;

    public ObjecDataFragment() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public ObjecDataFragment(Objective objective) {
        this.objective = objective;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_objec, container, false);

        bindUI(view);
        setValuesUI();

        return view;
    }

    private void bindUI(View view) {
        editTextName = view.findViewById(R.id.editTextName);
        editTextDesc = view.findViewById(R.id.editTextDesc);

        radioButtonPVLow = view.findViewById(R.id.radioButtonPVLow);
        radioButtonPLow = view.findViewById(R.id.radioButtonPLow);
        radioButtonPMed = view.findViewById(R.id.radioButtonPMed);
        radioButtonPHigh = view.findViewById(R.id.radioButtonPHigh);
        radioButtonPVHigh = view.findViewById(R.id.radioButtonPVHigh);

        radioButtonUVLow = view.findViewById(R.id.radioButtonUVLow);
        radioButtonULow = view.findViewById(R.id.radioButtonULow);
        radioButtonUMed = view.findViewById(R.id.radioButtonUMed);
        radioButtonUHigh = view.findViewById(R.id.radioButtonUHigh);
        radioButtonUVHigh = view.findViewById(R.id.radioButtonUVHigh);

        radioButtonEVLow = view.findViewById(R.id.radioButtonEVLow);
        radioButtonELow = view.findViewById(R.id.radioButtonELow);
        radioButtonEMed = view.findViewById(R.id.radioButtonEMed);
        radioButtonEHigh = view.findViewById(R.id.radioButtonEHigh);
        radioButtonEVHigh = view.findViewById(R.id.radioButtonEVHigh);

        radioButtonVer = view.findViewById(R.id.radioButtonVer);
        radioButtonNVer = view.findViewById(R.id.radioButtonNVer);

        spinnerCateg = view.findViewById(R.id.spinnerCateg);
        editTextComen = view.findViewById(R.id.editTextComen);
    }

    private void setValuesUI() {
        editTextName.setText(objective.getName());
        editTextDesc.setText(objective.getDescription());

        if (objective.getPrior() == 1) radioButtonPVLow.setChecked(true);
        else if (objective.getPrior() == 2) radioButtonPLow.setChecked(true);
        else if (objective.getPrior() == 3) radioButtonPMed.setChecked(true);
        else if (objective.getPrior() == 4) radioButtonPHigh.setChecked(true);
        else if (objective.getPrior() == 5) radioButtonPVHigh.setChecked(true);

        if (objective.getUrge() == 1) radioButtonUVLow.setChecked(true);
        else if (objective.getUrge() == 2) radioButtonULow.setChecked(true);
        else if (objective.getUrge() == 3) radioButtonUMed.setChecked(true);
        else if (objective.getUrge() == 4) radioButtonUHigh.setChecked(true);
        else if (objective.getUrge() == 5) radioButtonUVHigh.setChecked(true);

        if (objective.getEsta() == 1) radioButtonEVLow.setChecked(true);
        else if (objective.getEsta() == 2) radioButtonELow.setChecked(true);
        else if (objective.getEsta() == 3) radioButtonEMed.setChecked(true);
        else if (objective.getEsta() == 4) radioButtonEHigh.setChecked(true);
        else if (objective.getEsta() == 5) radioButtonEVHigh.setChecked(true);

        if (objective.isState()) radioButtonVer.setChecked(true);
        else radioButtonNVer.setChecked(true);

        spinnerCateg.setSelection(objective.getCategory() - 1);
        editTextComen.setText(objective.getCommentary());
    }

    public static void setValuesObjec() {
        objective.setName(editTextName.getText().toString());
        objective.setDescription(editTextDesc.getText().toString());

        if (radioButtonPVLow.isChecked()) objective.setPrior(1);
        else if (radioButtonPLow.isChecked()) objective.setPrior(2);
        else if (radioButtonPMed.isChecked()) objective.setPrior(3);
        else if (radioButtonPHigh.isChecked()) objective.setPrior(4);
        else if (radioButtonPVHigh.isChecked()) objective.setPrior(5);

        if (radioButtonUVLow.isChecked()) objective.setUrge(1);
        else if (radioButtonULow.isChecked()) objective.setUrge(2);
        else if (radioButtonUMed.isChecked()) objective.setUrge(3);
        else if (radioButtonUHigh.isChecked()) objective.setUrge(4);
        else if (radioButtonUVHigh.isChecked()) objective.setUrge(5);

        if (radioButtonEVLow.isChecked()) objective.setEsta(1);
        else if (radioButtonELow.isChecked()) objective.setEsta(2);
        else if (radioButtonEMed.isChecked()) objective.setEsta(3);
        else if (radioButtonEHigh.isChecked()) objective.setEsta(4);
        else if (radioButtonEVHigh.isChecked()) objective.setEsta(5);

        if (radioButtonVer.isChecked()) objective.setState(true);
        else objective.setState(false);

        objective.setCategory(spinnerCateg.getSelectedItemPosition() + 1);
        objective.setCommentary(editTextComen.getText().toString());
    }
}