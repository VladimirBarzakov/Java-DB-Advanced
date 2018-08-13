package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

public class FirstName {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();
        String param = reader.readLine();
        Optional<String> result = Arrays.stream(input.split(" "))
                .filter(x->{
                    boolean validation=false;
                    String[] params= param.split(" ");
                    for (String value:params) {
                        if (x.toUpperCase().startsWith(value.toUpperCase())){
                            validation=true;
                            break;
                        }
                    }
                return validation;
                })
                .sorted((x,y)->x.compareTo(y)).findFirst();
        if (result.isPresent()){
            System.out.println(result.get());
        } else {
            System.out.println("No match");
        }
    }
}
