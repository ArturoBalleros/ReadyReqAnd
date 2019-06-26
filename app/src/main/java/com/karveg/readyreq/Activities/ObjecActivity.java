/*
   Autor: Arturo Balleros Albillo
 */
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
import com.karveg.readyreq.Models.Generic;
import com.karveg.readyreq.Models.Objective;
import com.karveg.readyreq.R;
import com.karveg.readyreq.Utils.Utils;

public class ObjecActivity extends AppCompatActivity {


    private int intCode;

    private Toolbar toolbar;
    private ImageView toolbarIcon;
    private TextView toolbarTitle;
    private static TabLayout tabLayout;
    private static ViewPager viewPager;
    private static PageAdapter pageAdapter;
    private static FragmentManager fragmentManager;

    private static Objective objective;

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
                Objective.getObjec(getApplicationContext(), intCode, progressDialog);
            }

            if (bu.getSerializable("objec") != null) {
                objective = (Objective) bu.getSerializable("objec");
                if (bu.getInt("flagTab") != 0) setViewPager(bu.getInt("flagTab"));
                else setViewPager(MyApplication.NOTHING);
            }
        } else {//nuevo
            objective = new Objective();
            objective.setId(MyApplication.NOTHING);
            setViewPager(MyApplication.NOTHING);
        }

    }

    private void setToolbar() {
        toolbarIcon = findViewById(R.id.toolbarIcon);
        toolbarIcon.setImageResource(R.drawable.ic_objet);
        toolbarTitle = findViewById(R.id.toolbarTitle);
        toolbarTitle.setText(R.string.toolbar_objet);

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
                    ObjecDataFragment.setValuesObjec();
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    private static void setViewPager(int currentTab) {
        pageAdapter = new PageAdapter(fragmentManager, tabLayout.getTabCount(), objective, MyApplication.OBJETIVOS);
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
                ObjecDataFragment.setValuesObjec();
                saveObjetive();
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
        Intent i = new Intent(ObjecActivity.this, MainActivity.class);
        i.putExtra("Fragment", MyApplication.OBJETIVOS);
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

    public static void setValueObjec(Objective o) {
        objective = o;
        setViewPager(MyApplication.NOTHING);
    }

    private void saveObjetive() {
        String url;
        if (objective.getId() != MyApplication.NOTHING) { //Modifico

            url = MyApplication.HTTP + "://" + MyApplication.IP_SERVER + ":" + MyApplication.PORTHTTP + "/readyreq/objet_update.php?";
            url += "a=" + objective.getId() + "&";
            url += "b=" + objective.getName() + "&";
            url += "c=" + objective.getVersion() + "&";
            url += "d=" + Utils.DateToString(objective.getFech(), true) + "&";
            url += "e=" + objective.getDescription() + "&";
            url += "f=" + objective.getPrior() + "&";
            url += "g=" + objective.getUrge() + "&";
            url += "h=" + objective.getEsta() + "&";
            if (objective.isState()) url += "i=" + 1 + "&";
            else url += "i=" + 0 + "&";
            url += "j=" + objective.getCategory() + "&";
            url += "k=" + objective.getCommentary();
            Utils.create_update_delete(ObjecActivity.this, url, progressDialog, MyApplication.OBJETIVOS, true);

        } else { //Creo

            url = MyApplication.HTTP + "://" + MyApplication.IP_SERVER + ":" + MyApplication.PORTHTTP + "/readyreq/objet_create.php?";
            url += "a=" + objective.getName() + "&";
            url += "b=" + objective.getVersion() + "&";
            url += "c=" + Utils.DateToString(objective.getFech(), true) + "&";
            url += "d=" + objective.getDescription() + "&";
            url += "e=" + objective.getPrior() + "&";
            url += "f=" + objective.getUrge() + "&";
            url += "g=" + objective.getEsta() + "&";
            if (objective.isState()) url += "h=" + 1 + "&";
            else url += "h=" + 0 + "&";
            url += "i=" + objective.getCategory() + "&";
            url += "j=" + objective.getCommentary();
            Utils.create_update_delete(ObjecActivity.this, url, progressDialog, MyApplication.OBJETIVOS, true);
            Objective.getIdObjec(getApplicationContext(), objective.getName(), progressDialog);

        }
    }

    public static void saveObjects(Context ctx, int id) {
        String url = "";
        for (Generic g : objective.getAutors()) {
            url = MyApplication.HTTP + "://" + MyApplication.IP_SERVER + ":" + MyApplication.PORTHTTP + "/readyreq/rel_create.php?";
            url += "a=ObjAuto(idautor,idobj)&";
            url += "b=" + g.getId() + "," + id;
            Utils.saveObject(ctx, url);
        }
        for (Generic g : objective.getSources()) {
            url = MyApplication.HTTP + "://" + MyApplication.IP_SERVER + ":" + MyApplication.PORTHTTP + "/readyreq/rel_create.php?";
            url += "a=ObjFuen(idfuen,idobj)&";
            url += "b=" + g.getId() + "," + id;
            Utils.saveObject(ctx, url);
        }
        for (Generic g : objective.getObjetives()) {
            url = MyApplication.HTTP + "://" + MyApplication.IP_SERVER + ":" + MyApplication.PORTHTTP + "/readyreq/rel_create.php?";
            url += "a=ObjSubobj(idsubobj,idobj)&";
            url += "b=" + g.getId() + "," + id;
            Utils.saveObject(ctx, url);
        }
    }
}