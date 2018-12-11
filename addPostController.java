import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class addPostController {

    @FXML
    private Button postButton;
    @FXML
    private Button cancelButton;

    @FXML
    private Label noWritingErrorLabel;
    @FXML
    private TextArea postTextArea;

    @FXML
    void postButtonPressed(ActionEvent event) throws IOException {
        System.out.println("Post Button Pressed");

        //Goes back to Dashboard
        if(!postTextArea.getText().isEmpty()) {
            PersonDAO person = new PersonDAO();
            //DBUtil.addPost(FacebookLite.currentUser, new Post(postTextArea.getText()));
            person.addPost(FacebookLite.currentUser, new Post(postTextArea.getText()));

            Stage stage;
            Parent root;
            stage = (Stage) ((Button) (event.getSource())).getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("DashboardView.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

        else {
            System.out.println("Nothing is written in post");
            noWritingErrorLabel.setVisible(true);
        }

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

    public void initialize()
    {
        noWritingErrorLabel.setVisible(false);
    }
}
