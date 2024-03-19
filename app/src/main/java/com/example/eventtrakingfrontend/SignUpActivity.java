package com.example.eventtrakingfrontend;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class SignUpActivity extends AppCompatActivity {

    private EditText editTextUsername;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonSignUp;

    private ApiRequest apiRequest;
    private String apiUrl = "http://192.168.0.105:8080/api/signup"; // Your API endpoint URL

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_layout);

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        apiRequest = new ApiRequest(requestQueue, apiUrl);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonSignUp = findViewById(R.id.buttonSignUp);

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });
    }

    private void signUp() {
        String username = editTextUsername.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        apiRequest.signUp(username, email, password, new ApiRequest.ApiListener() {
            @Override
            public void onSuccess(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    // Handle success response
                    Toast.makeText(SignUpActivity.this, "Sign up successful", Toast.LENGTH_SHORT).show();
                    // Navigate back to LoginActivity after successful sign-up
                    startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                    finish(); // Close the current activity to prevent going back to it
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(SignUpActivity.this, "Error parsing response", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(VolleyError error) {
                // Handle error response
                Toast.makeText(SignUpActivity.this, "Sign up failed: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
