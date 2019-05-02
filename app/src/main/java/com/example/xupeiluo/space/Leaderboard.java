package com.example.xupeiluo.space;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.xupeiluo.space.GamePanel.*;

public class Leaderboard extends AppCompatActivity {
    TextView p1,s1,txtscore;
    EditText txtplayer,txtscoreinput;
    Button btnget,btnsend;
    long playercounter=0;
    DatabaseReference ref;
    private Player player;
    static int highscore=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        p1=(TextView)findViewById(R.id.playerview1);
        s1=(TextView)findViewById(R.id.scoreview1);
        btnget=(Button)findViewById(R.id.btnget);
        btnsend=(Button)findViewById(R.id.btnsend);
        txtplayer=(EditText)findViewById(R.id.txtplayer);
        txtscoreinput=(EditText) findViewById(R.id.txtscore);
        player=new Player();

        ref= FirebaseDatabase.getInstance().getReference().child("Players");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists())
                    playercounter = dataSnapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        btnsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //upload score + check whether can go to highest score board

                /*Integer scorea;
                scorea = GamePanel.getScore();
                String scoreastr=scorea.toString();
                txtscore.setText(scoreastr);*/

                int scorea=Integer.parseInt(txtscoreinput.getText().toString().trim());//use to get the score
                //不知道咋用getscore function所以我还是用了个edittext让user输入

                //determine whether or not the score is the highest score
                if (scorea>highscore){
                    player.setName(txtplayer.getText().toString().trim());
                    player.setScore(scorea);
                    ref.child(String.valueOf(playercounter+1)).setValue(player);

                    //ref.push().setValue(player);
                    Toast.makeText(Leaderboard.this,"Your score has been uploaded",Toast.LENGTH_LONG).show();
                    highscore=scorea;
                    String highplayer=txtplayer.getText().toString();
                }
                else{
                    Toast.makeText(Leaderboard.this,"Your score is not the new highest score ",Toast.LENGTH_LONG).show();
                }
            }
        });


        btnget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ref= FirebaseDatabase.getInstance().getReference().child("Players");
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        //get how much players in the database
                        int playernum= (int) dataSnapshot.getChildrenCount();
                        if (playernum==0){
                            Toast.makeText(Leaderboard.this,"Please upload score first",Toast.LENGTH_LONG).show();
                        }
                        else {
                            //print out the i
                            // info into given textview
                            String a;
                            a = Integer.toString(playernum);
                            String name1 = dataSnapshot.child(a).child("name").getValue().toString();
                            String score1 = dataSnapshot.child(a).child("score").getValue().toString();
                            p1.setText(name1);
                            s1.setText(score1);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
