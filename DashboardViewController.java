
import java.io.File;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.image.Image;


/*
    TODO: Object type for User Avatar? Then initialize
 */

public class DashboardViewController {


    @FXML
    private Button settingsButton;
    @FXML
    private Button editFriendsButton;
    @FXML
    private Button addPost;
    @FXML
    private Button friendsDeleteButton;
    @FXML
    private Button logoutButton;
    @FXML
    private Button postsDeleteButton;

    @FXML
    private Label ageLabel;
    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;

    @FXML
    private ListView friendsListView;
    @FXML
    private ListView postsListView;




    @FXML
    private ImageView userIconImg = new ImageView("Images/defaultUserIcon.png");
    @FXML
    private ImageView userImg;



    @FXML
    void friendIconButtonPressed(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        stage=(Stage) ((Button)(event.getSource())).getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("FriendProfileView.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

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
    void userIconButtonPressed(ActionEvent event) throws IOException{
        Stage stage = new Stage();

        System.out.println("User Icon Button Pressed");
        Button userIconButton = (Button) event.getSource();

        FileChooser.ExtensionFilter imageFilter
                = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(imageFilter);

        fileChooser.setTitle("Select Image");
        File file = fileChooser.showOpenDialog(stage);
        if(file != null){
            userIconImg.setFitHeight(50);
            userIconImg.setFitWidth(50);
            userIconButton.setGraphic(userIconImg);
        }

        userIconImg.setFitHeight(50);
        userIconImg.setFitWidth(50);
        userIconButton.setGraphic(userIconImg);
    }

    @FXML
    void editFriendsButtonPressed(ActionEvent event) throws IOException {

        Stage stage;
        Parent root;
        stage=(Stage) ((Button)(event.getSource())).getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("addFriendsView.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        System.out.println("Edit Friends");
    }

    @FXML
    void deleteFriendsButtonPressed(ActionEvent event) throws IOException {
        System.out.println("Delete Friends");

    }

    @FXML
    void addPostButtonPressed(ActionEvent event) throws IOException{

        Stage stage;
        Parent root;
        stage=(Stage) ((Button)(event.getSource())).getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("addPostView.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        System.out.println("Add Post");

    }

    @FXML
    void deletePostButtonPressed(ActionEvent event) throws IOException{
        System.out.println("Delete Post");
    }

    @FXML
    void logoutButtonPressed(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        stage=(Stage) ((Button)(event.getSource())).getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("FacebookLiteView.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        System.out.println("Logout Button pressed");
    }

}
