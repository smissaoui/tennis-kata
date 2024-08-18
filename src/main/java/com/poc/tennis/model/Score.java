package com.poc.tennis.model;

import lombok.Getter;

@Getter
public enum Score {

    ZERO(0),
    FIFTEEN(15),
    THIRTY(30),
    FORTY(40),
    ADVANTAGE(45);

    private final int point;

    Score(int point){
        this.point = point;
    }

    public Score next(){
        return values()[ordinal() + 1];
    }

    public Score previous(){
        return values()[ordinal() - 1];
    }
}