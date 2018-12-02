import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class DashboardViewController {

    @FXML
    private Button deletePost;

    @FXML
    private Label firstNameLabel;

    @FXML
    private Button settingsButton;

    @FXML
    private Button editFriendsButton;

    @FXML
    private Button addPost;

    @FXML
    private Button deleteFriends;

    @FXML
    void settingsButtonPressed(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        stage=(Stage) ((Button)(event.getSource())).getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("SettingsView.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void editFriendsButtonPressed(ActionEvent event) {
        System.out.println("Edit Friends");
    }

    @FXML
    void deleteFriendsButtonPressed(ActionEvent event) {
        System.out.println("Delete Friends");

    }

    @FXML
    void addPostButtonPressed(ActionEvent event) {
        System.out.println("Add Post");

    }

    @FXML
    void deletePostButtonPressed(ActionEvent event) {
        System.out.println("Delete Post");

    }
    

}

