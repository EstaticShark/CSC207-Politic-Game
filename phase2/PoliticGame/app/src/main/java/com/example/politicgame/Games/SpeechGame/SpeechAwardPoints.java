package com.example.politicgame.Games.SpeechGame;

public class SpeechAwardPoints {
    private final int POINTSGIVEN = 20; //determines how many points are won/lost
    private static int currentPoints;
    private static String feedback;
    SpeechAwardPoints(int rating){
        currentPoints = rating;
        feedback = "Your current rating is: " + currentPoints + "%";
    }

    static int getCurrentPoints() {
        return currentPoints;
    }

    static String getFeedback() {
        return feedback;
    }

    void awardPoints(){
        if (currentPoints <= 100 - POINTSGIVEN) {
            currentPoints += POINTSGIVEN;
            feedback = "Your current rating is: " + currentPoints + "%";
            System.out.println(currentPoints);
        }
    }

    void losePoints() {
        if (currentPoints >= POINTSGIVEN) {
            currentPoints -= POINTSGIVEN;
            feedback = "Your current rating is: " + currentPoints + "%";
        }
    }



}
