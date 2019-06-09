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
import com.karveg.readyreq.Fragments.ReqInfoFragment;
import com.karveg.readyreq.Models.Generic;
import com.karveg.readyreq.Models.ReqInfo;
import com.karveg.readyreq.R;
import com.karveg.readyreq.Utils.Utils;

public class ReqInfoActivity extends AppCompatActivity {


    private int intCode;

    private Toolbar toolbar;
    private ImageView toolbarIcon;
    private TextView toolbarTitle;
    private static TabLayout tabLayout;
    private static ViewPager viewPager;
    private static PageAdapter pageAdapter;
    private static FragmentManager fragmentManager;
    private static ReqInfo reqinfo;
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
                ReqInfo.getReque(getApplicationContext(), intCode, progressDialog);
            }

            if (bu.getSerializable("reqinfo") != null) {
                reqinfo = (ReqInfo) bu.getSerializable("reqinfo");
                if (bu.getInt("flagTab") != 0) setViewPager(bu.getInt("flagTab"));
                else setViewPager(MyApplication.NOTHING);
            }
        } else {//nuevo
            reqinfo = new ReqInfo();
            reqinfo.setId(MyApplication.NOTHING);
            setViewPager(MyApplication.NOTHING);
        }
    }

    private void setToolbar() {
        toolbarIcon = findViewById(R.id.toolbarIcon);
        toolbarIcon.setImageResource(R.drawable.ic_ri);
        toolbarTitle = findViewById(R.id.toolbarTitle);
        toolbarTitle.setTextSize(18);
        toolbarTitle.setText(R.string.toolbar_ri);

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
        tabLayout.addTab(tabLayout.newTab().setText(R.string.datesp));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int pos = tab.getPosition();
                viewPager.setCurrentItem(pos);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                if (tab.getPosition() == MyApplication.DATA) ReqInfoFragment.setValuesReque();
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    private static void setViewPager(int currentTab) {
        pageAdapter = new PageAdapter(fragmentManager, tabLayout.getTabCount(), reqinfo, MyApplication.REQ_INFO);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        viewPager.setAdapter(pageAdapter);
        if (currentTab != MyApplication.NOTHING) viewPager.setCurrentItem(currentTab);
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
                ReqInfoFragment.setValuesReque();
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
        Intent i = new Intent(ReqInfoActivity.this, MainActivity.class);
        i.putExtra("Fragment", MyApplication.REQ_INFO);
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

    public static void setValueReque(ReqInfo r) {
        reqinfo = r;
        setViewPager(MyApplication.NOTHING);
    }

    private void saveReque() {
        String url;
        if (reqinfo.getId() != MyApplication.NOTHING) { //Modifico
            url = "http://" + MyApplication.IP_SERVER + ":" + MyApplication.PORTHTTP + "/readyreq/reqinfo_update.php?";
            url += "a=" + reqinfo.getId() + "&";
            url += "b=" + reqinfo.getName() + "&";
            url += "c=" + reqinfo.getVersion() + "&";
            url += "d=" + Utils.DateToString(reqinfo.getFech(), true) + "&";
            url += "e=" + reqinfo.getDescription() + "&";
            url += "f=" + reqinfo.getTimeMed() + "&";
            url += "g=" + reqinfo.getTimeMax() + "&";
            url += "h=" + reqinfo.getOcuMed() + "&";
            url += "i=" + reqinfo.getOcuMax() + "&";
            url += "j=" + reqinfo.getPrior() + "&";
            url += "k=" + reqinfo.getUrge() + "&";
            url += "l=" + reqinfo.getEsta() + "&";
            if (reqinfo.isState()) url += "m=" + 1 + "&";
            else url += "m=" + 0 + "&";
            url += "n=" + reqinfo.getCategory() + "&";
            url += "o=" + reqinfo.getCommentary();
            Utils.create_update_delete(ReqInfoActivity.this, url, progressDialog, MyApplication.REQ_INFO, true);

        } else { //Creo

            url = "http://" + MyApplication.IP_SERVER + ":" + MyApplication.PORTHTTP + "/readyreq/reqinfo_create.php?";
            url += "a=" + reqinfo.getName() + "&";
            url += "b=" + reqinfo.getVersion() + "&";
            url += "c=" + Utils.DateToString(reqinfo.getFech(), true) + "&";
            url += "d=" + reqinfo.getDescription() + "&";
            url += "e=" + reqinfo.getTimeMed() + "&";
            url += "f=" + reqinfo.getTimeMax() + "&";
            url += "g=" + reqinfo.getOcuMed() + "&";
            url += "h=" + reqinfo.getOcuMax() + "&";
            url += "i=" + reqinfo.getPrior() + "&";
            url += "j=" + reqinfo.getUrge() + "&";
            url += "k=" + reqinfo.getEsta() + "&";
            if (reqinfo.isState()) url += "l=" + 1 + "&";
            else url += "l=" + 0 + "&";
            url += "m=" + reqinfo.getCategory() + "&";
            url += "n=" + reqinfo.getCommentary();
            Utils.create_update_delete(ReqInfoActivity.this, url, progressDialog, MyApplication.REQ_INFO, true);
            ReqInfo.getIdReque(getApplicationContext(), reqinfo.getName(), progressDialog);

        }

    }

    public static void saveObjects(Context ctx, int id) {
        String url = "";
        for (Generic g : reqinfo.getAutors()) {
            url = "http://" + MyApplication.IP_SERVER + ":" + MyApplication.PORTHTTP + "/readyreq/rel_create.php?";
            url += "a=ReqIAuto(idautor,idreq)&";
            url += "b=" + g.getId() + "," + id;
            Utils.saveObject(ctx, url);
        }
        for (Generic g : reqinfo.getSources()) {
            url = "http://" + MyApplication.IP_SERVER + ":" + MyApplication.PORTHTTP + "/readyreq/rel_create.php?";
            url += "a=ReqIFuen(idfuen,idreq)&";
            url += "b=" + g.getId() + "," + id;
            Utils.saveObject(ctx, url);
        }
        for (Generic g : reqinfo.getObjetives()) {
            url = "http://" + MyApplication.IP_SERVER + ":" + MyApplication.PORTHTTP + "/readyreq/rel_create.php?";
            url += "a=ReqIObj(idobj,idreq)&";
            url += "b=" + g.getId() + "," + id;
            Utils.saveObject(ctx, url);
        }
        for (Generic g : reqinfo.getRequirements()) {
            url = "http://" + MyApplication.IP_SERVER + ":" + MyApplication.PORTHTTP + "/readyreq/rel_create.php?";
            url += "a=ReqIReqR(idreqr,tiporeq,idreq)&";
            url += "b=" + g.getId() + "," + Utils.deterTipoReq(g.getImage()) + "," + id;
            Utils.saveObject(ctx, url);
        }
        for (Generic g : reqinfo.getDatEspec()) {
            url = "http://" + MyApplication.IP_SERVER + ":" + MyApplication.PORTHTTP + "/readyreq/rel_create.php?";
            url += "a=ReqIDatEsp(idreq,descrip)&";
            url += "b=" + id + ",'" + g.getName() + "'";
            Utils.saveObject(ctx, url);
        }
    }
}