package com.example.recipe_dbms;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecipeController {

    @FXML
    private TextField titleInput;
    @FXML
    private TextField descriptionInput;
    @FXML
    private TextField timeInput;
    @FXML
    private TextField vegInput;
    @FXML
    private TextField typeInput;
    @FXML
    private TextField regionInput;
    @FXML
    private TextField creatorInput;


    @FXML
    private Label title;
    @FXML
    private Label description;
    @FXML
    private Label creatorName;
    @FXML
    private Label cookingTime;
    @FXML
    private Label dishType;
    @FXML
    private Label isVeg;
    @FXML
    private Label region;


    DataBaseConnection connectNow = new DataBaseConnection();
    Connection connectDB = connectNow.getConnection();

    @FXML
    private void addRecipe() {
        String title = titleInput.getText();
        String description = descriptionInput.getText();
        Double time = Double.valueOf(timeInput.getText());
        String isVeg = vegInput.getText();
        String type = typeInput.getText();
        String region = regionInput.getText();
        String creatorName = creatorInput.getText();


        String sql = "INSERT INTO recipe(" + " title, description, time, is_veg, recipe_type, region, username)" +
                "values (?,?,?,?,?,?,?);";

        try(PreparedStatement st = connectDB.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            st.setString(1, title);
            st.setString(2, description);
            st.setDouble(3,time);
            st.setString(4, isVeg);
            st.setString(5, type);
            st.setString(6, region);
            st.setString(7, creatorName);

            int affectedRows = st.executeUpdate();

            if(affectedRows == 0) {
                throw new SQLException("Creating recipe failed.");
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void setData(Recipe rowData) {
        // Populate the labels with data from the selected row
        title.setText(rowData.getTitle());
        description.setText(rowData.getDescription());
        creatorName.setText(rowData.getCreatorName());
        cookingTime.setText(String.valueOf(rowData.getTime()));
        dishType.setText(rowData.getDishType());
        isVeg.setText(rowData.getIsVeg());
        region.setText(rowData.getRegion());
    }

    @FXML
    private void deleteRecipe() {
        String recipeTitle = title.getText();
        try {
            String query = "DELETE FROM recipe WHERE title=?";
            PreparedStatement preparedStatement = connectDB.prepareStatement(query);
            preparedStatement.setString(1, recipeTitle);

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Recipe deleted successfully!");
            } else {
                System.out.println("Failed to delete recipe");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void updateRecipe(){
        Recipe recipeData = new Recipe(title.getText(), description.getText(), Double.valueOf(cookingTime.getText()),dishType.getText(), isVeg.getText(),region.getText());

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateRecipe.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));

            UpdateController updateController = loader.getController();
            updateController.setData(recipeData);

            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
