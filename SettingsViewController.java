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

    boolean hideAge, hideFirstName, hideLastName, hideFriends, hidePosts, hideStatus;

    @FXML
    private CheckBox statusCheckBox;
    @FXML
    private CheckBox firstnameCheckBox;
    @FXML
    private CheckBox lastnameCheckBox;
    @FXML
    private CheckBox ageCheckBox;
    @FXML
    private CheckBox friendsCheckBox;
    @FXML
    private CheckBox postsCheckBox;

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
    private TextField statusTextField;


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
    void postsCheckboxPressed() {
        if(postsCheckBox.isSelected()){
            System.out.println("Email Checked");
            hidePosts = true;
        }
        else{
            System.out.println("Email UnChecked");
            hidePosts = false;
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
    void friendsCheckboxPressed() {
        if(friendsCheckBox.isSelected()){
            System.out.println("Phone Checked");
            hideFriends = true;
        }
        else{
            System.out.println("Phone UnChecked");
            hideFriends = false;
        }
    }

    @FXML
    void saveButtonPressed(ActionEvent event) throws IOException {

        PersonDAO personDAO = new PersonDAO();
        //TODO: Save values to database
        //Age
        FacebookLite.currentUser.setAge(Integer.parseInt(ageTextField.getText()));
        //DBUtil.updateField(FacebookLite.currentUser, "age", ageTextField.getText());
        personDAO.updateField(FacebookLite.currentUser, "age", ageTextField.getText());
        //Status
        FacebookLite.currentUser.setStatus(statusTextField.getText());
        //DBUtil.updateField(FacebookLite.currentUser, "status", statusTextField.getText());
        personDAO.updateField(FacebookLite.currentUser, "status", statusTextField.getText());
        //Hide stuff
        FacebookLite.currentUser.setHideAge(ageCheckBox.isSelected());
        FacebookLite.currentUser.setHideFriends(friendsCheckBox.isSelected());
        FacebookLite.currentUser.setHidePosts(postsCheckBox.isSelected());
        FacebookLite.currentUser.setHideStatus(statusCheckBox.isSelected());
        //DBUtil.updateField(FacebookLite.currentUser,"hidden", "hideAge", String.valueOf(hideAge));
        personDAO.updateField(FacebookLite.currentUser,"hidden", "hideAge", String.valueOf(hideAge));
        //DBUtil.updateField(FacebookLite.currentUser,"hidden", "hideStatus", String.valueOf(hideStatus));
        //DBUtil.updateField(FacebookLite.currentUser,"hidden","hidePosts", String.valueOf(hidePosts));
        //DBUtil.updateField(FacebookLite.currentUser,"hidden","hideFriends", String.valueOf(hideFriends));

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

    public void initialize()
    {
        ageCheckBox.setSelected(FacebookLite.currentUser.isHideAge());
        statusCheckBox.setSelected(FacebookLite.currentUser.isHideStatus());
        postsCheckBox.setSelected(FacebookLite.currentUser.isHidePosts());
        friendsCheckBox.setSelected(FacebookLite.currentUser.isHideFriends());

        ageTextField.setText(String.valueOf(FacebookLite.currentUser.getAge()));
        statusTextField.setText(FacebookLite.currentUser.getStatus());
    }

}
