package com.example.connect;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int turn=0;
    int[] gamestate={2,2,2,2,2,2,2,2,2};
    int[][] winner={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{2,5,8},{1,4,7},{0,4,8},{2,4,6}};
    boolean active=true;
    public void dropIn(View view) {

        ImageView counter = (ImageView) view;
        int tagcount = Integer.parseInt(counter.getTag().toString());
        if (gamestate[tagcount] == 2 && active) {
            gamestate[tagcount] = turn;

            counter.setTranslationY(-1500);
            if (turn == 0) {
                counter.setImageResource(R.drawable.yellow);
                turn = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                turn = 0;
            }
            counter.animate().translationYBy(1500).rotation(3600).setDuration(500);
            for (int[] winningposition : winner) {
                if (gamestate[winningposition[0]] == gamestate[winningposition[1]] && gamestate[winningposition[1]] == gamestate[winningposition[2]] && gamestate[winningposition[0]] != 2) {
                    active=false;
                    if (turn == 1) {
                        Toast.makeText(this, "Yellow Wins!", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(this, "Red Wins!", Toast.LENGTH_LONG).show();
                    }
                    Button playagain=(Button) findViewById(R.id.playagain);
                    playagain.setVisibility(View.VISIBLE);

                }

            }
        }
    }
    public void reset(View view){
        Button playagain=(Button) findViewById(R.id.playagain);
        playagain.setVisibility(View.INVISIBLE);
        GridLayout myGrid=(GridLayout)findViewById(R.id.myGrid);
        for(int i=0; i<myGrid.getChildCount(); i++) {
            ImageView counter = (ImageView) myGrid.getChildAt(i);
            counter.setImageDrawable(null);
        }
        for(int i=0;i<gamestate.length;i++)
        {
            gamestate[i]=2;
        }
         turn=0;
         active=true;


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
