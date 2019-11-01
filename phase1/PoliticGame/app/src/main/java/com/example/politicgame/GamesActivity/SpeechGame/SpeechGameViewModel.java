package com.example.politicgame.GamesActivity.SpeechGame;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class SpeechGameViewModel extends ViewModel {
  private SpeechRepository speechRepo;
  private boolean exitPoint = false;

  public SpeechRepository getSpeechRepo() {
    return speechRepo;
  }

  public String loadPrompt() {
    String displayPrompt = new String();
    if (this.speechRepo.getPrompt().size() > 0) {
      displayPrompt = this.speechRepo.getPrompt().get(0);
      this.getSpeechRepo().getPrompt().remove(displayPrompt);
    } else {
      exitPoint = true;
    }
    return displayPrompt;
  }

  public String loadAnswer() {
    String displayAnswer = new String();
    if (this.speechRepo.getAnswer().size() > 0) {
      displayAnswer = this.speechRepo.getAnswer().get(0);
      this.getSpeechRepo().getAnswer().remove(displayAnswer);
    } else {
      exitPoint = true;
    }
    return displayAnswer;
  }

  public ArrayList<String> loadChoice() {
    ArrayList<String> displayChoice = new ArrayList<>();
    if (this.speechRepo.getChoice().size() > 0) {
      displayChoice = this.speechRepo.getChoice().get(0);
      this.getSpeechRepo().getChoice().remove(displayChoice);
    } else {
      exitPoint = true;
      displayChoice.add("");
      displayChoice.add("");
      displayChoice.add("");
      displayChoice.add("");
    }
    return displayChoice;
  }

  public SpeechGameViewModel() {
    this.speechRepo = new SpeechRepository();
  }

  private Context context;
  private int roundNum = 3;

  public void loadQuestions() {
    this.speechRepo.loadQuestions(roundNum);
  }
}
