package com.karveg.readyreq.App;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.karveg.readyreq.Utils.SharedP;

public class MyApplication extends Application {

    public static String IP_SERVER;
    public static String USER;
    public static String PASS;
    public static String DATABASE;
    public static int PORT;

    public static final int NOTHING = -1;
    public static final int GRUPO = 0;
    public static final int PAQUETES = 1;
    public static final int OBJETIVOS = 2;
    public static final int ACTORES = 3;
    public static final int REQ_FUNC = 4;
    public static final int REQ_NO_FUN = 5;
    public static final int REQ_INFO = 6;

    public static final int DATA = 0;
    public static final int AUTH = 1;
    public static final int SOUR = 2;
    public static final int OBJE = 3;
    public static final int REQU = 4;
    public static final int ACTO = 5;

    public static final int SEC_NOR = 46;
    public static final int SEC_EXC = 47;
    public static final int DAT_ESP = 65;

    private SharedPreferences sharedPref;

    @Override
    public void onCreate() {
        super.onCreate();

        sharedPref = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        IP_SERVER = SharedP.getIPSerPrefs(sharedPref);
        USER = SharedP.getUserPrefs(sharedPref);
        PASS = SharedP.getPassPrefs(sharedPref);
        DATABASE = SharedP.getBasePrefs(sharedPref);
        PORT = SharedP.getPortPrefs(sharedPref);

    }
}
