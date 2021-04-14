package com.example.CST438_project3_C;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.CST438_project3_C.data.User;

/**
 * This activity lets a user create and upload a riddle
 * @author Francisco Hernandez
 * @version 1.1
 * @since 04-14-2021
 */
public class UserCreateRiddleActivity extends AppCompatActivity {
    public static final String USER_CREATE_RIDDLE_ACTIVITY = "UserCreateRiddleActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_create_riddle);
        //The following is code for when the riddle database is made
        //String name = savedInstanceState.getString(NAME);
        EditText eRiddle = findViewById(R.id.user_create_riddle_riddle);
        EditText eAnswer = findViewById(R.id.user_create_riddle_answer);
        String riddle = eRiddle.getText().toString();
        String answer = eAnswer.getText().toString();
        Button upload = findViewById(R.id.user_create_riddle_button);
        upload.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String msg = "";
                if(riddle.isEmpty()){
                    msg += "You can't upload a blank riddle!\n";
                }
                if(answer.isEmpty()){
                    msg += "You can't upload a riddle without an answer!\n";
                }
                if(!(msg.isEmpty())){
                    Toast t = Toast.makeText(UserCreateRiddleActivity.this, msg,
                            Toast.LENGTH_LONG);
                    t.show();
                }
            }
        });

    }


}
