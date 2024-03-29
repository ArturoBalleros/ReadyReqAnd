/*
   Autor: Arturo Balleros Albillo
 */
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
import com.karveg.readyreq.Activities.ReqInfoActivity;
import com.karveg.readyreq.App.MyApplication;
import com.karveg.readyreq.R;
import com.karveg.readyreq.Utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class ReqInfo extends ObjEstandar implements Serializable {

    private int TimeMed;
    private int TimeMax;
    private int OcuMed;
    private int OcuMax;
    private List<Generic> Requirements = new ArrayList<>();
    private List<Generic> DatEspec = new ArrayList<>();

    public ReqInfo() {
    }

    public int getTimeMed() {
        return TimeMed;
    }

    public void setTimeMed(int timeMed) {
        TimeMed = timeMed;
    }

    public int getTimeMax() {
        return TimeMax;
    }

    public void setTimeMax(int timeMax) {
        TimeMax = timeMax;
    }

    public int getOcuMed() {
        return OcuMed;
    }

    public void setOcuMed(int ocuMed) {
        OcuMed = ocuMed;
    }

    public int getOcuMax() {
        return OcuMax;
    }

    public void setOcuMax(int ocuMax) {
        OcuMax = ocuMax;
    }

    public List<Generic> getRequirements() {
        return Requirements;
    }

    public void setRequirements(List<Generic> requirements) {
        Requirements = requirements;
    }

    public List<Generic> getDatEspec() {
        return DatEspec;
    }

    public void setDatEspec(List<Generic> datEspec) {
        DatEspec = datEspec;
    }

    public static void getReque(final Context ctx, int intCode, final AlertDialog progressDialog) {
        progressDialog.show();

        RequestQueue request;
        JsonObjectRequest jsonObjectRequest;

        request = Volley.newRequestQueue(ctx);

        String url = MyApplication.HTTP + "://" + MyApplication.IP_SERVER + ":" + MyApplication.PORTHTTP + "/readyreq/reqinfo_search.php?";
        url += "a=" + intCode;
        url = url.replace(" ", "%20");
        jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
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
                    Toast.makeText(ctx, R.string.error_empty_param, Toast.LENGTH_SHORT).show();
                else if (jsonObject.optString("a").equals("No2"))
                    Toast.makeText(ctx, R.string.error_read_file, Toast.LENGTH_SHORT).show();
                else if (jsonObject.optString("a").equals("No3"))
                    Toast.makeText(ctx, R.string.error_connect, Toast.LENGTH_SHORT).show();
                else if (jsonObject.optString("a").equals("No4"))
                    Toast.makeText(ctx, R.string.error_query, Toast.LENGTH_SHORT).show();
                else if (jsonObject.optString("a").equals("No5"))
                    Toast.makeText(ctx, R.string.error_empty_query, Toast.LENGTH_SHORT).show();
                else {
                    ReqInfo r = new ReqInfo();
                    r.setId(jsonObject.optInt("Id"));
                    r.setName(jsonObject.optString("Nombre"));
                    r.setVersion(jsonObject.optDouble("Version"));
                    r.setFech(Utils.StringToDate(jsonObject.optString("Fecha"), true));
                    r.setDescription(jsonObject.optString("Descripcion"));
                    r.setTimeMed(jsonObject.optInt("TiemMed"));
                    r.setTimeMax(jsonObject.optInt("TiemMax"));
                    r.setOcuMed(jsonObject.optInt("OcuMed"));
                    r.setOcuMax(jsonObject.optInt("OcuMax"));
                    r.setPrior(jsonObject.optInt("Prioridad"));
                    r.setUrge(jsonObject.optInt("Urgencia"));
                    r.setEsta(jsonObject.optInt("Estabilidad"));
                    if (jsonObject.optInt("Estado") == 1) r.setState(true);
                    else r.setState(false);
                    r.setCategory(jsonObject.optInt("Categoria"));
                    r.setCommentary(jsonObject.optString("Comentario"));

                    json = response.optJSONArray("Resul2");
                    if (json != null)
                        r.setAutors(Utils.JSONArrayToListGeneric(json, MyApplication.GRUPO));
                    else r.setAutors(new ArrayList<Generic>());

                    json = response.optJSONArray("Resul3");
                    if (json != null)
                        r.setSources(Utils.JSONArrayToListGeneric(json, MyApplication.GRUPO));
                    else r.setSources(new ArrayList<Generic>());

                    json = response.optJSONArray("Resul4");
                    if (json != null)
                        r.setObjetives(Utils.JSONArrayToListGeneric(json, MyApplication.OBJETIVOS));
                    else r.setObjetives(new ArrayList<Generic>());

                    json = response.optJSONArray("Resul5");
                    if (json != null)
                        r.setRequirements(Utils.JSONArrayToListGeneric(json, MyApplication.REQU));
                    else r.setRequirements(new ArrayList<Generic>());

                    json = response.optJSONArray("Resul6");
                    if (json != null)
                        r.setDatEspec(Utils.JSONArrayToListGeneric(json, MyApplication.NOTHING));
                    else r.setDatEspec(new ArrayList<Generic>());

                    ReqInfoActivity.setValueReque(r);
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

    public static void getIdReque(final Context ctx, String Name, final AlertDialog progressDialog) {
        progressDialog.show();

        RequestQueue request;
        JsonObjectRequest jsonObjectRequest;

        request = Volley.newRequestQueue(ctx);

        String url = MyApplication.HTTP + "://" + MyApplication.IP_SERVER + ":" + MyApplication.PORTHTTP + "/readyreq/reqinfo_id.php?";
        url += "a=" + Name;
        url = url.replace(" ", "%20");
        jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
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
                    Toast.makeText(ctx, R.string.error_empty_param, Toast.LENGTH_SHORT).show();
                else if (jsonObject.optString("a").equals("No2"))
                    Toast.makeText(ctx, R.string.error_read_file, Toast.LENGTH_SHORT).show();
                else if (jsonObject.optString("a").equals("No3"))
                    Toast.makeText(ctx, R.string.error_connect, Toast.LENGTH_SHORT).show();
                else if (jsonObject.optString("a").equals("No4"))
                    Toast.makeText(ctx, R.string.error_query, Toast.LENGTH_SHORT).show();
                else if (jsonObject.optString("a").equals("No5"))
                    Toast.makeText(ctx, R.string.error_empty_query, Toast.LENGTH_SHORT).show();
                else {
                    ReqInfoActivity.saveObjects(ctx, jsonObject.optInt("Id"));
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