package com.company;

public class Dog extends Animal {

    @Override
    public void produceSound() {
        System.out.println("BauBau");
    }

    public Dog(String name, Long age, String gender) {
        super(name, age, gender);
    }
}
