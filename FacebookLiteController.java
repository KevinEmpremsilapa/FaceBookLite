import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class FacebookLiteController {

    String username;
    String password;

    @FXML
    private TextField usernameTextField;

    @FXML
    private Label loginErrorLabel;

    @FXML
    private Button resetPasswordButton;
    @FXML
    private Button registerButton;
    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passwordField;


    @FXML
    void registerButtonPressed(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        stage=(Stage) ((Button)(event.getSource())).getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("RegisterView.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        System.out.print("Register Button Pressed\n");
    }

    @FXML
    void loginButtonPressed(ActionEvent event) throws IOException {

        //TODO: Set Visible to true if user enters wrong username/password
        //loginErrorlabel.setVisible(true);
        /*
        if(!userNameField.getText().equals("")){
            username = userNameField.getText();
        }
        if(!passwordField.getText().equals("")){
            password = passwordField.getText();
        }

        System.out.println(username);
        System.out.println(password);
        */
        Stage stage;
        Parent root;
        stage=(Stage) ((Button)(event.getSource())).getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("DashboardView.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        System.out.print("Login Button Pressed\n");
    }

    @FXML
    void resetPasswordButtonPressed(ActionEvent event) throws IOException{
        Stage stage;
        Parent root;
        stage=(Stage) ((Button)(event.getSource())).getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("ResetPasswordView.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.print("Reset Password Button Pressed\n");

    }
}
