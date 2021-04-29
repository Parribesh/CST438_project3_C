package com.example.CST438_project3_C;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private Button eLogin;
    private Button eRegister;
    private Button eCreateRiddle;
    private Button eSolveRiddle;
    private Button eAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        eLogin = findViewById(R.id.btnLogin);
        eRegister = findViewById(R.id.btnRegister);
        eCreateRiddle = findViewById(R.id.btnCreateRiddle);
        eSolveRiddle = findViewById(R.id.btnSolveRiddle);
        eAdmin = findViewById(R.id.btnAdmin);

        eLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        eRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateUserActivity.class);
                startActivity(intent);
            }
        });

        eCreateRiddle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UserCreateRiddleActivity.class);
                startActivity(intent);
            }
        });
        eSolveRiddle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SolveRiddleActivity.class);
                startActivity(intent);
            }
        });

        eAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AdminActivity.class);
                startActivity(intent);
            }
        });
    }

}