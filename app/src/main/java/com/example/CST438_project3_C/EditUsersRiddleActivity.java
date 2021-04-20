package com.example.CST438_project3_C;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

/**
 * This acticity lets users edit riddles they created.
 */
public class EditUsersRiddleActivity extends AppCompatActivity {
    public static final String EDIT_USERS_RIDDLE_ACTIVITY = "EditUsersRiddleActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(EDIT_USERS_RIDDLE_ACTIVITY, "onCreate called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_riddle_activity);

    }
}
