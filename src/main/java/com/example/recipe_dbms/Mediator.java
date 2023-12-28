package com.example.recipe_dbms;

public class Mediator {
    private static Mediator instance;

    private HelloController helloController;
    private RecipeController recipeController;
    private UpdateController updateController;

    private Mediator() {
        // private constructor to enforce singleton pattern
    }

    public static Mediator getInstance() {
        if (instance == null) {
            instance = new Mediator();
        }
        return instance;
    }

    public void setHelloController(HelloController helloController) {
        this.helloController = helloController;
    }

    public void setRecipeController(RecipeController recipeController) {
        this.recipeController = recipeController;
    }

    public void setUpdateController(UpdateController updateController) {
        this.updateController = updateController;
    }

    public void refreshHello() {
        if (helloController != null) {
            helloController.refreshTable();
        }
    }
}

