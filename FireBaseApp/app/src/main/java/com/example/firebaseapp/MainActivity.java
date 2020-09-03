package com.example.firebaseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    EditText text1;

    DatabaseReference ref;
    String textsent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this,"FireBase Connected",Toast.LENGTH_SHORT).show();
        text1 = findViewById(R.id.editTextTextPersonName);

        ref = FirebaseDatabase.getInstance().getReference();
        text1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                textsent=text1.getText().toString().trim();
                ref.removeValue();
                ref.push().setValue(textsent);
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

    }
}