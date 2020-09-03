package com.example.twoactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class SecondActivity extends AppCompatActivity {
    Thread Connect_thread ;
    EditText IP, port;
    TextView chatbox;
    EditText etMessage;
    Button btnSend;
    Button btnConnect;
    String PC_IP="100";
    int PC_PORT=100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.activity_second);
        IP = (EditText)findViewById(R.id.etIP);
        port = (EditText)findViewById(R.id.etPort);
        chatbox = (TextView)findViewById(R.id.tvMessages);
        etMessage = (EditText)findViewById(R.id.etMessage);
        btnSend = (Button)findViewById(R.id.btnSend);
        btnConnect = (Button)findViewById(R.id.btnConnect);
try {
    btnConnect.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            chatbox.setText("");
            PC_IP = IP.getText().toString().trim();
            PC_PORT = Integer.parseInt(port.getText().toString());
            Connect_thread = new Thread(new Connect_thread());
            Connect_thread.start();
        }
    });
    btnSend.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String message = etMessage.getText().toString();
            if (!message.isEmpty()) {
                new Thread(new Transmit_thread(message)).start();
            }
        }
    });
}catch (NullPointerException n) {
n.printStackTrace();
}

    }
    private PrintWriter output;
    private BufferedReader input;
    class Connect_thread implements Runnable {
        public void run() {
            Socket socket;
            try {
                socket = new Socket(PC_IP, PC_PORT);
                output = new PrintWriter(socket.getOutputStream());
                input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        chatbox.setText("Connected\n");
                    }
                });
                new Thread(new Receiver_thread()).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    class Receiver_thread implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    final String message = input.readLine();
                    if (message != null) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                chatbox.append("PC: " + message + "\n");
                            }
                        });
                    } else {
                        Connect_thread = new Thread(new Connect_thread());
                        Connect_thread.start();
                        return;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    class Transmit_thread implements Runnable {
        private String message;
        Transmit_thread(String message) {
            this.message = message;
        }
        @Override
        public void run() {
            output.write(message+"\n");
            output.flush();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    chatbox.append("Client: " + message + "\n");
                    etMessage.setText("");
                }
            });
        }
    }


    }
