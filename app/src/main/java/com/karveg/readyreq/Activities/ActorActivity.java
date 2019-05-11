package com.karveg.readyreq.Activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
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
import com.karveg.readyreq.Fragments.ActorDataFragment;
import com.karveg.readyreq.Models.Actor;
import com.karveg.readyreq.Models.Generic;
import com.karveg.readyreq.R;
import com.karveg.readyreq.Utils.Utils;

public class ActorActivity extends AppCompatActivity {

    private int intCode;

    private Toolbar toolbar;
    private ImageView toolbarIcon;
    private TextView toolbarTitle;
    private static TabLayout tabLayout;
    private static ViewPager viewPager;
    private static PageAdapter pageAdapter;
    private static FragmentManager fragmentManager;

    private static Actor actor;

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
                Actor.getActor(getApplicationContext(), intCode, progressDialog);
            }

            if (bu.getSerializable("actor") != null) {
                actor = (Actor) bu.getSerializable("actor");
                if (bu.getInt("flagTab") != 0) setViewPager(bu.getInt("flagTab"));
                else setViewPager(MyApplication.NOTHING);
            }
        } else {//nuevo
            actor = new Actor();
            actor.setId(MyApplication.NOTHING);
            setViewPager(MyApplication.NOTHING);
        }

    }


    private void setToolbar() {
        toolbarIcon = findViewById(R.id.toolbarIcon);
        toolbarIcon.setImageResource(R.drawable.ic_actor);
        toolbarTitle = findViewById(R.id.toolbarTitle);
        toolbarTitle.setText(R.string.toolbar_actor);

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
                    ActorDataFragment.setValuesActor();
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    private static void setViewPager(int currentTab) {
        pageAdapter = new PageAdapter(fragmentManager, tabLayout.getTabCount(), actor, MyApplication.ACTORES);
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
                ActorDataFragment.setValuesActor();
                saveActor();
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
        Intent i = new Intent(ActorActivity.this, MainActivity.class);
        i.putExtra("Fragment", MyApplication.ACTORES);
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

    public static void setValueActor(Actor a) {
        actor = a;
        setViewPager(MyApplication.NOTHING);
    }

    private void saveActor() {
        String url;
        if (actor.getId() != MyApplication.NOTHING) { //Modifico

            url = "http://" + MyApplication.IP_SERVER + ":" + MyApplication.PORTHTTP + "/readyreq/actor_update.php?";
            url += "a=" + actor.getId() + "&";
            url += "b=" + actor.getName() + "&";
            url += "c=" + actor.getDescription() + "&";
            url += "d=" + actor.getComple() + "&";
            url += "e=" + actor.getDescComple() + "&";
            url += "f=" + actor.getCategory() + "&";
            url += "g=" + actor.getCommentary();
            Utils.create_update_delete(ActorActivity.this, url, progressDialog, MyApplication.ACTORES, true);

        } else { //Creo

            url = "http://" + MyApplication.IP_SERVER + ":" + MyApplication.PORTHTTP + "/readyreq/actor_create.php?";
            url += "a=" + actor.getName() + "&";
            url += "b=" + actor.getDescription() + "&";
            url += "c=" + actor.getComple() + "&";
            url += "d=" + actor.getDescComple() + "&";
            url += "e=" + actor.getCategory() + "&";
            url += "f=" + actor.getCommentary();
            Utils.create_update_delete(ActorActivity.this, url, progressDialog, MyApplication.ACTORES, true);
            Actor.getIdActor(getApplicationContext(), actor.getName(), progressDialog);

        }
    }

    public static void saveObjects(Context ctx, int id) {
        String url = "";
        for (Generic g : actor.getAutors()) {
            url = "http://" + MyApplication.IP_SERVER + ":" + MyApplication.PORTHTTP + "/readyreq/rel_create.php?";
            url += "a=actauto(idautor,idact)&";
            url += "b=" + g.getId() + "," + id;
            Utils.saveObject(ctx, url);
        }
        for (Generic g : actor.getSources()) {
            url = "http://" + MyApplication.IP_SERVER + ":" + MyApplication.PORTHTTP + "/readyreq/rel_create.php?";
            url += "a=actfuen(idfuen,idact)&";
            url += "b=" + g.getId() + "," + id;
            Utils.saveObject(ctx, url);
        }
    }
}