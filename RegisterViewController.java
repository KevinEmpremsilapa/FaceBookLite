import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegisterViewController {

    String firstNameInput;
    String lastNameInput;
    String userNameInput;
    String passwordUserInput;
    int ageUserInput;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private Button submitButton;

    @FXML
    private TextField userName;

    @FXML
    private PasswordField passwordInput;

    @FXML
    private TextField ageInput;

    // ADDED
    @FXML
    private Button backRegisterLoginButton;

    @FXML
    void submitButtonPressed(ActionEvent event) throws IOException {
        firstNameInput = firstName.getText();
        lastNameInput = lastName.getText();
        passwordUserInput = passwordInput.getText();
        ageUserInput = Integer.valueOf(ageInput.getText());

        System.out.println("First Name: " + firstNameInput);
        System.out.println("Last Name: " + lastNameInput);
        System.out.println("Password: " + passwordUserInput);
        System.out.println("Age: " + ageUserInput);

        Stage stage;
        Parent root;
        stage=(Stage) ((Button)(event.getSource())).getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("DashboardView.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
