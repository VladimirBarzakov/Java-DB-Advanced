package com.company;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class Exercise_9 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Properties props = new Properties();
        //Type your database user_name here:
        props.setProperty("user", "*****");
        //Type your database password here:
        props.setProperty("password","*****");
        int minionId =Integer.parseInt(scanner.nextLine());

        try( Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minionsdb", props);
             CallableStatement stmt = connection.prepareCall("CALL usp_get_older(?)");
             PreparedStatement getResult = connection.prepareStatement("SELECT name, `age` from minions WHERE  id= ?")
        ) {
            stmt.setInt(1,minionId);
            stmt.executeQuery();
            getResult.setInt(1,minionId);
            ResultSet resultSet = getResult.executeQuery();
            resultSet.beforeFirst();
            while (resultSet.next()){
                System.out.printf("%s %d", resultSet.getString("name"), resultSet.getInt("age"));
            }



        } catch (SQLException ex){
            ex.printStackTrace();
        }

    }
}
