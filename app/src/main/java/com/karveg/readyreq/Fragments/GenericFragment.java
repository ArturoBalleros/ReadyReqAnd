package com.karveg.readyreq.Fragments;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.karveg.readyreq.Activities.GroupActivity;
import com.karveg.readyreq.Activities.ListActivity;
import com.karveg.readyreq.Activities.PackageActivity;
import com.karveg.readyreq.Activities.ReqFunActivity;
import com.karveg.readyreq.Activities.ReqInfoActivity;
import com.karveg.readyreq.Activities.ReqNFunActivity;
import com.karveg.readyreq.Adapters.GenericAdapter;
import com.karveg.readyreq.App.MyApplication;
import com.karveg.readyreq.Models.Actor;
import com.karveg.readyreq.Models.Generic;
import com.karveg.readyreq.Models.Objective;
import com.karveg.readyreq.Models.ReqFun;
import com.karveg.readyreq.Models.ReqInfo;
import com.karveg.readyreq.Models.ReqNFun;
import com.karveg.readyreq.Activities.ObjecActivity;
import com.karveg.readyreq.R;
import com.karveg.readyreq.Utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class GenericFragment extends Fragment {

    private static List<Generic> objects;
    private static RecyclerView mRecyclerView;
    private static RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayout;
    private FloatingActionButton fab;

    private static Context ctx;
    private static FragmentActivity act;

    private static AlertDialog progressDialog;
    private RequestQueue request;
    private JsonObjectRequest jsonObjectRequest;
    private static int mode;
    private String filePHP = "No";
    private String buscar = "No";

    public GenericFragment() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public GenericFragment(int mode) {
        this.mode = mode;
    }

    @SuppressLint("ValidFragment")
    public GenericFragment(int mode, String filePHP, String buscar) {
        this.mode = mode;
        this.filePHP = filePHP;
        this.buscar = buscar;
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
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        ctx = getContext();
        act=getActivity();

        request = Volley.newRequestQueue(ctx);
        progressDialog = CreateProgressDialog().create();


        objects = new ArrayList<>();
        if (filePHP.equals("No") && buscar.equals("No"))
            getObjects();
        else
            getObjectsSearch();

        //Metodos necesarios
        bindUI(view);
        configRecyclerView();
        Events();

        return view;
    }

    private void bindUI(View view) {
        mRecyclerView = view.findViewById(R.id.recyclerViewGroup);
        fab = view.findViewById(R.id.fabAdd);
    }

    private void configRecyclerView() {
        mLayout = new LinearLayoutManager(getContext());

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mRecyclerView.setLayoutManager(mLayout);

        updateRecyclerView();
    }

    public static void updateRecyclerView() {
        mAdapter = new GenericAdapter(objects, R.layout.cardview_default, ctx, mode, progressDialog, true, new GenericAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Generic object, View v) {
                goActivity(object.getId());
            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }

    public void Events() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goActivity(MyApplication.NOTHING);
            }
        });
    }

    private static void goActivity(int Id) {
        Context c = mRecyclerView.getContext();
        Intent i = new Intent();
        if (mode == MyApplication.GRUPO)
            i = new Intent(c.getApplicationContext(), GroupActivity.class);
        if (mode == MyApplication.PAQUETES)
            i = new Intent(c.getApplicationContext(), PackageActivity.class);
        if (mode == MyApplication.ACTORES)
            i = new Intent(c.getApplicationContext(), ActorActivity.class);
        if (mode == MyApplication.OBJETIVOS)
            i = new Intent(c.getApplicationContext(), ObjecActivity.class);
        if (mode == MyApplication.REQ_NO_FUN)
            i = new Intent(c.getApplicationContext(), ReqNFunActivity.class);
        if (mode == MyApplication.REQ_INFO)
            i = new Intent(c.getApplicationContext(), ReqInfoActivity.class);
        if (mode == MyApplication.REQ_FUNC)
            i = new Intent(c.getApplicationContext(), ReqFunActivity.class);

        if (Id != MyApplication.NOTHING)
            i.putExtra("code", Id);

        c.startActivity(i);
        act.finish();
    }

    private AlertDialog.Builder CreateProgressDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.progress_dialog, null);
        dialogBuilder.setView(dialogView);
        dialogBuilder.setCancelable(false);
        return dialogBuilder;
    }

    private void getObjects() {

        progressDialog.show();
        String url = Utils.getUrlList(mode);

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
                    Toast.makeText(getContext(), R.string.error_read_file, Toast.LENGTH_SHORT).show();
                else if (jsonObject.optString("a").equals("No2"))
                    Toast.makeText(getContext(), R.string.error_connect, Toast.LENGTH_SHORT).show();
                else if (jsonObject.optString("a").equals("No3"))
                    Toast.makeText(getContext(), R.string.error_query, Toast.LENGTH_SHORT).show();
                else if (jsonObject.optString("a").equals("No4"))
                    Toast.makeText(getContext(), R.string.error_empty_query, Toast.LENGTH_SHORT).show();
                else {
                    Generic d;
                    try {
                        for (int i = 0; i < json.length(); i++) {
                            jsonObject = json.getJSONObject(i);
                            d = new Generic(jsonObject.optInt("Id"), jsonObject.optString("Nombre"), Utils.imageSelect(mode));
                            objects.add(d);
                        }
                        updateRecyclerView();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
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

    private void getObjectsSearch() {

        progressDialog.show();

        String url = "";
        url = "http://" + MyApplication.IP_SERVER + ":8080/readyreq/" + filePHP + ".php?";
        url += "a=" + buscar;
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
                    Toast.makeText(getContext(), R.string.error_empty_param, Toast.LENGTH_SHORT).show();
                else if (jsonObject.optString("a").equals("No2"))
                    Toast.makeText(getContext(), R.string.error_read_file, Toast.LENGTH_SHORT).show();
                else if (jsonObject.optString("a").equals("No3"))
                    Toast.makeText(getContext(), R.string.error_connect, Toast.LENGTH_SHORT).show();
                else if (jsonObject.optString("a").equals("No4"))
                    Toast.makeText(getContext(), R.string.error_query, Toast.LENGTH_SHORT).show();
                else if (jsonObject.optString("a").equals("No5"))
                    Toast.makeText(getContext(), R.string.error_empty_query, Toast.LENGTH_SHORT).show();
                else {
                    Generic d;
                    try {
                        for (int i = 0; i < json.length(); i++) {
                            jsonObject = json.getJSONObject(i);
                            d = new Generic(jsonObject.optInt("Id"), jsonObject.optString("Nombre"), Utils.imageSelect(mode));
                            objects.add(d);
                        }
                        updateRecyclerView();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
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
}
