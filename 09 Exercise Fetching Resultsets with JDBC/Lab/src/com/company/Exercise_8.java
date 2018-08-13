package com.company;

import java.sql.*;
import java.util.*;

public class Exercise_8 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Properties props = new Properties();
        //Type your database user_name here:
        props.setProperty("user", "*******");
        //Type your database password here:
        props.setProperty("password","********");

        int[] params = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minionsdb", props);
             PreparedStatement update = connection.prepareStatement("");
             Statement getResult = connection.prepareStatement("")){

            String stringParams = "";
            String sqlUpdateStmt="UPDATE minions set name=concat(UPPER(SUBSTRING(name,1,1)),SUBSTRING(name,2,LENGTH (name)-1)),`age`=`age`+1 where `id` in ";
            for (int i = 0; i < params.length; i++) {
                stringParams+=params[i]+", ";
            }
            stringParams=stringParams.substring(0,stringParams.length()-2);
            sqlUpdateStmt+="("+stringParams+")";
            update.executeUpdate(sqlUpdateStmt);

            ResultSet rs = getResult.executeQuery("SELECT  name, age from minions");
            rs.beforeFirst();
            while (rs.next()){
                System.out.format("%s %d%n",rs.getString("name"),rs.getInt("age"));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
