package com.example.eventtrakingfrontend;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ApiRequest {

    public interface ApiListener {
        void onSuccess(String response);


        void onError(VolleyError error);


    }
    public interface ApiListenerList  {
        void onSuccess(List<Event> events);
        void onError(VolleyError error);
        void onError(Exception e);
    }

    public interface EventListener {
        void onSuccess(List<Event> events);
        void onError(Exception e);
    }

    private final RequestQueue requestQueue;
    private final String url;

    public ApiRequest(RequestQueue requestQueue, String url) {
        this.requestQueue = requestQueue;
        this.url = url;
    }

    public void login(final String username, final String password, final ApiListener listener) {
        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("username", username);
            jsonBody.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, this.url, jsonBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        listener.onSuccess(response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        listener.onError(error);
                    }
                }) {
            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        };

        requestQueue.add(request);
    }

    public void signUp(final String username, final String email, final String password, final ApiListener listener) {
        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("username", username);
            jsonBody.put("email", email);
            jsonBody.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        listener.onSuccess(response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        listener.onError(error);
                    }
                }) {
            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        };

        requestQueue.add(request);
    }

    public void fetchEvents(final EventListener listener) {
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        List<Event> events = new ArrayList<>();
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject eventObj = response.getJSONObject(i);
                                List<String> tags = new ArrayList<>();
                                JSONArray tagsArray = eventObj.getJSONArray("tags");
                                for (int j = 0; j < tagsArray.length(); j++) {
                                    tags.add(tagsArray.getString(j));
                                }
                                Event event = new Event(
                                        eventObj.getInt("eventId"),
                                        eventObj.getInt("userId"),
                                        eventObj.getString("eventTitle"),
                                        eventObj.getString("eventDateTime"),
                                        eventObj.getString("addressName"),
                                        eventObj.getInt("image"),
                                        eventObj.getString("description"),
                                        tags
                                );
                                events.add(event);
                            }
                            listener.onSuccess(events);
                        } catch (Exception e) {
                            listener.onError(e);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        listener.onError(error);
                    }
                });
        requestQueue.add(request);
    }
}
