package com.example.library;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
    private EditText emailField, passwordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailField = findViewById(R.id.email);
        passwordField = findViewById(R.id.password);
    }

    public void onLogin(View view) {
        String email = emailField.getText().toString();
        String password = passwordField.getText().toString();

        // Authenticate user (assuming a function authenticateUser exists)
        if (authenticateUser(email, password)) {
            Intent intent = new Intent(LoginActivity.this, BookListActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Invalid login credentials", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean authenticateUser(String email, String password) {
        return true;
    }
}
