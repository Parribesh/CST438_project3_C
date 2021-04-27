package com.example.CST438_project3_C;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.CST438_project3_C.data.User;
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
    private String userKey;

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
                    DatabaseReference dr = FirebaseDatabase.getInstance().getReference().child("users");
                    dr.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                                if(dataSnapshot.toString().contains("fjhernan@gmail.com")) {
                                    userKey = dataSnapshot.getKey();
                                    DatabaseReference d = dr.child(dataSnapshot.getKey());
                                    Map<String, Object> update = new HashMap<>();
                                    update.put("Riddle","Testing");
                                    d.updateChildren(update);
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
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
