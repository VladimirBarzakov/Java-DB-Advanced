package com.company;

public class HumanFirstNameEx extends RuntimeException {
    public String message = "Expected upper case letter!Argument: firstName";

    public HumanFirstNameEx(){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
