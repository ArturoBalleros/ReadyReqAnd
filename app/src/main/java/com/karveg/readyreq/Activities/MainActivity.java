package com.karveg.readyreq.Activities;


import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.karveg.readyreq.App.MyApplication;
import com.karveg.readyreq.Fragments.ConfigFragment;
import com.karveg.readyreq.Fragments.EstimFragment;
import com.karveg.readyreq.Fragments.GenericFragment;
import com.karveg.readyreq.R;
import com.karveg.readyreq.Utils.Utils;


public class MainActivity extends AppCompatActivity {
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    int mode_select;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setToolBar();
        navigationView = findViewById(R.id.navView);
        drawerLayout = findViewById(R.id.drawer_layout);
/*
       Intent i = new Intent(MainActivity.this, ObjecActivity.class);
        i.putExtra("code", 3);
        startActivity(i);
        finish();*/

        Bundle bu = getIntent().getExtras();
        if (bu != null && bu.getInt("Fragment") > MyApplication.NOTHING) {
            setFragmentToContent(bu.getInt("Fragment"));
        } else {
            setFragmentToContent(MyApplication.GRUPO);
        }

        //Eventos para cambiar de pestaña
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectedFragment(item);
                drawerLayout.closeDrawers();
                return true;
            }
        });
    }

    private void setToolBar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //ya esta inyectado con esto el menu
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_home);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home://esto es el burger
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    //que se ponga en el nav como seleccionado
    private void setFragmentToContent(int posFragment) {
        MenuItem item = navigationView.getMenu().getItem(posFragment);
        selectedFragment(item);
    }

    //Cuando selecciono algo en el nav
    private void selectedFragment(MenuItem item) {
        boolean fragmentTransaction = false;
        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.menu_grupo:
                mode_select = MyApplication.GRUPO;
                fragment = new GenericFragment(mode_select);
                fragmentTransaction = true;
                break;
            case R.id.menu_paque:
                mode_select = MyApplication.PAQUETES;
                fragment = new GenericFragment(mode_select);
                fragmentTransaction = true;
                break;
            case R.id.menu_objet:
                mode_select = MyApplication.OBJETIVOS;
                fragment = new GenericFragment(mode_select);
                fragmentTransaction = true;
                break;
            case R.id.menu_actor:
                mode_select = MyApplication.ACTORES;
                fragment = new GenericFragment(mode_select);
                fragmentTransaction = true;
                break;
            case R.id.menu_rf:
                mode_select = MyApplication.REQ_FUNC;
                fragment = new GenericFragment(mode_select);
                fragmentTransaction = true;
                break;
            case R.id.menu_rnf:
                mode_select = MyApplication.REQ_NO_FUN;
                fragment = new GenericFragment(mode_select);
                fragmentTransaction = true;
                break;
            case R.id.menu_ri:
                mode_select = MyApplication.REQ_INFO;
                fragment = new GenericFragment(mode_select);
                fragmentTransaction = true;
                break;
            case R.id.menu_estim:
                mode_select = MyApplication.NOTHING;
                fragment = new EstimFragment();
                fragmentTransaction = true;
                break;
            case R.id.menu_conf:
                mode_select = MyApplication.NOTHING;
                fragment = new ConfigFragment();
                fragmentTransaction = true;
                break;
        }
        if (fragmentTransaction == true) {
            changeFragment(fragment, item);
            supportInvalidateOptionsMenu();
        }
    }

    //cambiar al fragment seleccionado
    private void changeFragment(Fragment frag, MenuItem item) {
        item.setChecked(true);
        getSupportActionBar().setTitle(item.getTitle());
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, frag).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (mode_select != MyApplication.NOTHING) {
            getMenuInflater().inflate(R.menu.toolbar_search, menu);

            MenuItem searchItem = menu.findItem(R.id.searchView);
            final SearchView searchView = (SearchView) searchItem.getActionView();

            searchView.setQueryHint(getApplicationContext().getString(R.string.text_name));
            searchView.setMaxWidth(android.R.attr.width);
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                        if (!query.isEmpty()) {
                            MenuItem item = navigationView.getMenu().getItem(mode_select);
                            Fragment fragment = new GenericFragment(mode_select, Utils.getNameSearch(mode_select), query);
                            changeFragment(fragment, item);
                            //se oculta el EditText
                            searchView.clearFocus();
                            searchView.setQuery("", false);
                            searchView.setIconified(true);
                        }
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    return true;
                }
            });
        }
        return super.onCreateOptionsMenu(menu);
    }
}
