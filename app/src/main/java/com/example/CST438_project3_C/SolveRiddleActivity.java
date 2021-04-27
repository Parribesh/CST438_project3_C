package com.example.CST438_project3_C;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SolveRiddleActivity extends AppCompatActivity {
    //------------------------------
    //Variables
    //------------------------------
    private String riddle, solution;
    private EditText guess;

    private Button solveBtn;
    private Button randomRiddleBtn;

    private int maxTries = 3;
    private int attemptsLeft;
    private int randomNumber;

    //----------------------------------------------------------------------------------------
    //onCreate
    //----------------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solve_riddle);

        //Get riddle and solution from package.
        //riddle = findViewById(R.id.riddle);
        //solution = findViewById(R.id.solution);
        attemptsLeft = maxTries;

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        solveBtn.setOnClickListener(new View.OnClickListener()
        {
           @Override
           public void onClick(View view){
               /*
               * if(guess.Text.equals(solution){
               *    print "That's correct!!!"
               * }
               * else{
               *    print "Not quite the right answer. Try again!"
               * }
               * */
           }
        });

        randomRiddleBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view){
                //randomNumber = getRandomNumber();

                //query database using number somehow
                //set riddle and solution = returned riddle
            }
        });
    }
}