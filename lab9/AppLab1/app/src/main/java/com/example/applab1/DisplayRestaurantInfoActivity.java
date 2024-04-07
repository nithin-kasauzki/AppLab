package com.example.applab1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayRestaurantInfoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_restaurant_info);

        TextView tvRestaurantName = findViewById(R.id.tvRestaurantName);
        TextView tvAddress = findViewById(R.id.tvAddress);
        TextView tvPhone = findViewById(R.id.tvPhone);

        Intent intent = getIntent();
        if(intent != null) {
            String restaurantName = intent.getStringExtra("RESTAURANT_NAME");
            String address = intent.getStringExtra("ADDRESS");
            String phone = intent.getStringExtra("PHONE");

            tvRestaurantName.setText(restaurantName);
            tvAddress.setText(address);
            tvPhone.setText(phone);
        }
    }

}