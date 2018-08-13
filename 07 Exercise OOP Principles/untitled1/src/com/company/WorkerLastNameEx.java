package com.company;

public class WorkerLastNameEx extends RuntimeException {
    public String message = "Expected length more than 3 symbols!Argument: lastName";
    public WorkerLastNameEx(){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
