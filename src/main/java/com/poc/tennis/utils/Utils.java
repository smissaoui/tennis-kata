package com.poc.tennis.utils;

import lombok.experimental.UtilityClass;

import static com.poc.tennis.utils.Constant.ADVANTAGE;

@UtilityClass
public class Utils {

    public String transformScoreToString(int score){
        if(score == 45){
            return ADVANTAGE;
        }else {
            return Integer.toString(score);
        }
    }
}
