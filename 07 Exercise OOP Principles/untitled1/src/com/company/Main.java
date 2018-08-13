package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Scanner scanner = new Scanner(System.in);
        String line;
        try {
            line=scanner.nextLine();
            String[] tokens = line.split(" ");
            Student student = new Student(tokens[0],tokens[1],tokens[2]);

            line=scanner.nextLine();
            tokens=line.split(" ");
            Worker worker = new Worker(tokens[0],tokens[1],Integer.parseInt(tokens[2]),Integer.parseInt(tokens[3]));

            System.out.printf("First Name: %s"+System.lineSeparator()
                    +"Last Name: %s"+System.lineSeparator()
                    +"Faculty number: %s"+System.lineSeparator(),student.getFirstName(),student.getLastName(),student.getFackNumber());
            System.out.println();
            System.out.printf("First Name: %s\n" +
                    "Last Name: %s\n" +
                    "Week Salary: %.2f\n" +
                    "Hours per day: %.2f\n" +
                    "Salary per hour: %.2f",worker.getFirstName(),worker.getLastName(),(double)worker.weekSalary,(double)worker.workHoursPerWeek,worker.getSalaryPerHour());
        } catch (FackNumEx|HumanFirstNameLenEx|HumanFirstNameEx|HumanLastNameLenEx|HumanLastNameEx|WorkerLastNameEx|WeekSalaryEx|WorkingHoursEx e){
            System.out.println(e.getMessage());
        }



    }
}
