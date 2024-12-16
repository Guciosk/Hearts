package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) {

        Parent root;
        try {
            primaryStage.setResizable(true);
            primaryStage.setMinWidth(1024);
            primaryStage.setMinHeight(768);
            primaryStage.setTitle("Heart Disease Risk Predictor");
            root = FXMLLoader.load(getClass().getResource("/views/LandingPage.fxml"));
            Scene scene = new Scene(root,1024,768);
            String landingPageCSS = this.getClass().getResource("/views/landingpage.css").toExternalForm();
            scene.getStylesheets().add(landingPageCSS);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}