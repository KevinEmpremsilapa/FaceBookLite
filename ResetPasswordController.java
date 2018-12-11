import javafx.event.ActionEvent;

import java.awt.*;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class ResetPasswordController {

    @FXML
    private Button backButton;
    @FXML
    private Button resetPasswordButton;

    @FXML
    private TextField emailTextField;

    @FXML
    private PasswordField newPasswordField;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private Label passwordErrorLabel;
    @FXML
    private Label usernameErrorLabel;


    @FXML
    private void backButtonPressed(ActionEvent event) throws IOException{
        Stage stage;
        Parent root;
        stage=(Stage) ((Button)(event.getSource())).getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("FaceBookLiteView.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        System.out.println("Back Button Pressed\n");
    }
    @FXML
    private void resetPasswordButtonPressed(ActionEvent event) throws IOException{
        PersonDAO personDAO = new PersonDAO();

        usernameErrorLabel.setVisible(false);
        passwordErrorLabel.setVisible(false);
        if(newPasswordField.getText().equals(confirmPasswordField.getText()))
        {
            if(DBUtil.searchField("Users","username", emailTextField.getText())) {
                //DBUtil.updatePassword(emailTextField.getText(), AES.encrypt(newPasswordField.getText(),emailTextField.getText()));
                personDAO.updatePassword(emailTextField.getText(), AES.encrypt(newPasswordField.getText(), emailTextField.getText()));

                Stage stage;

                Parent root;
                stage = (Stage) ((Button) (event.getSource())).getScene().getWindow();
                root = FXMLLoader.load(getClass().getResource("FaceBookLiteView.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                System.out.println("Reset Button Pressed\n");
            }
            else {
                System.out.println("No username found");
                usernameErrorLabel.setVisible(true);
            }
        }

        else {
            System.out.println("Passwords don't match");
            passwordErrorLabel.setVisible(true);
        }
    }

    public void initialize()
    {
        usernameErrorLabel.setVisible(false);
        passwordErrorLabel.setVisible(false);
    }

}
