/*
   Autor: Arturo Balleros Albillo
 */
package com.karveg.readyreq.Activities;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.karveg.readyreq.App.MyApplication;
import com.karveg.readyreq.Fragments.DatePickerFragment;
import com.karveg.readyreq.Models.Package;
import com.karveg.readyreq.R;
import com.karveg.readyreq.Utils.Utils;

public class PackageActivity extends AppCompatActivity {

    private int intCode;
    private Toolbar toolbar;
    private ImageView toolbarIcon;
    private TextView toolbarTitle;

    private static TextView editTextName;
    private static TextView editTextVer;
    private static TextView editTextDate;
    private static Spinner spinnerCateg;
    private static TextView editTextComen;
    private static Package pack;

    private AlertDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package);

        progressDialog = CreateProgressDialog().create();

        setToolbar();
        bindUI();

        //Bundle
        Bundle bu = getIntent().getExtras();
        if (bu != null && bu.getInt("code") != MyApplication.NOTHING) intCode = bu.getInt("code");
        else intCode = MyApplication.NOTHING;

        pack = new Package();

        if (intCode != MyApplication.NOTHING)
            Package.getPackage(getApplicationContext(), intCode, progressDialog);

        editTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
    }

    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because january is zero
                editTextDate.setText(day + "/" + (month + 1) + "/" + year);
            }
        });
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    private void bindUI() {
        editTextName = findViewById(R.id.editTextName);
        spinnerCateg = findViewById(R.id.spinnerCateg);
        editTextComen = findViewById(R.id.editTextComen);
        editTextVer = findViewById(R.id.editTextVer);
        editTextDate = findViewById(R.id.editTextDate);
    }

    private void setToolbar() {
        toolbarIcon = findViewById(R.id.toolbarIcon);
        toolbarIcon.setImageResource(R.drawable.ic_paque);
        toolbarTitle = findViewById(R.id.toolbarTitle);
        toolbarTitle.setText(R.string.toolbar_paque);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
                setValuesPackage();
                savePackage();
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
        Intent i = new Intent(PackageActivity.this, MainActivity.class);
        i.putExtra("Fragment", MyApplication.PAQUETES);
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

    public static void setValuesUI(Package p) {
        pack = p;
        editTextName.setText(pack.getName());
        editTextVer.setText(pack.getVersion() + "");
        editTextDate.setText(Utils.DateToString(pack.getFech(), false));
        spinnerCateg.setSelection(pack.getCategory() - 1);
        editTextComen.setText(pack.getCommentary());
    }

    public void setValuesPackage() {
        pack.setName(editTextName.getText().toString());
        pack.setVersion(Double.parseDouble(editTextVer.getText().toString()));
        pack.setFech(Utils.StringToDate(editTextDate.getText().toString(), false));
        pack.setCategory(spinnerCateg.getSelectedItemPosition() + 1);
        pack.setCommentary(editTextComen.getText().toString());
    }

    private void savePackage() {
        String url;
        if (intCode != MyApplication.NOTHING) { //Modifico

            url = MyApplication.HTTP + "://" + MyApplication.IP_SERVER + ":" + MyApplication.PORTHTTP + "/readyreq/paq_update.php?";
            url += "a=" + pack.getId() + "&";
            url += "b=" + pack.getName() + "&";
            url += "c=" + pack.getVersion() + "&";
            url += "d=" + Utils.DateToString(pack.getFech(), true) + "&";
            url += "e=" + pack.getCategory() + "&";
            url += "f=" + pack.getCommentary();

        } else { //Creo

            url = MyApplication.HTTP + "://" + MyApplication.IP_SERVER + ":" + MyApplication.PORTHTTP + "/readyreq/paq_create.php?";
            url += "a=" + pack.getName() + "&";
            url += "b=" + pack.getVersion() + "&";
            url += "c=" + Utils.DateToString(pack.getFech(), true) + "&";
            url += "d=" + pack.getCategory() + "&";
            url += "e=" + pack.getCommentary();

        }
        Utils.create_update_delete(PackageActivity.this, url, progressDialog, MyApplication.PAQUETES, true);
    }
}