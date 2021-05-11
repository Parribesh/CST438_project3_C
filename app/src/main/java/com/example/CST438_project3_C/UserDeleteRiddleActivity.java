package com.example.CST438_project3_C;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class UserDeleteRiddleActivity extends AppCompatActivity {
    ArrayList<String> arrayList = new ArrayList<>();
    ListView listView;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_delete_riddles);
        listView = (ListView) findViewById(R.id.user_delete_list_view);
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);
        String loggedUser = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference dr = FirebaseDatabase.getInstance().getReference()
                .child("riddles").child(loggedUser);


        dr.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful()){
                    Toast p;
                    DataSnapshot snapshot = task.getResult();
                    for(DataSnapshot snap : snapshot.getChildren()){
                        String data = snap.getValue().toString();
                        arrayList.add(data);
                        arrayAdapter.notifyDataSetChanged();
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                Toast k = Toast.makeText(UserDeleteRiddleActivity.this, "hello world",
                                        Toast.LENGTH_LONG);
                                k.show();
                            }
                        });
                    }
                }
            }
        });
/*
        if(task.isSuccessful()){
            String value = task.getResult().getValue().toString();
            String edited = value.substring(1, value.length()-1);
            String [] strings = edited.split("=", 2);
        }
  */

        Button returnButton = findViewById(R.id.user_delete_return_button);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent intent = new Intent(UserDeleteRiddleActivity.this
                            , MainActivity.class);
                    startActivity(intent);
            }
        });
    }

}
