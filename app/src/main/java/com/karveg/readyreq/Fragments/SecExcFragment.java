/*
   Autor: Arturo Balleros Albillo
 */
package com.karveg.readyreq.Fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.karveg.readyreq.Adapters.SecExcAdapter;
import com.karveg.readyreq.App.MyApplication;
import com.karveg.readyreq.Models.Generic;
import com.karveg.readyreq.Models.ReqFun;
import com.karveg.readyreq.R;
import com.karveg.readyreq.Utils.SimpleItemTouchHelperCallback;
import com.karveg.readyreq.Utils.Utils;

import java.util.List;

public class SecExcFragment extends Fragment {

    private static ReqFun reqfun;
    private int mode;

    private static List<Generic> objects;
    private static RecyclerView mRecyclerView;
    private static SecExcAdapter mAdapter;
    private RecyclerView.LayoutManager mLayout;
    private FloatingActionButton fabAdd;
    private static FloatingActionButton fabDel;
    private static FloatingActionButton fabUpd;
    private static ItemTouchHelper mItemTouchHelper;
    private static Context ctx;

    private AlertDialog progressDialog;

    public SecExcFragment() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public SecExcFragment(int mode, ReqFun reqfun) {
        this.reqfun = reqfun;
        this.mode = mode;
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
        objects = reqfun.getSecExc();
        ctx = getContext();
        bindUI(view);
        fabDel.setVisibility(View.VISIBLE);
        configRecyclerView();
        Events();

        return view;
    }

    private void bindUI(View view) {
        mRecyclerView = view.findViewById(R.id.recyclerViewGroup);
        fabAdd = view.findViewById(R.id.fabAdd);
        fabDel = view.findViewById(R.id.fabDel);
        fabUpd = view.findViewById(R.id.fabUpd);
    }

    private void configRecyclerView() {
        mLayout = new LinearLayoutManager(getContext());
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(mLayout);

        updateRecyclerView();

    }

    public static void updateRecyclerView() {
        mAdapter = new SecExcAdapter(objects, R.layout.cardview_default, new SecExcAdapter.ICallback() {
            @Override
            public void onItemPositionChange(int fromPosition, int toPosition) {
                if (fabUpd.getVisibility() == View.INVISIBLE)
                    fabUpd.setVisibility(View.VISIBLE);
            }

            @Override
            public void onItemClick(Generic object, int position, View v) {
                object.setSelected(!object.isSelected());
                visibilityFab();
                v.setBackgroundColor(object.isSelected() ? Color.CYAN : Color.parseColor("#E2E2E2"));
            }
        });
        mRecyclerView.setAdapter(mAdapter);

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(ctx, mAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
        visibilityFab();
    }

    public void Events() {
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.showAlertForItem(objects, getContext(), MyApplication.SEC_EXC, reqfun.getId());
            }
        });
        fabDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.showDeleteItems(objects, reqfun.getId(), getContext(), mode, MyApplication.SEC_EXC, progressDialog);
            }
        });
        fabUpd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "";
                url = MyApplication.HTTP + "://" + MyApplication.IP_SERVER + ":" + MyApplication.PORTHTTP + "/readyreq/rel_delete.php?";
                url += "a=ReqSecExc where idreq = " + reqfun.getId() + ";";
                Utils.create_update_delete(getContext(), url, progressDialog, MyApplication.SEC_EXC, false);
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

    private static void visibilityFab() {
        boolean visibility = false;
        for (Generic g : objects)
            if (g.isSelected()) visibility = true;
        if (visibility) fabDel.setVisibility(View.VISIBLE);
        else fabDel.setVisibility(View.INVISIBLE);
    }

    public static void updateSecExc() {
        String url = "";
        for (Generic g : reqfun.getSecExc()) {
            url = MyApplication.HTTP + "://" + MyApplication.IP_SERVER + ":" + MyApplication.PORTHTTP + "/readyreq/rel_create.php?";
            url += "a=ReqSecExc(idreq,descrip)&";
            url += "b=" + reqfun.getId() + ",'" + g.getName() + "'";
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Utils.saveObject(ctx, url);
        }
        fabUpd.setVisibility(View.INVISIBLE);
    }
}