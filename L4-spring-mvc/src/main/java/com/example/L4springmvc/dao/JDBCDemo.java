package com.example.L4springmvc.dao;

import java.sql.*;

public class JDBCDemo {

    Connection connection;

    JDBCDemo(){
        try {
            connection  = DriverManager.getConnection("jdbc:mysql://localhost:3306/myDB"
                    , "user", "password");
        } catch (Exception e){
            //eat
        }

    }


    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/myDB"
                , "user", "password");

        /**
         * CREATE TABLE CAR (
         *
         * 	ID INT,
         * 	name varchar(255),
         *   	engineid INT
         *
         * )
         *
         * CREATE TABLE ENGINE (
         *
         * 	ID INT,
         * 	name varchar(255),
         *   	cc INT
         *
         * )
         *
         * INSERT INTO ENGINE VALUES (1, 'V8', 1000)
         * INSERT INTO CAR VALUES (1, 'Buggati  Vevron', 1)
         * SELECT * FROM ENGINE
         * SELECT * FROM CAR
         *
         * SELECT * FROM CAR CAR
         * JOIN ENGINE
         * ON CAR.engineid = ENGINE.ID
         */
        Statement statement = connection.createStatement();
        statement.execute("SELECT * FROM CARS");
        statement.execute("SELECT * FROM ENGINE where id = 10");
        statement.execute("SELECT * FROM ENGINE where id = 11");
        statement.execute("SELECT * FROM ENGINE where id = 12");

        String selctSQl = "SELECT * FROM CARS where ID=?";

        PreparedStatement preparedStatement = connection.prepareStatement(selctSQl);

        preparedStatement.setInt(1, 2);
        preparedStatement.setString(2, "Buggatii Veyron");

    }

    public void SelectCarsByName(String name) throws SQLException {
        String selctSQl = "SELECT * FROM CARS where ID=?";
        PreparedStatement preparedStatement = connection.prepareStatement(selctSQl);
        preparedStatement.setString(2, "Buggatii Veyron");
        preparedStatement.execute();
    }
}
