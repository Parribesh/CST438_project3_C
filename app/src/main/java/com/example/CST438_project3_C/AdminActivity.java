package com.example.CST438_project3_C;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class AdminActivity extends AppCompatActivity {

    private Button eManageUsers;
    private Button eManageRiddles;
    private Button eHome;
    private Button eManageAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        eManageUsers = findViewById(R.id.btnManageUsers);
        eManageRiddles = findViewById(R.id.btnManageRiddles);
        eHome = findViewById(R.id.btnHome);
        eManageAccount = findViewById(R.id.btnManageAdmin);

        eManageUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivity.this, AdminSerachUsers.class);
                startActivity(intent);
            }
        });
/*
        eManageRiddles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        eHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        eManageAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
*/
    }

}