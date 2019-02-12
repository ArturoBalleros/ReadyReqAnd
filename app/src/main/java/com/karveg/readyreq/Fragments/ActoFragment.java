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
import android.widget.TextView;

import com.karveg.readyreq.Activities.ListActivity;
import com.karveg.readyreq.Adapters.ActoAdapter;
import com.karveg.readyreq.Adapters.AutoAdapter;
import com.karveg.readyreq.App.MyApplication;
import com.karveg.readyreq.Models.Generic;
import com.karveg.readyreq.Models.ReqFun;
import com.karveg.readyreq.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ActoFragment extends Fragment {

    private static List<Generic> objects;
    private static RecyclerView mRecyclerView;
    private static RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayout;
    private FloatingActionButton fab;

    private static AlertDialog progressDialog;

    private static Context ctx;
    private static int mode;
    private static ReqFun reqfun;

    public ActoFragment() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public ActoFragment(int mode, ReqFun reqfun) {
        this.mode = mode;
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
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        progressDialog = CreateProgressDialog().create();
        ctx = getContext();
        objects = reqfun.getActors();
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
        mAdapter = new ActoAdapter(objects, reqfun.getId(), R.layout.cardview_default, ctx, mode, progressDialog);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void addItemTab() {
        Context c = mRecyclerView.getContext();
        Intent i;
        i = new Intent(c.getApplicationContext(), ListActivity.class);
        i.putExtra("mode", mode);
        i.putExtra("flagTab", MyApplication.ACTO);
        i.putExtra("reqfun", reqfun);
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
