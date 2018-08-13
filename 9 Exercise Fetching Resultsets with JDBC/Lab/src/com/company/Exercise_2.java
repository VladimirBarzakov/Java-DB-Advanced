package com.company;

import java.sql.*;
import java.util.Properties;

public class Exercise_2 {
    public static void main(String[] args) {

        Properties props = new Properties();
        //Type your database user_name here:
        props.setProperty("user", "*********");
        //Type your database password here:
        props.setProperty("password","***********");



        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minionsdb", props);
             PreparedStatement prStmt = connection.prepareStatement(
                     "SELECT v.name as `name`, count(vm.id_minion) as `minions` from villains_minions as vm \n" +
                     "JOIN villains as v on vm.id_villain=v.id\n" +
                     "GROUP BY v.id\n" +
                     "HAVING `minions`>3\n" +
                     "ORDER BY `minions` DESC")){

            ResultSet resultSet = prStmt.executeQuery();
            resultSet.beforeFirst();
            while (resultSet.next()){
                System.out.format("%s %d%n",resultSet.getString("name"), resultSet.getInt("minions"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
