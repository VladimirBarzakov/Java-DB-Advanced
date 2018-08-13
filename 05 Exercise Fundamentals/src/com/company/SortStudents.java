package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class SortStudents {
    public static void main(String[] args) throws IOException {
        ArrayList<String> studentGroups = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input;
        while(!"END".equals(input=reader.readLine())){
            studentGroups.add(input.trim());
        }
        studentGroups.stream()
                .map(x->x.split(" "))
                .sorted((x,y)->{
                    int result = x[1].compareTo(y[1]);
                    if (result==0){
                        result=y[0].compareTo(x[0]);
                    }
                    return  result;
                })
                .forEach(x-> System.out.printf("%s %s"+System.lineSeparator(),x[0],x[1]));
    }
}
