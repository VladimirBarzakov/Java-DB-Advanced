package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class StudnetsByAge {
    public static void main(String[] args) throws IOException {
        ArrayList<String> studentGroups = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input;
        while(!"END".equals(input=reader.readLine())){
            studentGroups.add(input.trim());
        }
        studentGroups.stream()
                .map(x->x.split(" "))
                .filter(x->Integer.parseInt(x[2])>=18 && Integer.parseInt(x[2])<=24)
                .forEach(x-> System.out.printf("%s %s %s"+System.lineSeparator(),x[0],x[1],x[2]));
    }
}
