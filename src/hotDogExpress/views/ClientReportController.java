/**
 * Created by Wilterson on 07/06/2017.
 */
package hotDogExpress.views;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;
import hotDogExpress.MainApp;
import hotDogExpress.models.*;
import hotDogExpress.util.DateUtil;
import hotDogExpress.util.Singleton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;

import java.io.FileNotFoundException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class ClientReportController implements Initializable{

    private Singleton app;

    private MainApp mainApp;

    private ObservableList<SellObservable> listSellsClient = FXCollections.observableArrayList();

    @FXML
    private TableView<SellObservable> tableViewCompras;

    @FXML
    private TableColumn<SellObservable, LocalDate> sellDataColumn;

    @FXML
    private TableColumn<SellObservable, Number> sellTotalColumn;

    @FXML
    private Label lblCodSell;

    @FXML
    private Label lblDataSell;

    @FXML
    private GridPane gridPaneProdList;

    @FXML
    private JFXButton btnAddNew;

    @FXML
    private JFXButton btnEdit;

    @FXML
    private JFXButton btnRemove;

    @FXML
    private JFXButton btnBack;

    @FXML
    private Label lblTotalGeral;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            this.app = Singleton.getInstance();

            List<Sell> list = app.getSells();
            for (Sell sell : list) {
                if (sell.getBuyer().getId() == app.getUserActive().getId()){
                    listSellsClient.add(new SellObservable(sell));
                }
            }

            tableViewCompras.setItems(listSellsClient);
            initTableCompras();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void initTableCompras() {
        sellDataColumn.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        sellTotalColumn.setCellValueFactory(cellData -> cellData.getValue().totalProperty());

        showSellDetails(null);

        tableViewCompras.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showSellDetails(newValue));
    }

    private void showSellDetails(SellObservable sell) {
        if(sell != null){
            gridPaneProdList.getChildren().clear();
            lblTotalGeral.setText("");

            lblCodSell.setText(Integer.toString(sell.getCod()));
            lblDataSell.setText(DateUtil.format(sell.getDate()));

            List<Product> products = sell.getProducts();

            int count = 1;
            for (Product prod : products) {
                Label nameItem = new Label(prod.getName());
                Label priceItem = new Label(app.format(prod.getPrice()));

                GridPane.setConstraints(nameItem, 0, count);
                GridPane.setConstraints(priceItem, 1, count);

                Label item = new Label("Item");
                Label price = new Label("Valor");

                GridPane.setConstraints(item, 0, 0);
                GridPane.setConstraints(price, 1, 0);

                gridPaneProdList.getChildren().addAll(item, price, nameItem, priceItem);
                count++;
            }

            lblTotalGeral.setText(app.format(sell.getTotal()));

        }else{
            lblCodSell.setText("");
            lblDataSell.setText("");
            lblTotalGeral.setText("");
            gridPaneProdList.getChildren().clear();
        }
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    void backHome(ActionEvent event) {
        mainApp.backHome();
    }
}
