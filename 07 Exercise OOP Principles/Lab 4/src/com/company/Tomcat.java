package com.company;

public class Tomcat extends Animal{
    private String name;
    private Long age;
    private String gender;

    public Tomcat(String name, Long age) {
        super(name, age, "Male");
    }

    @Override
    public void produceSound() {
        System.out.println("Give me one million b***h");
    }
}
