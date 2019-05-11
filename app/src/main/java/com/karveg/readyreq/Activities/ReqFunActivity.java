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
import com.karveg.readyreq.Fragments.ReqFunFragment;
import com.karveg.readyreq.Models.Generic;
import com.karveg.readyreq.Models.ReqFun;
import com.karveg.readyreq.R;
import com.karveg.readyreq.Utils.Utils;

public class ReqFunActivity extends AppCompatActivity {
    private int intCode;

    private Toolbar toolbar;
    private ImageView toolbarIcon;
    private TextView toolbarTitle;
    private static TabLayout tabLayout;
    private static ViewPager viewPager;
    private static PageAdapter pageAdapter;
    private static FragmentManager fragmentManager;

    private static ReqFun reqfun;

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
                ReqFun.getReque(getApplicationContext(), intCode, progressDialog);
            }

            if (bu.getSerializable("reqfun") != null) {
                reqfun = (ReqFun) bu.getSerializable("reqfun");
                if (bu.getInt("flagTab") != 0) setViewPager(bu.getInt("flagTab"));
                else setViewPager(MyApplication.NOTHING);
            }
        } else {//nuevo
            reqfun = new ReqFun();
            reqfun.setId(MyApplication.NOTHING);
            reqfun.setPackage(MyApplication.NOTHING);
            setViewPager(MyApplication.NOTHING);
        }
    }

    private void setToolbar() {
        toolbarIcon = findViewById(R.id.toolbarIcon);
        toolbarIcon.setImageResource(R.drawable.ic_rf);
        toolbarTitle = findViewById(R.id.toolbarTitle);
        toolbarTitle.setTextSize(18);
        toolbarTitle.setText(R.string.toolbar_rf);

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
        tabLayout.addTab(tabLayout.newTab().setText(R.string.actor));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.secnor));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.secexc));
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
                    ReqFunFragment.setValuesReque();
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    private static void setViewPager(int currentTab) {
        pageAdapter = new PageAdapter(fragmentManager, tabLayout.getTabCount(), reqfun, MyApplication.REQ_FUNC);
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
                ReqFunFragment.setValuesReque();
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
        Intent i = new Intent(ReqFunActivity.this, MainActivity.class);
        i.putExtra("Fragment", MyApplication.REQ_FUNC);
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

    public static void setValueReque(ReqFun r) {
        reqfun = r;
        setViewPager(MyApplication.NOTHING);
    }

    private void saveReque() {
        String url;
        if (reqfun.getId() != MyApplication.NOTHING) { //Modifico
            url = "http://" + MyApplication.IP_SERVER + ":8080/readyreq/reqfun_update.php?";
            url += "a=" + reqfun.getId() + "&";
            url += "b=" + reqfun.getName() + "&";
            url += "c=" + reqfun.getDescription() + "&";
            url += "d=" + reqfun.getPackage() + "&";
            url += "e=" + reqfun.getPreCond() + "&";
            url += "f=" + reqfun.getPostCond() + "&";
            url += "g=" + reqfun.getComplejidad() + "&";//complejidad
            url += "h=" + reqfun.getPrior() + "&";
            url += "i=" + reqfun.getUrge() + "&";
            url += "j=" + reqfun.getEsta() + "&";
            if (reqfun.isState()) url += "k=" + 1 + "&";
            else url += "k=" + 0 + "&";
            url += "l=" + reqfun.getCategory() + "&";
            url += "m=" + reqfun.getCommentary();
            Utils.create_update_delete(ReqFunActivity.this, url, progressDialog, MyApplication.REQ_FUNC, true);

        } else { //Creo

            url = "http://" + MyApplication.IP_SERVER + ":8080/readyreq/reqfun_create.php?";
            url += "a=" + reqfun.getName() + "&";
            url += "b=" + reqfun.getDescription() + "&";
            url += "c=" + reqfun.getPackage() + "&";
            url += "d=" + reqfun.getPreCond() + "&";
            url += "e=" + reqfun.getPostCond() + "&";
            url += "f=" + reqfun.getComplejidad() + "&";//complejidad
            url += "g=" + reqfun.getPrior() + "&";
            url += "h=" + reqfun.getUrge() + "&";
            url += "i=" + reqfun.getEsta() + "&";
            if (reqfun.isState()) url += "j=" + 1 + "&";
            else url += "j=" + 0 + "&";
            url += "k=" + reqfun.getCategory() + "&";
            url += "l=" + reqfun.getCommentary();
            Utils.create_update_delete(ReqFunActivity.this, url, progressDialog, MyApplication.REQ_FUNC, true);
            ReqFun.getIdReque(getApplicationContext(), reqfun.getName(), progressDialog);

        }

    }

    public static void saveObjects(Context ctx, int id) {
        String url = "";
        for (Generic g : reqfun.getAutors()) {
            url = "http://" + MyApplication.IP_SERVER + ":8080/readyreq/rel_create.php?";
            url += "a=reqauto(idautor,idreq)&";
            url += "b=" + g.getId() + "," + id;
            Utils.saveObject(ctx, url);
        }
        for (Generic g : reqfun.getSources()) {
            url = "http://" + MyApplication.IP_SERVER + ":8080/readyreq/rel_create.php?";
            url += "a=reqfuen(idfuen,idreq)&";
            url += "b=" + g.getId() + "," + id;
            Utils.saveObject(ctx, url);
        }
        for (Generic g : reqfun.getObjetives()) {
            url = "http://" + MyApplication.IP_SERVER + ":8080/readyreq/rel_create.php?";
            url += "a=reqobj(idobj,idreq)&";
            url += "b=" + g.getId() + "," + id;
            Utils.saveObject(ctx, url);
        }
        for (Generic g : reqfun.getRequirements()) {
            url = "http://" + MyApplication.IP_SERVER + ":8080/readyreq/rel_create.php?";
            url += "a=reqreqr(idreqr,tiporeq,idreq)&";
            url += "b=" + g.getId() + "," + Utils.deterTipoReq(g.getImage()) + "," + id;
            Utils.saveObject(ctx, url);
        }
        for (Generic g : reqfun.getActors()) {
            url = "http://" + MyApplication.IP_SERVER + ":8080/readyreq/rel_create.php?";
            url += "a=reqact(idact,idreq)&";
            url += "b=" + g.getId() + "," + id;
            Utils.saveObject(ctx, url);
        }
        for (Generic g : reqfun.getSecNor()) {
            url = "http://" + MyApplication.IP_SERVER + ":8080/readyreq/rel_create.php?";
            url += "a=reqsecnor(idreq,descrip)&";
            url += "b=" + id + ",'" + g.getName() + "'";
            Utils.saveObject(ctx, url);
        }
        for (Generic g : reqfun.getSecExc()) {
            url = "http://" + MyApplication.IP_SERVER + ":8080/readyreq/rel_create.php?";
            url += "a=reqsecexc(idreq,descrip)&";
            url += "b=" + id + ",'" + g.getName() + "'";
            Utils.saveObject(ctx, url);
        }
    }
}