module com.example.recipe_dbms {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.recipe_dbms to javafx.fxml;
    exports com.example.recipe_dbms;
}