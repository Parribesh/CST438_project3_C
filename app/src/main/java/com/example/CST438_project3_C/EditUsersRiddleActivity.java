package com.example.CST438_project3_C;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

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

        String riddleID = "0";
        String loggedUser = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference dr = FirebaseDatabase.getInstance().getReference()
                .child("riddles").child(loggedUser).child(riddleID);

        EditText eRiddle = findViewById(R.id.user_edit_riddle);
        EditText eAnswer = findViewById(R.id.user_edit_solution);

        dr.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful()){
                    String value = task.getResult().getValue().toString();
                    String edited = value.substring(1, value.length()-1);
                    String [] strings = edited.split("=", 2);
                    eRiddle.setText(strings[0]);
                    eAnswer.setText(strings[1]);
                }
            }
        });

        Button finished = findViewById(R.id.user_edit_finish_button);
        finished.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast t;
                boolean checkR = checkRiddle(eRiddle.getText().toString());
                boolean checkS = checkSolution(eAnswer.getText().toString());

                if(!checkR && !checkS){
                    AlertDialog.Builder builder = new AlertDialog.Builder(EditUsersRiddleActivity.this);
                    builder.setMessage("Have you finished editing your riddle?");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //finish activity
                            Map<String, Object> change = new HashMap<>();
                            change.put(eRiddle.getText().toString(), eAnswer.getText().toString());
                            dr.setValue(change);

                            Intent intent = new Intent(EditUsersRiddleActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //do nothing
                            Toast p = Toast.makeText(EditUsersRiddleActivity.this, ":(",
                                    Toast.LENGTH_LONG);
                            p.show();
                        }
                    });
                    builder.show();
                } else {
                    //Something wrong with the Riddle and its Solution
                    String msg = "";
                    if(checkR){
                        msg += "Riddle can't be blank!\n";
                    }
                    if(checkS){
                        msg += "You can't have a riddle without an solution!";
                    }
                    t = Toast.makeText(EditUsersRiddleActivity.this, msg,
                            Toast.LENGTH_LONG);
                    t.show();
                }
            }
        });
    }

    private boolean checkRiddle(String riddle){
        if(riddle.isEmpty()){
            return true;
        }
        return false;
    }

    private boolean checkSolution(String solution){
        if(solution.isEmpty()){
            return true;
        }
        return false;
    }
}
