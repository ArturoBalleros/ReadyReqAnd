package com.karveg.readyreq.Fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.karveg.readyreq.App.MyApplication;
import com.karveg.readyreq.Models.Generic;
import com.karveg.readyreq.Models.ReqFun;
import com.karveg.readyreq.R;

import java.util.ArrayList;
import java.util.List;

public class ReqFunFragment extends Fragment {

    private static ReqFun reqfun;

    private static Spinner spinnerPack;
    private static EditText editTextName;
    private static EditText editTextDesc;

    private static EditText editTextPreCond;
    private static EditText editTextPostCond;

    private static RadioButton radioButtonCLow;
    private static RadioButton radioButtonCMed;
    private static RadioButton radioButtonCHigh;

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

    private static List<Generic> packages;
    private static Context ctx;

    public ReqFunFragment() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public ReqFunFragment(ReqFun reqfun) {
        this.reqfun = reqfun;
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
        View view = inflater.inflate(R.layout.fragment_req_fun, container, false);
        ctx = getContext();
        bindUI(view);
        setValuesUI();
        ReqFun.getPackages(ctx);

        return view;
    }

    private void bindUI(View view) {
        spinnerPack = view.findViewById(R.id.spinnerPack);
        editTextName = view.findViewById(R.id.editTextName);
        editTextDesc = view.findViewById(R.id.editTextDesc);

        editTextPreCond = view.findViewById(R.id.editTextPreCond);
        editTextPostCond = view.findViewById(R.id.editTextPostCond);

        radioButtonCLow = view.findViewById(R.id.radioButtonCLow);
        radioButtonCMed = view.findViewById(R.id.radioButtonCMed);
        radioButtonCHigh = view.findViewById(R.id.radioButtonCHigh);

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
        editTextName.setText(reqfun.getName());
        editTextDesc.setText(reqfun.getDescription());

        editTextPreCond.setText(reqfun.getPreCond());
        editTextPostCond.setText(reqfun.getPostCond());

        if (reqfun.getComplejidad() == 1) radioButtonCLow.setChecked(true);
        else if (reqfun.getComplejidad() == 2) radioButtonCMed.setChecked(true);
        else if (reqfun.getComplejidad() == 3) radioButtonCHigh.setChecked(true);

        if (reqfun.getPrior() == 1) radioButtonPVLow.setChecked(true);
        else if (reqfun.getPrior() == 2) radioButtonPLow.setChecked(true);
        else if (reqfun.getPrior() == 3) radioButtonPMed.setChecked(true);
        else if (reqfun.getPrior() == 4) radioButtonPHigh.setChecked(true);
        else if (reqfun.getPrior() == 5) radioButtonPVHigh.setChecked(true);

        if (reqfun.getUrge() == 1) radioButtonUVLow.setChecked(true);
        else if (reqfun.getUrge() == 2) radioButtonULow.setChecked(true);
        else if (reqfun.getUrge() == 3) radioButtonUMed.setChecked(true);
        else if (reqfun.getUrge() == 4) radioButtonUHigh.setChecked(true);
        else if (reqfun.getUrge() == 5) radioButtonUVHigh.setChecked(true);

        if (reqfun.getEsta() == 1) radioButtonEVLow.setChecked(true);
        else if (reqfun.getEsta() == 2) radioButtonELow.setChecked(true);
        else if (reqfun.getEsta() == 3) radioButtonEMed.setChecked(true);
        else if (reqfun.getEsta() == 4) radioButtonEHigh.setChecked(true);
        else if (reqfun.getEsta() == 5) radioButtonEVHigh.setChecked(true);

        if (reqfun.isState()) radioButtonVer.setChecked(true);
        else radioButtonNVer.setChecked(true);

        spinnerCateg.setSelection(reqfun.getCategory() - 1);
        editTextComen.setText(reqfun.getCommentary());
    }

    public static void setValuesReque() {
        reqfun.setName(editTextName.getText().toString());
        reqfun.setDescription(editTextDesc.getText().toString());

        reqfun.setPackage(0);
        for (Generic g : packages)
            if (g.getName().equals(spinnerPack.getSelectedItem().toString()))
                reqfun.setPackage(g.getId());

        reqfun.setPreCond(editTextPreCond.getText().toString());
        reqfun.setPostCond(editTextPostCond.getText().toString());

        if (radioButtonCLow.isChecked()) reqfun.setComplejidad(1);
        else if (radioButtonCMed.isChecked()) reqfun.setComplejidad(2);
        else if (radioButtonCHigh.isChecked()) reqfun.setComplejidad(3);

        if (radioButtonPVLow.isChecked()) reqfun.setPrior(1);
        else if (radioButtonPLow.isChecked()) reqfun.setPrior(2);
        else if (radioButtonPMed.isChecked()) reqfun.setPrior(3);
        else if (radioButtonPHigh.isChecked()) reqfun.setPrior(4);
        else if (radioButtonPVHigh.isChecked()) reqfun.setPrior(5);

        if (radioButtonUVLow.isChecked()) reqfun.setUrge(1);
        else if (radioButtonULow.isChecked()) reqfun.setUrge(2);
        else if (radioButtonUMed.isChecked()) reqfun.setUrge(3);
        else if (radioButtonUHigh.isChecked()) reqfun.setUrge(4);
        else if (radioButtonUVHigh.isChecked()) reqfun.setUrge(5);

        if (radioButtonEVLow.isChecked()) reqfun.setEsta(1);
        else if (radioButtonELow.isChecked()) reqfun.setEsta(2);
        else if (radioButtonEMed.isChecked()) reqfun.setEsta(3);
        else if (radioButtonEHigh.isChecked()) reqfun.setEsta(4);
        else if (radioButtonEVHigh.isChecked()) reqfun.setEsta(5);

        if (radioButtonVer.isChecked()) reqfun.setState(true);
        else reqfun.setState(false);

        reqfun.setCategory(spinnerCateg.getSelectedItemPosition() + 1);
        reqfun.setCommentary(editTextComen.getText().toString());
    }

    public static void setPackages(List<Generic> p) {
        packages = p;
        packages.add(0, new Generic(1, "No Asignado"));
        ArrayList<String> namePack = new ArrayList<>();
        for (Generic g : packages) namePack.add(g.getName());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(ctx, android.R.layout.simple_list_item_1, namePack);
        spinnerPack.setAdapter(adapter);
        setValueSpinnerInit();
    }

    private static void setValueSpinnerInit() {
        if (reqfun.getPackage() != MyApplication.NOTHING) {
            for (int i = 0; i < packages.size(); i++)
                if (packages.get(i).getId() == reqfun.getPackage())
                    for (int j = 0; j < spinnerPack.getAdapter().getCount(); j++)
                        if (packages.get(i).getName().equals(spinnerPack.getAdapter().getItem(j)))
                            spinnerPack.setSelection(j);
        } else
            spinnerPack.setSelection(0);
    }
}