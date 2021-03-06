package hotDogExpress.views;

import com.jfoenix.controls.JFXButton;
import hotDogExpress.MainApp;
import hotDogExpress.util.Singleton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable{

    private Stage window;
    private Singleton app;
    private MainApp mainApp;
    private BorderPane rootLayout;

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

    @FXML
    private JFXButton btnViewFoodMenu;

    @FXML
    private JFXButton btnBuy;

    @FXML
    private JFXButton btnMyReport;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            this.app = Singleton.getInstance();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void showBuy(ActionEvent event) {
        mainApp.initClientBuy();
    }

    @FXML
    void showClientReport(ActionEvent event) {
        mainApp.initClientReport();
    }

    @FXML
    void showFoodMenu(ActionEvent event) throws IOException {
        mainApp.initCardapio();
    }


    @FXML
    void showClients(ActionEvent event) {
        mainApp.iniClients();
    }

    @FXML
    void showEmployees(ActionEvent event) {
        mainApp.initEmployees();
    }

    @FXML
    void showSellsReport(ActionEvent event) {
        mainApp.initSellsReport();
    }

    @FXML
    void showStorage(ActionEvent event) {
        mainApp.initManageStorage("client");
    }

    @FXML
    void showManageStorage(ActionEvent event) {
        mainApp.initManageStorage("admin");
    }

    @FXML
    void showSystemLog(ActionEvent event) {
        mainApp.initSystemLog();
    }

    @FXML
    void showCardapio(ActionEvent event) {
        mainApp.initManageCardapio();
    }

    @FXML
    void showStorageRequest(ActionEvent event) {

    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}
