package com.example.CST438_project3_C;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

public class SolveRiddleActivity extends AppCompatActivity {
    //------------------------------
    //Variables
    //------------------------------
    FirebaseDatabase fd;
    DatabaseReference dr;

    private String riddle, solution;
    private String guessText;
    private EditText guess;

    private TextView riddleText;
    private TextView solutionText;
    private TextView scoreText;

    private Button solveBtn;
    private Button randomRiddleBtn;
    private Button giveUpBtn;

    private int randomNumber;

    private int AmountOfUsers;
    private int AmountOfRiddles;

    private int RiddlesSolved = 0;

    private boolean alreadySolved = false;

    //----------------------------------------------------------------------------------------
    //onCreate
    //----------------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solve_riddle);

        fd = FirebaseDatabase.getInstance();
        dr = fd.getReference();


        solveBtn = findViewById(R.id.solveBtn);
        randomRiddleBtn = findViewById(R.id.randomRiddleBtn);
        giveUpBtn = findViewById(R.id.giveUpBtn);

        riddleText = findViewById(R.id.riddleTextView);
        solutionText = findViewById(R.id.solutionTextView);
        scoreText = findViewById(R.id.scoreTextView);

        riddle = "The solution is ONE";
        solution = "ONE";

        riddleText.setText(riddle);
        setSolutionText("Answer: ");
        setScoreText(RiddlesSolved);

        solveBtn.setOnClickListener(new View.OnClickListener()
        {
           @Override
           public void onClick(View view){
               if(alreadySolved){
                   toastMaker("You already solved this one!");
               }
               else{
                   guess = findViewById(R.id.guessEditText);
                   guessText = guess.getText().toString().trim();

                   if(guessText.equals(solution)){
                       toastMaker("That's correct!");
                       setSolutionText("Answer: " + solution);

                       RiddlesSolved += 1;
                       alreadySolved = true;

                       setScoreText(RiddlesSolved);
                   }
                   else{
                       toastMaker("That's not quite right. Try again!");
                   }
               }
           }
        });

        randomRiddleBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view){
                alreadySolved = false;

                dr.child("riddles").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> riddleTask) {
                        if(riddleTask.isSuccessful()){
                            DataSnapshot userTask = riddleTask.getResult();
                            AmountOfUsers = (int)userTask.getChildrenCount();
                            Random random = new Random();

                            randomNumber = random.nextInt(AmountOfUsers);

                            int userCount = 0;
                            for(DataSnapshot userSnapshot: userTask.getChildren())
                            {
                                if(userCount == randomNumber){
                                    AmountOfRiddles = (int)userSnapshot.getChildrenCount();

                                    randomNumber = random.nextInt(AmountOfRiddles);
                                    int riddleCount = 0;

                                    for(DataSnapshot riddleSnapshot: userSnapshot.getChildren()){
                                        if(riddleCount == randomNumber){
                                            String value = riddleSnapshot.getValue().toString();
                                            String edited = value.substring(1, value.length()-1);
                                            String [] strings = edited.split("=", 2);
                                            riddle = strings[0];
                                            solution = strings[1];
                                            riddleText.setText(riddle);
                                            solutionText.setText("");
                                           break;
                                        }
                                        riddleCount++;
                                    }
                                    break;
                                }
                                userCount++;
                            }
                        }
                    }
                });
            }
        });

        giveUpBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view){
                setSolutionText("Answer: " + solution);
                alreadySolved = true;
            }
        });
    }

    //===========================================================================
    //setScoreText(int) : Sets scoreTextView to string + passed int
    //===========================================================================
    private void setScoreText(int i)
    {
        String scoreString = "Current score: " + i;
        scoreText.setText(scoreString);
    }
    //===========================================================================
    //setSolutionText(String) : Sets solutionTextView to the passed string
    //===========================================================================
    private void setSolutionText(String s)
    {
       solutionText.setText(s);
    }

    //======================================================================================
    //toastMaker(String) : Will print a toast that displays the provided string.
    //                     (Retrieved from code provided in Dr. C's class in 2019)
    //======================================================================================
    private void toastMaker(String message)
    {
        Toast t = Toast.makeText(this.getApplicationContext(),message,Toast.LENGTH_LONG );
        //Using CENTER_VERTICAL to make Dylan happy... or perhaps just less angry.
        t.setGravity(Gravity.CENTER_VERTICAL,0,0);
        t.show();
    }
    //======================================================================================
}