import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class addFriendsController {

    @FXML
    private Button backButton;
    @FXML
    private Button addFriendsButton;

    @FXML
    private ListView addFriendsListView;

    @FXML
    private TextField searchBarTextField;

    @FXML
    void backButtonPressed(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        stage=(Stage) ((Button)(event.getSource())).getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("DashboardView.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.print("Back Button Pressed\n");
    }

    @FXML
    void addFriendsButtonPressed(ActionEvent event) throws IOException {

        //TODO: Save friend to database
        Stage stage;
        Parent root;
        stage=(Stage) ((Button)(event.getSource())).getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("DashboardView.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Add Friends Button Pressed");
    }
}
