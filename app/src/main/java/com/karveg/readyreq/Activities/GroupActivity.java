package com.karveg.readyreq.Activities;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.karveg.readyreq.App.MyApplication;
import com.karveg.readyreq.Fragments.DatePickerFragment;
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
    private static TextView editTextVer;
    private static TextView editTextDate;
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
        editTextOrg = findViewById(R.id.editTextOrg);
        editTextRol = findViewById(R.id.editTextRol);
        radioButtonYes = findViewById(R.id.radioButtonYes);
        radioButtonNo = findViewById(R.id.radioButtonNo);
        spinnerCateg = findViewById(R.id.spinnerCateg);
        editTextComen = findViewById(R.id.editTextComen);
        editTextVer = findViewById(R.id.editTextVer);
        editTextDate = findViewById(R.id.editTextDate);
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
        editTextVer.setText(worker.getVersion() + "");
        editTextDate.setText(Utils.DateToString(worker.getFech(), false));
        editTextOrg.setText(worker.getOrganization());
        editTextRol.setText(worker.getRole());
        if (worker.isDeveloper()) radioButtonYes.setChecked(true);
        else radioButtonNo.setChecked(true);
        spinnerCateg.setSelection(worker.getCategory() - 1);
        editTextComen.setText(worker.getCommentary());

    }

    public void setValuesWorker() {
        worker.setName(editTextName.getText().toString());
        worker.setVersion(Double.parseDouble(editTextVer.getText().toString()));
        worker.setFech(Utils.StringToDate(editTextDate.getText().toString(), false));
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
            url += "c=" + worker.getVersion() + "&";
            url += "d=" + Utils.DateToString(worker.getFech(), true) + "&";
            url += "e=" + worker.getOrganization() + "&";
            url += "f=" + worker.getRole() + "&";
            if (worker.isDeveloper()) url += "g=" + 1 + "&";
            else url += "g=" + 0 + "&";
            url += "h=" + worker.getCategory() + "&";
            url += "i=" + worker.getCommentary();

        } else { //Creo

            url = "http://" + MyApplication.IP_SERVER + ":" + MyApplication.PORTHTTP + "/readyreq/group_create.php?";
            url += "a=" + worker.getName() + "&";
            url += "b=" + worker.getVersion() + "&";
            url += "c=" + Utils.DateToString(worker.getFech(), true) + "&";
            url += "d=" + worker.getOrganization() + "&";
            url += "e=" + worker.getRole() + "&";
            if (worker.isDeveloper()) url += "f=" + 1 + "&";
            else url += "f=" + 0 + "&";
            url += "g=" + worker.getCategory() + "&";
            url += "h =" + worker.getCommentary();

        }
        Utils.create_update_delete(GroupActivity.this, url, progressDialog, MyApplication.GRUPO, true);
    }
}