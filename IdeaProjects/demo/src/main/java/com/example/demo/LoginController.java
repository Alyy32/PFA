package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class LoginController {

    public Button returnButtonn;
    @FXML
    private Button loginButton;

    @FXML
    private Button signInButton;

    @FXML
    private void handleLogin(ActionEvent event) {
        loadScene(event, "/com/example/demo/login.fxml");
    }

    @FXML
    private void handleSignIn(ActionEvent event) {
        loadScene(event, "/com/example/demo/sign.fxml");
    }
    @FXML
    private void handleReturn(ActionEvent event) {
        loadScene(event, "/com/example/demo/Acceuil.fxml");
    }


    private void loadScene(ActionEvent event, String fxmlPath) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlPath)));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);

            // Preserve window size and position
            stage.setWidth(stage.getWidth());
            stage.setHeight(stage.getHeight());

            stage.setScene(scene);
        } catch (IOException e) {
            showErrorAlert(
                    "Failed to load: " + fxmlPath,
                    e.getMessage());
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
