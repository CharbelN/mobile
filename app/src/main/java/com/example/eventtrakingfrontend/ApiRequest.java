package com.example.eventtrakingfrontend;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class ApiRequest {

    public interface ApiListener {
        void onSuccess(String response);
        void onError(VolleyError error);
    }

    private final RequestQueue requestQueue;
    private final String url;

    public ApiRequest(RequestQueue requestQueue, String url) {
        this.requestQueue = requestQueue;
        this.url = url;
    }

    public void login(final String username, final String password, final ApiListener listener) {
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                listener.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onError(error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("password", password);
                return params;
            }
        };

        requestQueue.add(request);
    }
}