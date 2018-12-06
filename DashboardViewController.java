import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/*
    TODO: Object type for User Avatar? Then initialize
 */

public class DashboardViewController {

    @FXML
    private Button deletePost;

    @FXML
    private Label firstNameLabel;

    @FXML
    private Label lastNameLabel;


    @FXML
    private Button settingsButton;

    @FXML
    private Button editFriendsButton;

    @FXML
    private Button addPost;

    @FXML
    private Button deleteFriends;

    // Added
    @FXML
    private Label ageLabel;





    @FXML
    void settingsButtonPressed(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        stage=(Stage) ((Button)(event.getSource())).getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("SettingsView.fxml"));
        Scene scene = new Scene(root);
        //scene.getStylesheets().add(getClass().getResource("styles/Font/UserInterface.css").toExternalForm())
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

/*
    TODO: Fix ERROR when clicking "Login" Button on first page
    Dec 02, 2018 2:44:41 PM javafx.fxml.FXMLLoader$ValueElement processValue
    WARNING: Loading FXML document with JavaFX API of version 8.0.171 by JavaFX runtime of version 8.0.131
    Dec 02, 2018 2:44:41 PM com.sun.javafx.css.parser.CSSParser term
    WARNING: CSS Error parsing '*{-fx-background-color: #3b5998; -fx-border-color: ;}: Unexpected token ';' at [1,51]

 */

