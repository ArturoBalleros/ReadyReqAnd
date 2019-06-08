package com.karveg.readyreq.Fragments;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.karveg.readyreq.Models.ReqNFun;
import com.karveg.readyreq.R;
import com.karveg.readyreq.Utils.Utils;

public class ReqNFunFragment extends Fragment {

    private static ReqNFun reqnfun;

    private static EditText editTextName;
    private static EditText editTextVer;
    private static EditText editTextDate;
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

    public ReqNFunFragment() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public ReqNFunFragment(ReqNFun reqnfun) {
        this.reqnfun = reqnfun;
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
        View view = inflater.inflate(R.layout.fragment_req_nfun, container, false);

        bindUI(view);
        setValuesUI();

        editTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        return view;
    }

    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because january is zero
                editTextDate.setText(day + "/" + (month + 1) + "/" + year);
            }
        });
        newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
    }

    private void bindUI(View view) {
        editTextName = view.findViewById(R.id.editTextName);
        editTextVer = view.findViewById(R.id.editTextVer);
        editTextDate = view.findViewById(R.id.editTextDate);
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
        editTextName.setText(reqnfun.getName());
        editTextVer.setText(reqnfun.getVersion() + "");
        editTextDate.setText(Utils.DateToString(reqnfun.getFech(), false));
        editTextDesc.setText(reqnfun.getDescription());

        if (reqnfun.getPrior() == 1) radioButtonPVLow.setChecked(true);
        else if (reqnfun.getPrior() == 2) radioButtonPLow.setChecked(true);
        else if (reqnfun.getPrior() == 3) radioButtonPMed.setChecked(true);
        else if (reqnfun.getPrior() == 4) radioButtonPHigh.setChecked(true);
        else if (reqnfun.getPrior() == 5) radioButtonPVHigh.setChecked(true);

        if (reqnfun.getUrge() == 1) radioButtonUVLow.setChecked(true);
        else if (reqnfun.getUrge() == 2) radioButtonULow.setChecked(true);
        else if (reqnfun.getUrge() == 3) radioButtonUMed.setChecked(true);
        else if (reqnfun.getUrge() == 4) radioButtonUHigh.setChecked(true);
        else if (reqnfun.getUrge() == 5) radioButtonUVHigh.setChecked(true);

        if (reqnfun.getEsta() == 1) radioButtonEVLow.setChecked(true);
        else if (reqnfun.getEsta() == 2) radioButtonELow.setChecked(true);
        else if (reqnfun.getEsta() == 3) radioButtonEMed.setChecked(true);
        else if (reqnfun.getEsta() == 4) radioButtonEHigh.setChecked(true);
        else if (reqnfun.getEsta() == 5) radioButtonEVHigh.setChecked(true);

        if (reqnfun.isState()) radioButtonVer.setChecked(true);
        else radioButtonNVer.setChecked(true);

        spinnerCateg.setSelection(reqnfun.getCategory() - 1);
        editTextComen.setText(reqnfun.getCommentary());
    }

    public static void setValuesReque() {
        reqnfun.setName(editTextName.getText().toString());
        reqnfun.setVersion(Double.parseDouble(editTextVer.getText().toString()));
        reqnfun.setFech(Utils.StringToDate(editTextDate.getText().toString(), false));
        reqnfun.setDescription(editTextDesc.getText().toString());

        if (radioButtonPVLow.isChecked()) reqnfun.setPrior(1);
        else if (radioButtonPLow.isChecked()) reqnfun.setPrior(2);
        else if (radioButtonPMed.isChecked()) reqnfun.setPrior(3);
        else if (radioButtonPHigh.isChecked()) reqnfun.setPrior(4);
        else if (radioButtonPVHigh.isChecked()) reqnfun.setPrior(5);

        if (radioButtonUVLow.isChecked()) reqnfun.setUrge(1);
        else if (radioButtonULow.isChecked()) reqnfun.setUrge(2);
        else if (radioButtonUMed.isChecked()) reqnfun.setUrge(3);
        else if (radioButtonUHigh.isChecked()) reqnfun.setUrge(4);
        else if (radioButtonUVHigh.isChecked()) reqnfun.setUrge(5);

        if (radioButtonEVLow.isChecked()) reqnfun.setEsta(1);
        else if (radioButtonELow.isChecked()) reqnfun.setEsta(2);
        else if (radioButtonEMed.isChecked()) reqnfun.setEsta(3);
        else if (radioButtonEHigh.isChecked()) reqnfun.setEsta(4);
        else if (radioButtonEVHigh.isChecked()) reqnfun.setEsta(5);

        if (radioButtonVer.isChecked()) reqnfun.setState(true);
        else reqnfun.setState(false);

        reqnfun.setCategory(spinnerCateg.getSelectedItemPosition() + 1);
        reqnfun.setCommentary(editTextComen.getText().toString());
    }
}