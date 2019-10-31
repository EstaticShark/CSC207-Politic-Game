package com.example.politicgame.BabyGame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.politicgame.GameActivity;
import com.example.politicgame.PauseButton;
import com.example.politicgame.R;
import com.example.politicgame.SpeechGame.SpeechInstructionActivity;

public class BabyActivity extends GameActivity implements BabyDraw {
  // Happiness of the baby. Also the player's score.
  private Integer happiness = 50;

  private TextView scoreDisplay;
  private Timer timer;

  @Override
  public void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);

    // Embed BabyView into xml layout
    setContentView(R.layout.activity_baby);
    BabyView babyView = new BabyView(this);
    babyView.setBabyDraw(this);
    Thread thread = new Thread(babyView);
    thread.start();
    FrameLayout babyFrame = findViewById(R.id.babyFrame);
    babyFrame.addView(babyView);

    setTitle("The Baby Game");

    // Score
    scoreDisplay = findViewById(R.id.scoreDisplay);
    String score = happiness.toString() + "%";
    scoreDisplay.setText(score);

    // Next Button (delete later)
    final Button next = findViewById(R.id.next);
    next.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            openSpeechGame();
          }
        });

    // Generate Pause Button
    new PauseButton((ConstraintLayout) findViewById(R.id.babyLayout), this);

    // Start Timer
    TextView timerView = findViewById(R.id.timerDisplay);
    timer = new Timer(timerView);
  }

  @Override
  protected void onPause() {
    super.onPause();
    timer.pause();
  }

  @Override
  protected void onResume() {
    super.onResume();
    timer.resume();
  }

  void openSpeechGame() {
    Intent switchSpeechIntent = new Intent(this, SpeechInstructionActivity.class);
    startActivity(switchSpeechIntent);
    finish();
  }

  public void updateScore(int happinessChange) {
    happiness += happinessChange;
    String score = happiness.toString() + "%";
    scoreDisplay.setText(score);
  }
}
