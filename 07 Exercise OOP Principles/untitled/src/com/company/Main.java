package com.company;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
	// write your code here
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] tokens = reader.readLine().trim().split(" ");
        Car car = new Car(Double.parseDouble(tokens[1]), Double.parseDouble(tokens[2]));
        tokens = reader.readLine().trim().split(" ");
        Truck truck = new Truck(Double.parseDouble(tokens[1]), Double.parseDouble(tokens[2]));

        int n = Integer.parseInt(reader.readLine());
        for (int i = 0; i < n; i++) {
            tokens = reader.readLine().trim().split(" ");
            String action = tokens[0];
            String type =tokens[1];
            Double quanity=Double.parseDouble(tokens[2]);

            switch (action){
                case "Drive":
                    switch (type){
                        case "Car":
                            car.drive(quanity);
                            break;
                        case "Truck":
                            truck.drive(quanity);
                            break;
                }
                break;
                case "Refuel":
                    switch (type){
                        case "Car":
                            car.refuel(quanity);
                            break;
                        case "Truck":
                            truck.refuel(quanity);
                            break;
                    }
                break;
            }
        }

        System.out.println(car.toString());
        System.out.println(truck.toString());
    }
}
