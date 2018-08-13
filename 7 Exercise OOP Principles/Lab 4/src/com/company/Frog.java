package com.company;

public class Frog extends Animal{
    @Override
    public void produceSound() {
        System.out.println("Frogggg");
    }

    public Frog(String name, Long age, String gender) {
        super(name, age, gender);
    }
}
