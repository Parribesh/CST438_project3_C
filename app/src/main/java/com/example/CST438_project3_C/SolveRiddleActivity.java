package com.example.CST438_project3_C;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SolveRiddleActivity extends AppCompatActivity {
    //------------------------------
    //Variables
    //------------------------------
    private String riddle, solution;
    private String guessText;
    private EditText guess;

    private TextView riddleText;
    private TextView solutionText;

    private Button solveBtn;
    private Button randomRiddleBtn;
    private Button giveUpBtn;

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

        solveBtn = findViewById(R.id.solveBtn);
        randomRiddleBtn = findViewById(R.id.randomRiddleBtn);
        giveUpBtn = findViewById(R.id.giveUpBtn);

        riddleText = findViewById(R.id.riddleTextView);
        solutionText = findViewById(R.id.solutionTextView);

        //Get riddle and solution from package.
        //riddle = findViewById(R.id.riddle);
        //solution = findViewById(R.id.solution);
        riddle = "The solution is ONE";
        solution = "ONE";

        riddleText.setText(riddle);
        setSolutionText("Answer: ");



        attemptsLeft = maxTries;
        /*
            HEY! What you need to do is:
                Create a giveUpBtn.
                Create a SolutionTextView
                    Have it display: "Answer: "

                Create a revealSolution class that sets the SolutionTextView to:
                    "Answer: <solution>"

                Call revealSolution whenever the user solves it or clicks the giveUpBtn.
         */

        solveBtn.setOnClickListener(new View.OnClickListener()
        {
           @Override
           public void onClick(View view){
               guess = findViewById(R.id.guessEditText);
               guessText = guess.getText().toString().trim();

                if(guessText.equals(solution)){
                   toastMaker("That's correct!");
                   setSolutionText("Answer: " + solution);
                }
                else{
                   toastMaker("That's not quite right. Try again!");
                }

           }
        });

        randomRiddleBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view){
                toastMaker("WIP!!!");
                //randomNumber = getRandomNumber();

                //query database using number somehow
                //set riddle and solution = returned riddle
            }
        });

        giveUpBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view){
                setSolutionText("Answer: " + solution);
            }
        });
    }

    //===========================================================================
    //setSolutionText(String) : Sets solutionTextView to the passed string
    //===========================================================================
    private void setSolutionText(String s){
       solutionText.setText(s);
    }

    //======================================================================================
    //toastMaker(String) : Will print a toast that displays the provided string.
    //                     (Retrieved from code provided in Dr. C's class in 2019)
    //======================================================================================
    private void toastMaker(String message){
        Toast t = Toast.makeText(this.getApplicationContext(),message,Toast.LENGTH_LONG );
        //Using CENTER_VERTICAL to make Dylan happy... or perhaps just less angry.
        t.setGravity(Gravity.CENTER_VERTICAL,0,0);
        t.show();
    }
    //======================================================================================
}