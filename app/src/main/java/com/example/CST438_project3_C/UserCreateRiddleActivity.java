package com.example.CST438_project3_C;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.CST438_project3_C.data.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

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

        EditText eRiddle = findViewById(R.id.user_create_riddle_riddle);
        EditText eAnswer = findViewById(R.id.user_create_riddle_answer);
        Button upload = findViewById(R.id.user_create_riddle_button);
        upload.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                boolean checkR = checkRiddles(eRiddle.getText().toString());
                boolean checkS = checkSolution(eAnswer.getText().toString());
                if(!checkR && !checkS){
                    //If everything is good
                    String loggedUser = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    DatabaseReference dr = FirebaseDatabase.getInstance()
                            .getReference().child("riddles").child(loggedUser);

                    FirebaseDatabase.getInstance().getReference().child("riddles")
                            .child(loggedUser).get()
                            .addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DataSnapshot> task) {
                                    if(task.isSuccessful()){
                                        DataSnapshot dataSnapshot = task.getResult();
                                        int i = 0;
                                        for(DataSnapshot data : dataSnapshot.getChildren()){
                                            i++;
                                        }
                                        Map<String, Object> update = new HashMap<>();
                                        update.put(eRiddle.getText().toString(), eAnswer.getText().toString());
                                        dr.child(String.valueOf(i)).updateChildren(update);
                                    }
                                }
                            });

                    Intent intent = new Intent(UserCreateRiddleActivity.this, EditUsersRiddleActivity.class);
                    startActivity(intent);
                } else{
                    //If everything is not good
                    String msg = "";
                    if(checkR){
                        msg += "Riddle can't be blank!\n";
                    }
                    if(checkS){
                        msg += "You can't have a riddle without a solution!";
                    }
                    Toast t = Toast.makeText(UserCreateRiddleActivity.this, msg,
                            Toast.LENGTH_LONG);
                    t.show();
                }
            }
        });
    }

    private boolean checkRiddles(String riddle){
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
