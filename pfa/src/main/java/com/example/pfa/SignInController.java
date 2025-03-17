package com.example.pfa;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class SignInController implements Initializable {
    @FXML
    private TextField phoneNumberField;
    @FXML
    private ChoiceBox<String> CB;
    @FXML
    private TextField emailField;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Label warningsLabel;

    private final String[] Choix = {"artisan", "client"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CB.getItems().addAll(Choix);
    }

    public String handleChoiceSelection() {
        return CB.getValue();
    }

    public void handleReturn(ActionEvent event) {
        moveTo("Acceuil.fxml", event);
    }

    public void suivante(ActionEvent event) {
        if (!validateInputs()) {
            return;
        }
        if (registerUser()) {
            moveTo("verification.fxml", event);
        } else {
            warningsLabel.setText("Erreur lors de l'insertion en base de données.");
        }
    }

    public void moveTo(String link, ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(link)));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean validateInputs() {
        String usernameText = username.getText().trim();
        String emailText = emailField.getText().trim();
        String passwordText = password.getText();
        String selectedType = CB.getValue();
        String phoneText = phoneNumberField.getText().trim();

        if (usernameText.isEmpty() || usernameText.length() > 8) {
            warningsLabel.setText("Username must be between 1 and 8 characters.");
            return false;
        }

        String emailLower = emailText.toLowerCase();
        if (emailText.isEmpty() ||
                (!emailLower.endsWith("@gmail.com") && !emailLower.endsWith("@outlook.com") && !emailLower.endsWith("@hotmail.com"))) {
            warningsLabel.setText("Email must be a valid Gmail, Outlook or Hotmail address.");
            return false;
        }

        if (passwordText.length() <= 8 || !passwordText.matches(".*[!@#$%^&*(),.?\":{}|<>].*")) {
            warningsLabel.setText("Password must be more than 8 characters and contain at least one special character.");
            return false;
        }

        if (selectedType == null || selectedType.isEmpty()) {
            warningsLabel.setText("Please select a user type.");
            return false;
        }

        if (phoneText.isEmpty()) {
            warningsLabel.setText("Phone number cannot be empty.");
            return false;
        }
        try {
            // On vérifie que le numéro contient au moins 8 chiffres.
            long phoneNum = Long.parseLong(phoneText);
            if (phoneText.length() < 8 || phoneNum < 10000000L) {
                warningsLabel.setText("Phone number must contain at least 8 digits.");
                return false;
            }
        } catch (NumberFormatException e) {
            warningsLabel.setText("Phone number must be numeric.");
            return false;
        }

        warningsLabel.setText("");
        return true;
    }

    private boolean registerUser() {
        String usernameText = username.getText().trim();
        String emailText = emailField.getText().trim();
        String passwordText = password.getText();
        String selectedType = CB.getValue();
        String phoneText = phoneNumberField.getText().trim();

        String sql = "INSERT INTO users (username, email, password, type, phoneNumber) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = new DataBaseConnection().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, usernameText);
            ps.setString(2, emailText);
            ps.setString(3, passwordText);
            ps.setString(4, selectedType);
            ps.setInt(5, Integer.parseInt(phoneText));

            int rowCount = ps.executeUpdate();
            return rowCount > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
