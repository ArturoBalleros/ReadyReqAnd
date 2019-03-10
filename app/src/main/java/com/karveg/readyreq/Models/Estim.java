package com.karveg.readyreq.Models;

import android.app.AlertDialog;
import android.content.Context;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.karveg.readyreq.Activities.ActorActivity;
import com.karveg.readyreq.App.MyApplication;
import com.karveg.readyreq.Fragments.EstimFragment;
import com.karveg.readyreq.R;
import com.karveg.readyreq.Utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class Estim {
    private int DSR;
    private int RTII;
    private int EUE;
    private int CIPR;
    private int RCMBAF;
    private int IE;
    private int U;
    private int CPS;
    private int ETC;
    private int HC;
    private int CS;
    private int DOTPC;
    private int UT;

    private int FWTP;
    private int AE;
    private int OOPE;
    private int LAC;
    private int M;
    private int SR;
    private int PTS;
    private int DPL;

    private int UUCPSim;
    private int UUCPMed;
    private int UUCPMax;
    private int AWSim;
    private int AWMed;
    private int AWMax;

    public int getDSR() {
        return DSR;
    }

    public void setDSR(int DSR) {
        this.DSR = DSR;
    }

    public int getRTII() {
        return RTII;
    }

    public void setRTII(int RTII) {
        this.RTII = RTII;
    }

    public int getEUE() {
        return EUE;
    }

    public void setEUE(int EUE) {
        this.EUE = EUE;
    }

    public int getCIPR() {
        return CIPR;
    }

    public void setCIPR(int CIPR) {
        this.CIPR = CIPR;
    }

    public int getRCMBAF() {
        return RCMBAF;
    }

    public void setRCMBAF(int RCMBAF) {
        this.RCMBAF = RCMBAF;
    }

    public int getIE() {
        return IE;
    }

    public void setIE(int IE) {
        this.IE = IE;
    }

    public int getU() {
        return U;
    }

    public void setU(int u) {
        U = u;
    }

    public int getCPS() {
        return CPS;
    }

    public void setCPS(int CPS) {
        this.CPS = CPS;
    }

    public int getETC() {
        return ETC;
    }

    public void setETC(int ETC) {
        this.ETC = ETC;
    }

    public int getHC() {
        return HC;
    }

    public void setHC(int HC) {
        this.HC = HC;
    }

    public int getCS() {
        return CS;
    }

    public void setCS(int CS) {
        this.CS = CS;
    }

    public int getDOTPC() {
        return DOTPC;
    }

    public void setDOTPC(int DOTPC) {
        this.DOTPC = DOTPC;
    }

    public int getUT() {
        return UT;
    }

    public void setUT(int UT) {
        this.UT = UT;
    }

    public int getFWTP() {
        return FWTP;
    }

    public void setFWTP(int FWTP) {
        this.FWTP = FWTP;
    }

    public int getAE() {
        return AE;
    }

    public void setAE(int AE) {
        this.AE = AE;
    }

    public int getOOPE() {
        return OOPE;
    }

    public void setOOPE(int OOPE) {
        this.OOPE = OOPE;
    }

    public int getLAC() {
        return LAC;
    }

    public void setLAC(int LAC) {
        this.LAC = LAC;
    }

    public int getM() {
        return M;
    }

    public void setM(int m) {
        M = m;
    }

    public int getSR() {
        return SR;
    }

    public void setSR(int SR) {
        this.SR = SR;
    }

    public int getPTS() {
        return PTS;
    }

    public void setPTS(int PTS) {
        this.PTS = PTS;
    }

    public int getDPL() {
        return DPL;
    }

    public void setDPL(int DPL) {
        this.DPL = DPL;
    }

    public int getUUCPSim() {
        return UUCPSim;
    }

    public void setUUCPSim(int UUCPSim) {
        this.UUCPSim = UUCPSim;
    }

    public int getUUCPMed() {
        return UUCPMed;
    }

    public void setUUCPMed(int UUCPMed) {
        this.UUCPMed = UUCPMed;
    }

    public int getUUCPMax() {
        return UUCPMax;
    }

    public void setUUCPMax(int UUCPMax) {
        this.UUCPMax = UUCPMax;
    }

    public int getAWSim() {
        return AWSim;
    }

    public void setAWSim(int AWSim) {
        this.AWSim = AWSim;
    }

    public int getAWMed() {
        return AWMed;
    }

    public void setAWMed(int AWMed) {
        this.AWMed = AWMed;
    }

    public int getAWMax() {
        return AWMax;
    }

    public void setAWMax(int AWMax) {
        this.AWMax = AWMax;
    }

    public static void getEstim(final Context ctx, final AlertDialog progressDialog) {
        progressDialog.show();

        RequestQueue request;
        JsonObjectRequest jsonObjectRequest;

        request = Volley.newRequestQueue(ctx);

        String url = "http://" + MyApplication.IP_SERVER + ":8080/readyreq/estim_list.php";
        url = url.replace(" ", "%20");
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                JSONArray json;
                JSONObject jsonObject = null;
                json = response.optJSONArray("Resul");
                try {
                    jsonObject = json.getJSONObject(0);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (jsonObject.optString("a").equals("No1"))
                    Toast.makeText(ctx, R.string.error_read_file, Toast.LENGTH_SHORT).show();
                else if (jsonObject.optString("a").equals("No2"))
                    Toast.makeText(ctx, R.string.error_connect, Toast.LENGTH_SHORT).show();
                else if (jsonObject.optString("a").equals("No3"))
                    Toast.makeText(ctx, R.string.error_query, Toast.LENGTH_SHORT).show();
                else if (jsonObject.optString("a").equals("No4"))
                    Toast.makeText(ctx, R.string.error_empty_query, Toast.LENGTH_SHORT).show();
                else {
                    Estim e = new Estim();
                    try {
                        for (int i = 0; i < json.length(); i++) {
                            jsonObject = json.getJSONObject(i);
                            if (jsonObject.optString("NomEst").equals("DSR"))
                                e.setDSR(jsonObject.optInt("ValEst"));
                            if (jsonObject.optString("NomEst").equals("RTII"))
                                e.setRTII(jsonObject.optInt("ValEst"));
                            if (jsonObject.optString("NomEst").equals("EUE"))
                                e.setEUE(jsonObject.optInt("ValEst"));
                            if (jsonObject.optString("NomEst").equals("CIPR"))
                                e.setCIPR(jsonObject.optInt("ValEst"));

                            if (jsonObject.optString("NomEst").equals("RCMBAF"))
                                e.setRCMBAF(jsonObject.optInt("ValEst"));
                            if (jsonObject.optString("NomEst").equals("IE"))
                                e.setIE(jsonObject.optInt("ValEst"));
                            if (jsonObject.optString("NomEst").equals("U"))
                                e.setU(jsonObject.optInt("ValEst"));
                            if (jsonObject.optString("NomEst").equals("CPS"))
                                e.setCPS(jsonObject.optInt("ValEst"));

                            if (jsonObject.optString("NomEst").equals("ETC"))
                                e.setETC(jsonObject.optInt("ValEst"));
                            if (jsonObject.optString("NomEst").equals("HC"))
                                e.setHC(jsonObject.optInt("ValEst"));
                            if (jsonObject.optString("NomEst").equals("CS"))
                                e.setCS(jsonObject.optInt("ValEst"));
                            if (jsonObject.optString("NomEst").equals("DOTPC"))
                                e.setDOTPC(jsonObject.optInt("ValEst"));
                            if (jsonObject.optString("NomEst").equals("UT"))
                                e.setUT(jsonObject.optInt("ValEst"));


                            if (jsonObject.optString("NomEst").equals("FWTP"))
                                e.setFWTP(jsonObject.optInt("ValEst"));
                            if (jsonObject.optString("NomEst").equals("AE"))
                                e.setAE(jsonObject.optInt("ValEst"));
                            if (jsonObject.optString("NomEst").equals("OOPE"))
                                e.setOOPE(jsonObject.optInt("ValEst"));
                            if (jsonObject.optString("NomEst").equals("LAC"))
                                e.setLAC(jsonObject.optInt("ValEst"));

                            if (jsonObject.optString("NomEst").equals("M"))
                                e.setM(jsonObject.optInt("ValEst"));
                            if (jsonObject.optString("NomEst").equals("SR"))
                                e.setSR(jsonObject.optInt("ValEst"));
                            if (jsonObject.optString("NomEst").equals("PTS"))
                                e.setPTS(jsonObject.optInt("ValEst"));
                            if (jsonObject.optString("NomEst").equals("DPL"))
                                e.setDPL(jsonObject.optInt("ValEst"));


                            if (jsonObject.optString("NomEst").equals("UUCPSim"))
                                e.setUUCPSim(jsonObject.optInt("ValEst"));
                            if (jsonObject.optString("NomEst").equals("UUCPMed"))
                                e.setUUCPMed(jsonObject.optInt("ValEst"));
                            if (jsonObject.optString("NomEst").equals("UUCPMax"))
                                e.setUUCPMax(jsonObject.optInt("ValEst"));


                            if (jsonObject.optString("NomEst").equals("AWSim"))
                                e.setAWSim(jsonObject.optInt("ValEst"));
                            if (jsonObject.optString("NomEst").equals("AWMed"))
                                e.setAWMed(jsonObject.optInt("ValEst"));
                            if (jsonObject.optString("NomEst").equals("AWMax"))
                                e.setAWMax(jsonObject.optInt("ValEst"));
                        }
                    } catch (JSONException ex) {
                        ex.printStackTrace();
                    }
                    EstimFragment.setValueEstim(e);
                }
                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ctx, "error: " + error.toString(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }) {
            @Override
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                try {
                    String jsonString = new String(response.data,
                            HttpHeaderParser.parseCharset(response.headers, PROTOCOL_CHARSET));
                    JSONObject result = null;
                    if (jsonString != null && jsonString.length() > 0)
                        result = new JSONObject(jsonString);
                    return Response.success(result,
                            HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    return Response.error(new ParseError(e));
                } catch (JSONException je) {
                    return Response.error(new ParseError(je));
                }
            }
        };
        request.add(jsonObjectRequest);
    }
}
