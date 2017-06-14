package hotDogExpress.views;

import com.jfoenix.controls.JFXButton;
import hotDogExpress.MainApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class HomeController {

    @FXML
    private JFXButton btnSellsReport;

    @FXML
    private JFXButton btnViewEmployees;

    @FXML
    private JFXButton btnViewClients;

    @FXML
    private JFXButton btnViewStorage;

    @FXML
    private JFXButton btnViewSystemLog;

    @FXML
    private JFXButton btnAddMenuItem;

    @FXML
    private JFXButton btnStorageRequest;

    @FXML
    private JFXButton btntorage;

    @FXML
    private JFXButton btnSystemLog;
    private MainApp mainApp;

    @FXML
    void showClients(ActionEvent event) {

    }

    @FXML
    void showEmployees(ActionEvent event) {

    }

    @FXML
    void showSellsReport(ActionEvent event) {

    }

    @FXML
    void showStorage(ActionEvent event) {

    }

    @FXML
    void showSystemLog(ActionEvent event) {

    }

    @FXML
    void showAddItem(ActionEvent event) {

    }

    @FXML
    void showStorageRequest(ActionEvent event) {

    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}
