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
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.karveg.readyreq.App.MyApplication;
import com.karveg.readyreq.Models.ReqFun;
import com.karveg.readyreq.Models.ReqInfo;
import com.karveg.readyreq.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReqInfoFragment extends Fragment {

    private static ReqInfo reqinfo;

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

    private static SeekBar seekBarOcuMed;
    private static SeekBar seekBarOcuMax;
    private static SeekBar seekBarTlifMed;
    private static SeekBar seekBarTlifMax;

    private TextView textViewOcuMed;
    private TextView textViewOcuMax;
    private TextView textViewTlifMed;
    private TextView textViewTlifMax;

    public ReqInfoFragment() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public ReqInfoFragment(ReqInfo reqinfo) {
        this.reqinfo = reqinfo;
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
        View view = inflater.inflate(R.layout.fragment_req_info, container, false);

        bindUI(view);
        Events();
              setValuesUI();

        return view;
    }

    private void Events(){
        seekBarOcuMed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int prog = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                prog = progress;
                textViewOcuMed.setText(String.valueOf(prog));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                prog = seekBar.getProgress();
                textViewOcuMed.setText(String.valueOf(prog));
            }
        });

        seekBarOcuMax.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int prog = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                prog = progress;
                textViewOcuMax.setText(String.valueOf(prog));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                prog = seekBar.getProgress();
                textViewOcuMax.setText(String.valueOf(prog));
            }
        });

        seekBarTlifMed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int prog = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                prog = progress;
                textViewTlifMed.setText(String.valueOf(prog));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                prog = seekBar.getProgress();
                textViewTlifMed.setText(String.valueOf(prog));
            }
        });

        seekBarTlifMax.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int prog = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                prog = progress;
                textViewTlifMax.setText(String.valueOf(prog));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                prog = seekBar.getProgress();
                textViewTlifMax.setText(String.valueOf(prog));
            }
        });
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

        seekBarOcuMed = view.findViewById(R.id.seekBarOcuMed);
        seekBarOcuMax = view.findViewById(R.id.seekBarOcuMax);
        seekBarTlifMed = view.findViewById(R.id.seekBarTlifMed);
        seekBarTlifMax = view.findViewById(R.id.seekBarTlifMax);

        textViewOcuMed = view.findViewById(R.id.textViewOcuMed);
        textViewOcuMax = view.findViewById(R.id.textViewOcuMax);
        textViewTlifMed = view.findViewById(R.id.textViewTlifMed);
        textViewTlifMax = view.findViewById(R.id.textViewTlifMax);

    }

    private void setValuesUI() {
        editTextName.setText(reqinfo.getName());
        editTextDesc.setText(reqinfo.getDescription());

        if (reqinfo.getPrior() == 1)
            radioButtonPVLow.setChecked(true);
        else if (reqinfo.getPrior() == 2)
            radioButtonPLow.setChecked(true);
        else if (reqinfo.getPrior() == 3)
            radioButtonPMed.setChecked(true);
        else if (reqinfo.getPrior() == 4)
            radioButtonPHigh.setChecked(true);
        else if (reqinfo.getPrior() == 5)
            radioButtonPVHigh.setChecked(true);

        if (reqinfo.getUrge() == 1)
            radioButtonUVLow.setChecked(true);
        else if (reqinfo.getUrge() == 2)
            radioButtonULow.setChecked(true);
        else if (reqinfo.getUrge() == 3)
            radioButtonUMed.setChecked(true);
        else if (reqinfo.getUrge() == 4)
            radioButtonUHigh.setChecked(true);
        else if (reqinfo.getUrge() == 5)
            radioButtonUVHigh.setChecked(true);

        if (reqinfo.getEsta() == 1)
            radioButtonEVLow.setChecked(true);
        else if (reqinfo.getEsta() == 2)
            radioButtonELow.setChecked(true);
        else if (reqinfo.getEsta() == 3)
            radioButtonEMed.setChecked(true);
        else if (reqinfo.getEsta() == 4)
            radioButtonEHigh.setChecked(true);
        else if (reqinfo.getEsta() == 5)
            radioButtonEVHigh.setChecked(true);

        if (reqinfo.isState())
            radioButtonVer.setChecked(true);
        else
            radioButtonNVer.setChecked(true);

        spinnerCateg.setSelection(reqinfo.getCategory() - 1);
        editTextComen.setText(reqinfo.getCommentary());

        seekBarOcuMed.setProgress(reqinfo.getOcuMed());
        seekBarOcuMax.setProgress(reqinfo.getOcuMax());
        seekBarTlifMed.setProgress(reqinfo.getTimeMed());
        seekBarTlifMax.setProgress(reqinfo.getTimeMax());

        textViewOcuMed.setText(String.valueOf(seekBarOcuMed.getProgress()));
        textViewOcuMax.setText(String.valueOf(seekBarOcuMax.getProgress()));
        textViewTlifMed.setText(String.valueOf(seekBarTlifMed.getProgress()));
        textViewTlifMax.setText(String.valueOf(seekBarTlifMax.getProgress()));

    }

    public static void setValuesReque() {
        reqinfo.setName(editTextName.getText().toString());
        reqinfo.setDescription(editTextDesc.getText().toString());

        if (radioButtonPVLow.isChecked())
            reqinfo.setPrior(1);
        else if (radioButtonPLow.isChecked())
            reqinfo.setPrior(2);
        else if (radioButtonPMed.isChecked())
            reqinfo.setPrior(3);
        else if (radioButtonPHigh.isChecked())
            reqinfo.setPrior(4);
        else if (radioButtonPVHigh.isChecked())
            reqinfo.setPrior(5);

        if (radioButtonUVLow.isChecked())
            reqinfo.setUrge(1);
        else if (radioButtonULow.isChecked())
            reqinfo.setUrge(2);
        else if (radioButtonUMed.isChecked())
            reqinfo.setUrge(3);
        else if (radioButtonUHigh.isChecked())
            reqinfo.setUrge(4);
        else if (radioButtonUVHigh.isChecked())
            reqinfo.setUrge(5);

        if (radioButtonEVLow.isChecked())
            reqinfo.setEsta(1);
        else if (radioButtonELow.isChecked())
            reqinfo.setEsta(2);
        else if (radioButtonEMed.isChecked())
            reqinfo.setEsta(3);
        else if (radioButtonEHigh.isChecked())
            reqinfo.setEsta(4);
        else if (radioButtonEVHigh.isChecked())
            reqinfo.setEsta(5);

        if (radioButtonVer.isChecked())
            reqinfo.setState(true);
        else
            reqinfo.setState(false);

        reqinfo.setCategory(spinnerCateg.getSelectedItemPosition() + 1);
        reqinfo.setCommentary(editTextComen.getText().toString());

        reqinfo.setOcuMed(seekBarOcuMed.getProgress());
        reqinfo.setOcuMax(seekBarOcuMax.getProgress());
        reqinfo.setTimeMed(seekBarTlifMed.getProgress());
        reqinfo.setTimeMax(seekBarTlifMax.getProgress());

    }

}
