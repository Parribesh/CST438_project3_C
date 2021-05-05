package com.example.CST438_project3_C;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserDeleteRiddleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_delete_riddles);

        String loggedUser = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference dr = FirebaseDatabase.getInstance().getReference()
                .child("riddles").child(loggedUser);

        dr.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                
            }
        });
/*
        if(task.isSuccessful()){
            String value = task.getResult().getValue().toString();
            String edited = value.substring(1, value.length()-1);
            String [] strings = edited.split("=", 2);
        }
  */

        Button returnButton = findViewById(R.id.user_delete_return_button);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent intent = new Intent(UserDeleteRiddleActivity.this
                            , MainActivity.class);
                    startActivity(intent);
            }
        });
    }

}
