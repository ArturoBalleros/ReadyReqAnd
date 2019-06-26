/*
   Autor: Arturo Balleros Albillo
 */
package com.karveg.readyreq.Fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
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
import com.karveg.readyreq.App.MyApplication;
import com.karveg.readyreq.R;
import com.karveg.readyreq.Utils.SharedP;
import com.karveg.readyreq.Utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class ConfigFragment extends Fragment {

    private RequestQueue request;
    private JsonObjectRequest jsonObjectRequest;
    private AlertDialog progressDialog;
    private SharedPreferences prefs;

    private EditText editTextIpServer;
    private EditText editTextIpServerMySQL;
    private EditText editTextUser;
    private EditText editTextPass;
    private EditText editTextDatabase;
    private EditText editTextPort;
    private EditText editTextPortHTTP;
    private Switch switchHTTP;
    private Button buttonConnect;


    public ConfigFragment() {
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
        View view = inflater.inflate(R.layout.fragment_config, container, false);

        //para poder enviar peticiones al server
        request = Volley.newRequestQueue(getContext());

        //Abro las SharedPreferences
        prefs = getActivity().getSharedPreferences("Preferences", Context.MODE_PRIVATE);

        progressDialog = CreateProgressDialog().create();

        bindUI(view);
        getDataSharedP();

        buttonConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webService();
            }
        });

        return view;
    }

    private void webService() {
        String url = "";
        final String http = switchHTTP.isChecked() ? "https" : "http";

        progressDialog.show();
        url = http + "://" + editTextIpServer.getText().toString() + ":" + editTextPortHTTP.getText().toString() + "/readyreq/conf_frag_connection.php?";
        if (editTextIpServerMySQL.getText().toString().isEmpty())
            url += "a=" + editTextIpServer.getText().toString() + "&";
        else
            url += "a=" + editTextIpServerMySQL.getText().toString() + "&";
        url += "b=" + editTextUser.getText().toString() + "&";
        url += "c=" + Utils.encrypt(editTextPass.getText().toString()) + "&";
        url += "d=" + editTextDatabase.getText().toString() + "&";
        url += "e=" + editTextPort.getText().toString();

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
                if (jsonObject.optString("a").equals("Si")) {

                    SharedP.saveIPSer(prefs, editTextIpServer.getText().toString());
                    MyApplication.IP_SERVER = editTextIpServer.getText().toString();

                    //Si la ip del servidor mysql esta vacia
                    if (editTextIpServerMySQL.getText().toString().isEmpty()) {
                        SharedP.saveIPSerSQL(prefs, editTextIpServer.getText().toString());
                        editTextIpServerMySQL.setText(editTextIpServer.getText().toString());
                    } else
                        SharedP.saveIPSerSQL(prefs, editTextIpServerMySQL.getText().toString());
                    MyApplication.IP_SERVER_SQL = editTextIpServerMySQL.getText().toString();

                    SharedP.saveUser(prefs, editTextUser.getText().toString());
                    MyApplication.USER = editTextUser.getText().toString();

                    SharedP.savePass(prefs, editTextPass.getText().toString());
                    MyApplication.PASS = editTextPass.getText().toString();

                    SharedP.saveDatabase(prefs, editTextDatabase.getText().toString());
                    MyApplication.DATABASE = editTextDatabase.getText().toString();

                    SharedP.savePort(prefs, Integer.parseInt(editTextPort.getText().toString()));
                    MyApplication.PORT = Integer.parseInt(editTextPort.getText().toString());

                    SharedP.savePortHttp(prefs, Integer.parseInt(editTextPortHTTP.getText().toString()));
                    MyApplication.PORTHTTP = Integer.parseInt(editTextPortHTTP.getText().toString());

                    SharedP.saveHttp(prefs, http);
                    MyApplication.HTTP = http;

                    Toast.makeText(getContext(), R.string.success, Toast.LENGTH_SHORT).show();

                } else if (jsonObject.optString("a").equals("No1"))
                    Toast.makeText(getContext(), R.string.error_empty_param, Toast.LENGTH_SHORT).show();
                else if (jsonObject.optString("a").equals("No2"))
                    Toast.makeText(getContext(), R.string.conf_frag_conn, Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getContext(), R.string.conf_frag_error_1, Toast.LENGTH_SHORT).show();

                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "error: " + error.toString(), Toast.LENGTH_SHORT).show();
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

    private void bindUI(View view) {
        editTextIpServer = view.findViewById(R.id.editTextIpServer);
        editTextIpServerMySQL = view.findViewById(R.id.editTextIpServerMySQL);
        editTextUser = view.findViewById(R.id.editTextUser);
        editTextPass = view.findViewById(R.id.editTextPass);
        editTextDatabase = view.findViewById(R.id.editTextDatabase);
        editTextPort = view.findViewById(R.id.editTextPort);
        editTextPortHTTP = view.findViewById(R.id.editTextPortHttp);
        switchHTTP = view.findViewById(R.id.switchHTTP);
        buttonConnect = view.findViewById(R.id.buttonConnect);
    }

    private void getDataSharedP() {
        editTextIpServer.setText(MyApplication.IP_SERVER);
        editTextIpServerMySQL.setText(MyApplication.IP_SERVER_SQL);
        editTextUser.setText(MyApplication.USER);
        editTextPass.setText(MyApplication.PASS);
        editTextDatabase.setText(MyApplication.DATABASE);
        editTextPort.setText(MyApplication.PORT + "");
        editTextPortHTTP.setText(MyApplication.PORTHTTP + "");
        switchHTTP.setChecked((MyApplication.HTTP.equals("http")) ? false : true);
    }

    private AlertDialog.Builder CreateProgressDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.progress_dialog, null);
        dialogBuilder.setView(dialogView);
        dialogBuilder.setCancelable(false);
        return dialogBuilder;
    }
}