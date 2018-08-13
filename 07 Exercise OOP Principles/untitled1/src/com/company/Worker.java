package com.company;

public class Worker extends Human {
    public int weekSalary;
    public int workHoursPerWeek;

    public Worker(String firstName, String lastName, int weekSalary, int workHoursPerWeek) {
        if (firstName.length()<4){
            throw new HumanFirstNameLenEx();
        } else if(!Character.isLetter(firstName.charAt(0))||Character.toUpperCase(firstName.charAt(0))!=firstName.charAt(0)){
            throw new HumanFirstNameEx();
        }

        if (lastName.length()<3){
            throw new HumanLastNameLenEx();
        } else if(!Character.isLetter(lastName.charAt(0))||Character.toUpperCase(lastName.charAt(0))!=lastName.charAt(0)){
            throw  new WorkerLastNameEx();
        }

        if (weekSalary<=10){
            throw new WeekSalaryEx();
        }

        if (workHoursPerWeek<1||workHoursPerWeek>12){
            throw new WorkingHoursEx();
        }

        this.firstName=firstName;
        this.lastName=lastName;
        this.weekSalary = weekSalary;
        this.workHoursPerWeek = workHoursPerWeek;
    }

    public double getSalaryPerHour(){
        return ((double)this.weekSalary/7.0)/ (double)this.workHoursPerWeek;
    }
}
