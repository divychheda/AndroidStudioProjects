package com.example.data_transferapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void tcp_act(View view) {
        Intent intent = new Intent(this,TC_transfer.class);
        startActivity(intent);
    }

    public void udp_act(View view) {
    }


}