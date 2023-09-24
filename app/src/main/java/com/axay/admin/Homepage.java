package com.axay.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Homepage extends AppCompatActivity {
    ImageButton pro1, pro2, pro3, pro4, pro5, pro6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        pro1 = findViewById(R.id.pro1);
        pro1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Homepage.this, Laptop_insert.class);
                startActivity(intent);
            }
        });


        pro2 = findViewById(R.id.pro2);
        pro2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Homepage.this, PcInsert.class);
                startActivity(intent);
            }
        });

        pro3 = findViewById(R.id.pro3);
        pro3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Homepage.this, TvInsert.class);
                startActivity(intent);
            }
        });
        pro4 = findViewById(R.id.pro4);
        pro4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Homepage.this, AcInsert.class);
                startActivity(intent);
            }
        });
        pro5 = findViewById(R.id.pro5);
        pro5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Homepage.this, WcInsert.class);
                startActivity(intent);
            }
        });

        pro6 = findViewById(R.id.pro6);
        pro6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Homepage.this, OvenInsert.class);
                startActivity(intent);
            }
        });


    }
}