/**
 * Created by Wilterson on 07/06/2017.
 */

package hotDogExpress.views;

import com.jfoenix.controls.JFXButton;
import com.sun.security.ntlm.Client;
import hotDogExpress.MainApp;
import hotDogExpress.models.Product;
import hotDogExpress.models.ProductObservable;
import hotDogExpress.models.lists.ProductList;
import hotDogExpress.util.Singleton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class CardapioController implements Initializable{

    private MainApp mainApp;

    private Singleton app;

    private Stage window;

    private List<Product> products;

    private ObservableList<Product> foodsObservableList;
    private ObservableList<Product> drinksObservableList;

    @FXML
    private TableView<Product> foodTable;

    @FXML
    private TableColumn<ProductObservable, String> foodCodColumn;

    @FXML
    private TableColumn<ProductObservable, String> foodNameColumn;

    @FXML
    private TableColumn<ProductObservable, String> foodPriceColumn;

    @FXML
    private TableView<Product> drinkTable;

    @FXML
    private TableColumn<ProductObservable, String> drinkCodColumn;

    @FXML
    private TableColumn<ProductObservable, String> drinkNameColumn;

    @FXML
    private TableColumn<ProductObservable, String> drinkPriceColumn;

    @FXML
    private JFXButton btnBuy;

    @FXML
    private JFXButton btnBack;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            this.app = Singleton.getInstance();
            carregarTableViewCardapio();
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }
    }

    @FXML
    void backToHome(ActionEvent event) {
        mainApp.backHome();
    }

    @FXML
    void showBuy(ActionEvent event) {

    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setWindow(Stage window) {
        this.window = window;
    }

    public void carregarTableViewCardapio() {

        foodCodColumn.setCellValueFactory(new PropertyValueFactory<>("productCod"));
        foodNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        foodPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        drinkCodColumn.setCellValueFactory(new PropertyValueFactory<>("productCod"));
        drinkNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        drinkPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        List<Product> listFoods = app.getProductsFood();
        List<Product> listDrinks = app.getProductsDrink();

        foodsObservableList = FXCollections.observableArrayList(listFoods);
        drinksObservableList = FXCollections.observableArrayList(listDrinks);

        foodTable.setItems(foodsObservableList);
        drinkTable.setItems(drinksObservableList);
    }
}
