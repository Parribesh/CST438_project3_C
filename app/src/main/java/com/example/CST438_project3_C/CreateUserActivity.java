package com.example.CST438_project3_C;


import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.CST438_project3_C.data.User;
import com.example.CST438_project3_C.data.UserDAO;
import com.example.CST438_project3_C.data.UserDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateUserActivity extends AppCompatActivity {

    private static final String TAG = "MyActivity";

    private EditText eName;
    private EditText ePassword;
    private Button eRegister;

    FirebaseDatabase fd;
    DatabaseReference dr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user2);

        eName = findViewById(R.id.etName);
        ePassword = findViewById(R.id.etPassword);
        eRegister = findViewById(R.id.btnLogin);



        eRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fd = FirebaseDatabase.getInstance();
                dr = fd.getReference("users");

                String userName = eName.getText().toString();
                String password = ePassword.getText().toString();

                User user = new User(userName, password);
                dr.setValue(user);

            }

        });
    }

}
