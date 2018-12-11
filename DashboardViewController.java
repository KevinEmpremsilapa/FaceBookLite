
import java.io.File;
import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
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

    private ObservableList<Person> friends = FXCollections.observableArrayList();
    private ObservableList<Post> posts = FXCollections.observableArrayList();

    public void initialize() throws IOException {
        firstNameLabel.setText(FacebookLite.currentUser.getFirstName());
        lastNameLabel.setText(FacebookLite.currentUser.getLastName());
        ageLabel.setText(String.valueOf(FacebookLite.currentUser.getAge()));


        friends = DBUtil.printAllFriends(FacebookLite.currentUser);
        friendsListView.setItems(friends);
        friendsListView.setCellFactory(param -> new ListCell<Person>(){
            private ImageView profilePic = new ImageView();
           @Override
           protected void updateItem(Person person, boolean empty)
           {
               profilePic = userIconImg;
               profilePic.setFitHeight(35);
               profilePic.setFitWidth(35);

               super.updateItem(person, empty);

               if(empty || person == null)
               {
                   setText(null);
               }
               else {
                   setText(person.getFullName());
                   setGraphic(profilePic);
               }
           }
        });

        posts = DBUtil.printAllPosts(FacebookLite.currentUser);
        postsListView.setItems(posts);
        postsListView.setCellFactory(param -> new ListCell<Post>(){
            @Override
            protected void updateItem(Post post, boolean empty)
            {
                super.updateItem(post, empty);

                if(empty || post == null)
                {
                    setText(null);
                }
                else {
                    setText(post.toString());
                }
            }
        });
        //System.out.println("Printing friends: " + friends.get(0));

    }

    @FXML
    void friendsViewButtonPressed(ActionEvent event) throws IOException {

        FacebookLite.friendProfile = (Person) friendsListView.getSelectionModel().getSelectedItem();
        if(FacebookLite.friendProfile != null) {
            Stage stage;
            Parent root;
            stage = (Stage) ((Button) (event.getSource())).getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("FriendProfileView.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

        else
            System.out.println("No friend selected");
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
        System.out.println("Delete Friends Button Pressed");

        Person person = new PersonDAO();
        PersonDAO personDAO = new PersonDAO();
        person = (Person) friendsListView.getSelectionModel().getSelectedItem();
        if(person !=null) {
            personDAO.deleteFriend(FacebookLite.currentUser, person);
            friendsListView.getItems().remove(person);
        }
        else
            System.out.println("No friend selected");
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

        PersonDAO personDAO = new PersonDAO();
        Post post = new Post();
        post = (Post) postsListView.getSelectionModel().getSelectedItem();

        if(post != null) {
            personDAO.deletePost(FacebookLite.currentUser, post);
            postsListView.getItems().remove(post);
            System.out.println("Delete Post");
        }
        else
            System.out.println("No post selected");
    }

    @FXML
    void logoutButtonPressed(ActionEvent event) throws IOException {
        FacebookLite.currentUser = null;

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
