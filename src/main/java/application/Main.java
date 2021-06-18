package application;

import db.CreateDB;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        CreateDB.main();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/OrderLibrary.fxml")));
        primaryStage.setTitle("Order 2.0");
        primaryStage.setScene(new Scene(root, 300, 400));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
