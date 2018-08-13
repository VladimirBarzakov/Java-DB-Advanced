package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] numbers = scanner.nextLine().split(" ");
        String[] sites = scanner.nextLine().split(" ");
        Smartphone smartphone = new Smartphone();
        smartphone.calling(numbers);
        smartphone.browsing(sites);
    }
}
