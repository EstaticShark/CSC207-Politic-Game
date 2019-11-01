package com.example.politicgame.UserActivity.LoginActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.example.politicgame.GamesActivity.BabyGame.BabyActivity;
import com.example.politicgame.GameActivity;
import com.example.politicgame.LeaderBoardActivity;
import com.example.politicgame.R;
import com.example.politicgame.SettingsActivity;

public class LoggedInActivity extends GameActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);

        //Select Characters button
        final Button selectCharactersButton = findViewById(R.id.logged_in_activity_select_char);
        selectCharactersButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openLoadCharacter();
            }
        });


        //Leaderboard button, opens the leaderboard
        final Button boardButton = findViewById(R.id.leader_board_logged);
        boardButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        // Code here executes on main thread after user presses button
                        openLeaderBoard();
                    }
                });


        //Settings button, opens the settings menu
        final Button settingButton = findViewById(R.id.settings);
        settingButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        // Code here executes on main thread after user presses button
                        openSettings();
                    }
                });

        ImageView trumpIMG = findViewById(R.id.bush);
        Animation animated_trump = AnimationUtils.loadAnimation(this, R.anim.animated_trump);
        trumpIMG.startAnimation(animated_trump);
    }

    public void openLoadCharacter () {
        Intent loadCharacters = new Intent(this, LoadCharacterActivity.class);
        startActivity(loadCharacters);
        finish();
    }

    public void openBabyGame() {
        /**
         * Starts the first game
         */
        Intent switchBabyIntent = new Intent(this, BabyActivity.class);
        startActivity(switchBabyIntent);
        finish();
    }


    public void openLeaderBoard() {
        /**
         * Opens the leaderboard screen
         */
        Intent switchBoardIntent = new Intent(this, LeaderBoardActivity.class);
        startActivityForResult(switchBoardIntent, 2);
    }

    public void openSettings() {
        /**
         * Open the settings menu
         */
        Intent settingsIntent = new Intent(this, SettingsActivity.class);
        settingsIntent.putExtra("SESSION_ID", "loggedIn");
        startActivity(settingsIntent);
        finish();
    }
}
