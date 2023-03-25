package com.example.xoxgame;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView playerOneScore, playerTwoScore, playerStatus;
    private Button[] buttons = new Button[9];
    private Button resetGame;

    private int playerOneScoreCount, playerTwoScoreCount, roundCount;
    boolean activePlayer;

    // p1 => 0
    // p2 => 1
    // bos => 2

    int[] gameState = {2,2,2,2,2,2,2,2,2};

    int[][] winPositions = {
            {0,1,2}, {3,4,5}, {6,7,8}, // satir
            {0,3,6}, {1,4,7}, {2,5,8}, // sutun
            {0,4,8}, {2,4,6} //capraz
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerOneScore = (TextView) findViewById(R.id.playerOneScore);
        playerTwoScore = (TextView) findViewById(R.id.playerTwoScore);
        playerStatus = (TextView) findViewById(R.id.playerStatus);
        resetGame = (Button) findViewById(R.id.resetGame);

        for (int i=0; i < buttons.length; i++){
            String buttonID = "btn_" + i;
            int resourceID = getResources().getIdentifier(buttonID,"id",getPackageName()); // id string olarak alamayacagi icin boyle bir donusum yaptik.
            buttons[i] = (Button) findViewById(resourceID);
            buttons[i].setOnClickListener(this); // hepsi icin ayri bir listener yerine parent'dan yaptik
        }

        roundCount = 0;
        playerOneScoreCount = 0;
        playerTwoScoreCount = 0;
        activePlayer= true; // iki oyuncu oldugu icin boolean deger kullanilabilir
    }

    @Override
    public void onClick(View v) {
        if(!((Button)v).getText().toString().equals("")){
            return;
        }

        String buttonId = v.getResources().getResourceEntryName(v.getId()); //btn_2
        int gameStatePointer = Integer.parseInt(buttonId.substring(buttonId.length()-1, buttonId.length())); // 2 son numarayi almak icin substring islemi

        if(activePlayer) {
            ((Button) v).setText("X");
            ((Button) v).setTextColor(Color.parseColor("#FFC34A"));
            gameState[gameStatePointer] = 0;
        }else {
            ((Button) v).setText("O");
            ((Button) v).setTextColor(Color.parseColor("#FFC34A"));
            gameState[gameStatePointer] = 1;
        }

        roundCount++;

        if(checkWinner()){
            if(activePlayer){
                playerOneScoreCount++;
                updatePlayerScore();
                Toast.makeText(this, "Birinci oyuncu Kazandi!!",Toast.LENGTH_SHORT).show();
                playAgain();
            }else{
                playerTwoScoreCount++;
                updatePlayerScore();
                Toast.makeText(this, "Ikinci oyuncu Kazandi!!",Toast.LENGTH_SHORT).show();
                playAgain();
            }
        } else if(roundCount == 9){ // berabere
            Toast.makeText(this, "Berabere!!",Toast.LENGTH_SHORT).show();
            playAgain();
        }else{
             activePlayer = !activePlayer;
        }

        if(playerOneScoreCount > playerTwoScoreCount){
            playerStatus.setText("Birinci Oyuncu Kazaniyor!");
        }else if(playerTwoScoreCount > playerOneScoreCount){
            playerStatus.setText("Ikinci Oyuncu Kazaniyor!");
        }else{
            playerStatus.setText("");
        }

        resetGame.setOnClickListener(new View.OnClickListener() {
        @Override
            public void onClick(View v){
            playAgain();
            playerOneScoreCount = 0;
            playerTwoScoreCount = 0;
            playerStatus.setText("");
            updatePlayerScore();
        }
        });
    }

    public boolean checkWinner(){
        boolean winnerResult = false;

        for(int[] winningPosition: winPositions){
            if(gameState[winningPosition[0]] == gameState[winningPosition[1]]
                    && gameState[winningPosition[1]] == gameState[winningPosition[2]]
                    && gameState[winningPosition[0]] != 2){

                winnerResult = true;
            }
        }
    return winnerResult;
    }

    public void updatePlayerScore(){
        playerOneScore.setText(Integer.toString(playerOneScoreCount));
        playerTwoScore.setText(Integer.toString(playerTwoScoreCount));
    }

    public void playAgain(){
        roundCount = 0;
        activePlayer = true;
        for(int i = 0; i<buttons.length;i++){
            gameState[i] = 2; // tabloyu yeniden sifirliyoruz
            buttons[i].setText("");
        }
    }
}