package com.company;

public class HumanFirstNameLenEx extends RuntimeException {

    public String message = "Expected length at least 4 symbols!Argument: firstName";
    public HumanFirstNameLenEx(){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
