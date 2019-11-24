package com.example.politicgame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.example.politicgame.Games.BabyGame.BabyGameInstruction;
import com.example.politicgame.Leaderboard.LeaderBoardActivity;
import com.example.politicgame.UserActivity.LoginActivity.LoginActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends GameActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    setTitle("Main Menu");

    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    // Login button, starts the login process
    final Button loginButton = findViewById(R.id.login);
    loginButton.setOnClickListener(
        new View.OnClickListener() {
          public void onClick(View v) {
            // Code here executes on main thread after user presses button
            openLoginPage();
          }
        });

    // Leaderboard button, opens the leaderboard
    final Button boardButton = findViewById(R.id.leaderBoard);
    boardButton.setOnClickListener(
        new View.OnClickListener() {
          public void onClick(View v) {
            // Code here executes on main thread after user presses button
            openLeaderBoard();
          }
        });

    // Settings button, opens the settings menu
    final Button settingButton = findViewById(R.id.settings);
    settingButton.setOnClickListener(
        new View.OnClickListener() {
          public void onClick(View v) {
            // Code here executes on main thread after user presses button
            openSettings();
          }
        });

    ImageView trumpIMG = findViewById(R.id.trump);
    Animation animated_trump = AnimationUtils.loadAnimation(this, R.anim.animated_trump);
    trumpIMG.startAnimation(animated_trump);
  }

  public void openLoginPage() {
    /** Opens the login page */
    Intent loginPageIntent = new Intent(this, LoginActivity.class);
    startActivity(loginPageIntent);
    finish();
  }

  public void openLeaderBoard() {
    /** Opens the leaderboard screen */
    Intent switchBoardIntent = new Intent(this, LeaderBoardActivity.class);
    startActivityForResult(switchBoardIntent, 2);
  }

  public void openSettings() {
    /** Open the settings menu */
    Intent settingsIntent = new Intent(this, SettingsActivity.class);
    settingsIntent.putExtra("SESSION_ID", "main");
    startActivity(settingsIntent);
    finish();
  }
}
