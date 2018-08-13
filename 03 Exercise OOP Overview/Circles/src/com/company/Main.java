package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        double[] input = Arrays.stream(reader.readLine().split(" ")).mapToDouble(Double::parseDouble).toArray();
        Circle circle1 = new Circle(input[0], input[1], input[2]);

        input = Arrays.stream(reader.readLine().split(" ")).mapToDouble(Double::parseDouble).toArray();
        Circle circle2 = new Circle(input[0], input[1], input[2]);

        if (circle1.intersect(circle2)){
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }
}
