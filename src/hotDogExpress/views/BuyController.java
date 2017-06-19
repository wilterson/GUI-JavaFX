package hotDogExpress.views;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;
import hotDogExpress.MainApp;
import hotDogExpress.models.*;
import hotDogExpress.util.Singleton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class BuyController implements Initializable{

    private MainApp mainApp;

    private Singleton app;

    private List<Product> foodsList;

    private List<Product> drinksList;

    private List<Product> listAllProds = new ArrayList<>();

    private double totalFood;
    private double totalDrink;
    private double totalGeral;

    List<Integer> listProdRef = new ArrayList<>();
    List<Product> listProd = new ArrayList<>();

    @FXML
    private GridPane gridFoods;

    @FXML
    private GridPane gridDrinks;

    @FXML
    private Label lblTotalFood;

    @FXML
    private Label lblTotalDrink;

    @FXML
    private Label lblTotalGeral;

    @FXML
    private VBox vBoxFood;

    @FXML
    private VBox vBoxDrink;

    @FXML
    private JFXButton btnBuy;

    @FXML
    private JFXButton btnBack;

    private Stage dialogStage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            this.app = Singleton.getInstance();
            this.foodsList = app.getProductsFood();
            this.drinksList = app.getProductsDrink();

            this.totalFood  = 0;
            this.totalDrink = 0;
            this.totalGeral = 0;

            this.listAllProds = app.getProducts();

            lblTotalDrink.setText(app.format(totalDrink));
            lblTotalFood.setText(app.format(totalFood));
            lblTotalGeral.setText(app.format(totalGeral));

            initializeGridMenu();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void initializeGridMenu() {

        GridPane gridPaneFood = gridFoods;
        gridPaneFood.setVgap(0);
        gridPaneFood.setHgap(15);

        GridPane gridPaneDrink = gridDrinks;
        gridPaneDrink.setVgap(0);
        gridPaneDrink.setHgap(15);

        int count = 1;
        for(int i = 0; i < foodsList.size(); i++){
            Product food = foodsList.get(i);

            JFXToggleButton btnComprar = new JFXToggleButton();
            btnComprar.setId(Integer.toString(food.getProductCod()));
            btnComprar.setLineSpacing(0.5);
            btnComprar.setOnAction(this::addItem);

            Label nameItem = new Label(food.getName());
            Label priceItem = new Label(app.format(food.getPrice()));

            Label comprar = new Label("Comprar");
            Label item = new Label("Item");
            Label price = new Label("Valor");

            GridPane.setConstraints(comprar, 0, 0);
            GridPane.setConstraints(item, 1, 0);
            GridPane.setConstraints(price, 2, 0);

            GridPane.setConstraints(btnComprar, 0, count);
            GridPane.setConstraints(nameItem, 1, count);
            GridPane.setConstraints(priceItem, 2, count);

            gridPaneFood.getChildren().addAll(comprar, item, price, nameItem, priceItem, btnComprar);

            count++;
        }

        count = 1;
        for(int i = 0; i < drinksList.size(); i++){
            Product drink = drinksList.get(i);

            Label nameItem = new Label(drink.getName());
            Label priceItem = new Label(app.format(drink.getPrice()));

            JFXToggleButton btnComprar = new JFXToggleButton();
            btnComprar.setId(Integer.toString(drink.getProductCod()));
            btnComprar.setLineSpacing(0.5);

            btnComprar.setOnAction(this::addItem);

            GridPane.setConstraints(btnComprar, 0, count);
            GridPane.setConstraints(nameItem, 1, count);
            GridPane.setConstraints(priceItem, 2, count);

            Label comprar = new Label("Comprar");
            Label item = new Label("Item");
            Label price = new Label("Valor");

            GridPane.setConstraints(comprar, 0, 0);
            GridPane.setConstraints(item, 1, 0);
            GridPane.setConstraints(price, 2, 0);

            gridPaneDrink.getChildren().addAll(comprar, item, price, nameItem, priceItem, btnComprar);

            count++;
        }
    }

    private void addItem(ActionEvent e) {
        String id = ((Control)e.getSource()).getId();
        int prodId = Integer.parseInt(id);

        updateTotal(prodId);
        listProdRef.add(prodId);
    }

    private void updateTotal(int id) {
        for(Product prod : listAllProds){
            if((prod.getProductCod() == id) && (prod.getType().equals(ProductType.food))){
                totalFood += prod.getPrice();
            }else if ((prod.getProductCod() == id) && (prod.getType().equals(ProductType.drink))){
                totalDrink += prod.getPrice();
            }

            resetPriceTotal();
        }

    }

    private void resetPriceTotal() {
        totalGeral = totalDrink + totalFood;

        lblTotalDrink.setText(app.format(totalDrink));
        lblTotalFood.setText(app.format(totalFood));
        lblTotalGeral.setText(app.format(totalGeral));
    }

    @FXML
    void handleClientBuy(ActionEvent event) throws IOException {
        for(int id : listProdRef) {
            for (Product product : listAllProds) {
                if (id == product.getProductCod()){
                    listProd.add(product);
                }
            }
        }

        boolean okClicked = mainApp.confirmCompra(listProd, totalGeral);

        if (okClicked) {

            Sell vendaTemp = new Sell();

            vendaTemp.setCod(app.getLastInsertedIdVenda());
            vendaTemp.setBuyer(app.getUserActive());
            vendaTemp.setProducts(listProd);
            vendaTemp.setSeller(app.getRandomSeller());
            vendaTemp.setTotal(totalGeral);
            vendaTemp.setDate(LocalDate.now());

            List<Sell> sells = app.getSells();
            sells.add(vendaTemp);

            app.setSells(sells);

            Alert alertConfirm = new Alert(Alert.AlertType.INFORMATION);
            alertConfirm.setTitle("Compra Efetuada");
            alertConfirm.setHeaderText("Compra efetuada com sucesso \n Aguarde seu pedido ficar pronto. \n\n Agradecemos a preferência!");
            alertConfirm.showAndWait();

            dialogStage.close();
            app.saveSells(sells);

            //Registra Log
            SystemLog log = new SystemLog(app.getLastInsertedIdLogs(), "Compra Efetuada", app.getUserActive(), LocalDate.now());
            List<SystemLog> logs = app.getLogs();
            logs.add(log);
            app.saveLogs(logs);
        }

    }

    @FXML
    void handleBack(ActionEvent event) {
        dialogStage.close();
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
}
