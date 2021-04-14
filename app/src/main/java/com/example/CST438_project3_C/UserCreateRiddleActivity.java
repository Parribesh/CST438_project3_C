package com.example.CST438_project3_C;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.CST438_project3_C.data.User;

import org.w3c.dom.Text;

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
        //Log.d doesn't work for some reason.
        //Log.d(USER_CREATE_RIDDLE_ACTIVITY, "onCreate Called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_create_riddle);

        EditText eRiddle = findViewById(R.id.user_create_riddle_riddle);
        EditText eAnswer = findViewById(R.id.user_create_riddle_answer);
        //for some reason these variables don't actually save data
        //String riddle = eRiddle.getText().toString();
        //String answer = eAnswer.getText().toString();
        Button upload = findViewById(R.id.user_create_riddle_button);
        upload.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String msg = "";
                Toast t;
                boolean check = false;
                if(eRiddle.getText().toString().isEmpty()){
                    msg += "You can't upload a blank riddle!\n";
                    check = true;
                } else{
                    msg += "You made a riddle!\n";
                }
                if(eAnswer.getText().toString().isEmpty()){
                    msg += "You can't upload a riddle without an answer!";
                    check = true;
                } else{
                    msg += "You have an answer!";
                }
                if(check == false){
                    t = Toast.makeText(UserCreateRiddleActivity.this, msg,
                            Toast.LENGTH_LONG);
                    t.show();
                } else{
                    t = Toast.makeText(UserCreateRiddleActivity.this, msg,
                            Toast.LENGTH_LONG);
                    t.show();
                    /*
                      Code for adding to database will go here
                     */
                }
            }
        });
    }
}
