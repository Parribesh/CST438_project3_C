package com.example.CST438_project3_C;


import androidx.annotation.NonNull;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.net.PasswordAuthentication;

public class CreateUserActivity extends AppCompatActivity {

    private static final String TAG = "MyActivity";

    private EditText eName;
    private EditText ePassword;
    private Button eRegister;

    private FirebaseAuth mAuth;
    DatabaseReference dr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user2);

        eName = findViewById(R.id.etName);
        ePassword = findViewById(R.id.etPassword);
        eRegister = findViewById(R.id.btnLogin);

        mAuth = FirebaseAuth.getInstance();

        eRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userName = eName.getText().toString().trim();
                String password = ePassword.getText().toString().trim();
                mAuth.createUserWithEmailAndPassword(userName, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                           @Override
                           public void onComplete(@NonNull Task<AuthResult> task) {
                               if(task.isSuccessful()){
                                   User user = new User(userName, password);
                                   FirebaseDatabase.getInstance().getReference("users")
                                           .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                           .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                       @Override
                                       public void onComplete(@NonNull Task<Void> task) {
                                           if(task.isSuccessful()){
                                               Toast.makeText(CreateUserActivity.this, "user has been registered", Toast.LENGTH_LONG).show();
                                           }else{
                                               Toast.makeText(CreateUserActivity.this, "Please check your credential/user not registered", Toast.LENGTH_LONG).show();

                                           }
                                       }
                                   });
                               }else{
                                   eName.setError("Should be an Email/Invalid Email");
                                   ePassword.setError("Password Should be atleast 6 char");
                                   Toast.makeText(CreateUserActivity.this, "Email or password not right", Toast.LENGTH_LONG).show();

                               }
                           }
                       }
                        );
//                User user = new User(userName, password);
//                dr.setValue(user);

            }

        });
    }

}
