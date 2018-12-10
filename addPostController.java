import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class addPostController {

    @FXML
    private Button postButton;
    @FXML
    private Button cancelButton;

    @FXML
    private TextArea postTextArea;

    @FXML
    void postButtonPressed(ActionEvent event) throws IOException {
        //TODO:Add posts to database
        DBUtil.addPost(FacebookLite.currentUser, new Post(postTextArea.getText()));
        //postTextArea.getText();


        //Goes back to Dashboard
        Stage stage;
        Parent root;
        stage=(Stage) ((Button)(event.getSource())).getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("DashboardView.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.print("Logout Button Pressed\n");
        System.out.println("Post Button Pressed");
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
        System.out.println("Cancel Button Pressed");
    }
}
