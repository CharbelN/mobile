package com.example.eventtrakingfrontend;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

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

    public void login(final ApiListener listener) {
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
        });

        requestQueue.add(request);
    }
}
