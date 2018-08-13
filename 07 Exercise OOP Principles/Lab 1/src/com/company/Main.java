package com.company;

import java.lang.instrument.IllegalClassFormatException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IllegalClassFormatException {
        Scanner scanner = new Scanner(System.in);
        Car car = new Ferrari(scanner.nextLine());
        System.out.println(car.toString());

        String ferrariName = Ferrari.class.getSimpleName();
        String carInterface = Car.class.getSimpleName();
        boolean isCreated = Car.class.isInterface();
        if (!isCreated) {
            throw new IllegalClassFormatException("No interface created!");
        }
    }
}
