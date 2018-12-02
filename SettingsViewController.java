import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;

public class SettingsViewController {

    boolean showAge;
    boolean showStatus;
    boolean showFriends;
    boolean showPosts;

    @FXML
    private CheckBox statusCheckbox;

    @FXML
    private CheckBox friendsCheckbox;

    @FXML
    private Button submitButton;

    @FXML
    private CheckBox postCheckbox;

    @FXML
    private CheckBox ageCheckbox;

    @FXML
    void ageCheckboxPressed() {
        if(ageCheckbox.isSelected() == true){
            System.out.println("Age Checked");
            showAge = false;
        }
        else{
            System.out.println("Age UnChecked");
            showAge = true;
        }
    }

    @FXML
    void statusCheckboxPressed() {
        if(statusCheckbox.isSelected() == true){
            System.out.println("Status Checked");
            showStatus = false;
        }
        else{
            System.out.println("Status UnChecked");
            showStatus = true;
        }
    }

    @FXML
    void friendsCheckboxPressed() {
        if(friendsCheckbox.isSelected() == true){
            System.out.println("Friends Checked");
            showFriends = false;
        }
        else{
            System.out.println("Friends UnChecked");
            showFriends = true;
        }
    }

    @FXML
    void postCheckboxPressed() {
        if(postCheckbox.isSelected() == true){
            System.out.println("Post Checked");
            showPosts = false;
        }
        else{
            System.out.println("Post UnChecked");
            showPosts = true;
        }
    }
    @FXML
    void submitButtonPressed(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        stage=(Stage) ((Button)(event.getSource())).getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("DashboardView.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
