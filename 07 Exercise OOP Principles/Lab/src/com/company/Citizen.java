package com.company;

public class Citizen implements Person, Birthable, Identifiable {

    private String name;
    private int age;
    private String birthDate;
    private String id;
    public Citizen(String name, int age,String id, String birthDate){
        this.age = age;
        this.name=name;
        this.birthDate=birthDate;
        this.id=id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public String getBirthDate() {
        return birthDate;
    }

    @Override
    public String getId() {
        return id;
    }
}
