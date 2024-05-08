package com.example.applab3;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class UserDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        TextView tvName = findViewById(R.id.tvName);
        TextView tvEmail = findViewById(R.id.tvEmail);
        TextView tvPhone = findViewById(R.id.tvPhone);

        String name = getIntent().getStringExtra("Name");
        String email = getIntent().getStringExtra("Email");
        String phone = getIntent().getStringExtra("Phone");

        tvName.setText(name);
        tvEmail.setText(email);
        tvPhone.setText(phone);
    }
}
