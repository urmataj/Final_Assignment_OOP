package com.example.recipe_dbms;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.*;

public class UserController {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TextField nameInput;
    @FXML
    private TextField passInput;

    DataBaseConnection connectNow = new DataBaseConnection();
    Connection connectDB = connectNow.getConnection();

    @FXML
    private void signupUser(){
        String username = nameInput.getText();
        String password = passInput.getText();

        String sql = """
                     INSERT INTO "user"(username, "password")
                     values (?,?)""";

        try(PreparedStatement st = connectDB.prepareStatement(sql)){
            st.setString(1,username);
            st.setString(2, password);

            int affectedRows = st.executeUpdate();

            if (affectedRows == 0){
                throw new SQLException("Signing up user failed.");
            } else {
                System.out.println("User successfully signed up.");
            }

            Stage stage = (Stage) anchorPane.getScene().getWindow();
            stage.close();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
