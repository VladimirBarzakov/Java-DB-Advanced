package com.company;

public class HumanLastNameLenEx extends RuntimeException{

    public String message = "Expected length at least 3 symbols!Argument: lastName";

    public HumanLastNameLenEx(){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
