package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.OptionalInt;

public class FindAndSumIntegers {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        OptionalInt result = Arrays.stream(reader.readLine().split(" "))
                .filter(x->{
                    try {
                        Integer.parseInt(x);
                        return true;
                    } catch (Exception e){
                        return false;
                    }
                }).mapToInt(Integer::parseInt).reduce((x,y)->x+y);

        if (result.isPresent()){
            System.out.println(result.getAsInt());
        } else {
            System.out.println("No match");
        }



    }
}
