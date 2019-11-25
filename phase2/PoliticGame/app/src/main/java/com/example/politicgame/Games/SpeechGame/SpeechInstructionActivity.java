package com.example.politicgame.Games.SpeechGame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.politicgame.GameActivity;
import com.example.politicgame.GameMode.GameMode;
import com.example.politicgame.GameMode.StampArcade;
import com.example.politicgame.Games.StampGame.StampInstructionActivity;
import com.example.politicgame.Application.PoliticGameApp;
import com.example.politicgame.R;

public class SpeechInstructionActivity extends GameActivity {

    final private String LEVEL_NAME = "LEVEL2";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (((GameMode)getIntent().getSerializableExtra("GameMode")).isGameComplete(app)) {
            openStampGame();
        }

        System.out.println("The current theme is blue: " + app.isThemeBlue());

        if (app.isThemeBlue()){
            setTheme(R.style.BlueTheme);
        } else {
            setTheme(R.style.RedTheme);
        }

        setContentView(R.layout.activity_speech_instruction);

        setTitle("The Speech Game Instructions");

        final Button button = findViewById(R.id.start_game);
        button.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        // Code here executes on main thread after user presses button
                        startSpeechGame();
                    }
                });
    }

    public void startSpeechGame() {
        Intent startSpeechIntent = new Intent(this, SpeechActivity.class);
        startSpeechIntent.putExtra("current rating", 0);

        // Added for game mode modification
        startSpeechIntent.putExtra("GameMode", getIntent().getSerializableExtra("GameMode"));

        startActivity(startSpeechIntent);
        finish();
    }

    /**
     *  Switch to Next Game View
     *  **/
    public void openStampGame() {
        Intent switchStampIntent = new Intent(this, StampInstructionActivity.class);

        // Added for game mode modification
        switchStampIntent.putExtra("GameMode", new StampArcade(/*app*/));

        startActivity(switchStampIntent);
        finish();
    }
}
