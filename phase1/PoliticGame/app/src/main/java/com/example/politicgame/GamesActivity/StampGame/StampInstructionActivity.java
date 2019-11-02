package com.example.politicgame.GamesActivity.StampGame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.politicgame.GameActivity;
import com.example.politicgame.PoliticGameApp;
import com.example.politicgame.R;

public class StampInstructionActivity extends GameActivity {
  @Override
  public void onCreate(Bundle savedInstanceState) {
    app = (PoliticGameApp) getApplication();

    System.out.println("The current theme is blue: " + app.isThemeBlue());

    if (app.isThemeBlue()) {
      setTheme(R.style.BlueTheme);
    } else {
      setTheme(R.style.RedTheme);
    }

    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_stamp_instruction);

    setTitle("The Stamp Game Instructions");

    final Button button = findViewById(R.id.start_game);
    button.setOnClickListener(
        new View.OnClickListener() {
          public void onClick(View v) {
            // Code here executes on main thread after user presses button
            startStampGame();
          }
        });
  }

  public void startStampGame() {
    Intent startStampIntent = new Intent(this, StampActivity.class);
    startActivity(startStampIntent);
    finish();
  }
}
