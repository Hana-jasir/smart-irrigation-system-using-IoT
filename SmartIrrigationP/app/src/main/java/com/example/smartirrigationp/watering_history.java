package com.example.smartirrigationp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.widget.ListView;
import android.widget.Switch;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class watering_history extends AppCompatActivity {

    private Button watBack;
    private ListView histListView;
    DatabaseReference DabaRf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watering_history);

        histListView=(ListView) findViewById(R.id.historyList);
        final ArrayAdapter<String> arrayAdapter1=new ArrayAdapter<String>(watering_history.this,android.R.layout.simple_list_item_1);





        DabaRf= FirebaseDatabase.getInstance().getReference().child("smart-irrigation-70d13").child("History");
        DabaRf.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                String value=dataSnapshot.getValue(String.class);

                arrayAdapter1.notifyDataSetChanged();


            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                arrayAdapter1.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        histListView.setAdapter(arrayAdapter1);







                ///////////////

        watBack = (Button) findViewById(R.id.back1);
        watBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openHomePage();
            }

        });


    }
    public void openHomePage(){
        Intent intentHome= new Intent(this, home.class);
        startActivity(intentHome);

    }
}
