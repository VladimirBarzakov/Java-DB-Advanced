package com.company;

public class Cat extends Animal {
    @Override
    public void produceSound() {
        System.out.println("MiauMiau");
    }

    public Cat(String name, Long age, String gender) {
        super(name, age, gender);
    }
}
