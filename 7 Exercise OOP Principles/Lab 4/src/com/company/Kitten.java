package com.company;

public class Kitten extends Animal{
    private String name;
    private Long age;
    private String gender;

    public Kitten(String name, Long age) {
        super(name,age,"Female");
    }

    @Override
    public void produceSound() {
        System.out.println("Miau");
    }
}
