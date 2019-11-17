package com.example.politicgame.Games.BabyGame;

import static java.lang.Thread.sleep;

public class EventsGenerator implements Runnable {

  private final EventManager eventManager;
  private boolean isRunning;

  EventsGenerator(EventManager eventManager) {
    this.eventManager = eventManager;
    isRunning = true;
  }

  @Override
  public void run() {
    while (isRunning) {
      eventManager.randomEvent();
      try {
        sleep(1000);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  public void setRunning(boolean runningState) {
    isRunning = runningState;
  }
}
