package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StudentsByEnroll {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input;
        List<String> students = new ArrayList<>();
        while (!"END".equals(input=reader.readLine().trim())){
            students.add(input);
        }
        students.stream()
                .map(x->x.split(" "))
                .filter(x->x[0].charAt(4)=='1' && (x[0].charAt(5)=='4'||x[0].charAt(5)=='5'))
                .forEach(x-> {
                    String output = "";
                    for(Object val:Arrays.stream(x).skip(1).toArray()){
                        output+=val+" ";
                    }
                    System.out.println(output.trim());
                });
    }
}
