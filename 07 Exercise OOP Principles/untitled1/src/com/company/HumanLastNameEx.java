package com.company;

public class HumanLastNameEx extends RuntimeException {

    public String message = "Expected upper case letter!Argument: lastName";

    public HumanLastNameEx(){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
