package gui;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import DataAccess.DatabaseHandler;
import animations.Shake;
import classes.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class SignInController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Pane paneInformation;

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Button btnSignIn;

    @FXML
    private Button btnSignUp;

    @FXML
    private Label lblErrors;

    @FXML
    void initialize() {
        btnSignIn.setOnAction(event -> {
            String emailText = txtEmail.getText().trim(); // trim() for deleting spaces
            String passwordText = txtPassword.getText().trim(); // trim() for deleting spaces

            if (!emailText.equals("") && !passwordText.equals("")) {
                loginUser(emailText, passwordText);
            } else {
                lblErrors.setTextFill(Color.TOMATO);
                lblErrors.setText("Login and/or password are empty");
            }
        });

        btnSignUp.setOnAction(event -> {
            openNewScene("/gui/SignUp.fxml");
        });

    }

    private void loginUser(String usernameText, String passwordText) {
        DatabaseHandler dbHandler = new DatabaseHandler();

        User user = new User();
        user.setUserName(usernameText);
        user.setPassword(passwordText);

//        dbHandler.getUser(user);

        ResultSet resultSet = dbHandler.getUser(user); //выделить память

        int counter = 0;

        while(true){
            try {
                if (!resultSet.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            counter++;
        }

        if(counter >= 1){
            openNewScene("/gui/SignUp.fxml");
        } else {
            Shake paneAnimation = new Shake(paneInformation);
            paneAnimation.playAnimation();
            lblErrors.setTextFill(Color.TOMATO);
            lblErrors.setText("Login and/or password are incorrect");
        }
    }


    private void openNewScene(String newWindow) {
        btnSignUp.getScene().getWindow().hide();

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource(newWindow));
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = fxmlLoader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }
}