/**
 * Created by Wilterson on 07/06/2017.
 */

package hotDogExpress.views;

import com.jfoenix.controls.JFXButton;
import hotDogExpress.MainApp;
import hotDogExpress.models.UserObservable;
import hotDogExpress.models.User;
import hotDogExpress.util.DateUtil;
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
import java.util.Optional;
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
            lblBirthdayClient.setText(DateUtil.format(user.getBirthday()));
            lblCreatedAtClient.setText(DateUtil.format(user.getCreated_at()));
        } else {
            lblCodClient.setText("");
            lblNameClient.setText("");
            lblEmailClient.setText("");
            lblCpfClient.setText("");
        }
    }

    @FXML
    void editClient(ActionEvent event) throws FileNotFoundException {
        UserObservable user = tableViewClients.getSelectionModel().getSelectedItem();
        if (user != null) {
            boolean okClicked = mainApp.editClient(user);
            if (okClicked) {
                showClientDetails(user);

                List<User> users = app.getUsers();
                for (int i = 0; i < users.size(); i++) {
                    User userTemp = users.get(i);

                    if (userTemp.getId() == user.getId()) {
                        userTemp.setRole(user.getRole());
                        userTemp.setNome(user.getNome());
                        userTemp.setEmail(user.getEmail());
                        userTemp.setBirthday(user.getBirthday());
                        userTemp.setCpf(user.getCpf());
                        userTemp.setCreated_at(user.getCreated_at());

                        users.set(i, userTemp);
                        break;
                    }
                }

                app.setUsers(users);

                try {
                    app.saveUsers(users);
                }catch(FileNotFoundException e){
                    System.out.println("Erro ao salvar clientes: " + e);
                }
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erro");
            alert.setHeaderText("Nada Selecionado");
            alert.setContentText("Selecione um cliente para editar.");
            alert.showAndWait();
        }
    }

    @FXML
    void removeClient(ActionEvent event) throws FileNotFoundException {
        int selectedIndex = tableViewClients.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Alert alertConfirm = new Alert(Alert.AlertType.CONFIRMATION);
            alertConfirm.setTitle("Excluir Cliente");
            alertConfirm.setHeaderText("Deseja excluir ?");

            Optional<ButtonType> choice = alertConfirm.showAndWait();

            if (choice.get() == ButtonType.OK) {
                UserObservable user = tableViewClients.getSelectionModel().getSelectedItem();

                List<User> users = app.getUsers();
                for (int i = 0; i < users.size(); i++) {
                    User userTemp = users.get(i);
                    if (userTemp.getId() == user.getId()) {
                        int userIndex = users.indexOf(userTemp);
                        users.remove(userIndex);
                        break;
                    }
                }

                app.setUsers(users);
                app.saveUsers(users);
                tableViewClients.getItems().remove(selectedIndex);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erro");
            alert.setHeaderText("Nenhum Cliente Selecionado");
            alert.setContentText("Selecione o cliente que deseja excluir.");
            alert.showAndWait();
        }
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    public void backHome(){
        this.mainApp.backHome();
    }
}
