import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    private TextField userName;
    @FXML
    private TextField ageInput;

    @FXML
    private Label usernameErrorLabel;
    @FXML
    private Button submitButton;
    // ADDED
    @FXML
    private Button backRegisterLoginButton;

    @FXML
    private PasswordField passwordInput;



    @FXML
    void submitButtonPressed(ActionEvent event) throws IOException {

        //Error Checking
        if(!DBUtil.searchField("Users","username", userName.getText())) {
            Person person = new PersonDAO();
            person.setFirstName(firstName.getText());
            person.setLastName(lastName.getText());
            person.setAge(Integer.parseInt(ageInput.getText()));
            person.setUsername(userName.getText());
            AES.encrypt(passwordInput.getText(), userName.getText());
            person.setPassword(new Password(AES.encrypt(passwordInput.getText(), userName.getText()), userName.getText()));
            person.setEmail("@");

            //DBUtil.registerUser(person);
            ((PersonDAO) person).registerPerson(person);
            FacebookLite.currentUser = person;

            System.out.println("First Name: " + person.getFirstName());
            System.out.println("Last Name: " + person.getLastName());
            System.out.println("Password: " + person.getPassword());
            System.out.println("Age: " + person.getAge());

            Stage stage;
            Parent root;
            stage = (Stage) ((Button) (event.getSource())).getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("DashboardView.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else {
            System.out.println("Username already exists");
            usernameErrorLabel.setVisible(true);
        }

        System.out.println("Submit Button Pressed\n");
    }

    @FXML
    void backRegisterLoginButtonPressed(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        stage=(Stage) ((Button)(event.getSource())).getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("FacebookLiteView.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Back Button Pressed\n");
    }

    public void initialize()
    {
        usernameErrorLabel.setVisible(false);
    }
}
