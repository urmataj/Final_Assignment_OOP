package com.example.recipe_dbms;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateController {

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

    public void setData(Recipe recipeData){
        titleInput.setText(recipeData.getTitle());
        descriptionInput.setText(recipeData.getDescription());
        timeInput.setText(String.valueOf(recipeData.getTime()));
        vegInput.setText(recipeData.getIsVeg());
        typeInput.setText(recipeData.getDishType());
        regionInput.setText(recipeData.getRegion());
    }

    DataBaseConnection connectNow = new DataBaseConnection();
    Connection connectDB = connectNow.getConnection();

    @FXML
    private void updateRecipe() {
        try {
            String title = titleInput.getText();
            String description = descriptionInput.getText();
            Double cookingTime = Double.valueOf(timeInput.getText());
            String isVeg = vegInput.getText();
            String dishType = typeInput.getText();
            String region = regionInput.getText();

            String query = """
                           UPDATE recipe SET title=?, description=?,"time"=?, is_veg=?, recipe_type=?, region=? WHERE title=?""";
            PreparedStatement preparedStatement = connectDB.prepareStatement(query);
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, description);
            preparedStatement.setDouble(3, cookingTime);
            preparedStatement.setString(4, isVeg);
            preparedStatement.setString(5, dishType);
            preparedStatement.setString(6, region);
            preparedStatement.setString(7, title);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Recipe updated successfully!");
            } else {
                System.out.println("Failed to update recipe");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
