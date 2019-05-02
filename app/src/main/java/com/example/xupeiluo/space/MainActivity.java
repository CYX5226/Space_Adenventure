package com.example.xupeiluo.space;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;

import android.widget.Toast;
import com.example.xupeiluo.space.MainThread;


public class MainActivity extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MainThread.setRunning(true);
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        Constants.SCREEN_HEIGHT = dm.heightPixels;
        Constants.SCREEN_WIDTH = dm.widthPixels;



        setContentView(new GamePanel(this));

        //super.onBackPressed();

        //setContentView(R.layout.activity_main);
    }

    @Override
    public void onBackPressed() {
        GamePanel.setTruePaused();
        backButtonHandler();


//        Intent intent = new Intent(MainActivity.this, StartMenuActivity.class);
//        startActivity(intent);
//        finish();

        //Toast.makeText(MainActivity.this,"Back Button is clicked.", Toast.LENGTH_LONG).show();
        //super.onBackPressed();
    }

    public void backButtonHandler() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        // Setting Dialog Title
        alertDialog.setTitle("Exit game?");
        // Setting Dialog Message
        alertDialog.setMessage("Are you sure you want to exit this game?");
        // Setting Icon to Dialog
        //alertDialog.setIcon(R.drawable.dialog_icon);
        // Setting Positive "Yes" Button
        alertDialog.setPositiveButton("YES",

                new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(MainActivity.this, StartMenuActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
                MainThread.setRunning(false);

                //finish();
                //MainThread.dumpStack();
                startActivity(intent);
                finish();


            }
        });
        // Setting Negative "NO" Button
        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to invoke NO event
                GamePanel.setFalsePaused();
                dialog.cancel();
            }
        });
        // Showing Alert Message
        alertDialog.show();
    }




}
