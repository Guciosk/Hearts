package controllers;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import models.UserData;

public class AssessmentPageController {

    @FXML
    private MenuItem FAQ;

    @FXML
    private MenuItem aboutHearts;

    @FXML
    private TextField ageTextField;

    @FXML
    private GridPane assessmentPagePane;

    @FXML
    private TextField cptTextField;

    @FXML
    private Label createdByLabel;

    @FXML
    private HBox footerHBox;

    @FXML
    private ComboBox<String> genderComboBox;

    @FXML
    private TextField rbpTextField;

    @FXML
    private TextField ecgTextField;

    @FXML
    private TextField cholTextField;

    @FXML
    private MenuButton helpButton;

    @FXML
    private Button homeButton;

    @FXML
    private MenuItem howToUse;

    @FXML
    private CheckBox fbsCheckBox;

    @FXML
    private TextField thalachTextField;

    @FXML
    private HBox menuBar;

    @FXML
    private Button resetButton;

    @FXML
    private ComboBox<String> oldpeakComboBox;


    @FXML
    private Button submitButton;

    @FXML
    private Label versionLabel;

    @FXML
    private CheckBox exangCheckBox;

    @FXML
    void initialize() {

        // allows for age to be 0-120
        ageTextField.setTextFormatter(new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.isEmpty()) {
                return change;
            }
            if (newText.matches("\\d{0,3}")) {
                try {
                    int ageValue = Integer.parseInt(newText);
                    if (ageValue <= 120) {
                        return change;
                    }
                } catch (NumberFormatException e) {
                    return null;
                }
            }
            return null;
        }));

        // allows for bmi to be 0.0-60.0
        cptTextField.setTextFormatter(new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.isEmpty()) {
                return change;
            }
            if (newText.matches("\\d{0,2}(\\.\\d?)?")) {
                try {
                    double cptValue = Double.parseDouble(newText);
                    if (cptValue <= 60) {
                        return change;
                    }
                } catch (NumberFormatException e) {
                    return null;
                }
            }
            return null;
        }));

        // allows for rbp to be 0.00-300.00
        rbpTextField.setTextFormatter(new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.isEmpty()) {
                return change;
            }
            if (newText.matches("\\d{0,3}(\\.\\d{0,2})?")) {
                try {
                    double averagerbpValue = Double.parseDouble(newText);
                    if (averagerbpValue <= 300) {
                        return change;
                    }
                } catch (NumberFormatException e) {
                    return null;
                }
            }
            return null;
        }));
        // combobox initialization
        genderComboBox.getItems().removeAll(genderComboBox.getItems());
        genderComboBox.getItems().addAll("Male", "Female", "Other");

        oldpeakComboBox.getItems().removeAll(oldpeakComboBox.getItems());
        oldpeakComboBox.getItems().addAll("Yes", "No");
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
                        "Q: Can Hearts diagnose Heart Disease?\n" +
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
    @FXML
    void onResetButtonClick(ActionEvent event) {
        // Textfields
        ageTextField.clear();
        cptTextField.clear();
        rbpTextField.clear();
        ecgTextField.clear();
        thalachTextField.clear();
        // Comboboxes
        genderComboBox.setValue(null);
        oldpeakComboBox.setValue(null);
        // Checkboxes
        fbsCheckBox.setSelected(false);
        exangCheckBox.setSelected(false);
    }
    @FXML
    void onSubmitButtonClick(ActionEvent event) {
        try {
            String age = ageTextField.getText();
            String gender = genderComboBox.getValue();
            String cpt = cptTextField.getText();
            String rbp = rbpTextField.getText();
            String chol = cholTextField.getText();
            boolean fbs = fbsCheckBox.isSelected();
            String ecg = ecgTextField.getText();
            String thalach = thalachTextField.getText();
            boolean exang = exangCheckBox.isSelected();
            String oldpeak = oldpeakComboBox.getValue();

            if (age.isEmpty() || gender == null || cpt.isEmpty() || rbp.isEmpty() || chol.isEmpty() ||
                    thalach == null  || oldpeak == null) { // see if any  field is empty
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Input Validation Error");
                alert.setHeaderText("Incomplete Fields");
                alert.setContentText("Please fill out all fields before submitting.");
                alert.showAndWait();
                return;

            }

            int ageValue = Integer.parseInt(age);
            double cptValue = Double.parseDouble(cpt);
            double rbpValue = Double.parseDouble(rbp);
            int cholValue = Integer.parseInt(chol);
            int thalachValue = Integer.parseInt(thalach);
            int ecgValue = Integer.parseInt(ecg);
            int oldpeakValue = Integer.parseInt(oldpeak);

            // BEGIN DATA PROCESSING

            UserData userData = new UserData(ageValue, gender, cptValue, rbpValue, cholValue, fbs,
                    ecgValue, thalachValue, exang, oldpeakValue);

            // PROCESS DATA HERE
            // *******************************************************************************************************
            boolean isAtElevatedRisk = false; // PUT METHOD TO ASSESS ELEVATED RISK HERE

            if (isAtElevatedRisk) {
                swapScene((Stage) submitButton.getScene().getWindow(), "/views/elevatedRisk.css",
                        "/views/ElevatedRisk.fxml", userData);
            } else {
                swapScene((Stage) submitButton.getScene().getWindow(), "/views/normalRisk.css",
                        "/views/NormalRisk.fxml");
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid numeric input. Please check your entries.");
        }
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

    void swapScene(Stage currentStage, String cssPage, String fxmlPage, UserData userData) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPage));
            Scene newScene = new Scene(loader.load());

            // pass user data
            Object controller = loader.getController();
            if (controller instanceof ElevatedRiskController) {
                ((ElevatedRiskController) controller).setUserData(userData);
            }

            double currentX = currentStage.getX();
            double currentY = currentStage.getY();
            double currentWidth = currentStage.getWidth();
            double currentHeight = currentStage.getHeight();
            currentStage.setScene(newScene);
            currentStage.setX(currentX);
            currentStage.setY(currentY);
            currentStage.setWidth(currentWidth);
            currentStage.setHeight(currentHeight);
            String css = getClass().getResource(cssPage).toExternalForm();
            newScene.getStylesheets().add(css);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}