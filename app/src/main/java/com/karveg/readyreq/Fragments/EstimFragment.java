package com.karveg.readyreq.Fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.karveg.readyreq.App.MyApplication;
import com.karveg.readyreq.Models.Estim;
import com.karveg.readyreq.R;
import com.karveg.readyreq.Utils.Utils;

public class EstimFragment extends Fragment {

    private static Estim estim;
    private AlertDialog progressDialog;
    private static Context ctx;

    private TextView textViewDSR;
    private static SeekBar seekBarDSR;
    private TextView textViewRTII;
    private static SeekBar seekBarRTII;
    private TextView textViewEUE;
    private static SeekBar seekBarEUE;
    private TextView textViewCIPR;
    private static SeekBar seekBarCIPR;
    private TextView textViewRCMBAF;
    private static SeekBar seekBarRCMBAF;
    private TextView textViewIE;
    private static SeekBar seekBarIE;
    private TextView textViewU;
    private static SeekBar seekBarU;
    private TextView textViewCPS;
    private static SeekBar seekBarCPS;
    private TextView textViewETC;
    private static SeekBar seekBarETC;
    private TextView textViewHC;
    private static SeekBar seekBarHC;
    private TextView textViewCS;
    private static SeekBar seekBarCS;
    private TextView textViewDOTPC;
    private static SeekBar seekBarDOTPC;
    private TextView textViewUT;
    private static SeekBar seekBarUT;

    private TextView textViewFWTP;
    private static SeekBar seekBarFWTP;
    private TextView textViewAE;
    private static SeekBar seekBarAE;
    private TextView textViewOOPE;
    private static SeekBar seekBarOOPE;
    private TextView textViewLAC;
    private static SeekBar seekBarLAC;
    private TextView textViewM;
    private static SeekBar seekBarM;
    private TextView textViewSR;
    private static SeekBar seekBarSR;
    private TextView textViewPTS;
    private static SeekBar seekBarPTS;
    private TextView textViewDPL;
    private static SeekBar seekBarDPL;

    private static TextView textViewUUCPSim;
    private static TextView textViewUUCPMed;
    private static TextView textViewUUCPMax;

    private static TextView textViewAWSim;
    private static TextView textViewAWMed;
    private static TextView textViewAWMax;

    private static TextView textViewValTFC;
    private static TextView textViewValEF;
    private static TextView textViewValUUCP;
    private static TextView textViewValAW;
    private static TextView textViewValUCP;

    private TextView textViewHXUCP;
    private static SeekBar seekBarHXUCP;

    private static TextView textViewValHE;

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
        progressDialog = CreateProgressDialog().create();
        ctx = getContext();

        bindUI(view);
        Events();
        Estim.getEstim(getContext(), progressDialog);

        return view;
    }

    private void bindUI(View view) {

        textViewDSR = view.findViewById(R.id.textViewDSR);
        textViewDSR.setText("0");
        seekBarDSR = view.findViewById(R.id.seekBarDSR);
        textViewRTII = view.findViewById(R.id.textViewRTII);
        textViewRTII.setText("0");
        seekBarRTII = view.findViewById(R.id.seekBarRTII);
        textViewEUE = view.findViewById(R.id.textViewEUE);
        textViewEUE.setText("0");
        seekBarEUE = view.findViewById(R.id.seekBarEUE);
        textViewCIPR = view.findViewById(R.id.textViewCIPR);
        textViewCIPR.setText("0");
        seekBarCIPR = view.findViewById(R.id.seekBarCIPR);

        textViewRCMBAF = view.findViewById(R.id.textViewRCMBAF);
        textViewRCMBAF.setText("0");
        seekBarRCMBAF = view.findViewById(R.id.seekBarRCMBAF);
        textViewIE = view.findViewById(R.id.textViewIE);
        textViewIE.setText("0");
        seekBarIE = view.findViewById(R.id.seekBarIE);
        textViewU = view.findViewById(R.id.textViewU);
        textViewU.setText("0");
        seekBarU = view.findViewById(R.id.seekBarU);
        textViewCPS = view.findViewById(R.id.textViewCPS);
        textViewCPS.setText("0");
        seekBarCPS = view.findViewById(R.id.seekBarCPS);

        textViewETC = view.findViewById(R.id.textViewETC);
        textViewETC.setText("0");
        seekBarETC = view.findViewById(R.id.seekBarETC);
        textViewHC = view.findViewById(R.id.textViewHC);
        textViewHC.setText("0");
        seekBarHC = view.findViewById(R.id.seekBarHC);
        textViewCS = view.findViewById(R.id.textViewCS);
        textViewCS.setText("0");
        seekBarCS = view.findViewById(R.id.seekBarCS);
        textViewDOTPC = view.findViewById(R.id.textViewDOTPC);
        textViewDOTPC.setText("0");
        seekBarDOTPC = view.findViewById(R.id.seekBarDOTPC);
        textViewUT = view.findViewById(R.id.textViewUT);
        textViewUT.setText("0");
        seekBarUT = view.findViewById(R.id.seekBarUT);


        textViewFWTP = view.findViewById(R.id.textViewFWTP);
        textViewFWTP.setText("0");
        seekBarFWTP = view.findViewById(R.id.seekBarFWTP);
        textViewAE = view.findViewById(R.id.textViewAE);
        textViewAE.setText("0");
        seekBarAE = view.findViewById(R.id.seekBarAE);
        textViewOOPE = view.findViewById(R.id.textViewOOPE);
        textViewOOPE.setText("0");
        seekBarOOPE = view.findViewById(R.id.seekBarOOPE);
        textViewLAC = view.findViewById(R.id.textViewLAC);
        textViewLAC.setText("0");
        seekBarLAC = view.findViewById(R.id.seekBarLAC);

        textViewM = view.findViewById(R.id.textViewM);
        textViewM.setText("0");
        seekBarM = view.findViewById(R.id.seekBarM);
        textViewSR = view.findViewById(R.id.textViewSR);
        textViewSR.setText("0");
        seekBarSR = view.findViewById(R.id.seekBarSR);
        textViewPTS = view.findViewById(R.id.textViewPTS);
        textViewPTS.setText("0");
        seekBarPTS = view.findViewById(R.id.seekBarPTS);
        textViewDPL = view.findViewById(R.id.textViewDPL);
        textViewDPL.setText("0");
        seekBarDPL = view.findViewById(R.id.seekBarDPL);


        textViewUUCPSim = view.findViewById(R.id.textViewUUCPSim);
        textViewUUCPMed = view.findViewById(R.id.textViewUUCPMed);
        textViewUUCPMax = view.findViewById(R.id.textViewUUCPMax);


        textViewAWSim = view.findViewById(R.id.textViewAWSim);
        textViewAWMed = view.findViewById(R.id.textViewAWMed);
        textViewAWMax = view.findViewById(R.id.textViewAWMax);


        textViewValTFC = view.findViewById(R.id.textViewValTFC);
        textViewValEF = view.findViewById(R.id.textViewValEF);
        textViewValUUCP = view.findViewById(R.id.textViewValUUCP);
        textViewValAW = view.findViewById(R.id.textViewValAW);
        textViewValUCP = view.findViewById(R.id.textViewValUCP);

        textViewHXUCP = view.findViewById(R.id.textViewHXUCP);
        seekBarHXUCP = view.findViewById(R.id.seekBarHXUCP);

        textViewValHE = view.findViewById(R.id.textViewValHE);

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
                estim.setDSR(seekBar.getProgress());
                calcValues();
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
                estim.setRTII(seekBar.getProgress());
                calcValues();
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
                estim.setEUE(seekBar.getProgress());
                calcValues();
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
                estim.setCIPR(seekBar.getProgress());
                calcValues();
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
                estim.setRCMBAF(seekBar.getProgress());
                calcValues();
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
                estim.setIE(seekBar.getProgress());
                calcValues();
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
                estim.setU(seekBar.getProgress());
                calcValues();
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
                estim.setCPS(seekBar.getProgress());
                calcValues();
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
                estim.setETC(seekBar.getProgress());
                calcValues();
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
                estim.setHC(seekBar.getProgress());
                calcValues();
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
                estim.setCS(seekBar.getProgress());
                calcValues();
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
                estim.setDOTPC(seekBar.getProgress());
                calcValues();
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
                estim.setUT(seekBar.getProgress());
                calcValues();
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
                estim.setFWTP(seekBar.getProgress());
                calcValues();
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
                estim.setAE(seekBar.getProgress());
                calcValues();
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
                estim.setOOPE(seekBar.getProgress());
                calcValues();
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
                estim.setLAC(seekBar.getProgress());
                calcValues();
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
                estim.setM(seekBar.getProgress());
                calcValues();
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
                estim.setSR(seekBar.getProgress());
                calcValues();
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
                estim.setPTS(seekBar.getProgress());
                calcValues();
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
                estim.setDPL(seekBar.getProgress());
                calcValues();
            }
        });

        seekBarHXUCP.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textViewHXUCP.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                textViewHXUCP.setText(String.valueOf(seekBar.getProgress()));
                calcValues();
            }
        });
    }

    private AlertDialog.Builder CreateProgressDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.progress_dialog, null);
        dialogBuilder.setView(dialogView);
        dialogBuilder.setCancelable(false);
        return dialogBuilder;
    }

    public static void setValueEstim(Estim e) {
        estim = e;
        setValueUI();
    }

    private static void setValueUI() {
        seekBarDSR.setProgress(estim.getDSR());
        seekBarRTII.setProgress(estim.getRTII());
        seekBarEUE.setProgress(estim.getEUE());
        seekBarCIPR.setProgress(estim.getCIPR());
        seekBarRCMBAF.setProgress(estim.getRCMBAF());
        seekBarIE.setProgress(estim.getIE());
        seekBarU.setProgress(estim.getU());
        seekBarCPS.setProgress(estim.getCPS());
        seekBarETC.setProgress(estim.getETC());
        seekBarHC.setProgress(estim.getHC());
        seekBarCS.setProgress(estim.getCS());
        seekBarDOTPC.setProgress(estim.getDOTPC());
        seekBarUT.setProgress(estim.getUT());

        seekBarFWTP.setProgress(estim.getFWTP());
        seekBarAE.setProgress(estim.getAE());
        seekBarOOPE.setProgress(estim.getOOPE());
        seekBarLAC.setProgress(estim.getLAC());
        seekBarM.setProgress(estim.getM());
        seekBarSR.setProgress(estim.getSR());
        seekBarPTS.setProgress(estim.getPTS());
        seekBarDPL.setProgress(estim.getDPL());

        textViewUUCPSim.setText(estim.getUUCPSim() + "");
        textViewUUCPMed.setText(estim.getUUCPMed() + "");
        textViewUUCPMax.setText(estim.getUUCPMax() + "");

        textViewAWSim.setText(estim.getAWSim() + "");
        textViewAWMed.setText(estim.getAWMed() + "");
        textViewAWMax.setText(estim.getAWMax() + "");

        calcValues();
    }

    private static void calcValues() {
        double TCF;
        double EF;
        int UUCP;
        int AW;
        int UCP;
        int HE;

        //Calcular TCF
        TCF = estim.getDSR() * 2;
        TCF += estim.getRTII();
        TCF += estim.getEUE();
        TCF += estim.getCIPR();
        TCF += estim.getRCMBAF();
        TCF += (estim.getIE() * 0.5);
        TCF += (estim.getU() * 0.5);
        TCF += (estim.getCPS() * 2);
        TCF += estim.getETC();
        TCF += estim.getHC();
        TCF += estim.getCS();
        TCF += estim.getDOTPC();
        TCF += estim.getUT();
        TCF /= 100;
        TCF += 0.6;
        try {
            textViewValTFC.setText((TCF + "").substring(0, 5));
        } catch (Exception e) {
            textViewValTFC.setText(TCF + "");
        }

        //Calcular EF
        EF = estim.getFWTP() * 1.5;
        EF += (estim.getAE() * 0.5);
        EF += estim.getOOPE();
        EF += (estim.getLAC() * 0.5);
        EF += estim.getM();
        EF += (estim.getSR() * 2);
        EF += (estim.getPTS() * -1);
        EF += (estim.getDPL() * -1);
        EF *= 0.03;
        EF -= 1.4;
        EF *= -1;
        try {
            textViewValEF.setText((EF + "").substring(0, 5));
        } catch (Exception e) {
            textViewValEF.setText(EF + "");
        }

        //Calcular UUCP
        UUCP = (estim.getUUCPSim() * 5) + (estim.getUUCPMed() * 10) + (estim.getUUCPMax() * 15);
        textViewValUUCP.setText(UUCP + "");

        //Calcular AW
        AW = estim.getAWSim() + (estim.getAWMed() * 2) + (estim.getAWMax() * 3);
        textViewValAW.setText(AW + "");

        //Calcular UCP
        UCP = (int) ((UUCP + AW) * TCF * EF);
        textViewValUCP.setText(UCP + "");

        //Calcular HE
        try {
            HE = UCP * seekBarHXUCP.getProgress();
        } catch (Exception e) {
            HE = 0;
        }
        textViewValHE.setText(HE + "");
    }

    public static void saveEstimates() {
        sendRequest("DSR", estim.getDSR());
        sendRequest("RTII", estim.getRTII());
        sendRequest("EUE", estim.getEUE());
        sendRequest("CIPR", estim.getCIPR());
        sendRequest("RCMBAF", estim.getRCMBAF());
        sendRequest("IE", estim.getIE());
        sendRequest("U", estim.getU());
        sendRequest("CPS", estim.getCPS());
        sendRequest("ETC", estim.getETC());
        sendRequest("HC", estim.getHC());
        sendRequest("CS", estim.getCS());
        sendRequest("DOTPC", estim.getDOTPC());
        sendRequest("UT", estim.getUT());

        sendRequest("FWTP", estim.getFWTP());
        sendRequest("AE", estim.getAE());
        sendRequest("OOPE", estim.getOOPE());
        sendRequest("LAC", estim.getLAC());
        sendRequest("M", estim.getM());
        sendRequest("SR", estim.getSR());
        sendRequest("PTS", estim.getPTS());
        sendRequest("DPL", estim.getDPL());
    }

    private static void sendRequest(String NomEst, int value) {
        String url = "http://" + MyApplication.IP_SERVER + ":" + MyApplication.PORTHTTP + "/readyreq/rel_update.php?";
        url += "a=Estim";
        url += "&b=ValEst = " + value;
        url += "&c=NomEst = '" + NomEst + "'";
        Utils.saveObject(ctx, url);
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}