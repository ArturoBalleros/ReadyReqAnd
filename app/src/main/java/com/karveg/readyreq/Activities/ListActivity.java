/*
   Autor: Arturo Balleros Albillo
 */
package com.karveg.readyreq.Activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
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
import com.karveg.readyreq.Adapters.GenericAdapter;
import com.karveg.readyreq.App.MyApplication;
import com.karveg.readyreq.Models.Actor;
import com.karveg.readyreq.Models.Generic;
import com.karveg.readyreq.Models.Objective;
import com.karveg.readyreq.Models.ReqFun;
import com.karveg.readyreq.Models.ReqInfo;
import com.karveg.readyreq.Models.ReqNFun;
import com.karveg.readyreq.R;
import com.karveg.readyreq.Utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private int mode;
    private int flagTab;
    private Actor actor;
    private Objective objective;
    private ReqInfo reqinfo;
    private ReqNFun reqnfun;
    private ReqFun reqfun;

    private List<Generic> objects;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayout;
    private FloatingActionButton fab;
    private AlertDialog progressDialog;
    private ImageView toolbarIcon;
    private TextView toolbarTitle;
    private Toolbar toolbar;
    private RequestQueue request;
    private JsonObjectRequest jsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        //Bundle
        Bundle bu = getIntent().getExtras();
        if (bu != null) {
            if (bu.getInt("mode") != MyApplication.NOTHING) mode = bu.getInt("mode");
            if (bu.getInt("flagTab") != MyApplication.NOTHING) flagTab = bu.getInt("flagTab");

            if (mode == MyApplication.ACTORES && bu.getSerializable("actor") != null)
                actor = (Actor) bu.getSerializable("actor");
            if (mode == MyApplication.OBJETIVOS && bu.getSerializable("objec") != null)
                objective = (Objective) bu.getSerializable("objec");
            if (mode == MyApplication.REQ_INFO && bu.getSerializable("reqinfo") != null)
                reqinfo = (ReqInfo) bu.getSerializable("reqinfo");
            if (mode == MyApplication.REQ_NO_FUN && bu.getSerializable("reqnfun") != null)
                reqnfun = (ReqNFun) bu.getSerializable("reqnfun");
            if (mode == MyApplication.REQ_FUNC && bu.getSerializable("reqfun") != null)
                reqfun = (ReqFun) bu.getSerializable("reqfun");
        }

        request = Volley.newRequestQueue(getApplicationContext());
        progressDialog = CreateProgressDialog().create();
        bindUI();
        setToolBar();
        getObjects();
        configRecyclerView();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addObjects();
            }
        });
    }

    private void configRecyclerView() {
        mLayout = new LinearLayoutManager(this);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mRecyclerView.setLayoutManager(mLayout);
    }

    private void updateRecyclerView() {
        mAdapter = new GenericAdapter(objects, R.layout.cardview_default, this, mode, progressDialog, false, new GenericAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Generic object, View v) {
                object.setSelected(!object.isSelected());
                v.setBackgroundColor(object.isSelected() ? Color.CYAN : Color.parseColor("#E2E2E2"));
            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }

    private void bindUI() {
        toolbar = findViewById(R.id.toolbar);
        mRecyclerView = findViewById(R.id.recyclerViewGroup);
        fab = findViewById(R.id.fabAdd);
        toolbarIcon = findViewById(R.id.toolbarIcon);
        toolbarTitle = findViewById(R.id.toolbarTitle);
    }

    private AlertDialog.Builder CreateProgressDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.progress_dialog, null);
        dialogBuilder.setView(dialogView);
        dialogBuilder.setCancelable(false);
        return dialogBuilder;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        goBack();
        return super.onKeyDown(keyCode, event);
    }

    private void goBack() {
        Intent i = new Intent();
        if (mode == MyApplication.ACTORES) {
            i = new Intent(getApplicationContext(), ActorActivity.class);
            i.putExtra("flagTab", flagTab);
            i.putExtra("actor", actor);
        }
        if (mode == MyApplication.OBJETIVOS) {
            i = new Intent(getApplicationContext(), ObjecActivity.class);
            i.putExtra("flagTab", flagTab);
            i.putExtra("objec", objective);
        }
        if (mode == MyApplication.REQ_FUNC) {
            i = new Intent(getApplicationContext(), ReqFunActivity.class);
            i.putExtra("flagTab", flagTab);
            i.putExtra("reqfun", reqfun);
        }
        if (mode == MyApplication.REQ_NO_FUN) {
            i = new Intent(getApplicationContext(), ReqNFunActivity.class);
            i.putExtra("flagTab", flagTab);
            i.putExtra("reqnfun", reqnfun);
        }
        if (mode == MyApplication.REQ_INFO) {
            i = new Intent(getApplicationContext(), ReqInfoActivity.class);
            i.putExtra("flagTab", flagTab);
            i.putExtra("reqinfo", reqinfo);
        }
        startActivity(i);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                goBack();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarTitle.setText(getTitleToolbar());
        toolbarIcon.setImageResource(getImageToolbar());
    }

    private String getTitleToolbar() {
        String title = "";
        if (flagTab == MyApplication.AUTH) title = getResources().getString(R.string.autho);
        if (flagTab == MyApplication.SOUR) title = getResources().getString(R.string.sourc);
        if (flagTab == MyApplication.OBJE) title = getResources().getString(R.string.objec);
        if (flagTab == MyApplication.REQU) title = getResources().getString(R.string.reque);
        if (flagTab == MyApplication.ACTO) title = getResources().getString(R.string.actor);
        return title;
    }

    private int getImageToolbar() {
        int image = 0;
        if (flagTab == MyApplication.AUTH) image = R.drawable.ic_grupo;
        if (flagTab == MyApplication.SOUR) image = R.drawable.ic_grupo;
        if (flagTab == MyApplication.OBJE) image = R.drawable.ic_objet;
        if (flagTab == MyApplication.REQU) image = R.drawable.ic_rf;
        if (flagTab == MyApplication.ACTO) image = R.drawable.ic_actor;
        return image;
    }

    private void getObjects() {
        String url = "";

        progressDialog.show();
        url = MyApplication.HTTP + "://" + MyApplication.IP_SERVER + ":" + MyApplication.PORTHTTP + "/readyreq/rel_list.php?";
        url += "a=" + mode + "&";
        url += "b=" + flagTab + "&";
        if (mode == MyApplication.ACTORES) url += "c=" + actor.getId();
        if (mode == MyApplication.OBJETIVOS) url += "c=" + objective.getId();
        if (mode == MyApplication.REQ_FUNC) url += "c=" + reqfun.getId();
        if (mode == MyApplication.REQ_NO_FUN) url += "c=" + reqnfun.getId();
        if (mode == MyApplication.REQ_INFO) url += "c=" + reqinfo.getId();

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
                    Toast.makeText(getApplicationContext(), R.string.error_empty_param, Toast.LENGTH_SHORT).show();
                else if (jsonObject.optString("a").equals("No2"))
                    Toast.makeText(getApplicationContext(), R.string.error_read_file, Toast.LENGTH_SHORT).show();
                else if (jsonObject.optString("a").equals("No3"))
                    Toast.makeText(getApplicationContext(), R.string.error_connect, Toast.LENGTH_SHORT).show();
                else if (jsonObject.optString("a").equals("No4"))
                    Toast.makeText(getApplicationContext(), R.string.error_query, Toast.LENGTH_SHORT).show();
                else if (jsonObject.optString("a").equals("No5"))
                    Toast.makeText(getApplicationContext(), R.string.error_empty_query, Toast.LENGTH_SHORT).show();
                else {
                    if (flagTab == MyApplication.AUTH || flagTab == MyApplication.SOUR)
                        objects = Utils.JSONArrayToListGeneric(json, MyApplication.GRUPO);
                    if (flagTab == MyApplication.OBJE)
                        objects = Utils.JSONArrayToListGeneric(json, MyApplication.OBJETIVOS);
                    if (flagTab == MyApplication.ACTO)
                        objects = Utils.JSONArrayToListGeneric(json, MyApplication.ACTORES);
                    if (flagTab == MyApplication.REQU)
                        objects = Utils.JSONArrayToListGeneric(json, MyApplication.REQU);
                    updateRecyclerView();
                }
                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ListActivity.this, "error: " + error.toString(), Toast.LENGTH_SHORT).show();
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
                    return Response.success(result, HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    return Response.error(new ParseError(e));
                } catch (JSONException je) {
                    return Response.error(new ParseError(je));
                }
            }
        };

        request.add(jsonObjectRequest);
    }

    private void addObjects() {
        for (Generic g : objects)
            if (g.isSelected()) {
                String url = MyApplication.HTTP + "://" + MyApplication.IP_SERVER + ":" + MyApplication.PORTHTTP + "/readyreq/rel_create.php?";
                progressDialog.show();
                if (mode == MyApplication.ACTORES) {
                    if (flagTab == MyApplication.AUTH) {
                        url += "a=ActAuto(idautor,idact)&";
                        url += "b=" + g.getId() + "," + actor.getId();
                        if (actor.getId() != MyApplication.NOTHING)
                            Utils.saveObject(getApplicationContext(), url);
                        actor.getAutors().add(g);
                    }
                    if (flagTab == MyApplication.SOUR) {
                        url += "a=ActFuen(idfuen,idact)&";
                        url += "b=" + g.getId() + "," + actor.getId();
                        if (actor.getId() != MyApplication.NOTHING)
                            Utils.saveObject(getApplicationContext(), url);
                        actor.getSources().add(g);
                    }
                }
                if (mode == MyApplication.OBJETIVOS) {
                    if (flagTab == MyApplication.AUTH) {
                        url += "a=ObjAuto(idautor,idobj)&";
                        url += "b=" + g.getId() + "," + objective.getId();
                        if (objective.getId() != MyApplication.NOTHING)
                            Utils.saveObject(getApplicationContext(), url);
                        objective.getAutors().add(g);
                    }
                    if (flagTab == MyApplication.SOUR) {
                        url += "a=objfuen(idfuen,idobj)&";
                        url += "b=" + g.getId() + "," + objective.getId();
                        if (objective.getId() != MyApplication.NOTHING)
                            Utils.saveObject(getApplicationContext(), url);
                        objective.getSources().add(g);
                    }
                    if (flagTab == MyApplication.OBJE) {
                        url += "a=ObjSubobj(idsubobj,idobj)&";
                        url += "b=" + g.getId() + "," + objective.getId();
                        if (objective.getId() != MyApplication.NOTHING)
                            Utils.saveObject(getApplicationContext(), url);
                        objective.getObjetives().add(g);
                    }
                }
                if (mode == MyApplication.REQ_FUNC) {
                    if (flagTab == MyApplication.AUTH) {
                        url += "a=ReqAuto(idautor,idreq)&";
                        url += "b=" + g.getId() + "," + reqfun.getId();
                        if (reqfun.getId() != MyApplication.NOTHING)
                            Utils.saveObject(getApplicationContext(), url);
                        reqfun.getAutors().add(g);
                    }
                    if (flagTab == MyApplication.SOUR) {
                        url += "a=ReqFuen(idfuen,idreq)&";
                        url += "b=" + g.getId() + "," + reqfun.getId();
                        if (reqfun.getId() != MyApplication.NOTHING)
                            Utils.saveObject(getApplicationContext(), url);
                        reqfun.getSources().add(g);
                    }
                    if (flagTab == MyApplication.OBJE) {
                        url += "a=ReqObj(idobj,idreq)&";
                        url += "b=" + g.getId() + "," + reqfun.getId();
                        if (reqfun.getId() != MyApplication.NOTHING)
                            Utils.saveObject(getApplicationContext(), url);
                        reqfun.getObjetives().add(g);
                    }
                    if (flagTab == MyApplication.REQU) {
                        url += "a=ReqReqR(idreqr,tiporeq,idreq)&";
                        url += "b=" + g.getId() + "," + Utils.deterTipoReq(g.getImage()) + "," + reqfun.getId();
                        if (reqfun.getId() != MyApplication.NOTHING)
                            Utils.saveObject(getApplicationContext(), url);
                        reqfun.getRequirements().add(g);
                    }
                    if (flagTab == MyApplication.ACTO) {
                        url += "a=ReqAct(idact,idreq)&";
                        url += "b=" + g.getId() + "," + reqfun.getId();
                        if (reqfun.getId() != MyApplication.NOTHING)
                            Utils.saveObject(getApplicationContext(), url);
                        reqfun.getActors().add(g);
                    }
                }
                if (mode == MyApplication.REQ_NO_FUN) {
                    if (flagTab == MyApplication.AUTH) {
                        url += "a=ReqNAuto(idautor,idreq)&";
                        url += "b=" + g.getId() + "," + reqnfun.getId();
                        if (reqnfun.getId() != MyApplication.NOTHING)
                            Utils.saveObject(getApplicationContext(), url);
                        reqnfun.getAutors().add(g);
                    }
                    if (flagTab == MyApplication.SOUR) {
                        url += "a=ReqNFuen(idfuen,idreq)&";
                        url += "b=" + g.getId() + "," + reqnfun.getId();
                        if (reqnfun.getId() != MyApplication.NOTHING)
                            Utils.saveObject(getApplicationContext(), url);
                        reqnfun.getSources().add(g);
                    }
                    if (flagTab == MyApplication.OBJE) {
                        url += "a=ReqNObj(idobj,idreq)&";
                        url += "b=" + g.getId() + "," + reqnfun.getId();
                        if (reqnfun.getId() != MyApplication.NOTHING)
                            Utils.saveObject(getApplicationContext(), url);
                        reqnfun.getObjetives().add(g);
                    }
                    if (flagTab == MyApplication.REQU) {
                        url += "a=ReqNReqR(idreqr,tiporeq,idreq)&";
                        url += "b=" + g.getId() + "," + Utils.deterTipoReq(g.getImage()) + "," + reqnfun.getId();
                        if (reqnfun.getId() != MyApplication.NOTHING)
                            Utils.saveObject(getApplicationContext(), url);
                        reqnfun.getRequirements().add(g);
                    }
                }
                if (mode == MyApplication.REQ_INFO) {
                    if (flagTab == MyApplication.AUTH) {
                        url += "a=ReqIAuto(idautor,idreq)&";
                        url += "b=" + g.getId() + "," + reqinfo.getId();
                        if (reqinfo.getId() != MyApplication.NOTHING)
                            Utils.saveObject(getApplicationContext(), url);
                        reqinfo.getAutors().add(g);
                    }
                    if (flagTab == MyApplication.SOUR) {
                        url += "a=ReqIFuen(idfuen,idreq)&";
                        url += "b=" + g.getId() + "," + reqinfo.getId();
                        if (reqinfo.getId() != MyApplication.NOTHING)
                            Utils.saveObject(getApplicationContext(), url);
                        reqinfo.getSources().add(g);
                    }
                    if (flagTab == MyApplication.OBJE) {
                        url += "a=ReqIObj(idobj,idreq)&";
                        url += "b=" + g.getId() + "," + reqinfo.getId();
                        if (reqinfo.getId() != MyApplication.NOTHING)
                            Utils.saveObject(getApplicationContext(), url);
                        reqinfo.getObjetives().add(g);
                    }
                    if (flagTab == MyApplication.REQU) {
                        url += "a=ReqIReqR(idreqr,tiporeq,idreq)&";
                        url += "b=" + g.getId() + "," + Utils.deterTipoReq(g.getImage()) + "," + reqinfo.getId();
                        if (reqinfo.getId() != MyApplication.NOTHING)
                            Utils.saveObject(getApplicationContext(), url);
                        reqinfo.getRequirements().add(g);
                    }
                }
                progressDialog.dismiss();
                goBack();
            }
    }
}