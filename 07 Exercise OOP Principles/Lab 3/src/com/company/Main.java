package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
	// write your code here
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        List<Minion> minions = new ArrayList<>();
        String line;
        while (!"End".equals(line=reader.readLine().trim())){
            String[] tokes = line.split(" ");

            String type = tokes[0];

            switch (type){
                case "Pet":
                    Pet pet = new Pet(tokes[1], tokes[2]);
                    minions.add(pet);
                    break;
                case "Robot":
                    Robot robot= new Robot(tokes[1], tokes[2]);
                    minions.add(robot);
                    break;
                case "Citizen":
                    Citizen citizen= new Citizen(tokes[1],Integer.parseInt(tokes[2]), tokes[3], tokes[4]);
                    minions.add(citizen);
                    break;
            }
        }
        String year=reader.readLine().trim();
        for (Minion minion:minions) {
            if (minion instanceof BirthDayScan &&   ((BirthDayScan) minion).isYearInTarget(year)){
                System.out.println(((BirthDayScan) minion).getBirthDate());
            }
        }
    }
}
