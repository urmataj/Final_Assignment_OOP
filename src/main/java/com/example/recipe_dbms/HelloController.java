package com.example.recipe_dbms;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private TableView<Recipe> recipeTableView;

    @FXML
    private TableColumn<Recipe, String> titleColumnName;
    @FXML
    private TableColumn<Recipe, String> typeColumnName;
    @FXML
    private TableColumn<Recipe, Double> timeColumnName;
    @FXML
    private TableColumn<Recipe, String> ingColumnName;
    @FXML
    private TableColumn<Recipe, String> descColumnName;
    @FXML
    private TableColumn<Recipe, String> isVegColumnName;
    @FXML
    private TableColumn<Recipe, String> regionColumnName;
    @FXML
    private TableColumn<Recipe, String> userColumnName;
    @FXML
    private TableColumn<Recipe, Void> buttonColumn;
    @FXML
    private TextField searchInput;


    ObservableList<Recipe> recipeObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resource) {

        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connectDB = connectNow.getConnection();

        String recipeQuery = """
                SELECT r.title,
                    r.description,
                    r."time",
                    STRING_AGG(i.ing_name, ', ') AS ing_names,
                    r.recipe_type,
                    r.is_veg,
                    r.region,
                    r.username
                FROM
                    recipe r
                LEFT JOIN
                    recipe_ing ri ON r.recipe_id = ri.recipe_id
                LEFT JOIN
                    ingredient i ON ri.ingredient_id = i.ingredient_id
                GROUP BY
                    r.title, r.description, r."time", r.recipe_type, r.is_veg, r.region, r.username
                ORDER BY r.title asc""";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(recipeQuery);

            while (queryOutput.next()) {

                String queryTitle = queryOutput.getString("title");
                String queryDesc = queryOutput.getString("description");
                Double queryTime = queryOutput.getDouble("time");
                String queryIngNames = queryOutput.getString("ing_names");
                String queryType = queryOutput.getString("recipe_type");
                String queryIsVeg = queryOutput.getString("is_veg");
                String queryRegion = queryOutput.getString("region");
                String queryUsername = queryOutput.getString("username");

                // Populate the ObservableList
                recipeObservableList.add(new Recipe(queryTitle, queryDesc, queryTime, queryIngNames, queryType, queryIsVeg, queryRegion, queryUsername));
            }

            titleColumnName.setCellValueFactory(new PropertyValueFactory<>("title"));
            descColumnName.setCellValueFactory(new PropertyValueFactory<>("description"));
            timeColumnName.setCellValueFactory(new PropertyValueFactory<>("time"));
            ingColumnName.setCellValueFactory(new PropertyValueFactory<>("ingNames"));
            typeColumnName.setCellValueFactory(new PropertyValueFactory<>("dishType"));
            isVegColumnName.setCellValueFactory(new PropertyValueFactory<>("isVeg"));
            regionColumnName.setCellValueFactory(new PropertyValueFactory<>("region"));
            userColumnName.setCellValueFactory(new PropertyValueFactory<>("creatorName"));

            buttonColumn.setCellFactory(new Callback<>() {
                @Override
                public TableCell<Recipe, Void> call(final TableColumn<Recipe, Void> param) {
                    return new TableCell<>() {
                        private final Button detailsButton = new Button("Details");

                        {
                            detailsButton.setOnAction((ActionEvent event) -> {
                                Recipe rowData = getTableView().getItems().get(getIndex());
                                openRecipeWindow(rowData);
                            });
                        }

                        @Override
                        protected void updateItem(Void item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty) {
                                setGraphic(null);
                            } else {
                                setGraphic(detailsButton);
                            }
                        }
                    };
                }
            });

            recipeTableView.setItems(recipeObservableList);


            // Initial filtered list
            FilteredList<Recipe> filteredData = new FilteredList<>(recipeObservableList, b -> true);

            searchInput.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(Recipe -> {

                    if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                        return true;
                    }

                    String searchKeyword = newValue.toLowerCase();

                    if (Recipe.getTitle().toLowerCase().contains(searchKeyword)) {
                        return true;
                    }
                    else if (Recipe.getIngNames().toLowerCase().contains(searchKeyword)) {
                        return true;
                    }
                    else if (Recipe.getDishType().toLowerCase().contains(searchKeyword)){
                        return true;
                    }
                    else if (Recipe.getIsVeg().toLowerCase().contains(searchKeyword)){
                        return true;
                    }
                    else if (Recipe.getRegion().toLowerCase().contains(searchKeyword)){
                        return true;
                    }
                    else if (Recipe.getCreatorName().toLowerCase().contains(searchKeyword)){
                        return true;
                    } else {
                        return false; // no match found
                    }
                });
            });

            SortedList<Recipe> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(recipeTableView.comparatorProperty());

            // apply filtered and sorted data to recipe Table View
            recipeTableView.setItems(sortedData);

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        Mediator.getInstance().setHelloController(this);
    }

    public void refreshTable() {
        recipeObservableList.clear();

        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connectDB = connectNow.getConnection();

        String recipeQuery = """
                SELECT r.title,
                    r.description,
                    r."time",
                    STRING_AGG(i.ing_name, ', ') AS ing_names,
                    r.recipe_type,
                    r.is_veg,
                    r.region,
                    r.username
                FROM
                    recipe r
                LEFT JOIN
                    recipe_ing ri ON r.recipe_id = ri.recipe_id
                LEFT JOIN
                    ingredient i ON ri.ingredient_id = i.ingredient_id
                GROUP BY
                    r.title, r.description, r."time", r.recipe_type, r.is_veg, r.region, r.username
                ORDER BY r.title asc""";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(recipeQuery);

            while (queryOutput.next()) {

                String queryTitle = queryOutput.getString("title");
                String queryDesc = queryOutput.getString("description");
                Double queryTime = queryOutput.getDouble("time");
                String queryIngNames = queryOutput.getString("ing_names");
                String queryType = queryOutput.getString("recipe_type");
                String queryIsVeg = queryOutput.getString("is_veg");
                String queryRegion = queryOutput.getString("region");
                String queryUsername = queryOutput.getString("username");

                // Populate the ObservableList
                recipeObservableList.add(new Recipe(queryTitle, queryDesc, queryTime, queryIngNames, queryType, queryIsVeg, queryRegion, queryUsername));
            }

            titleColumnName.setCellValueFactory(new PropertyValueFactory<>("title"));
            descColumnName.setCellValueFactory(new PropertyValueFactory<>("description"));
            timeColumnName.setCellValueFactory(new PropertyValueFactory<>("time"));
            ingColumnName.setCellValueFactory(new PropertyValueFactory<>("ingNames"));
            typeColumnName.setCellValueFactory(new PropertyValueFactory<>("dishType"));
            isVegColumnName.setCellValueFactory(new PropertyValueFactory<>("isVeg"));
            regionColumnName.setCellValueFactory(new PropertyValueFactory<>("region"));
            userColumnName.setCellValueFactory(new PropertyValueFactory<>("creatorName"));

            recipeTableView.setItems(recipeObservableList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goToCreateRecipe() {
        try {
            // Load the AddRecipe.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddRecipe.fxml"));
            Parent root = loader.load();

            // Create a new stage and set the scene
            Stage stage = new Stage();
            stage.setScene(new Scene(root));

            // Show the new stage
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goToLogin(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("User.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));

            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void openRecipeWindow(Recipe rowData) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Recipe.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));

            // Pass data to the RecipeController
            RecipeController recipeController = loader.getController();
            recipeController.setData(rowData);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}