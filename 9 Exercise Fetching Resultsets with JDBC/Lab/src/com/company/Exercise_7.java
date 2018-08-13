package com.company;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Exercise_7 {
    public static void main(String[] args) {

        Properties props = new Properties();
        //Type your database user_name here:
        props.setProperty("user", "*******");
        //Type your database password here:
        props.setProperty("password","***********");

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minionsdb", props);
             PreparedStatement prStmt = connection.prepareStatement(
                     "SELECT name FROM  minions")){

            ResultSet resultSet = prStmt.executeQuery();
            resultSet.beforeFirst();
            List<String> minions = new ArrayList<>();
            while (resultSet.next()){
                minions.add(resultSet.getString("name"));
            }
            for (int i = 0; i < minions.size()/2; i++) {

                System.out.println(minions.get(i));
                System.out.println(minions.get(minions.size()-1-i));
                if (i+1==minions.size()-2-i){
                    System.out.println(minions.get(i+1));
                    break;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
