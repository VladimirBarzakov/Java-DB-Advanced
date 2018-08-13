package com.company;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class LowerUpperBound {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] bound = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.stream(reader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .filter(x->x >= Math.min(bound[0],bound[1]) && x<= Math.max(bound[0],bound[1]) ).forEach(x-> System.out.print(x+" "));
    }
}
