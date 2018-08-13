package com.company;

public class Citizen extends Minion implements BirthDayScan{
    private String name;
    private int age;
    private String id;
    private String birthDate;

    public Citizen(String name, int age, String id, String birthDate) {
        this.name = name;
        this.age = age;
        this.id = id;
        this.birthDate=birthDate;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getId() {
        return id;
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
