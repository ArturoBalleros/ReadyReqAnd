package com.karveg.readyreq.Utils;

import android.content.SharedPreferences;

import com.karveg.readyreq.App.MyApplication;

public class SharedP {

    public static String getIPSerPrefs(SharedPreferences prefs) {
        return prefs.getString("IPServer", "192.168.0.106");
    }

    public static String getIPSerSQLPrefs(SharedPreferences prefs) {
        return prefs.getString("IPServerMySQL", "192.168.0.106");
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

    public static int getPortHttpPrefs(SharedPreferences prefs) {
        return prefs.getInt("PortHTTP", 8080);
    }

    public static String getHttpPrefs(SharedPreferences prefs) {
        return prefs.getString("HTTP", "http");
    }

    public static void saveIPSer(SharedPreferences prefs, String ipSer) {
        SharedPreferences.Editor editor = prefs.edit();//escribir
        editor.putString("IPServer", ipSer);
        MyApplication.IP_SERVER = ipSer;
        editor.commit();//esto pararía el codigo hasta ejecutarse y guardar todos los valores
        editor.apply();
    }

    public static void saveIPSerSQL(SharedPreferences prefs, String ipSerSQL) {
        SharedPreferences.Editor editor = prefs.edit();//escribir
        editor.putString("IPServerMySQL", ipSerSQL);
        MyApplication.IP_SERVER_SQL = ipSerSQL;
        editor.commit();//esto pararía el codigo hasta ejecutarse y guardar todos los valores
        editor.apply();
    }

    public static void saveUser(SharedPreferences prefs, String User) {
        SharedPreferences.Editor editor = prefs.edit();//escribir
        editor.putString("User", User);
        MyApplication.USER = User;
        editor.commit();//esto pararía el codigo hasta ejecutarse y guardar todos los valores
        editor.apply();
    }

    public static void savePass(SharedPreferences prefs, String Pass) {
        SharedPreferences.Editor editor = prefs.edit();//escribir
        editor.putString("Password", Pass);
        MyApplication.PASS = Pass;
        editor.commit();//esto pararía el codigo hasta ejecutarse y guardar todos los valores
        editor.apply();
    }

    public static void saveDatabase(SharedPreferences prefs, String Database) {
        SharedPreferences.Editor editor = prefs.edit();//escribir
        editor.putString("Database", Database);
        MyApplication.DATABASE = Database;
        editor.commit();//esto pararía el codigo hasta ejecutarse y guardar todos los valores
        editor.apply();
    }

    public static void savePort(SharedPreferences prefs, int Port) {
        SharedPreferences.Editor editor = prefs.edit();//escribir
        editor.putInt("Port", Port);
        MyApplication.PORT = Port;
        editor.commit();//esto pararía el codigo hasta ejecutarse y guardar todos los valores
        editor.apply();
    }

    public static void savePortHttp(SharedPreferences prefs, int PortHttp) {
        SharedPreferences.Editor editor = prefs.edit();//escribir
        editor.putInt("PortHTTP", PortHttp);
        MyApplication.PORTHTTP = PortHttp;
        editor.commit();//esto pararía el codigo hasta ejecutarse y guardar todos los valores
        editor.apply();
    }

    public static void saveHttp(SharedPreferences prefs, String Http) {
        SharedPreferences.Editor editor = prefs.edit();//escribir
        editor.putString("HTTP", Http);
        MyApplication.HTTP = Http;
        editor.commit();//esto pararía el codigo hasta ejecutarse y guardar todos los valores
        editor.apply();
    }
}