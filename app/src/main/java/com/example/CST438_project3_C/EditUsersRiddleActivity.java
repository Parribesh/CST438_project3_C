package com.example.CST438_project3_C;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

/**
 * This activity lets users edit riddles they created.
 */
public class EditUsersRiddleActivity extends AppCompatActivity {
    public static final String EDIT_USERS_RIDDLE_ACTIVITY = "EditUsersRiddleActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(EDIT_USERS_RIDDLE_ACTIVITY, "onCreate called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_riddle_activity);
        /*Get riddle/answer from database and display it

            code will go here

         */
        EditText eRiddle = findViewById(R.id.user_edit_riddle);
        EditText eAnswer = findViewById(R.id.user_edit_solution);
        Button finished = findViewById(R.id.user_edit_finish_button);
        finished.setOnClickListener(new View.OnClickListener() {
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
                if(!check){
                    t = Toast.makeText(EditUsersRiddleActivity.this, msg,
                            Toast.LENGTH_LONG);
                    t.show();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(EditUsersRiddleActivity.this);
                    builder.setMessage("Have you finished editing your riddle?");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //finish activity
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //do nothing
                        }
                    });
                }
            }
        });


    }
}
