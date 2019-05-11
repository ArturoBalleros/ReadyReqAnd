package com.karveg.readyreq.Utils;

import android.content.SharedPreferences;

import com.karveg.readyreq.App.MyApplication;

public class SharedP {

    public static String getIPSerPrefs(SharedPreferences prefs) {
        return prefs.getString("IPServer", "192.168.0.106");
    }

    public static String getUserPrefs(SharedPreferences prefs) {
        return prefs.getString("User", "programas");
    }

    public static String getPassPrefs(SharedPreferences prefs) {
        return prefs.getString("Password", "56151621");
    }

    public static String getBasePrefs(SharedPreferences prefs) {
        return prefs.getString("Database", "ready");
    }

    public static int getPortPrefs(SharedPreferences prefs) {
        return prefs.getInt("Port", 3306);
    }

    public static void saveIPSer(SharedPreferences prefs, String ipSer) {
        SharedPreferences.Editor editor = prefs.edit();//escribir
        editor.putString("IPServer", ipSer);
        MyApplication.IP_SERVER = ipSer;
        editor.commit();//esto pararía el codigo hasta ejecutarse y guardar todos los valores
        editor.apply();
    }

    public static void saveUser(SharedPreferences prefs, String User) {
        SharedPreferences.Editor editor = prefs.edit();//escribir
        editor.putString("User", User);
        MyApplication.USER= User;
        editor.commit();//esto pararía el codigo hasta ejecutarse y guardar todos los valores
        editor.apply();
    }

    public static void savePass(SharedPreferences prefs, String Pass) {
        SharedPreferences.Editor editor = prefs.edit();//escribir
        editor.putString("Password", Pass);
        MyApplication.PASS= Pass;
        editor.commit();//esto pararía el codigo hasta ejecutarse y guardar todos los valores
        editor.apply();
    }

    public static void saveDatabase(SharedPreferences prefs, String Database) {
        SharedPreferences.Editor editor = prefs.edit();//escribir
        editor.putString("Database", Database);
        MyApplication.DATABASE= Database;
        editor.commit();//esto pararía el codigo hasta ejecutarse y guardar todos los valores
        editor.apply();
    }

    public static void savePort(SharedPreferences prefs, int Port) {
        SharedPreferences.Editor editor = prefs.edit();//escribir
        editor.putInt("Port", Port);
        MyApplication.PORT= Port;
        editor.commit();//esto pararía el codigo hasta ejecutarse y guardar todos los valores
        editor.apply();
    }
}