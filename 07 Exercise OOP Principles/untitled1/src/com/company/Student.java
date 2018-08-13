package com.company;

public class Student extends Human {
    public String fackNumber;

    public Student(String firstName, String lastName, String fackNumber) {
        if (firstName.length()<4){
            throw new HumanFirstNameLenEx();
        } else if(!Character.isLetter(firstName.charAt(0))||Character.toUpperCase(firstName.charAt(0))!=firstName.charAt(0)){
            throw new HumanFirstNameEx();
        }

        if (lastName.length()<3){
            throw new HumanLastNameLenEx();
        } else if(!Character.isLetter(lastName.charAt(0))||Character.toUpperCase(lastName.charAt(0))!=lastName.charAt(0)){
            throw  new HumanLastNameEx();
        }

        if (fackNumber.length()<4||fackNumber.length()>10){
            throw new FackNumEx();
        }
        this.firstName=firstName;
        this.lastName=lastName;
        this.fackNumber=fackNumber;
    }

    public String getFackNumber() {
        return fackNumber;
    }
}
