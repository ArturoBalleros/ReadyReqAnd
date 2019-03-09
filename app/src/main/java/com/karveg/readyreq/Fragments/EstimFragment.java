package com.karveg.readyreq.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.karveg.readyreq.R;

public class EstimFragment extends Fragment {

    private static Context ctx;

    private TextView textViewDSR;
    private SeekBar seekBarDSR;
    private TextView textViewRTII;
    private SeekBar seekBarRTII;
    private TextView textViewEUE;
    private SeekBar seekBarEUE;
    private TextView textViewCIPR;
    private SeekBar seekBarCIPR;

    private TextView textViewRCMBAF;
    private SeekBar seekBarRCMBAF;
    private TextView textViewIE;
    private SeekBar seekBarIE;
    private TextView textViewU;
    private SeekBar seekBarU;
    private TextView textViewCPS;
    private SeekBar seekBarCPS;

    private TextView textViewETC;
    private SeekBar seekBarETC;
    private TextView textViewHC;
    private SeekBar seekBarHC;
    private TextView textViewCS;
    private SeekBar seekBarCS;
    private TextView textViewDOTPC;
    private SeekBar seekBarDOTPC;
    private TextView textViewUT;
    private SeekBar seekBarUT;

    private TextView textViewFWTP;
    private SeekBar seekBarFWTP;
    private TextView textViewAE;
    private SeekBar seekBarAE;
    private TextView textViewOOPE;
    private SeekBar seekBarOOPE;
    private TextView textViewLAC;
    private SeekBar seekBarLAC;

    private TextView textViewM;
    private SeekBar seekBarM;
    private TextView textViewSR;
    private SeekBar seekBarSR;
    private TextView textViewPTS;
    private SeekBar seekBarPTS;
    private TextView textViewDPL;
    private SeekBar seekBarDPL;


    public EstimFragment() {
        // Required empty public constructor
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
        View view = inflater.inflate(R.layout.fragment_estim, container, false);
        ctx = getContext();

        bindUI(view);
        Events();

        return view;
    }

    private void bindUI(View view) {

        textViewDSR = view.findViewById(R.id.textViewDSR);
        seekBarDSR = view.findViewById(R.id.seekBarDSR);
        textViewRTII = view.findViewById(R.id.textViewRTII);
        seekBarRTII = view.findViewById(R.id.seekBarRTII);
        textViewEUE = view.findViewById(R.id.textViewEUE);
        seekBarEUE = view.findViewById(R.id.seekBarEUE);
        textViewCIPR = view.findViewById(R.id.textViewCIPR);
        seekBarCIPR = view.findViewById(R.id.seekBarCIPR);

        textViewRCMBAF = view.findViewById(R.id.textViewRCMBAF);
        seekBarRCMBAF = view.findViewById(R.id.seekBarRCMBAF);
        textViewIE = view.findViewById(R.id.textViewIE);
        seekBarIE = view.findViewById(R.id.seekBarIE);
        textViewU = view.findViewById(R.id.textViewU);
        seekBarU = view.findViewById(R.id.seekBarU);
        textViewCPS = view.findViewById(R.id.textViewCPS);
        seekBarCPS = view.findViewById(R.id.seekBarCPS);

        textViewETC = view.findViewById(R.id.textViewETC);
        seekBarETC = view.findViewById(R.id.seekBarETC);
        textViewHC = view.findViewById(R.id.textViewHC);
        seekBarHC = view.findViewById(R.id.seekBarHC);
        textViewCS = view.findViewById(R.id.textViewCS);
        seekBarCS = view.findViewById(R.id.seekBarCS);
        textViewDOTPC = view.findViewById(R.id.textViewDOTPC);
        seekBarDOTPC = view.findViewById(R.id.seekBarDOTPC);
        textViewUT = view.findViewById(R.id.textViewUT);
        seekBarUT = view.findViewById(R.id.seekBarUT);


        textViewFWTP = view.findViewById(R.id.textViewFWTP);
        seekBarFWTP = view.findViewById(R.id.seekBarFWTP);
        textViewAE = view.findViewById(R.id.textViewAE);
        seekBarAE = view.findViewById(R.id.seekBarAE);
        textViewOOPE = view.findViewById(R.id.textViewOOPE);
        seekBarOOPE = view.findViewById(R.id.seekBarOOPE);
        textViewLAC = view.findViewById(R.id.textViewLAC);
        seekBarLAC = view.findViewById(R.id.seekBarLAC);

        textViewM = view.findViewById(R.id.textViewM);
        seekBarM = view.findViewById(R.id.seekBarM);
        textViewSR = view.findViewById(R.id.textViewSR);
        seekBarSR = view.findViewById(R.id.seekBarSR);
        textViewPTS = view.findViewById(R.id.textViewPTS);
        seekBarPTS = view.findViewById(R.id.seekBarPTS);
        textViewDPL = view.findViewById(R.id.textViewDPL);
        seekBarDPL = view.findViewById(R.id.seekBarDPL);
    }

    private void Events() {
        seekBarDSR.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textViewDSR.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                textViewDSR.setText(String.valueOf(seekBar.getProgress()));
            }
        });
        seekBarRTII.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textViewRTII.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                textViewRTII.setText(String.valueOf(seekBar.getProgress()));
            }
        });
        seekBarEUE.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textViewEUE.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                textViewEUE.setText(String.valueOf(seekBar.getProgress()));
            }
        });
        seekBarCIPR.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textViewCIPR.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                textViewCIPR.setText(String.valueOf(seekBar.getProgress()));
            }
        });

        seekBarRCMBAF.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textViewRCMBAF.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                textViewRCMBAF.setText(String.valueOf(seekBar.getProgress()));
            }
        });
        seekBarIE.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textViewIE.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                textViewIE.setText(String.valueOf(seekBar.getProgress()));
            }
        });
        seekBarU.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textViewU.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                textViewU.setText(String.valueOf(seekBar.getProgress()));
            }
        });
        seekBarCPS.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textViewCPS.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                textViewCPS.setText(String.valueOf(seekBar.getProgress()));
            }
        });

        seekBarETC.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textViewETC.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                textViewETC.setText(String.valueOf(seekBar.getProgress()));
            }
        });
        seekBarHC.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textViewHC.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                textViewHC.setText(String.valueOf(seekBar.getProgress()));
            }
        });
        seekBarCS.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textViewCS.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                textViewCS.setText(String.valueOf(seekBar.getProgress()));
            }
        });
        seekBarDOTPC.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textViewDOTPC.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                textViewDOTPC.setText(String.valueOf(seekBar.getProgress()));
            }
        });
        seekBarUT.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textViewUT.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                textViewUT.setText(String.valueOf(seekBar.getProgress()));
            }
        });


        seekBarFWTP.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textViewFWTP.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                textViewFWTP.setText(String.valueOf(seekBar.getProgress()));
            }
        });
        seekBarAE.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textViewAE.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                textViewAE.setText(String.valueOf(seekBar.getProgress()));
            }
        });
        seekBarOOPE.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textViewOOPE.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                textViewOOPE.setText(String.valueOf(seekBar.getProgress()));
            }
        });
        seekBarLAC.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textViewLAC.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                textViewLAC.setText(String.valueOf(seekBar.getProgress()));
            }
        });

        seekBarM.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textViewM.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                textViewM.setText(String.valueOf(seekBar.getProgress()));
            }
        });
        seekBarSR.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textViewSR.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                textViewSR.setText(String.valueOf(seekBar.getProgress()));
            }
        });
        seekBarPTS.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textViewPTS.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                textViewPTS.setText(String.valueOf(seekBar.getProgress()));
            }
        });
        seekBarDPL.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textViewDPL.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                textViewDPL.setText(String.valueOf(seekBar.getProgress()));
            }
        });
    }
}
