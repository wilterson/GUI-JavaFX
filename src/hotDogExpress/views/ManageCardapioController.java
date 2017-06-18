package hotDogExpress.views;

import com.jfoenix.controls.JFXButton;
import hotDogExpress.MainApp;
import hotDogExpress.models.*;
import hotDogExpress.util.Singleton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class ManageCardapioController implements Initializable{

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
    private JFXButton btnAddNew;

    @FXML
    private JFXButton btnBack;
    private MainApp mainApp;

    private Singleton app;

    private ObservableList<ProductObservable> listProductsFoods = FXCollections.observableArrayList();
    private ObservableList<ProductObservable> listProductsDrinks = FXCollections.observableArrayList();

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
    void handleAddNewItem(ActionEvent event) throws FileNotFoundException {
        ProductObservable product = new ProductObservable();
        boolean okClicked = mainApp.editProduct(product);
        if (okClicked) {
            Product productTemp = new Product();

            productTemp.setProductCod(app.getLastInsertedIdProduct());
            productTemp.setName(product.getName());
            productTemp.setPrice(product.getPrice());
            productTemp.setType(product.getType());

            List<Product> products = app.getProducts();
            products.add(productTemp);

            app.setProducts(products);

            if (productTemp.getType() == ProductType.food){
                foodTable.getItems().add(productTemp);
            }else{
                drinkTable.getItems().add(productTemp);
            }

            app.saveProducts(products);
        }
    }

    @FXML
    void handleRemoveItem() throws FileNotFoundException {
        int selectedIndexFood = foodTable.getSelectionModel().getSelectedIndex();
        int selectedIndexDrink = drinkTable.getSelectionModel().getSelectedIndex();

        if ((selectedIndexDrink >= 0) || (selectedIndexFood >= 0)) {
            Alert alertConfirm = new Alert(Alert.AlertType.CONFIRMATION);
            alertConfirm.setTitle("Excluir Item do Carápio");
            alertConfirm.setHeaderText("Deseja excluir ?");

            TableView<Product> tableSelected = selectedIndexDrink >= 0 ? drinkTable : foodTable;

            Optional<ButtonType> choice = alertConfirm.showAndWait();

            if (choice.get() == ButtonType.OK) {
                Product productObservable = tableSelected.getSelectionModel().getSelectedItem();

                List<Product> products = app.getProducts();
                for (int i = 0; i < products.size(); i++) {
                    Product prodTemp = products.get(i);
                    if (prodTemp.getProductCod() == productObservable.getProductCod()) {
                        int prodIndex = products.indexOf(prodTemp);
                        products.remove(prodIndex);
                        break;
                    }
                }

                app.setProducts(products);
                app.saveProducts(products);
                if(tableSelected == foodTable){
                    tableSelected.getItems().remove(selectedIndexFood);
                }else{
                    tableSelected.getItems().remove(selectedIndexDrink);
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erro");
            alert.setHeaderText("Nenhum Item do Cardápio Selecionado");
            alert.setContentText("Selecione um item para excluir.");
            alert.showAndWait();
        }
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    void backToHome(ActionEvent event) {
        this.mainApp.backHome();
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

        ObservableList<Product> foodsObservableList = FXCollections.observableArrayList(listFoods);
        ObservableList<Product> drinksObservableList = FXCollections.observableArrayList(listDrinks);

        foodTable.setItems(foodsObservableList);
        drinkTable.setItems(drinksObservableList);
    }
}

