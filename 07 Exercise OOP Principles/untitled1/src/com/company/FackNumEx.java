package com.company;

public class FackNumEx extends RuntimeException {

    public String message = "Invalid faculty number!";

    public FackNumEx(){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
