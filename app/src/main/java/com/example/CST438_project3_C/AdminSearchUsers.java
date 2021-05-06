package com.example.CST438_project3_C;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

//etUsernamebtnSearchUsers
public class AdminSearchUsers extends AppCompatActivity {
    private Button eSearch;
    private EditText eUsername;

    DatabaseReference dr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_search_users);
        eSearch =  findViewById(R.id.btnSearchUsers);
        eUsername =  findViewById(R.id.etUsername);


        eSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = eUsername.getText().toString().trim();
                if(username.isEmpty())
                {
                    //error
                }
                    //query the database
                dr = FirebaseDatabase.getInstance().getReference();
                Query userRef = dr.child("users").orderByChild("userName").startAt(username).endAt(username + "\uf8ff");
                    //populate a list
            }
        });
    }
}