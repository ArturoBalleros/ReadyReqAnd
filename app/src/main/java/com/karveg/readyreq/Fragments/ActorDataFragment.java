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

import com.karveg.readyreq.Models.Actor;
import com.karveg.readyreq.R;
import com.karveg.readyreq.Utils.Utils;

public class ActorDataFragment extends Fragment {

    private static Actor actor;

    private static EditText editTextName;
    private static TextView editTextVer;
    private static TextView editTextDate;
    private static EditText editTextDesc;
    private static RadioButton radioButtonLow;
    private static RadioButton radioButtonMed;
    private static RadioButton radioButtonHigh;
    private static EditText editTextDescComp;
    private static Spinner spinnerCateg;
    private static EditText editTextComen;

    public ActorDataFragment() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public ActorDataFragment(Actor actor) {
        this.actor = actor;
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
        View view = inflater.inflate(R.layout.fragment_actor, container, false);

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
        editTextDesc = view.findViewById(R.id.editTextDesc);
        radioButtonLow = view.findViewById(R.id.radioButtonLow);
        radioButtonMed = view.findViewById(R.id.radioButtonMed);
        radioButtonHigh = view.findViewById(R.id.radioButtonHigh);
        editTextDescComp = view.findViewById(R.id.editTextDescComp);
        spinnerCateg = view.findViewById(R.id.spinnerCateg);
        editTextComen = view.findViewById(R.id.editTextComen);
        editTextVer = view.findViewById(R.id.editTextVer);
        editTextDate = view.findViewById(R.id.editTextDate);
    }

    private void setValuesUI() {
        editTextName.setText(actor.getName());
        editTextVer.setText(actor.getVersion() + "");
        editTextDate.setText(Utils.DateToString(actor.getFech(), false));
        editTextDesc.setText(actor.getDescription());
        if (actor.getComple() == 1) radioButtonLow.setChecked(true);
        else if (actor.getComple() == 2) radioButtonMed.setChecked(true);
        else radioButtonHigh.setChecked(true);
        editTextDescComp.setText(actor.getDescComple());
        spinnerCateg.setSelection(actor.getCategory() - 1);
        editTextComen.setText(actor.getCommentary());
    }

    public static void setValuesActor() {
        actor.setName(editTextName.getText().toString());
        actor.setVersion(Double.parseDouble(editTextVer.getText().toString()));
        actor.setFech(Utils.StringToDate(editTextDate.getText().toString(), false));
        actor.setDescription(editTextDesc.getText().toString());
        actor.setDescComple(editTextDescComp.getText().toString());
        if (radioButtonLow.isChecked()) actor.setComple(1);
        else if (radioButtonMed.isChecked()) actor.setComple(2);
        else actor.setComple(3);
        actor.setCategory(spinnerCateg.getSelectedItemPosition() + 1);
        actor.setCommentary(editTextComen.getText().toString());
    }
}