package com.example.eventtrakingfrontend;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerViewEvents);
        progressBar = findViewById(R.id.progressBar);

        // Initialize API request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        // Define API URL
        String apiUrl = "http://192.168.0.100:8080/api/events";

        // Create an instance of ApiRequest
        ApiRequest apiRequest = new ApiRequest(requestQueue, apiUrl);

        // Fetch events from the API
        apiRequest.fetchEvents(new ApiRequest.ApiListenerList<List<Event>>() {
            @Override
            public void onSuccess(List<Event> events) {
                // Hide the progress bar
                progressBar.setVisibility(View.GONE);

                // Set the events to the RecyclerView adapter
                EventAdapter eventAdapter = new EventAdapter(MainActivity.this, events);
                recyclerView.setAdapter(eventAdapter);
            }

            @Override
            public void onError(VolleyError error) {
                // Hide the progress bar
                progressBar.setVisibility(View.GONE);

                // Handle the error
                Toast.makeText(MainActivity.this, "Error fetching events: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Exception e) {

            }
        });
            }

}



