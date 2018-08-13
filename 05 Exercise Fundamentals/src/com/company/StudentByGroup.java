package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class StudentByGroup {
    public static void main(String[] args) throws IOException {
        ArrayList<String> studentGroups = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input;
        while(!"END".equals(input=reader.readLine())){
            studentGroups.add(input.trim());
        }
        studentGroups.stream()
                .filter(x->x.substring(x.length()-2).equals(" 2"))
                .map(x->x.substring(0,x.length()-2))
                .map(x->x.split(" "))
                .sorted((x,y)->x[0].compareTo(y[0]))
                .forEach(x-> System.out.printf("%s %s"+System.lineSeparator(),x[0],x[1]));
    }
}
