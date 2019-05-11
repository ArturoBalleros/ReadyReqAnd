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
import com.karveg.readyreq.Activities.PackageActivity;
import com.karveg.readyreq.App.MyApplication;
import com.karveg.readyreq.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class Package {
    private int Id;
    private String Name;
    private int Category;
    private String Commentary;

    public Package() {
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

    public static void getPackage(final Context ctx, int intCode, final AlertDialog progressDialog) {
        progressDialog.show();

        RequestQueue request;
        JsonObjectRequest jsonObjectRequest;

        request = Volley.newRequestQueue(ctx);

        String url = "http://" + MyApplication.IP_SERVER + ":8080/readyreq/paq_search.php?";
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
                    Package p = new Package();
                    p.setId(jsonObject.optInt("Id"));
                    p.setName(jsonObject.optString("Nombre"));
                    p.setCategory(jsonObject.optInt("Categoria"));
                    p.setCommentary(jsonObject.optString("Comentario"));
                    PackageActivity.setValuesUI(p);
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