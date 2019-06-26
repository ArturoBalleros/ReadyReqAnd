/*
   Autor: Arturo Balleros Albillo
 */
package com.karveg.readyreq.Fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.karveg.readyreq.Activities.ListActivity;
import com.karveg.readyreq.Adapters.AutoAdapter;
import com.karveg.readyreq.App.MyApplication;
import com.karveg.readyreq.Models.Actor;
import com.karveg.readyreq.Models.Generic;
import com.karveg.readyreq.Models.Objective;
import com.karveg.readyreq.Models.ReqFun;
import com.karveg.readyreq.Models.ReqInfo;
import com.karveg.readyreq.Models.ReqNFun;
import com.karveg.readyreq.R;

import java.util.List;

public class AuthFragment extends Fragment {

    private static List<Generic> objects;
    private static RecyclerView mRecyclerView;
    private static RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayout;
    private FloatingActionButton fab;

    private static AlertDialog progressDialog;

    private static Context ctx;
    private static int mode;
    private static Actor actor;
    private static Objective objective;
    private static ReqFun reqfun;
    private static ReqNFun reqnfun;
    private static ReqInfo reqinfo;

    public AuthFragment() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public AuthFragment(int mode, Actor actor) {
        this.mode = mode;
        this.actor = actor;
    }

    @SuppressLint("ValidFragment")
    public AuthFragment(int mode, Objective objective) {
        this.mode = mode;
        this.objective = objective;
    }

    @SuppressLint("ValidFragment")
    public AuthFragment(int mode, ReqFun reqfun) {
        this.mode = mode;
        this.reqfun = reqfun;
    }

    @SuppressLint("ValidFragment")
    public AuthFragment(int mode, ReqNFun reqnfun) {
        this.mode = mode;
        this.reqnfun = reqnfun;
    }

    @SuppressLint("ValidFragment")
    public AuthFragment(int mode, ReqInfo reqinfo) {
        this.mode = mode;
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
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        progressDialog = CreateProgressDialog().create();
        ctx = getContext();
        getListObject();
        bindUI(view);
        configRecyclerView();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItemTab();
            }
        });

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
        int id = MyApplication.NOTHING;
        if (mode == MyApplication.ACTORES) id = actor.getId();
        if (mode == MyApplication.OBJETIVOS) id = objective.getId();
        if (mode == MyApplication.REQ_FUNC) id = reqfun.getId();
        if (mode == MyApplication.REQ_NO_FUN) id = reqnfun.getId();
        if (mode == MyApplication.REQ_INFO) id = reqinfo.getId();

        mAdapter = new AutoAdapter(objects, id, R.layout.cardview_default, ctx, mode, progressDialog);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void getListObject() {
        if (mode == MyApplication.ACTORES) objects = actor.getAutors();
        if (mode == MyApplication.OBJETIVOS) objects = objective.getAutors();
        if (mode == MyApplication.REQ_FUNC) objects = reqfun.getAutors();
        if (mode == MyApplication.REQ_NO_FUN) objects = reqnfun.getAutors();
        if (mode == MyApplication.REQ_INFO) objects = reqinfo.getAutors();
    }

    private void addItemTab() {
        Context c = mRecyclerView.getContext();
        Intent i;
        i = new Intent(c.getApplicationContext(), ListActivity.class);
        i.putExtra("mode", mode);
        i.putExtra("flagTab", MyApplication.AUTH);
        if (mode == MyApplication.ACTORES) i.putExtra("actor", actor);
        if (mode == MyApplication.OBJETIVOS) i.putExtra("objec", objective);
        if (mode == MyApplication.REQ_INFO) i.putExtra("reqinfo", reqinfo);
        if (mode == MyApplication.REQ_NO_FUN) i.putExtra("reqnfun", reqnfun);
        if (mode == MyApplication.REQ_FUNC) i.putExtra("reqfun", reqfun);
        c.startActivity(i);
        getActivity().finish();
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