package com.company;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class Exercise_5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Properties props = new Properties();
        //Type your database user_name here:
        props.setProperty("user", "********");
        //Type your database password here:
        props.setProperty("password","******");

        String param = scanner.nextLine();
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minionsdb", props);
             PreparedStatement update = connection.prepareStatement(
                     "UPDATE towns set name=UPPER(name) where country_located=?");
             PreparedStatement getResult = connection.prepareStatement("SELECT name from towns where country_located= ?")){

            update.setString(1,param);

           int rowsAffected = update.executeUpdate();

           if (rowsAffected==0 ){
               System.out.println("No town names were affected.");
           } else{
               System.out.format("%d town names were affected.%n",rowsAffected);
               getResult.setString(1,param);
               ResultSet resultSet = getResult.executeQuery();
               resultSet.beforeFirst();
               List<String> towns = new ArrayList<>();
               while (resultSet.next()){
                   towns.add(resultSet.getString("name"));
               }
               System.out.println(towns.toString());
           }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
