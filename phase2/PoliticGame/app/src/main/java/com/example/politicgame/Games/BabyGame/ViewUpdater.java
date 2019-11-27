package com.example.politicgame.Games.BabyGame;

/**
 * An interface designed to provide a dependency injection between BabyView and EventManager.
 * EventManager needs to access certain parts of BabyView but cannot do so directly because BabyView
 * depends on EventManager.
 */
interface ViewUpdater {
  /** Draws baby and events. */
  void drawUpdate();

  /**
   * Updates happiness.
   *
   * @param happinessChange the amount to change happiness by
   */
  void updateScore(int happinessChange);

  /**
   * Updates event action.
   *
   * @param eventAction the action to be performed
   */
  void updateEventAction(String eventAction);
}
