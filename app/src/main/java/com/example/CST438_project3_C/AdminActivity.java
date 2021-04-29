package com.example.CST438_project3_C;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private Button eEditUser;
    private Button eDeleteUser;
    private Button eEditRiddle;
    private Button eDeleteRiddle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        eEditUser = findViewById(R.id.btnEditUser);
        eDeleteUser = findViewById(R.id.btnRegister);
        eEditRiddle = findViewById(R.id.btnCreateRiddle);
        eDeleteRiddle = findViewById(R.id.btnSolveRiddle);

        eEditUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        eDeleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        eEditRiddle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        eDeleteRiddle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

    }

}