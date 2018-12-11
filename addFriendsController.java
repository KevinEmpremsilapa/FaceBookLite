import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
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

    ObservableList<Person> users = FXCollections.observableArrayList();

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
        System.out.println("Add Friends Button Pressed");

        //TODO: Save friend to database
        Person selected = new PersonDAO();
        PersonDAO person = new PersonDAO();
        selected = (Person) addFriendsListView.getSelectionModel().getSelectedItem();
        if(selected != null) {
            person.addFriend(FacebookLite.currentUser, selected);


            Stage stage;
            Parent root;
            stage = (Stage) ((Button) (event.getSource())).getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("DashboardView.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else
            System.out.println("No friend selected");
    }

    public void initialize()
    {
        users = DBUtil.printAllUsers(FacebookLite.currentUser);
        addFriendsListView.setItems(users);
        addFriendsListView.setCellFactory(param -> new ListCell<Person>(){
            @Override
            protected void updateItem(Person person, boolean empty)
            {
                super.updateItem(person, empty);

                if(empty || person == null)
                {
                    setText(null);
                }
                else {
                    setText(person.getFullName());
                }
            }
        });

        FilteredList<Person> filteredData = new FilteredList<>(users ,p -> true);

        //Filter
        searchBarTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person ->{
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if(person.getFirstName().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                }else if(person.getLastName().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                }
                return false;
            });
        });

        SortedList<Person> sortedData = new SortedList<>(filteredData);

        addFriendsListView.setItems(sortedData);
    }

}
