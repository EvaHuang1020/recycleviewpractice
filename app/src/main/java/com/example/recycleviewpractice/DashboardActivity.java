package com.example.recycleviewpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class DashboardActivity extends AppCompatActivity {
    String[] features =  new String[]{"看地點","看人員"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


    }
}