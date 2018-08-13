package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class GroupbyGroup {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Person> persons = new ArrayList<>();
        String input;
        while (!"END".equals(input=reader.readLine().trim())){
            String[] tokens = input.split(" ");
            String name = tokens[0]+" "+tokens[1];
            int group = Integer.parseInt(tokens[2]);
            Person person = new Person(name, group);
            persons.add(person);
        }

        persons.stream()
                .collect(Collectors.groupingBy(Person::getGroup))
        .entrySet().stream().forEach(x->{
            String output = "";
            output+=x.getKey()+" - ";
            output+=String.join(", ",x.getValue().stream().map(y->y.getName()).collect(Collectors.toList()));
            System.out.println(output);
        });

    }
}

class Person {
    private String Name;
    private int Group;

    public Person(){}

    public Person(String name, int group){
        this.Group=group;
        this.Name=name;
    }

    public String getName() {
        return Name;
    }

    public int getGroup() {
        return Group;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setGroup(int group) {
        Group = group;
    }
}