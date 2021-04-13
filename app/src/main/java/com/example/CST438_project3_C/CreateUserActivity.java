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

public class CreateUserActivity extends AppCompatActivity {

    private static final String TAG = "MyActivity";

    private EditText eName;
    private EditText ePassword;
    private Button eRegister;



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
                User user = new User(eName.getText().toString(), ePassword.getText().toString());
                UserDatabase db = UserDatabase.getInMemoryDatabase(v.getContext());
                if(db.getUserDAO().getUserByUsername(user.getUserName()) == null){
                    db.getUserDAO().insert(user);
                    Toast.makeText(CreateUserActivity.this, "Account Created", Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(CreateUserActivity.this, "Account Already Created", Toast.LENGTH_SHORT).show();

                }
                Intent intent = new Intent(CreateUserActivity.this, LoginActivity.class);
                startActivity(intent);

            }

        });
    }

}
