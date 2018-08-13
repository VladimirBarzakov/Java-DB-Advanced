package com.company;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {
	// write your code here
        Scanner sc = new Scanner(System.in);

        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password","Disturbed1");

        String salary = sc.nextLine();


        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/soft_uni", props);
             PreparedStatement stmt = connection.prepareStatement("SELECT * FROM employees WHERE salary > ?")
             ){
            stmt.setDouble(1,Double.parseDouble(salary));
            ResultSet rs = stmt.executeQuery();
            rs.beforeFirst();
            while (rs.next()){
                System.out.println(rs.getString("first_name")+" "+rs.getString("last_name"));
            }

        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }
}
