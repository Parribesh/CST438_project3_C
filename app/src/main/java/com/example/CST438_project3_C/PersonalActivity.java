package com.example.CST438_project3_C;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PersonalActivity extends AppCompatActivity {

    private Button eCreateRdl;
    private Button eSolveRdl;
    private Button eYourRdl;
    private Button eLogOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);

        eCreateRdl = findViewById(R.id.btnCreateRdl);
        eSolveRdl = findViewById(R.id.btnSolveRdl);
        eYourRdl = findViewById(R.id.btnYourRdl);
        eLogOut = findViewById(R.id.btnLogOut);


        eCreateRdl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonalActivity.this, UserCreateRiddleActivity.class);
                startActivity(intent);
            }
        });
        eSolveRdl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonalActivity.this, SolveRiddleActivity.class);
                startActivity(intent);
            }
        });

        eLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(PersonalActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        eYourRdl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(PersonalActivity.this, UserDeleteRiddleActivity.class);
                startActivity(intent);
            }
        });
    }
}