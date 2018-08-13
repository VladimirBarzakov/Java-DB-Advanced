package com.company;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class DiabloLab {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password","Disturbed1");

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/diablo", props)) {
            try (PreparedStatement stmt = connection.prepareStatement(
                    "SELECT u.first_name as `firstName`, u.last_name as `lastName`, count(us.id) as `countGames` from users_games as us\n" +
                    "INNER JOIN users as u on us.user_id=u.id\n" +
                    "where u.user_name=?\n" +
                    "GROUP BY u.id")){

                String userName = sc.nextLine();

                stmt.setString(1,userName);
                ResultSet rs = stmt.executeQuery();
                rs.beforeFirst();
                if (!rs.next()){
                    System.out.println("No such user exists");
                } else{
                    rs.beforeFirst();
                    while (rs.next()){
                        System.out.format("User: %s %n%s %s has played %d games",
                                userName,
                                rs.getString("firstName"),
                                rs.getString("lastName"),
                                rs.getInt("countGames"));
                    }
                }

            } catch (SQLException ex){
                ex.printStackTrace();
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
