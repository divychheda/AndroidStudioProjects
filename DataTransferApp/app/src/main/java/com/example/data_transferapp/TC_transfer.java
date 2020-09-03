package com.example.data_transferapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TC_transfer extends AppCompatActivity {

    Thread Connect_thread = null;
    EditText IP, port;
    TextView chatbox;
    EditText etMessage;
    Button btnSend;
    String PC_IP;
    int PC_PORT;
    Button btnConnect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IP = (EditText) findViewById(R.id.etIP);
        port = (EditText) findViewById(R.id.etPort);
        chatbox = (TextView) findViewById(R.id.tvMessages);
        etMessage = (EditText) findViewById(R.id.etMessage);
        btnSend = (Button) findViewById(R.id.btnSend);
        btnConnect = (Button) findViewById(R.id.btnConnect);
    }
}