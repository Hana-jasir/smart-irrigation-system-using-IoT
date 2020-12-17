package com.example.smartirrigationp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.loader.content.Loader;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;


public class MainActivity extends AppCompatActivity {
    private EditText raspPiID;
    private Button LogIn;
    private int count=5;
   // private DatabaseReference firestoreReff;
   // private static final String FIRE_LOG="Fire_log";





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        raspPiID =(EditText) findViewById(R.id.rasPI1);
        LogIn = (Button) findViewById(R.id.Log_in);
        LogIn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
               validate(raspPiID.getText().toString());
            }

        });





    }

   private void validate(String raspPiID1) {
       ConstraintLayout LogInLay;
       LogInLay= (ConstraintLayout) findViewById(R.id.logInCon);
       if(raspPiID1.equals("1")){
           openHomePage();
       }else if(raspPiID1.equals("")){
           Snackbar logInError=Snackbar.make(LogInLay,"Please enter the id",2000);
           logInError.show();
       }
       else{
         count--;
         Snackbar logInError=Snackbar.make(LogInLay,"The entered ID is wrong",2000);
         logInError.show();

         if(count==0){
             LogIn.setEnabled(false);
         }
       }



    }


    public void openHomePage(){
        Intent intentHome= new Intent(this, home.class);
        startActivity(intentHome);

    }


}
