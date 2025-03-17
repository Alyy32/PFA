package com.example.pfa;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.sql.Connection;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import java.sql.ResultSet;
import java.sql.Statement;
import java.io.IOException;
import java.util.Objects;

public class LoginController {
    public Button loginButton;
    /// appel
    @FXML
    private TextField email;
    @FXML
    private TextField password;
    @FXML
    private Label warningLabel;
 ///////////////////////////////////////////////////////////////////////////////////////////////////////
    /// retouner a la page d'acceuil
    @FXML
    private void returnButtonClicked(ActionEvent event) throws IOException {
        Parent root=FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Acceuil.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void loginButtonOnAction()  {
        if (email.getText().isEmpty() || password.getText().isEmpty()) {
            warningLabel.setText("Please Enter Username/Password");
        }
        else{
            validateLogin();
        }

    }
    public void validateLogin(){
        DataBaseConnection connectNow=new DataBaseConnection();
        Connection conn=connectNow.getConnection();
        String verifyLogin="SELECT COUNT(1) FROM users WHERE email='"+email.getText()+"' AND password='"+password.getText()+"'";
        try{
            Statement statement=conn.createStatement();
            ResultSet queryResult=statement.executeQuery(verifyLogin);
            while(queryResult.next()){
                if(queryResult.getInt(1)==1){
                    warningLabel.setText("Login Successful");
                }
                else{
                    warningLabel.setText("Login Failed");
                }
            }
            }
        catch(Exception e){
            e.printStackTrace();
        }
    }

}
