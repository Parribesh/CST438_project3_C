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
    private String msg = "";

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
                Toast t;
                boolean check = checkRiddles(eRiddle.getText().toString(),
                        eAnswer.getText().toString());
                if(!check){
                    //If everything is good
                    t = Toast.makeText(UserCreateRiddleActivity.this, ":)",
                            Toast.LENGTH_LONG);
                    t.show();
                    /*
                      Code for adding to database will go here
                     */
                } else{
                    //If everything is not good
                    t = Toast.makeText(UserCreateRiddleActivity.this, msg,
                            Toast.LENGTH_LONG);
                    t.show();
                    msg = "";
                }
            }
        });
    }

    private boolean checkRiddles(String riddle, String solution){
        boolean check = false;
        if(riddle.isEmpty()){
            check = true;
            msg += "You can't upload a blank riddle!\n";
        }
        if(solution.isEmpty()){
            check = true;
            msg += "You can't upload a riddle without a solution!";
        }
        return check;
    }

}
