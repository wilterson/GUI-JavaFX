/**
 * Created by Wilterson on 07/06/2017.
 */

package hotDogExpress.views;

import com.jfoenix.controls.JFXButton;
import hotDogExpress.MainApp;
import hotDogExpress.models.Product;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class CardapioController implements Initializable{

    private MainApp mainApp;

    private Stage window;

    private List<Product> products;

    private ObservableList<Product> productsObservableList;

    @FXML
    private Label productFoodLabel;

    @FXML
    private Label productFoodValue;

    @FXML
    private Label productDrinkLabel;

    @FXML
    private Label productDrinkValue;

    @FXML
    private JFXButton btnBuy;

    @FXML
    private JFXButton btnBack;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

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

}
