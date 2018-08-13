package com.company;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class Exercise_4 {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        Properties props = new Properties();
        //Type your database user_name here:
        props.setProperty("user", "********");
        //Type your database password here:
        props.setProperty("password","**********");

        System.out.print("Minion: ");
        String[] minionParams = scanner.nextLine().split(" ");
        System.out.print("Villain: ");
        String villainName=scanner.next();
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minionsdb", props);

        try (
             Statement insertStatement=connection.createStatement();
             PreparedStatement getVillaintId=connection.prepareStatement(
                     "SELECT id from villains\n" +
                     "WHERE `name`= ?");
             PreparedStatement getTownId=connection.prepareStatement(
                     "SELECT id FROM towns \n" +
                     "WHERE `name` = ?");
             PreparedStatement getIdNewMinion=connection.prepareStatement(
                     "SELECT id from minions\n" +
                     "where `name`= ? \n" +
                     "ORDER BY id DESC \n")){
            String textResult="";

            connection.setAutoCommit(false);

            String minionName=minionParams[0];
            int age = Integer.parseInt(minionParams[1]);
            String townName = minionParams[2];

            int townId=-1;
            getTownId.setString(1,townName);
            ResultSet townResult=getTownId.executeQuery();
            townResult.beforeFirst();
            if (!townResult.next()){
                insertStatement.executeUpdate("INSERT INTO towns (`name`, `country_located`) VALUE ('"+townName+"','NOT SPECIFIED')");
                textResult+=String.format("Town %s was added to the database.%n",townName);
            }else{
                townId=townResult.getInt("id");
            }

            int villaintId=-1;
            getVillaintId.setString(1,villainName);
            ResultSet villainResult =getVillaintId.executeQuery();
            villainResult.beforeFirst();
            if (!villainResult.next()){
                insertStatement.executeUpdate("INSERT INTO villains (`name`, `evilness_factor`) VALUE ('"+villainName+"','evil')");
                textResult+=String.format("Villain %s was added to the database.%n",villainName);
            }else {
                villaintId=villainResult.getInt("id");
            }

            //connection.commit();
            townId = getPropertyId(getTownId, townId);
            villaintId = getPropertyId(getVillaintId, villaintId);

            insertStatement.executeUpdate("INSERT INTO minions (`name`,`age`, `town_id`) VALUE ('"+minionName+"',"+age+","+townId+")");

            getIdNewMinion.setString(1,minionName);
            ResultSet minionResult=getIdNewMinion.executeQuery();
            minionResult.beforeFirst();
            minionResult.next();
            int minionId=minionResult.getInt("id");

            insertStatement.executeUpdate("INSERT INTO villains_minions (`id_minion`, `id_villain`) VALUE ("+minionId+","+villaintId+")");

            textResult+=String.format("Successfully added %s to be minion of %s",minionName,villainName);

            System.out.println(textResult);

            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();

        }
        finally {
            connection.close();
        }
    }

    private static int getPropertyId(PreparedStatement stmt, int result) throws SQLException {
        ResultSet townResult;
        if (result==-1){
            townResult=stmt.executeQuery();
            townResult.beforeFirst();
            townResult.next();
            result=townResult.getInt("id");
        }
        return result;
    }
}
