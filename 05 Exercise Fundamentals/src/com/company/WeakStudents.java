package com.company;

        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.util.ArrayList;
        import java.util.Arrays;

public class WeakStudents {
    public static void main(String[] args) throws IOException {
        ArrayList<String> studentGroups = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input;
        while(!"END".equals(input=reader.readLine())){
            studentGroups.add(input.trim());
        }
        studentGroups.stream()
                .map(x->x.split(" "))
                .filter(x-> Arrays.stream(Arrays.copyOfRange(x, 2, x.length)).
                        mapToDouble(Double::parseDouble).filter(y->y<=3.0).count()>=2)
                .forEach(x-> System.out.printf("%s %s"+System.lineSeparator(),x[0],x[1]));
    }
}
