/**
 * Created by Wilterson on 07/06/2017.
 */

package hotDogExpress.views;

import com.jfoenix.controls.JFXButton;
import hotDogExpress.MainApp;
import hotDogExpress.models.UserObservable;
import hotDogExpress.models.User;
import hotDogExpress.util.Singleton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ClientController implements Initializable{
    private MainApp mainApp;

    private Singleton app;

    @FXML
    private TableView<UserObservable> tableViewClients;

    @FXML
    private TableColumn<UserObservable, String> clientsNameColumn;

    @FXML
    private TableColumn<UserObservable, String> clientsEmailColumn;

    @FXML
    private Label lblCodClient;

    @FXML
    private Label lblNameClient;

    @FXML
    private Label lblEmailClient;

    @FXML
    private Label lblCpfClient;

    @FXML
    private Label lblBirthdayClient;

    @FXML
    private Label lblCreatedAtClient;

    @FXML
    private JFXButton btnAddNew;

    @FXML
    private JFXButton btnEdit;

    @FXML
    private JFXButton btnRemove;

    private ObservableList<UserObservable> listUserClient = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            this.app = Singleton.getInstance();

            List<User> list = app.getUsersClients();
            for (User user : list) {
                listUserClient.add(new UserObservable(user));
            }

            tableViewClients.setItems(listUserClient);

            initTableClients();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void initTableClients() {
        clientsNameColumn.setCellValueFactory(cellData -> cellData.getValue().nomeProperty());
        clientsEmailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());

        showClientDetails(null);

        tableViewClients.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showClientDetails(newValue));
    }

    private void showClientDetails(UserObservable user) {
        if (user != null) {
            lblCodClient.setText(Integer.toString(user.getId()));
            lblNameClient.setText(user.getNome());
            lblEmailClient.setText(user.getEmail());
            lblCpfClient.setText(user.getCpf());
//            lblBirthdayClient.setText(user.getBirthday());
//            lblCreatedAtClient.setText(user.getCreatedAt());
        } else {
            lblCodClient.setText("");
            lblNameClient.setText("");
            lblEmailClient.setText("");
            lblCpfClient.setText("");
        }
    }

    @FXML
    void addNewClient(ActionEvent event) {

    }

    @FXML
    void editClient(ActionEvent event) {

    }

    @FXML
    void removeClient(ActionEvent event) {

    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    public void backHome(){
        this.mainApp.backHome();
    }
}
