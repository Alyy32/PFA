package com.example.pfa;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class SignInController implements Initializable {
    @FXML
    private Button poursuivre;
    @FXML
    private TextField emailField;
    @FXML
    private ChoiceBox<String> CB;
    @FXML
    private Button pour;

    private static final Logger LOGGER = Logger.getLogger(SignInController.class.getName());
    private static final String[] CHOICES = {"Artisan", "Client"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CB.getItems().addAll(CHOICES);
        CB.setValue(CHOICES[0]); // Set default value
    }

    /**
     * Handles role selection
     */
    @FXML
    private void handleChoiceSelection(ActionEvent event) {
        String selected = CB.getValue();
        LOGGER.info("User selected: " + selected);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("ChoiceBox Selection");
        alert.setHeaderText(null);
        alert.setContentText(selected == null ? "No selection made. Please choose an option." : "Selected: " + selected);
        alert.showAndWait();
    }
    private Stage stage;
    private Scene scene;
    private Parent root;
    public void handleReturn(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Acceuil.fxml")));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void suivante(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("verification.fxml")));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}