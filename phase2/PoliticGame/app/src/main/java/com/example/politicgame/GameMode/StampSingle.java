package com.example.politicgame.GameMode;

import android.content.Context;
import android.content.Intent;

import com.example.politicgame.Application.PoliticGameApp;

public class StampSingle extends StampGameMode{
    PoliticGameApp app;

    StampSingle(PoliticGameApp app){
        super(app);
    }

    //TODO: Implement this
    public Intent next(Context lastActivity){
        return null; //TODO: REMOVE THIS
    }

    //TODO: Implement this, which includes implementing a seperate JSON writing system
    public void save(int score){

    }
}