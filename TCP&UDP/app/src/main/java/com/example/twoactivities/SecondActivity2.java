package com.example.twoactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

public class SecondActivity2 extends AppCompatActivity {
    EditText IP, port;
    EditText etMessage;
    Button btnSend;
    String PC_IP = "192.168.43.244";
    String modifiedSentence;
    public int PC_PORT = 100;
    byte[] send_data = new byte[2048];
    byte[] receiveData = new byte[2048];
    TextView msgg ;
    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
   // StrictMode.setThreadPolicy(policy);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.activity_second2);
        IP = findViewById(R.id.etIP);
        port = (EditText) findViewById(R.id.etPort);
        etMessage = (EditText) findViewById(R.id.etMessage);
        btnSend = (Button) findViewById(R.id.btnSend);
        PC_IP = IP.getText().toString().trim();
        PC_PORT = Integer.parseInt(port.getText().toString());
        msgg = findViewById(R.id.textView3);

    }
    public void send(View view) throws IOException {
        String messageSEND = etMessage.getText().toString();
        DatagramSocket client_socket = new DatagramSocket(PC_PORT);
        InetAddress IPAddress =  InetAddress.getByName(PC_IP);
        while (true){
            send_data = messageSEND.getBytes();
            DatagramPacket send_packet = new DatagramPacket(send_data,messageSEND.length(), IPAddress, PC_PORT);
            client_socket.send(send_packet);

            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            client_socket.receive(receivePacket);
            modifiedSentence = new String(receivePacket.getData());
            msgg.setText(modifiedSentence);

        }


        }
}
