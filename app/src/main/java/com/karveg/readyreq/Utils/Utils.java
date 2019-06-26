/*
   Autor: Arturo Balleros Albillo
 */
package com.karveg.readyreq.Utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.karveg.readyreq.Activities.MainActivity;
import com.karveg.readyreq.App.MyApplication;
import com.karveg.readyreq.Fragments.ActoFragment;
import com.karveg.readyreq.Fragments.AuthFragment;
import com.karveg.readyreq.Fragments.DatEspFragment;
import com.karveg.readyreq.Fragments.GenericFragment;
import com.karveg.readyreq.Fragments.ObjecFragment;
import com.karveg.readyreq.Fragments.RequFragment;
import com.karveg.readyreq.Fragments.SecExcFragment;
import com.karveg.readyreq.Fragments.SecNorFragment;
import com.karveg.readyreq.Fragments.SourFragment;
import com.karveg.readyreq.Models.Generic;
import com.karveg.readyreq.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Utils {

    public static Date StringToDate(String input, boolean MySQL) {
        Date date = new Date();
        try {
            date = MySQL ? new SimpleDateFormat("yyyy-MM-dd").parse(input) : new SimpleDateFormat("dd/MM/yyyy").parse(input);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String DateToString(Date input, boolean MySQL) {
        DateFormat df = MySQL ? new SimpleDateFormat("yyyy/MM/dd") : new SimpleDateFormat("dd/MM/yyyy");
        return df.format(input);
    }

    public static String encrypt(String input) {
        String claveEncriptada = "";
        int cont = 0;
        Random r = new Random();
        String[] valoresAleatorios = new String[]{"a", "A", "b", "B", "c", "C", "d", "D", "e", "E", "f", "F", "g", "G", "h", "H", "j", "J", "k", "K", "l", "L", "m", "M", "n", "N", "o", "O", "p", "P", "q", "Q", "r", "R", "s", "S", "t", "T", "u", "U", "v", "V", "w", "W", "x", "X", "y", "Y", "z", "Z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

        for (int i = 0; i < input.length(); i++) {
            claveEncriptada += input.charAt(i);
            cont++;
            for (int j = 0; j < cont; j++)
                claveEncriptada += valoresAleatorios[r.nextInt(valoresAleatorios.length)];
        }
        return claveEncriptada;
    }

    public static int imageSelect(int mode) {
        if (MyApplication.GRUPO == mode) return R.drawable.ic_grupo;
        else if (MyApplication.PAQUETES == mode) return R.drawable.ic_paque;
        else if (MyApplication.OBJETIVOS == mode) return R.drawable.ic_objet;
        else if (MyApplication.ACTORES == mode) return R.drawable.ic_actor;
        else if (MyApplication.REQ_FUNC == mode) return R.drawable.ic_rf;
        else if (MyApplication.REQ_NO_FUN == mode) return R.drawable.ic_rnf;
        else if (MyApplication.REQ_INFO == mode) return R.drawable.ic_ri;
        else return -1;
    }

    public static String getUrlList(int mode) {
        if (MyApplication.GRUPO == mode)
            return MyApplication.HTTP + "://" + MyApplication.IP_SERVER + ":" + MyApplication.PORTHTTP + "/readyreq/group_frag_list.php";
        else if (MyApplication.PAQUETES == mode)
            return MyApplication.HTTP + "://" + MyApplication.IP_SERVER + ":" + MyApplication.PORTHTTP + "/readyreq/paq_frag_list.php";
        else if (MyApplication.OBJETIVOS == mode)
            return MyApplication.HTTP + "://" + MyApplication.IP_SERVER + ":" + MyApplication.PORTHTTP + "/readyreq/objet_frag_list.php";
        else if (MyApplication.ACTORES == mode)
            return MyApplication.HTTP + "://" + MyApplication.IP_SERVER + ":" + MyApplication.PORTHTTP + "/readyreq/actor_frag_list.php";
        else if (MyApplication.REQ_FUNC == mode)
            return MyApplication.HTTP + "://" + MyApplication.IP_SERVER + ":" + MyApplication.PORTHTTP + "/readyreq/reqfun_frag_list.php";
        else if (MyApplication.REQ_NO_FUN == mode)
            return MyApplication.HTTP + "://" + MyApplication.IP_SERVER + ":" + MyApplication.PORTHTTP + "/readyreq/reqnfun_frag_list.php";
        else if (MyApplication.REQ_INFO == mode)
            return MyApplication.HTTP + "://" + MyApplication.IP_SERVER + ":" + MyApplication.PORTHTTP + "/readyreq/reqinfo_frag_list.php";
        else return "";
    }

    public static int deterTipoReq(int imag) {
        int tipo = 0;
        if (imag == R.drawable.ic_ri) tipo = 1;
        if (imag == R.drawable.ic_rnf) tipo = 2;
        if (imag == R.drawable.ic_rf) tipo = 3;
        return tipo;
    }

    public static String getUrlDelete(int mode, int flagTab, int id, Generic g) {
        String resul = "";

        int idO = g.getId();
        int tipoReq = deterTipoReq(g.getImage());
        String Descrip = g.getName();

        if (MyApplication.GRUPO == mode && flagTab == MyApplication.NOTHING)
            resul = MyApplication.HTTP + "://" + MyApplication.IP_SERVER + ":" + MyApplication.PORTHTTP + "/readyreq/group_delete.php?a=" + id;
        if (MyApplication.PAQUETES == mode && flagTab == MyApplication.NOTHING)
            resul = MyApplication.HTTP + "://" + MyApplication.IP_SERVER + ":" + MyApplication.PORTHTTP + "/readyreq/paq_delete.php?a=" + id;
        if (MyApplication.OBJETIVOS == mode && flagTab == MyApplication.NOTHING)
            resul = MyApplication.HTTP + "://" + MyApplication.IP_SERVER + ":" + MyApplication.PORTHTTP + "/readyreq/objet_delete.php?a=" + id;
        if (MyApplication.ACTORES == mode && flagTab == MyApplication.NOTHING)
            resul = MyApplication.HTTP + "://" + MyApplication.IP_SERVER + ":" + MyApplication.PORTHTTP + "/readyreq/actor_delete.php?a=" + id;
        if (MyApplication.REQ_FUNC == mode && flagTab == MyApplication.NOTHING)
            resul = MyApplication.HTTP + "://" + MyApplication.IP_SERVER + ":" + MyApplication.PORTHTTP + "/readyreq/reqfun_delete.php?a=" + id;
        if (MyApplication.REQ_NO_FUN == mode && flagTab == MyApplication.NOTHING)
            resul = MyApplication.HTTP + "://" + MyApplication.IP_SERVER + ":" + MyApplication.PORTHTTP + "/readyreq/reqnfun_delete.php?a=" + id;
        if (MyApplication.REQ_INFO == mode && flagTab == MyApplication.NOTHING)
            resul = MyApplication.HTTP + "://" + MyApplication.IP_SERVER + ":" + MyApplication.PORTHTTP + "/readyreq/reqinfo_delete.php?a=" + id;

        if (MyApplication.OBJETIVOS == mode && flagTab == MyApplication.AUTH)
            resul = MyApplication.HTTP + "://" + MyApplication.IP_SERVER + ":" + MyApplication.PORTHTTP + "/readyreq/rel_delete.php?a=ObjAuto where idobj = " + id + " and idautor = " + idO;
        if (MyApplication.OBJETIVOS == mode && flagTab == MyApplication.SOUR)
            resul = MyApplication.HTTP + "://" + MyApplication.IP_SERVER + ":" + MyApplication.PORTHTTP + "/readyreq/rel_delete.php?a=ObjFuen where idobj = " + id + " and idfuen = " + idO;
        if (MyApplication.OBJETIVOS == mode && flagTab == MyApplication.OBJE)
            resul = MyApplication.HTTP + "://" + MyApplication.IP_SERVER + ":" + MyApplication.PORTHTTP + "/readyreq/rel_delete.php?a=ObjSubobj where idobj = " + id + " and idsubobj = " + idO;

        if (MyApplication.ACTORES == mode && flagTab == MyApplication.AUTH)
            resul = MyApplication.HTTP + "://" + MyApplication.IP_SERVER + ":" + MyApplication.PORTHTTP + "/readyreq/rel_delete.php?a=ActAuto where idact = " + id + " and idautor = " + idO;
        if (MyApplication.ACTORES == mode && flagTab == MyApplication.SOUR)
            resul = MyApplication.HTTP + "://" + MyApplication.IP_SERVER + ":" + MyApplication.PORTHTTP + "/readyreq/rel_delete.php?a=ActFuen where idact = " + id + " and idfuen = " + idO;

        if (MyApplication.REQ_NO_FUN == mode && flagTab == MyApplication.AUTH)
            resul = MyApplication.HTTP + "://" + MyApplication.IP_SERVER + ":" + MyApplication.PORTHTTP + "/readyreq/rel_delete.php?a=ReqNAuto where idreq = " + id + " and idautor = " + idO;
        if (MyApplication.REQ_NO_FUN == mode && flagTab == MyApplication.SOUR)
            resul = MyApplication.HTTP + "://" + MyApplication.IP_SERVER + ":" + MyApplication.PORTHTTP + "/readyreq/rel_delete.php?a=ReqNFuen where idreq = " + id + " and idfuen = " + idO;
        if (MyApplication.REQ_NO_FUN == mode && flagTab == MyApplication.OBJE)
            resul = MyApplication.HTTP + "://" + MyApplication.IP_SERVER + ":" + MyApplication.PORTHTTP + "/readyreq/rel_delete.php?a=ReqNObj where idreq = " + id + " and idobj = " + idO;
        if (MyApplication.REQ_NO_FUN == mode && flagTab == MyApplication.REQU)
            resul = MyApplication.HTTP + "://" + MyApplication.IP_SERVER + ":" + MyApplication.PORTHTTP + "/readyreq/rel_delete.php?a=ReqNReqR where idreq = " + id + " and idreqr = " + idO + " and tiporeq = " + tipoReq;

        if (MyApplication.REQ_INFO == mode && flagTab == MyApplication.AUTH)
            resul = MyApplication.HTTP + "://" + MyApplication.IP_SERVER + ":" + MyApplication.PORTHTTP + "/readyreq/rel_delete.php?a=ReqIAuto where idreq = " + id + " and idautor = " + idO;
        if (MyApplication.REQ_INFO == mode && flagTab == MyApplication.SOUR)
            resul = MyApplication.HTTP + "://" + MyApplication.IP_SERVER + ":" + MyApplication.PORTHTTP + "/readyreq/rel_delete.php?a=ReqIFuen where idreq = " + id + " and idfuen = " + idO;
        if (MyApplication.REQ_INFO == mode && flagTab == MyApplication.OBJE)
            resul = MyApplication.HTTP + "://" + MyApplication.IP_SERVER + ":" + MyApplication.PORTHTTP + "/readyreq/rel_delete.php?a=ReqIObj where idreq = " + id + " and idobj = " + idO;
        if (MyApplication.REQ_INFO == mode && flagTab == MyApplication.REQU)
            resul = MyApplication.HTTP + "://" + MyApplication.IP_SERVER + ":" + MyApplication.PORTHTTP + "/readyreq/rel_delete.php?a=ReqIReqR where idreq = " + id + " and idreqr = " + idO + " and tiporeq = " + tipoReq;
        if (MyApplication.REQ_INFO == mode && flagTab == MyApplication.DAT_ESP)
            resul = MyApplication.HTTP + "://" + MyApplication.IP_SERVER + ":" + MyApplication.PORTHTTP + "/readyreq/rel_delete.php?a=ReqIDatEsp where idreq = " + id + " and descrip = '" + Descrip + "'";

        if (MyApplication.REQ_FUNC == mode && flagTab == MyApplication.AUTH)
            resul = MyApplication.HTTP + "://" + MyApplication.IP_SERVER + ":" + MyApplication.PORTHTTP + "/readyreq/rel_delete.php?a=ReqAuto where idreq = " + id + " and idautor = " + idO;
        if (MyApplication.REQ_FUNC == mode && flagTab == MyApplication.SOUR)
            resul = MyApplication.HTTP + "://" + MyApplication.IP_SERVER + ":" + MyApplication.PORTHTTP + "/readyreq/rel_delete.php?a=ReqFuen where idreq = " + id + " and idfuen = " + idO;
        if (MyApplication.REQ_FUNC == mode && flagTab == MyApplication.OBJE)
            resul = MyApplication.HTTP + "://" + MyApplication.IP_SERVER + ":" + MyApplication.PORTHTTP + "/readyreq/rel_delete.php?a=ReqObj where idreq = " + id + " and idobj = " + idO;
        if (MyApplication.REQ_FUNC == mode && flagTab == MyApplication.REQU)
            resul = MyApplication.HTTP + "://" + MyApplication.IP_SERVER + ":" + MyApplication.PORTHTTP + "/readyreq/rel_delete.php?a=ReqReqR where idreq = " + id + " and idreqr = " + idO + " and tiporeq = " + tipoReq;
        if (MyApplication.REQ_FUNC == mode && flagTab == MyApplication.ACTO)
            resul = MyApplication.HTTP + "://" + MyApplication.IP_SERVER + ":" + MyApplication.PORTHTTP + "/readyreq/rel_delete.php?a=ReqAct where idreq = " + id + " and idact = " + idO;
        if (MyApplication.REQ_FUNC == mode && flagTab == MyApplication.SEC_NOR)
            resul = MyApplication.HTTP + "://" + MyApplication.IP_SERVER + ":" + MyApplication.PORTHTTP + "/readyreq/rel_delete.php?a=ReqSecNor where idreq = " + id + " and descrip = '" + Descrip + "'";
        if (MyApplication.REQ_FUNC == mode && flagTab == MyApplication.SEC_EXC)
            resul = MyApplication.HTTP + "://" + MyApplication.IP_SERVER + ":" + MyApplication.PORTHTTP + "/readyreq/rel_delete.php?a=ReqSecExc where idreq = " + id + " and descrip = '" + Descrip + "'";

        return resul;
    }

    public static String getNameSearch(int mode) {
        if (MyApplication.GRUPO == mode) return "group_frag_list_search";
        else if (MyApplication.PAQUETES == mode) return "paq_frag_list_search";
        else if (MyApplication.OBJETIVOS == mode) return "objet_frag_list_search";
        else if (MyApplication.ACTORES == mode) return "actor_frag_list_search";
        else if (MyApplication.REQ_FUNC == mode) return "reqfun_frag_list_search";
        else if (MyApplication.REQ_NO_FUN == mode) return "reqnfun_frag_list_search";
        else if (MyApplication.REQ_INFO == mode) return "reqinfo_frag_list_search";
        else return "";
    }

    public static void showDeleteItems(final List<Generic> objects, final int id, final Context ctx, final int mode, final int flagTab, final AlertDialog progressDialog) {

        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(ctx);
        builder.setTitle(R.string.delete);
        builder.setMessage(ctx.getString(R.string.quest_delete));
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Se recorre al reves para que evitar problemas con punteros
                Generic g;
                for (int i = (objects.size() - 1); i >= 0; i--) {
                    g = objects.get(i);
                    if (g.isSelected()) {
                        objects.remove(i);
                        Utils.create_update_delete(ctx, Utils.getUrlDelete(mode, flagTab, id, g), progressDialog, mode, false);
                    }
                }
                updateFragment(flagTab);
            }
        });

        android.support.v7.app.AlertDialog dialog = builder.create();
        dialog.show();
    }

    public static void showCtxDelete(final List<Generic> objects, final int pos, final int id, final Context ctx, final int mode, final int flagTab, final AlertDialog progressDialog) {

        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(ctx);
        builder.setTitle(R.string.delete);
        builder.setMessage(objects.get(pos).getName() + "\n" + ctx.getString(R.string.quest_delete));
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (id != MyApplication.NOTHING) {
                    Utils.create_update_delete(ctx, Utils.getUrlDelete(mode, flagTab, id, objects.get(pos)), progressDialog, mode, false);
                    objects.remove(pos);
                    updateFragment(flagTab);
                }
            }
        });

        android.support.v7.app.AlertDialog dialog = builder.create();
        dialog.show();
    }

    private static void updateFragment(int flagTab) {
        if (flagTab == MyApplication.NOTHING) GenericFragment.updateRecyclerView();
        if (flagTab == MyApplication.AUTH) AuthFragment.updateRecyclerView();
        if (flagTab == MyApplication.SOUR) SourFragment.updateRecyclerView();
        if (flagTab == MyApplication.OBJE) ObjecFragment.updateRecyclerView();
        if (flagTab == MyApplication.REQU) RequFragment.updateRecyclerView();
        if (flagTab == MyApplication.ACTO) ActoFragment.updateRecyclerView();
        if (flagTab == MyApplication.SEC_NOR) SecNorFragment.updateRecyclerView();
        if (flagTab == MyApplication.SEC_EXC) SecExcFragment.updateRecyclerView();
        if (flagTab == MyApplication.DAT_ESP) DatEspFragment.updateRecyclerView();
    }

    public static void create_update_delete(final Context ctx, String url, final AlertDialog progressDialog, final int mode, final boolean salt) {
        progressDialog.show();

        RequestQueue request;
        JsonObjectRequest jsonObjectRequest;

        request = Volley.newRequestQueue(ctx);

        url = url.replace(" ", "%20");
        url = url.replace("\n", "%0A");

        jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                JSONArray json;
                JSONObject jsonObject = null;
                json = response.optJSONArray("Resul");
                try {
                    jsonObject = json.getJSONObject(0);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (jsonObject.optString("a").equals("No1"))
                    Toast.makeText(ctx, R.string.error_empty_param, Toast.LENGTH_SHORT).show();
                else if (jsonObject.optString("a").equals("No2"))
                    Toast.makeText(ctx, R.string.error_read_file, Toast.LENGTH_SHORT).show();
                else if (jsonObject.optString("a").equals("No3"))
                    Toast.makeText(ctx, R.string.error_connect, Toast.LENGTH_SHORT).show();
                else if (jsonObject.optString("a").equals("No4"))
                    Toast.makeText(ctx, R.string.error_query, Toast.LENGTH_SHORT).show();
                else {
                    Toast.makeText(ctx, R.string.success, Toast.LENGTH_SHORT).show();
                    if (salt) {
                        Intent i = new Intent(ctx, MainActivity.class);
                        i.putExtra("Fragment", mode);
                        ctx.startActivity(i);
                        ((Activity) ctx).finish();
                    }
                    if (mode == MyApplication.DAT_ESP) DatEspFragment.updateDatEsp();
                    if (mode == MyApplication.SEC_NOR) SecNorFragment.updateSecNor();
                    if (mode == MyApplication.SEC_EXC) SecExcFragment.updateSecExc();
                }
                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ctx, "error: " + error.toString(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }) {
            @Override
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                try {
                    String jsonString = new String(response.data,
                            HttpHeaderParser.parseCharset(response.headers, PROTOCOL_CHARSET));
                    JSONObject result = null;
                    if (jsonString != null && jsonString.length() > 0)
                        result = new JSONObject(jsonString);
                    return Response.success(result,
                            HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    return Response.error(new ParseError(e));
                } catch (JSONException je) {
                    return Response.error(new ParseError(je));
                }
            }
        };

        request.add(jsonObjectRequest);

    }

    public static void saveObject(final Context ctx, String url) {
        url = url.replace(" ", "%20");

        RequestQueue request;
        JsonObjectRequest jsonObjectRequest;

        request = Volley.newRequestQueue(ctx);

        jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                JSONArray json;
                JSONObject jsonObject = null;
                json = response.optJSONArray("Resul");
                try {
                    jsonObject = json.getJSONObject(0);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (jsonObject.optString("a").equals("No1"))
                    Toast.makeText(ctx, R.string.error_empty_param, Toast.LENGTH_SHORT).show();
                if (jsonObject.optString("a").equals("No2"))
                    Toast.makeText(ctx, R.string.error_read_file, Toast.LENGTH_SHORT).show();
                if (jsonObject.optString("a").equals("No3"))
                    Toast.makeText(ctx, R.string.error_connect, Toast.LENGTH_SHORT).show();
                if (jsonObject.optString("a").equals("No4"))
                    Toast.makeText(ctx, R.string.error_query, Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ctx, "error: " + error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                try {
                    String jsonString = new String(response.data,
                            HttpHeaderParser.parseCharset(response.headers, PROTOCOL_CHARSET));
                    JSONObject result = null;
                    if (jsonString != null && jsonString.length() > 0)
                        result = new JSONObject(jsonString);
                    return Response.success(result,
                            HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    return Response.error(new ParseError(e));
                } catch (JSONException je) {
                    return Response.error(new ParseError(je));
                }
            }
        };

        request.add(jsonObjectRequest);
    }

    public static List<Generic> JSONArrayToListGeneric(JSONArray json, int mode) {
        List<Generic> list = new ArrayList<>();
        JSONObject jsonObject = null;
        Generic g;
        try {
            for (int i = 0; i < json.length(); i++) {
                jsonObject = json.getJSONObject(i);
                if (mode == MyApplication.GRUPO) {
                    g = new Generic(jsonObject.optInt("Id"), jsonObject.optString("Nombre"), R.drawable.ic_grupo);
                    list.add(g);
                }
                if (mode == MyApplication.PAQUETES) {
                    g = new Generic(jsonObject.optInt("Id"), jsonObject.optString("Nombre"), R.drawable.ic_paque);
                    list.add(g);
                }
                if (mode == MyApplication.OBJETIVOS) {
                    g = new Generic(jsonObject.optInt("Id"), jsonObject.optString("Nombre"), R.drawable.ic_objet);
                    list.add(g);
                }
                if (mode == MyApplication.ACTORES) {
                    g = new Generic(jsonObject.optInt("Id"), jsonObject.optString("Nombre"), R.drawable.ic_actor);
                    list.add(g);
                }
                if (mode == MyApplication.REQU) {
                    if (jsonObject.optInt("Tipo") == 1)
                        g = new Generic(jsonObject.optInt("Id"), jsonObject.optString("Nombre"), R.drawable.ic_ri);
                    else if (jsonObject.optInt("Tipo") == 2)
                        g = new Generic(jsonObject.optInt("Id"), jsonObject.optString("Nombre"), R.drawable.ic_rnf);
                    else
                        g = new Generic(jsonObject.optInt("Id"), jsonObject.optString("Nombre"), R.drawable.ic_rf);
                    list.add(g);
                }
                if (mode == MyApplication.NOTHING) {
                    g = new Generic(i + 1, jsonObject.optString("Descrip"));
                    list.add(g);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    //Traducir
    public static void showAlertForItem(final List<Generic> objects, final Context ctx, final int mode, final int id) {

        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(ctx);//creo el alert
        builder.setTitle(R.string.new_desc);

        //inflo la vista, rescato la UI y le pongo titulo
        View viewInflated = LayoutInflater.from(ctx).inflate(R.layout.dialog_item, null);
        final EditText input = viewInflated.findViewById(R.id.editTextCtxMenu);
        builder.setView(viewInflated);
        builder.setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String text = input.getText().toString().trim();//quitar los espacios de delante y detras
                if (text.length() > 0) {
                    Generic g = new Generic(objects.size() + 1, text);
                    objects.add(g);
                    String url = MyApplication.HTTP + "://" + MyApplication.IP_SERVER + ":" + MyApplication.PORTHTTP + "/readyreq/rel_create.php?";
                    if (mode == MyApplication.SEC_NOR) url += "a=ReqSecNor(idreq,descrip)&";
                    if (mode == MyApplication.SEC_EXC) url += "a=ReqSecExc(idreq,descrip)&";
                    if (mode == MyApplication.DAT_ESP) url += "a=ReqIDatEsp(idreq,descrip)&";
                    url += "b=" + id + ",'" + text + "'";
                    if (id != MyApplication.NOTHING) saveObject(ctx, url);
                }
                updateFragment(mode);
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        android.support.v7.app.AlertDialog dialog = builder.create();
        dialog.show();
    }
}