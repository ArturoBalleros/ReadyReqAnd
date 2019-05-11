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

import com.karveg.readyreq.Models.Actor;
import com.karveg.readyreq.R;

public class ActorDataFragment extends Fragment {

    private static Actor actor;

    private static EditText editTextName;
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

        return view;
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
    }

    private void setValuesUI() {
        editTextName.setText(actor.getName());
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
        actor.setDescription(editTextDesc.getText().toString());
        actor.setDescComple(editTextDescComp.getText().toString());
        if (radioButtonLow.isChecked()) actor.setComple(1);
        else if (radioButtonMed.isChecked()) actor.setComple(2);
        else actor.setComple(3);
        actor.setCategory(spinnerCateg.getSelectedItemPosition() + 1);
        actor.setCommentary(editTextComen.getText().toString());
    }
}