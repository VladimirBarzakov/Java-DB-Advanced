package com.company;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class Exercise_3 {
    public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

            Properties props = new Properties();
            //Type your database user_name here:
            props.setProperty("user", "*******");
            //Type your database password here:
            props.setProperty("password","**********");

            int param = Integer.parseInt(scanner.nextLine());

            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minionsdb", props);
                 PreparedStatement getVillainStmt = connection.prepareStatement("SELECT name from villains WHERE \n" +
                         "id=?");
                 PreparedStatement getMinionsStmt=connection.prepareStatement(
                         "SELECT m.name, m.age FROM villains_minions as vm \n" +
                         "JOIN minions as m on vm.id_minion=m.id\n" +
                         "JOIN villains as v on v.id=vm.id_villain\n" +
                         "WHERE vm.id_villain=?")
                 ){

                getVillainStmt.setInt(1,param);
                ResultSet rs = getVillainStmt.executeQuery();

                rs.beforeFirst();
                if (!rs.next()){
                    System.out.format("No villain with ID %d exists in the database.",param);
                } else{
                    StringBuilder builder = new StringBuilder();
                    builder.append(String.format("Villain: %s%n",rs.getString("name")));
                    getMinionsStmt.setInt(1,param);
                    ResultSet rsMinions = getMinionsStmt.executeQuery();

                    int counter=1;
                    rsMinions.beforeFirst();
                    while (rsMinions.next()){
                        builder.append(String.format("%d. %s %d%n",counter++,rsMinions.getString("name"),rsMinions.getInt("age")));
                    }
                    System.out.println(builder.toString().trim());

                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

}
