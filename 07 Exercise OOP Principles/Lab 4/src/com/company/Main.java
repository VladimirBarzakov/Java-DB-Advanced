package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
	// write your code here
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String line;

        while (!"Beast!".equals(line=reader.readLine())){
            try {
                String type = line;
                String[] tokens = reader.readLine().trim().split(" ");
                String name = tokens[0];
                Long age= Long.parseLong(tokens[1]);
                String gender;
                switch (type) {
                    case "Cat":
                        gender = tokens[2];
                        Cat cat = new Cat(name, age, gender);
                        System.out.println(type);
                        System.out.printf("%s %d %s" + System.lineSeparator(), cat.getName(), cat.getAge(), cat.getGender());
                        cat.produceSound();
                        break;
                    case "Dog":
                        gender = tokens[2];
                        Dog dog = new Dog(name, age, gender);
                        System.out.println(type);
                        System.out.printf("%s %d %s" + System.lineSeparator(), dog.getName(), dog.getAge(), dog.getGender());
                        dog.produceSound();
                        break;
                    case "Frog":
                        gender = tokens[2];
                        Frog frog = new Frog(name, age, gender);
                        System.out.println(type);
                        System.out.printf("%s %d %s" + System.lineSeparator(), frog.getName(), frog.getAge(), frog.getGender());
                        frog.produceSound();
                        break;
                    case "Tomcat":
                        Tomcat tomcat = new Tomcat(name, age);
                        System.out.println(type);
                        System.out.printf("%s %d %s" + System.lineSeparator(), tomcat.getName(), tomcat.getAge(), tomcat.getGender());
                        tomcat.produceSound();
                        break;
                    case "Kitten":
                        Kitten kitten = new Kitten(name, age);
                        System.out.println(type);
                        System.out.printf("%s %d %s" + System.lineSeparator(), kitten.getName(), kitten.getAge(), kitten.getGender());
                        kitten.produceSound();
                        break;
                        default:
                            gender = tokens[2];
                            Other other = new Other(name,age,gender);
                            System.out.println(type);
                            System.out.printf("%s %d %s" + System.lineSeparator(), other.getName(), other.getAge(), other.getGender());
                            other.produceSound();
                        break;

                }

            } catch (MyException|ArrayIndexOutOfBoundsException e){
                System.out.println("Invalid input!");
            }
        }
    }
}
