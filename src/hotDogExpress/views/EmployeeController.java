package hotDogExpress.views;

import com.jfoenix.controls.JFXButton;
import hotDogExpress.MainApp;
import hotDogExpress.models.User;
import hotDogExpress.models.UserObservable;
import hotDogExpress.util.Singleton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class EmployeeController implements Initializable{

    private MainApp mainApp;

    private Singleton app;

    @FXML
    private TableView<UserObservable> tableViewClients;

    @FXML
    private TableColumn<UserObservable, String> employeeNameColumn;

    @FXML
    private TableColumn<UserObservable, String> employeeDepartmentColumn;

    @FXML
    private Label lblCodEmployee;

    @FXML
    private Label lblNameEmployee;

    @FXML
    private Label lblEmailEmployee;

    @FXML
    private Label lblCpfEmployee;

    @FXML
    private Label lblBirthdayEmployee;

    @FXML
    private Label lblCreatedAtEmployee;

    @FXML
    private Label lblDepartmentEmployee;

    @FXML
    private JFXButton btnAddNew;

    @FXML
    private JFXButton btnEdit;

    @FXML
    private JFXButton btnRemove;

    @FXML
    private JFXButton btnBack;

    private ObservableList<UserObservable> listUserEmployee = FXCollections.observableArrayList();

    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            this.app = Singleton.getInstance();

            List<User> list = app.getUsersEmployees();
            for (User user : list) {
                listUserEmployee.add(new UserObservable(user));
            }

            tableViewClients.setItems(listUserEmployee);

            initTableEmployees();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void initTableEmployees() {
        employeeNameColumn.setCellValueFactory(cellData -> cellData.getValue().nomeProperty());
        employeeDepartmentColumn.setCellValueFactory(cellData -> cellData.getValue().departmentProperty());

        showEmployeeDetails(null);

        tableViewClients.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showEmployeeDetails(newValue));
    }

    private void showEmployeeDetails(UserObservable user) {
        if (user != null) {
            lblCodEmployee.setText(Integer.toString(user.getId()));
            lblNameEmployee.setText(user.getNome());
            lblEmailEmployee.setText(user.getEmail());
            lblCpfEmployee.setText(user.getCpf());
            lblDepartmentEmployee.setText(user.getDepartment());
            //            lblBirthdayClient.setText(user.getBirthday());
            //            lblCreatedAtClient.setText(user.getCreatedAt());
        } else {
            lblCodEmployee.setText("");
            lblNameEmployee.setText("");
            lblEmailEmployee.setText("");
            lblCpfEmployee.setText("");
            lblDepartmentEmployee.setText("");
        }
    }

    @FXML
    void handleNewEmployee(ActionEvent event) {
//        UserObservable tempUser = new UserObservable();
//        boolean okClicked = mainApp.showPersonEditDialog(tempPerson);
//        if (okClicked) {
//            mainApp.getPersonData().add(tempPerson);
//        }
    }

    @FXML
    void backHome(ActionEvent event) {
        this.mainApp.backHome();
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

}
