import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

public class SettingsViewController {

    boolean hideAge, hideFirstName, hideLastName, hidePhone, hideEmail, hideStatus;

    @FXML
    private CheckBox statusCheckBox;
    @FXML
    private CheckBox firstnameCheckBox;
    @FXML
    private CheckBox lastnameCheckBox;
    @FXML
    private CheckBox ageCheckBox;
    @FXML
    private CheckBox phoneCheckBox;
    @FXML
    private CheckBox emailCheckBox;

    @FXML
    private Button saveButton;
    @FXML
    private Button cancelButton;


    @FXML
    private TextField firstnameTextField;
    @FXML
    private TextField lastnameTextField;
    @FXML
    private TextField ageTextField;
    @FXML
    private TextField phoneTextField;
    @FXML
    private TextField emailTextField;


    @FXML
    void ageCheckboxPressed() {
        if(ageCheckBox.isSelected()){
            System.out.println("Age Checked");
            hideAge = true;
        }
        else{
            System.out.println("Age UnChecked");
            hideAge = false;
        }
    }
    @FXML
    void statusCheckboxPressed() {
        if(statusCheckBox.isSelected()){
            System.out.println("Status Checked");
            hideStatus = true;
        }
        else{
            System.out.println("Status UnChecked");
            hideStatus = false;
        }
    }
    @FXML
    void emailCheckboxPressed() {
        if(emailCheckBox.isSelected()){
            System.out.println("Email Checked");
            hideEmail = true;
        }
        else{
            System.out.println("Email UnChecked");
            hideEmail = false;
        }
    }
    @FXML
    void lastnameCheckboxPressed() {
        if(lastnameCheckBox.isSelected()){
            System.out.println("Last Name Checked");
            hideLastName = true;
        }
        else{
            System.out.println("Last Name UnChecked");
            hideLastName = false;
        }
    }
    @FXML
    void firstnameCheckboxPressed() {
        if(firstnameCheckBox.isSelected()){
            System.out.println("First Name Checked");
            hideFirstName = true;
        }
        else{
            System.out.println("First Name UnChecked");
            hideFirstName = false;
        }
    }
    @FXML
    void phoneCheckboxPressed() {
        if(phoneCheckBox.isSelected()){
            System.out.println("Phone Checked");
            hidePhone = true;
        }
        else{
            System.out.println("Phone UnChecked");
            hidePhone = false;
        }
    }

    @FXML
    void saveButtonPressed(ActionEvent event) throws IOException {

        //TODO: Save values to database

        Stage stage;
        Parent root;
        stage=(Stage) ((Button)(event.getSource())).getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("DashboardView.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        System.out.println("Save Button Pressed\n");
    }
    @FXML
    void cancelButtonPressed(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        stage=(Stage) ((Button)(event.getSource())).getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("DashboardView.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        System.out.println("Cancel Button Pressed\n");
    }

}
