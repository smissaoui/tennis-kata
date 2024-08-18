package com.poc.tennis.exception;

public class IncorrectInputException extends Exception{

    public IncorrectInputException(String errorMessage){
        super(errorMessage);
    }
}
