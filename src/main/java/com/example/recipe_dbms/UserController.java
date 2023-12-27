package com.example.recipe_dbms;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.sql.*;

public class UserController {

    @FXML
    private TextField nameInput;
    @FXML
    private TextField passInput;

    DataBaseConnection connectNow = new DataBaseConnection();
    Connection connectDB = connectNow.getConnection();

    @FXML
    private int signupUser(){
        int generatedId = -1;
        String username = nameInput.getText();
        String password = passInput.getText();

        String sql = """
                     INSERT INTO "user"(username, "password")
                     values (?,?)""";

        try(PreparedStatement st = connectDB.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            st.setString(1,username);
            st.setString(2, password);

            int affectedRows = st.executeUpdate();

            if (affectedRows == 0){
                throw new SQLException("Signing up user failed.");
            }
            try(ResultSet generatedKeys = st.getGeneratedKeys()){
                if (generatedKeys.next()){
                    generatedId = generatedKeys.getInt(1);
                    return generatedId;
                }
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return generatedId;
    }
}
