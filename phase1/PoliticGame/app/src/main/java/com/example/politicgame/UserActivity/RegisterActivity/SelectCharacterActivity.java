package com.example.politicgame.UserActivity.RegisterActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.politicgame.Character.GameCharacter;
import com.example.politicgame.Character.PoliticianA;
import com.example.politicgame.Character.PoliticianB;
import com.example.politicgame.Character.UserAccount;
import com.example.politicgame.GameActivity;
import com.example.politicgame.GamesActivity.BabyGame.BabyActivity;
import com.example.politicgame.PoliticGameApp;
import com.example.politicgame.R;
import com.example.politicgame.UserActivity.LoginActivity.LoggedInActivity;

public class SelectCharacterActivity extends GameActivity {

    private PoliticGameApp app;
    private int currCharacter;
    private GameCharacter selectedCharacter;
    private Drawable highlight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = (PoliticGameApp) getApplication();
        setContentView(R.layout.activity_select_character);

        setTitle("Select Game Character");

        currCharacter = 0;
        highlight = getResources().getDrawable(R.drawable.highlight);

        final ImageView charAButton = findViewById(R.id.imageButton);
        final ImageView charBButton = findViewById(R.id.imageButton2);
        final TextView inputName = findViewById(R.id.name_input);
        final Button submitName = findViewById(R.id.submit_name);
        final Button backButton = findViewById(R.id.backButton);
        final TextView error_name = findViewById(R.id.error_name);
        final TextView error_select = findViewById(R.id.error_character);

        //Character A is selected
        charAButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        currCharacter = 1;
                        selectedCharacter = new PoliticianA();

                        charAButton.setBackground(highlight);
                        charBButton.setBackground(null);
                    }
                });

        //Character B is selected
        charBButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        currCharacter = 2;
                        selectedCharacter = new PoliticianB();

                        charBButton.setBackground(highlight);
                        charAButton.setBackground(null);
                    }
                });


        submitName.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        String name = inputName.getText().toString();
                        if (currCharacter != 0 && !name.equals(null) && !name.equals("")){
                            characterSet(name);
                        }

                        if (currCharacter == 0){
                            error_select.setText("Please select a character");
                        } else {
                            error_select.setText("");
                        }

                        if (name.equals("")){
                            error_name.setText("Enter a character name");
                        } else {
                            error_name.setText("");
                        }
                    }
                });

        backButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        openLoggedIn();
                    }
                });
    }

    public void characterSet(String name){
        selectedCharacter.setName(name);
        UserAccount user = app.getCurrentUser();
        user.addCharArray(selectedCharacter.getJsonCharacter());
        System.out.println("Saved!!!");
        user.saveToDb();

        //Sets current characters' name
        app.setCurrentCharacter(name);

        startGame();
    }

  public void startGame() {
      Intent startGameIntent = new Intent(this, BabyActivity.class);
      startActivity(startGameIntent);
      finish();
  }
}
