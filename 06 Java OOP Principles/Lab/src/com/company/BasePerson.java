package com.company;

public abstract class BasePerson implements Person{
    private String name;

    public BasePerson(String name) {
        this.name = name;
    }

    public BasePerson(){}

    @Override
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
