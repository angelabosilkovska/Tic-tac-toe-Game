package com.android.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int[] [] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    int activePlayer = 0;
    boolean gameActive = true;
    public void dropIn(View view){
        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        Log.i("tappedCounter", String.valueOf(tappedCounter));
        if(gameState[tappedCounter] == 2 && gameActive) {
        gameState[tappedCounter] = activePlayer;
        counter.setTranslationX(-1500);
        if(activePlayer == 0) {
            counter.setImageResource(R.drawable.x);
            activePlayer = 1;
        }
        else{
            counter.setImageResource(R.drawable.o_removebg_preview);
            activePlayer = 0;
        }
        counter.animate().translationXBy(1500).setDuration(300);
        for( int[] winningPosition : winningPositions) {
            if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {
                gameActive = false;
                String winner = "";
                if (activePlayer == 1) {
                    winner = "X";
                } else {
                    winner = "O";
                }
                Button playAgain = (Button) findViewById(R.id.button5);
                TextView winnerTxt = (TextView) findViewById(R.id.textView2);
                winnerTxt.setText(winner + " has won!");
                playAgain.setVisibility(View.VISIBLE);
                winnerTxt.setVisibility(View.VISIBLE);
            }
            else if(tappedCounter == 8){
                Button playAgain = (Button) findViewById(R.id.button5);
                TextView winnerTxt = (TextView) findViewById(R.id.textView2);
                winnerTxt.setText("Nobody has won!");
                playAgain.setVisibility(View.VISIBLE);
                winnerTxt.setVisibility(View.VISIBLE);
            }
        }
        }
    }
    public void playAgain(View view){

        Button playAgain = (Button) findViewById(R.id.button5);
        TextView winnerTxt = (TextView) findViewById(R.id.textView2);
        playAgain.setVisibility(View.INVISIBLE);
        winnerTxt.setVisibility(View.INVISIBLE);

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout2);
        for(int i=0; i<gridLayout.getChildCount(); i++){
            ImageView counters = (ImageView) gridLayout.getChildAt(i);
            counters.setImageDrawable(null);
        }
        for(int i=0; i<gameState.length; i++){
            gameState[i] = 2;
        }
        activePlayer = 0;
        gameActive = true;
    }

}