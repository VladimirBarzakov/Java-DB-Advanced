package com.company;

public class WorkingHoursEx extends RuntimeException {

    public String message = "Expected value mismatch!Argument: workHoursPerDay";
    public WorkingHoursEx(){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
