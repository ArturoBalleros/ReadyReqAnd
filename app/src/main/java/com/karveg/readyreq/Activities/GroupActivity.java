package com.karveg.readyreq.Activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.karveg.readyreq.App.MyApplication;
import com.karveg.readyreq.Models.Worker;
import com.karveg.readyreq.R;
import com.karveg.readyreq.Utils.Utils;

public class GroupActivity extends AppCompatActivity {

    private int intCode;
    private Toolbar toolbar;
    private ImageView toolbarIcon;
    private TextView toolbarTitle;

    private static TextView editTextName;
    private static TextView editTextOrg;
    private static TextView editTextRol;
    private static RadioButton radioButtonYes;
    private static RadioButton radioButtonNo;
    private static Spinner spinnerCateg;
    private static TextView editTextComen;
    private static Worker worker;

    private AlertDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);

        progressDialog = CreateProgressDialog().create();

        setToolbar();
        bindUI();

        //Bundle
        Bundle bu = getIntent().getExtras();
        if (bu != null && bu.getInt("code") != MyApplication.NOTHING) intCode = bu.getInt("code");
        else intCode = MyApplication.NOTHING;

        worker = new Worker();

        if (intCode != MyApplication.NOTHING)
            Worker.getWorker(getApplicationContext(), intCode, progressDialog);
    }

    private void bindUI() {
        editTextName = findViewById(R.id.editTextName);
        editTextOrg = findViewById(R.id.editTextOrg);
        editTextRol = findViewById(R.id.editTextRol);
        radioButtonYes = findViewById(R.id.radioButtonYes);
        radioButtonNo = findViewById(R.id.radioButtonNo);
        spinnerCateg = findViewById(R.id.spinnerCateg);
        editTextComen = findViewById(R.id.editTextComen);
    }

    private void setToolbar() {
        toolbarIcon = findViewById(R.id.toolbarIcon);
        toolbarIcon.setImageResource(R.drawable.ic_grupo);
        toolbarTitle = findViewById(R.id.toolbarTitle);
        toolbarTitle.setText(R.string.toolbar_grupo);

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
                setValuesWorker();
                saveWorker();
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
        Intent i = new Intent(GroupActivity.this, MainActivity.class);
        i.putExtra("Fragment", MyApplication.GRUPO);
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

    public static void setValuesUI(Worker w) {
        worker = w;
        editTextName.setText(worker.getName());
        editTextOrg.setText(worker.getOrganization());
        editTextRol.setText(worker.getRole());
        if (worker.isDeveloper()) radioButtonYes.setChecked(true);
        else radioButtonNo.setChecked(true);
        spinnerCateg.setSelection(worker.getCategory() - 1);
        editTextComen.setText(worker.getCommentary());

    }

    public void setValuesWorker() {
        worker.setName(editTextName.getText().toString());
        worker.setOrganization(editTextOrg.getText().toString());
        worker.setRole(editTextRol.getText().toString());
        if (radioButtonYes.isChecked()) worker.setDeveloper(true);
        else worker.setDeveloper(false);
        worker.setCategory(spinnerCateg.getSelectedItemPosition() + 1);
        worker.setCommentary(editTextComen.getText().toString());
    }

    private void saveWorker() {
        String url;
        if (intCode != MyApplication.NOTHING) { //Modifico

            url = "http://" + MyApplication.IP_SERVER + ":" + MyApplication.PORTHTTP + "/readyreq/group_update.php?";
            url += "a=" + worker.getId() + "&";
            url += "b=" + worker.getName() + "&";
            url += "c=" + worker.getOrganization() + "&";
            url += "d=" + worker.getRole() + "&";
            if (worker.isDeveloper()) url += "e=" + 1 + "&";
            else url += "e=" + 0 + "&";
            url += "f=" + worker.getCategory() + "&";
            url += "g=" + worker.getCommentary();

        } else { //Creo

            url = "http://" + MyApplication.IP_SERVER + ":" + MyApplication.PORTHTTP + "/readyreq/group_create.php?";
            url += "a=" + worker.getName() + "&";
            url += "b=" + worker.getOrganization() + "&";
            url += "c=" + worker.getRole() + "&";
            if (worker.isDeveloper()) url += "d=" + 1 + "&";
            else url += "d=" + 0 + "&";
            url += "e=" + worker.getCategory() + "&";
            url += "f=" + worker.getCommentary();

        }
        Utils.create_update_delete(GroupActivity.this, url, progressDialog, MyApplication.GRUPO, true);
    }
}