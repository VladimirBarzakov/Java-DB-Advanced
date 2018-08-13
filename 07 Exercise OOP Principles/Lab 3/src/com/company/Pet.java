package com.company;

public class Pet extends Minion implements BirthDayScan{
    private  String name;
    private String birthDate;

    public Pet(String name, String birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }
    @Override
    public String getBirthDate() {
        return birthDate;
    }

    @Override
    public boolean isYearInTarget(String year) {
        return this.birthDate.endsWith(year);
    }
}
