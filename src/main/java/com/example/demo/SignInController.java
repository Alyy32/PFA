package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SignInController implements Initializable {
    public TextField emailField;
    @FXML
    private ChoiceBox<String> CB;

    private static final Logger LOGGER = Logger.getLogger(SignInController.class.getName());
    private static final String[] CHOICES = {"Artisan", "Client"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CB.getItems().addAll(CHOICES);
    }
    @FXML
    private Button pour;

    @FXML
    private void handleChoiceSelection(ActionEvent event) {
        String selected = CB.getValue();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("ChoiceBox Selection");
        alert.setHeaderText(null);

        if (selected == null) {
            alert.setContentText("No selection made. Please choose an option.");
        } else {
            alert.setContentText("Selected: " + selected);
        }
        alert.showAndWait();
    }

    @FXML
    private void handleReturn(ActionEvent event) {
        loadScene(event, "/com/example/demo/Acceuil.fxml");
    }


    @FXML
    private void poursuivre(ActionEvent event) {
        loadScene(event, "/com/example/demo/verification.fxml");
    }

    private void loadScene(ActionEvent event, String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(fxmlPath)));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);

            // Preserve window size
            double width = stage.getWidth();
            double height = stage.getHeight();

            stage.setScene(scene);
            stage.setWidth(width);
            stage.setHeight(height);

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to load scene: " + fxmlPath, e);
            showErrorAlert("Failed to load: " + fxmlPath, e.getMessage());
        }
    }

    private void showErrorAlert(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Scene Loading Error");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
