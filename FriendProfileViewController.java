import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;

import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FriendProfileViewController {


    @FXML
    private Label firstNameFriendLabel;

    @FXML
    private Label lastNameFriendLabel;

    @FXML
    private Text statusFriendLabel;

    @FXML
    private Label ageFriendLabel;

    @FXML
    private Button userFriendIconButton;

    @FXML
    private Button backToMyProfileButton;

    @FXML
    private ImageView friendIconImg = new ImageView("Images/defaultUserIcon.png");

    @FXML
    private Button friendIconCircle;
    @FXML
    private ListView postsFriendListView;

    ObservableList<Post> posts = FXCollections.observableArrayList();

    @FXML
    void backToMyProfileButtonPressed(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        stage=(Stage) ((Button)(event.getSource())).getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("DashboardView.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void initialize()
    {
        firstNameFriendLabel.setText(FacebookLite.friendProfile.getFirstName());
        lastNameFriendLabel.setText(FacebookLite.friendProfile.getLastName());
        ageFriendLabel.setText(String.valueOf(FacebookLite.friendProfile.getAge()));

        posts = DBUtil.printAllPosts(FacebookLite.friendProfile);
        postsFriendListView.setItems(posts);
        postsFriendListView.setCellFactory(param -> new ListCell<Post>(){
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
    }



}
