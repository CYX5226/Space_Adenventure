package com.example.xupeiluo.space;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class StartMenuActivity extends AppCompatActivity {
    Button gotoboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        gotoboard=(Button)findViewById(R.id.gotoboard);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_menu);
        //show user already connect to firebase
        Toast.makeText(StartMenuActivity.this,"Connection to Firebase success",Toast.LENGTH_LONG);
    }


    public void StartMain(View view) {
        Intent mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);
    }

    public void StartBoard(View view){
        Intent boardIntent= new Intent(this,Leaderboard.class);
        startActivity(boardIntent);
    }
}
