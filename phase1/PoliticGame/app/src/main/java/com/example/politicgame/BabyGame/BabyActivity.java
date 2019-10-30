package com.example.politicgame.BabyGame;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.politicgame.MainActivity;
import com.example.politicgame.PauseButton;
import com.example.politicgame.PoliticGameApp;
import com.example.politicgame.R;
import com.example.politicgame.SpeechGame.SpeechInstructionActivity;

public class BabyActivity extends AppCompatActivity {
  // Happiness of the baby. Also the player's score.
  protected PoliticGameApp app;
  static Integer happiness = 50;
  long timeLeft;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    app = (PoliticGameApp) getApplication();

    System.out.println("The current theme is blue: " + app.isThemeBlue());

    if (app.isThemeBlue()){
      setTheme(R.style.BlueTheme);
    } else {
      setTheme(R.style.RedTheme);
    }

    super.onCreate(savedInstanceState);

    // Embed BabyView into xml layout
    setContentView(R.layout.activity_baby);
    BabyView babyView = new BabyView(this);
    FrameLayout babyFrame = findViewById(R.id.babyFrame);
    babyFrame.addView(babyView);

    // Timer
    final TextView timer_display = findViewById(R.id.timer_display);

    // Score
    TextView score_display = findViewById(R.id.score_display);
    String score = happiness.toString() + "%";
    score_display.setText(score);

    // Game lasts for 60 seconds.
//    final CountDownTimer timer =
//        new CountDownTimer(60000, 1000) {
//          public void onTick(long millisUntilFinished) {
//            timeLeft = millisUntilFinished;
//            String timeLeft = "seconds remaining: " + millisUntilFinished / 1000;
//            timer_display.setText(timeLeft);
//            if ((millisUntilFinished / 1000) % 5 == 0) {
//              randomEvent(); // Randomly chooses a baby action (can be nothing)
//            }
//          }
//
//          public void onFinish() {
//            openSpeechGame();
//          }
//        };
//    timer.start();

    // Next Button (delete later)
    final Button next = findViewById(R.id.next);
    next.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            openSpeechGame();
//            timer.cancel();
          }
        });

    // Generate Pause Button
    new PauseButton((ConstraintLayout) findViewById(R.id.babyLayout), this);
  }

//  void randomEvent() {
//    Random rand = new Random();
//    final int randomNum = rand.nextInt((4) + 1) + 1;
//    if (randomNum == 1) {
//      startActivityForResult(new Intent(this, Shake.class), randomNum);
//    }
//    new CountDownTimer(5000, 1000) {
//      @Override
//      public void onTick(long millisUntilFinished) {}
//
//      @Override
//      public void onFinish() {
//        finishActivity(randomNum);
//      }
//    }.start();
//  }

  void openSpeechGame() {
    Intent switchSpeechIntent = new Intent(this, SpeechInstructionActivity.class);
    startActivity(switchSpeechIntent);
    finish();
  }

  public void openMainMenu() {
    Intent mainMenuIntent = new Intent(this, MainActivity.class);
    startActivity(mainMenuIntent);
    finish();
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    // requestCode refers to the request code parameter of openPauseMenu's startActivityForResult
    // call
    if (requestCode == 1) {
      if (resultCode == RESULT_OK) {
        int userInput = data.getIntExtra("result", 0);

        if (userInput == 1) {
          Log.i("Pause Result", "User has decided to resume play");
        } else if (userInput == 2) {
          Log.i("Pause Result", "User has decided to quit the game");
          openMainMenu();
        }
      } else {
        Log.i("Result Code", "Result code is " + resultCode);
      }
    }
  }
}
