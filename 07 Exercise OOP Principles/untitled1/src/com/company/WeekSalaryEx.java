package com.company;

public class WeekSalaryEx extends RuntimeException {

    public String message = "Expected value mismatch!Argument: weekSalary";
    public WeekSalaryEx(){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
