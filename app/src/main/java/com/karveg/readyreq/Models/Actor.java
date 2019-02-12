package com.karveg.readyreq.Models;

import android.app.AlertDialog;
import android.content.Context;
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
import com.karveg.readyreq.Activities.ActorActivity;
import com.karveg.readyreq.Activities.GroupActivity;
import com.karveg.readyreq.App.MyApplication;
import com.karveg.readyreq.R;
import com.karveg.readyreq.Utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class
Actor implements Serializable {
    private int Id;
    private String Name;
    private String Description;
    private int Comple;
    private String DescComple;
    private int Category;
    private String Commentary;
    private List<Generic> Autors = new ArrayList<>();
    private List<Generic> Sources= new ArrayList<>();

    public Actor() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getComple() {
        return Comple;
    }

    public void setComple(int comple) {
        Comple = comple;
    }

    public String getDescComple() {
        return DescComple;
    }

    public void setDescComple(String descComple) {
        DescComple = descComple;
    }

    public int getCategory() {
        return Category;
    }

    public void setCategory(int category) {
        Category = category;
    }

    public String getCommentary() {
        return Commentary;
    }

    public void setCommentary(String commentary) {
        Commentary = commentary;
    }

    public List<Generic> getAutors() {
        return Autors;
    }

    public void setAutors(List<Generic> autors) {
        Autors = autors;
    }

    public List<Generic> getSources() {
        return Sources;
    }

    public void setSources(List<Generic> sources) {
        Sources = sources;
    }

    public static void getActor(final Context ctx, int intCode, final AlertDialog progressDialog) {
        progressDialog.show();

        RequestQueue request;
        JsonObjectRequest jsonObjectRequest;

        request = Volley.newRequestQueue(ctx);

        String url = "http://" + MyApplication.IP_SERVER + ":8080/readyreq/actor_search.php?";
        url += "a=" + intCode;
        url = url.replace(" ", "%20");
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
                else if (jsonObject.optString("a").equals("No5"))
                    Toast.makeText(ctx, R.string.error_empty_query, Toast.LENGTH_SHORT).show();
                else {
                    Actor a = new Actor();
                    a.setId(jsonObject.optInt("Id"));
                    a.setName(jsonObject.optString("Nombre"));
                    a.setDescription(jsonObject.optString("Descripcion"));
                    a.setComple(jsonObject.optInt("Complejidad"));
                    a.setDescComple(jsonObject.optString("DescComple"));
                    a.setCategory(jsonObject.optInt("Categoria"));
                    a.setCommentary(jsonObject.optString("Comentario"));

                    json = response.optJSONArray("Resul2");
                    if (json != null)
                        a.setAutors(Utils.JSONArrayToListGeneric(json,MyApplication.GRUPO));
                    else
                        a.setAutors(new ArrayList<Generic>());

                    json = response.optJSONArray("Resul3");
                    if (json != null)
                        a.setSources(Utils.JSONArrayToListGeneric(json,MyApplication.GRUPO));
                    else a.setSources(new ArrayList<Generic>());

                   ActorActivity.setValueActor(a);
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

    public static void getIdActor(final Context ctx, String Name, final AlertDialog progressDialog) {
        progressDialog.show();

        RequestQueue request;
        JsonObjectRequest jsonObjectRequest;

        request = Volley.newRequestQueue(ctx);

        String url = "http://" + MyApplication.IP_SERVER + ":8080/readyreq/actor_id.php?";
        url += "a=" + Name;
        url = url.replace(" ", "%20");
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
                else if (jsonObject.optString("a").equals("No5"))
                    Toast.makeText(ctx, R.string.error_empty_query, Toast.LENGTH_SHORT).show();
                else {
                    ActorActivity.saveObjects(ctx,jsonObject.optInt("Id"));
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
}
