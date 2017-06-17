package hotDogExpress.views;

import com.jfoenix.controls.JFXButton;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import hotDogExpress.MainApp;
import hotDogExpress.models.User;
import hotDogExpress.models.UserObservable;
import hotDogExpress.util.DateUtil;
import hotDogExpress.util.Singleton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmployeesController implements Initializable{

    private MainApp mainApp;

    private Singleton app;

    @FXML
    private TableView<UserObservable> tableViewEmployees;

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

            tableViewEmployees.setItems(listUserEmployee);

            initTableEmployees();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void initTableEmployees() {
        employeeNameColumn.setCellValueFactory(cellData -> cellData.getValue().nomeProperty());
        employeeDepartmentColumn.setCellValueFactory(cellData -> cellData.getValue().departmentProperty());

        showEmployeeDetails(null);

        tableViewEmployees.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showEmployeeDetails(newValue));
    }

    private void showEmployeeDetails(UserObservable user) {
        if (user != null) {
            lblCodEmployee.setText(Integer.toString(user.getId()));
            lblNameEmployee.setText(user.getNome());
            lblEmailEmployee.setText(user.getEmail());
            lblCpfEmployee.setText(user.getCpf());
            lblDepartmentEmployee.setText(user.getDepartment());
            lblBirthdayEmployee.setText(DateUtil.format(user.getBirthday()));
            lblCreatedAtEmployee.setText(DateUtil.format(user.getCreated_at()));
        } else {
            lblCodEmployee.setText("");
            lblNameEmployee.setText("");
            lblEmailEmployee.setText("");
            lblCpfEmployee.setText("");
            lblDepartmentEmployee.setText("");
            lblBirthdayEmployee.setText("");
            lblCreatedAtEmployee.setText("");
        }
    }

    @FXML
    void editEmployee(ActionEvent event) {
        UserObservable user = tableViewEmployees.getSelectionModel().getSelectedItem();
        if (user != null) {
            boolean okClicked = mainApp.editEmployee(user);
            if (okClicked) {
                showEmployeeDetails(user);

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
                        userTemp.setDepartment(user.getDepartment());

                        users.set(i, userTemp);
                        break;
                    }
                }

                app.setUsers(users);

                try {
                    app.saveUsers(users);
                }catch(FileNotFoundException e){
                    System.out.println("Erro ao salvar funcionários: " + e);
                }
            }

        } else {
            Alert alertError = new Alert(Alert.AlertType.WARNING);
            alertError.setTitle("Erro");
            alertError.setHeaderText("Nada Selecionado");
            alertError.setContentText("Selecione um funcionário para editar.");
            alertError.showAndWait();
        }
    }

    @FXML
    void handleNewEmployee(ActionEvent event) throws FileNotFoundException {
        UserObservable user = new UserObservable();
        boolean okClicked = mainApp.editEmployee(user);
        if (okClicked) {
            User userTemp = new User();

            userTemp.setId(app.getLastInsertedId());
            userTemp.setNome(user.getNome());
            userTemp.setEmail(user.getEmail());
            userTemp.setSenha(user.getSenha());
            userTemp.setDepartment(user.getDepartment());
            userTemp.setCpf(user.getCpf());
            userTemp.setRole("employee");
            userTemp.setCreated_at(LocalDate.now());

            List<User> users = app.getUsers();
            users.add(userTemp);

            app.setUsers(users);
//            insertUser();

            app.saveUsers(users);
        }
    }

    private void insertUser() {
        listUserEmployee.clear();

        List<User> list = app.getUsers();
        for (int i = 0; i < list.size(); i++) {
            User user = list.get(i);
            list.add(new User(user));
        }

        tableViewEmployees.setItems(listUserEmployee);
    }

    @FXML
    void removeEmployee(ActionEvent event) throws FileNotFoundException {
        int selectedIndex = tableViewEmployees.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Alert alertConfirm = new Alert(Alert.AlertType.CONFIRMATION);
            alertConfirm.setTitle("Excluir Funcionário");
            alertConfirm.setHeaderText("Deseja excluir ?");

            Optional<ButtonType> choice = alertConfirm.showAndWait();

            if (choice.get() == ButtonType.OK) {
                UserObservable user = tableViewEmployees.getSelectionModel().getSelectedItem();

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
                tableViewEmployees.getItems().remove(selectedIndex);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erro");
            alert.setHeaderText("Nenhum Funcionário Selecionado");
            alert.setContentText("Selecione o cliente que deseja excluir.");
            alert.showAndWait();
        }
    }

    @FXML
    void backHome(ActionEvent event) {
        this.mainApp.backHome();
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

}
