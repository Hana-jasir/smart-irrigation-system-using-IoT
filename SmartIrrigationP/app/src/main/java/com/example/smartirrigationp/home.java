package com.example.smartirrigationp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class home extends AppCompatActivity {
    Spinner fields1;
    ArrayList<String> fieldList=new ArrayList<>();
    DatabaseReference DabaRf;

    Switch manualS;
    Switch pump1S;
    Switch lightS;
    ImageButton refreshButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        /////////
      //  fields1= (Spinner) findViewById(R.id.fieldSp);
        //String [] FieldList=new String[1];
        //FieldList[0]="Field_1";
         ///ArrayAdapter<String[]> arrayAdapter1;
        refreshButton=(ImageButton) findViewById(R.id.refesh);





        //////////////

        DabaRf= FirebaseDatabase.getInstance().getReference().child("smart-irrigation-70d13").child("Field");
        refreshHome();



        /////////////////
        manualS=(Switch) findViewById(R.id.manual);
        pump1S=(Switch) findViewById(R.id.pump1);

        final Map<String, Object> manaulUpdate1= new HashMap<>();
        manaulUpdate1.put("Field1/Manual","1");
        final Map<String, Object> manaulUpdate0= new HashMap<>();
        manaulUpdate0.put("Field1/Manual","0");

        final View finalView = null;
        manualS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(manualS.isChecked()){

                    DabaRf.updateChildren(manaulUpdate1);

                    pump1S.setVisibility(finalView.VISIBLE);


                }
                if(manualS.isChecked()==false) {
                    DabaRf.updateChildren(manaulUpdate0);
                    pump1S.setVisibility(finalView.INVISIBLE);

                    finish();
                    startActivity(getIntent());

                }

            }
        });

        ///////////////////

        pump1S.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Map<String, Object> pumpOn= new HashMap<>();
                pumpOn.put("Field1/Motor","on");
                final Map<String, Object> pumpOff= new HashMap<>();
                pumpOff.put("Field1/Motor","off");

                if(pump1S.isChecked()){
                    DabaRf.updateChildren(pumpOn);


                }else if(pump1S.isChecked()==false){

                    DabaRf.updateChildren(pumpOff);

                }

            }
        });
        /////////////////
        lightS=(Switch) findViewById(R.id.light);
        final Map<String, Object> lightOn= new HashMap<>();
        lightOn.put("light","on");
        final Map<String, Object> lightOff= new HashMap<>();
        lightOff.put("light","off");


        lightS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(lightS.isChecked()){

                    DabaRf.updateChildren(lightOn);

                }else if(lightS.isChecked()==false){

                    DabaRf.updateChildren(lightOff);

                }
            }
        });


        //////////////////

        Button waHistory = (Button) findViewById(R.id.watHestory1);
        waHistory.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openWateringHestory();
            }

        });

        //////////////////////

        Button singOut = (Button) findViewById(R.id.singOut);
        singOut.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SingOut0();
            }
        });

        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshHome();
                finish();
                startActivity(getIntent());


            }
        });

    }

    public void SingOut0() {
        Intent intentHome = new Intent(this, MainActivity.class);
        startActivity(intentHome);
    }

    public void openWateringHestory() {
        Intent intentHes = new Intent(this, watering_history.class);
        startActivity(intentHes);

    }

    public void mosterLevelRefrech(String mostLevelB){
        final TextView mostLevel1=(TextView) findViewById(R.id.mostLevel);
        String low="low";
        String high="high";
        if(mostLevelB.equals("1")){
           mostLevel1.setText(low);

        }else if( (mostLevelB.equals("0")) ){

           mostLevel1.setText(high);
        }


    }

    public void refreshHome(){
        DabaRf= FirebaseDatabase.getInstance().getReference().child("smart-irrigation-70d13").child("Field");
        DabaRf.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String mostLevelB=dataSnapshot.child("Field1").child("Moisture").getValue().toString();
                String pumpMode= dataSnapshot.child("Field1").child("Motor").getValue().toString();
                mosterLevelRefrech(mostLevelB);
                pumpRefresh(pumpMode);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


            }
        });


    }

    public void pumpRefresh(String pumpMode1){
        TextView pumpModeText=(TextView) findViewById(R.id.pumpMode);
        String on="on";
        String off="off";
        if(pumpMode1.equals("on")){
            pumpModeText.setText(on);

        }else if(pumpMode1.equals("off")){

            pumpModeText.setText(off);
        }

    }
}
