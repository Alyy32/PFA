package com.example.pfa;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class LoginController {
    @FXML
    private Button loginButton;
    @FXML
    private Button signInButton;
    @FXML
    private CheckBox artisan;
    @FXML
    private CheckBox client;

    @FXML
    private void handleLogin(ActionEvent event) {
        loadScene(event, "/com/example/pfa/login.fxml");
    }

    @FXML
    private void handleSignIn(ActionEvent event) {
        loadScene(event, "/com/example/pfa/sign.fxml");
    }

    @FXML
    private void handleReturn(ActionEvent event) {
        loadScene(event, "/com/example/pfa/Acceuil.fxml"); // FIXED PATH
    }

    private void loadScene(ActionEvent event, String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            double width = stage.getWidth();
            double height = stage.getHeight();

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setWidth(width);
            stage.setHeight(height);
        } catch (IOException e) {
            showErrorAlert("Failed to load: " + fxmlPath, e.getMessage());
            e.printStackTrace(); // Log error for debugging
        }
    }

    private void showErrorAlert(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
