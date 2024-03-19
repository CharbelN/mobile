package com.example.eventtrakingfrontend;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

public class LoginActivity extends AppCompatActivity {

    private final String apiUrl = "http://192.168.0.105:8080/api/login";
    private RequestQueue requestQueue;

    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button buttonLogin;
    private TextView textViewSignUp; // Initialize this TextView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        // Initialize Volley request queue
        requestQueue = Volley.newRequestQueue(this);

        // Initialize UI elements
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        textViewSignUp = findViewById(R.id.textViewSignUp); // Initialize TextView

        // Set click listener for login button
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve username and password from EditText fields
                String username = editTextUsername.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                // Perform validation (e.g., check if fields are empty)
                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please enter username and password", Toast.LENGTH_SHORT).show();
                } else {
                    // Make API call to authenticate user
                    authenticateUser(username, password);
                }
            }
        });

        // Set click listener for sign up text view
        textViewSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start SignUpActivity
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            }
        });
    }

    private void authenticateUser(String username, String password) {
        // Create an instance of ApiRequest with the requestQueue and API URL
        ApiRequest apiRequest = new ApiRequest(requestQueue, apiUrl);

        // Make the login API call
        apiRequest.login(username, password, new ApiRequest.ApiListener() {
            @Override
            public void onSuccess(String response) {
                // Handle successful response
                // For example, parse the response and navigate to the next screen
                Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                // Start new activity or perform other operations after successful login
            }

            @Override
            public void onError(VolleyError error) {
                // Handle error
                // For example, display an error message to the user
                Toast.makeText(LoginActivity.this, "Login failed: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
