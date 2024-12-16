package controllers;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;


public class LandingPageController {

    @FXML
    private MenuItem FAQ;

    @FXML
    private MenuItem aboutHearts;

    @FXML
    private Button btnAssessment;

    @FXML
    private Button btnLearnMore;

    @FXML
    private Label callToActionLabel;

    @FXML
    private Label createdByLabel;

    @FXML
    private HBox footerHBox;


    @FXML
    private MenuButton helpButton;

    @FXML
    private Button homeButton;

    @FXML
    private MenuItem howToUse;

    @FXML
    private GridPane landingPagePane;

    @FXML
    private ImageView logoImage;

    @FXML
    private Label logoLabel;

    @FXML
    private HBox menuBar;

    @FXML
    private Label taglineLabel;

    @FXML
    private Label versionLabel;

    @FXML
    void initialize() {
        Platform.runLater(() -> {
            landingPagePane.requestFocus();
        });
    }
    @FXML
    void onAboutHeartsClick(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About Hearts");
        alert.setHeaderText("About Hearts");
        alert.setContentText(
                "Hearts helps assess your Heart Disease risk using a machine learning model based on your input data.");
        alert.showAndWait();
    }

    @FXML
    void onBeginAssessmentButtonClick(ActionEvent event) {
        swapScene((Stage) btnAssessment.getScene().getWindow(), "/views/assessmentPage.css", "/views/AssessmentPage.fxml");
    }

    @FXML
    void onFaqClick(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("FAQs");
        alert.setHeaderText("Frequently Asked Questions");
        alert.setContentText(
                "Q: What is CPT?\n" +
                        "A: CTP stands for Chest Pain Type (1-4). It is used to help calculate the risk.\n\n"
                        +
                        "Q: What does RBP mean?\n" +
                        "A: It refers to your resting blood pressure (on admission to the hospital).\n\n" +
                        "Q: Who should use Hearts?\n" +
                        "A: Hearts is designed for anyone who wants to assess their risk of Heart Disease. \n\n"
                        +
                        "Q: How accurate is Hearts prediction?\n" +
                        "A: Hearts provides an estimated risk using a machine learning model\n\n"
                        +
                        "Q: Can HeartDiseaseAware diagnose Heart Disease?\n" +
                        "A: No, the app cannot diagnose Heart Disease, just help make an assumption. \n\n"
                        +
                        "Q: What does OldPeak mean?\n" +
                        "A: OldPeak refers to depression induced by exercise.\n\n");
        alert.showAndWait();
    }

    @FXML
    void onHomeButtonClick(ActionEvent event) {
        swapScene((Stage) homeButton.getScene().getWindow(), "/views/landingPage.css", "/views/landingPage.fxml");
    }

    @FXML
    void onHowToUseClick(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("How to Use Hearts");
        alert.setHeaderText("How to Use Hearts");
        alert.setContentText(
                "1. Fill out the required fields.\n2. Click Submit to calculate your risk.\n3. Review the results.\n");
        alert.showAndWait();
    }
    void swapScene(Stage currentStage, String cssPage, String fxmlPage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPage));
            Scene newScene = new Scene(loader.load());
            double currentX = currentStage.getX();
            double currentY = currentStage.getY();
            double currentWidth = currentStage.getWidth();
            double currentHeight = currentStage.getHeight();
            currentStage.setScene(newScene);
            currentStage.setX(currentX);
            currentStage.setY(currentY);
            currentStage.setWidth(currentWidth);
            currentStage.setHeight(currentHeight);
            currentStage.setScene(newScene);
            String css = getClass().getResource(cssPage).toExternalForm();
            newScene.getStylesheets().add((css));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}