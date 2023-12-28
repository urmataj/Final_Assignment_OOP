package com.example.recipe_dbms;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataBaseConnection {

    public Connection databaseLink;

    public Connection getConnection(){

        String databaseName = "postgres";
        String databaseUser = "postgres";
        String databasePassword = "123456";
        String url  = "jdbc:postgresql://localhost:5432/" + databaseName;

        try{
            databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return databaseLink;
    }
}
