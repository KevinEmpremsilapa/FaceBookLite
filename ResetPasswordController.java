import javafx.event.ActionEvent;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
        //TODO: Save the new password to database

        Stage stage;
        Parent root;
        stage=(Stage) ((Button)(event.getSource())).getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("FaceBookLiteView.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Reset Button Pressed\n");

    }



}
