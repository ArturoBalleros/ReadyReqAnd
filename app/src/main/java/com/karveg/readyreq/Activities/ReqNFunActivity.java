package com.karveg.readyreq.Activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.karveg.readyreq.Adapters.PageAdapter;
import com.karveg.readyreq.App.MyApplication;
import com.karveg.readyreq.Fragments.ObjecDataFragment;
import com.karveg.readyreq.Fragments.ReqNFunFragment;
import com.karveg.readyreq.Models.Generic;
import com.karveg.readyreq.Models.Objective;
import com.karveg.readyreq.Models.ReqNFun;
import com.karveg.readyreq.R;
import com.karveg.readyreq.Utils.Utils;

public class ReqNFunActivity extends AppCompatActivity {

    private int intCode;

    private Toolbar toolbar;
    private ImageView toolbarIcon;
    private TextView toolbarTitle;
    private static TabLayout tabLayout;
    private static ViewPager viewPager;
    private static PageAdapter pageAdapter;
    private static FragmentManager fragmentManager;

    private static ReqNFun reqnfun;

    private AlertDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        progressDialog = CreateProgressDialog().create();

        setToolbar();
        setTabLayout();
        viewPager = findViewById(R.id.viewPager);
        fragmentManager = getSupportFragmentManager();

        //Bundle
        Bundle bu = getIntent().getExtras();
        if (bu != null) {
            if (bu.getInt("code") != 0) {
                intCode = bu.getInt("code");
                ReqNFun.getReque(getApplicationContext(), intCode, progressDialog);
            }
            if (bu.getSerializable("reqnfun") != null) {
                reqnfun = (ReqNFun) bu.getSerializable("reqnfun");
                if (bu.getInt("flagTab") != 0)
                    setViewPager(bu.getInt("flagTab"));
                else
                    setViewPager(MyApplication.NOTHING);
            }
        } else {//nuevo
            reqnfun = new ReqNFun();
            reqnfun.setId(MyApplication.NOTHING);
            setViewPager(MyApplication.NOTHING);
        }

    }

    private void setToolbar() {
        toolbarIcon = findViewById(R.id.toolbarIcon);
        toolbarIcon.setImageResource(R.drawable.ic_rnf);
        toolbarTitle = findViewById(R.id.toolbarTitle);
        toolbarTitle.setTextSize(18);
        toolbarTitle.setText(R.string.toolbar_rnf);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setTabLayout() {
        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.data));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.autho));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.sourc));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.objec));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.reque));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int pos = tab.getPosition();
                viewPager.setCurrentItem(pos);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                if (tab.getPosition() == MyApplication.DATA)
                    ReqNFunFragment.setValuesReque();
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    private static void setViewPager(int currentTab) {
        pageAdapter = new PageAdapter(fragmentManager, tabLayout.getTabCount(), reqnfun, MyApplication.REQ_NO_FUN);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        viewPager.setAdapter(pageAdapter);
        if (currentTab != MyApplication.NOTHING)
            viewPager.setCurrentItem(currentTab);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.ctx_menu_save, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                goBack();
                return true;
            case R.id.saveObject:
                ReqNFunFragment.setValuesReque();
                saveReque();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        goBack();
        return super.onKeyDown(keyCode, event);
    }

    private void goBack() {
        Intent i = new Intent(ReqNFunActivity.this, MainActivity.class);
        i.putExtra("Fragment", MyApplication.REQ_NO_FUN);
        startActivity(i);
        finish();
    }

    private AlertDialog.Builder CreateProgressDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.progress_dialog, null);
        dialogBuilder.setView(dialogView);
        dialogBuilder.setCancelable(false);
        return dialogBuilder;
    }

    public static void setValueReque(ReqNFun r) {
        reqnfun = r;
        setViewPager(MyApplication.NOTHING);
    }

    private void saveReque() {
        String url;
        if (reqnfun.getId() != MyApplication.NOTHING) { //Modifico

            url = "http://" + MyApplication.IP_SERVER + ":8080/readyreq/reqnfun_update.php?";
            url += "a=" + reqnfun.getId() + "&";
            url += "b=" + reqnfun.getName() + "&";
            url += "c=" + reqnfun.getDescription() + "&";
            url += "d=" + reqnfun.getPrior() + "&";
            url += "e=" + reqnfun.getUrge() + "&";
            url += "f=" + reqnfun.getEsta() + "&";
            if (reqnfun.isState())
                url += "g=" + 1 + "&";
            else
                url += "g=" + 0 + "&";
            url += "h=" + reqnfun.getCategory() + "&";
            url += "i=" + reqnfun.getCommentary();
            Utils.create_update_delete(ReqNFunActivity.this, url, progressDialog, MyApplication.REQ_NO_FUN, true);

        } else { //Creo

            url = "http://" + MyApplication.IP_SERVER + ":8080/readyreq/reqnfun_create.php?";
            url += "a=" + reqnfun.getName() + "&";
            url += "b=" + reqnfun.getDescription() + "&";
            url += "c=" + reqnfun.getPrior() + "&";
            url += "d=" + reqnfun.getUrge() + "&";
            url += "e=" + reqnfun.getEsta() + "&";
            if (reqnfun.isState())
                url += "f=" + 1 + "&";
            else
                url += "f=" + 0 + "&";
            url += "g=" + reqnfun.getCategory() + "&";
            url += "h=" + reqnfun.getCommentary();
            Utils.create_update_delete(ReqNFunActivity.this, url, progressDialog, MyApplication.REQ_NO_FUN, true);
            ReqNFun.getIdReque(getApplicationContext(), reqnfun.getName(), progressDialog);

        }
    }

    public static void saveObjects(Context ctx, int id) {
        String url = "";
        for (Generic g : reqnfun.getAutors()) {
            url = "http://" + MyApplication.IP_SERVER + ":8080/readyreq/rel_create.php?";
            url += "a=reqnauto(idautor,idreq)&";
            url += "b=" + g.getId() + "," + id;
            Utils.saveObject(ctx, url);
        }
        for (Generic g : reqnfun.getSources()) {
            url = "http://" + MyApplication.IP_SERVER + ":8080/readyreq/rel_create.php?";
            url += "a=reqnfuen(idfuen,idreq)&";
            url += "b=" + g.getId() + "," + id;
            Utils.saveObject(ctx, url);
        }
        for (Generic g : reqnfun.getObjetives()) {
            url = "http://" + MyApplication.IP_SERVER + ":8080/readyreq/rel_create.php?";
            url += "a=reqnobj(idobj,idreq)&";
            url += "b=" + g.getId() + "," + id;
            Utils.saveObject(ctx, url);
        }
        for (Generic g : reqnfun.getRequirements()) {
            url = "http://" + MyApplication.IP_SERVER + ":8080/readyreq/rel_create.php?";
            url += "a=reqnreqr(idreqr,tiporeq,idreq)&";
            url += "b=" + g.getId() + "," + Utils.deterTipoReq(g.getImage()) + "," + reqnfun.getId();
            Utils.saveObject(ctx, url);
        }
    }
}
