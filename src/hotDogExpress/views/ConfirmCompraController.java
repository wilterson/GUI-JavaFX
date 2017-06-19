/**
 * Created by Wilterson on 18/06/2017.
 */

package hotDogExpress.views;

import com.jfoenix.controls.JFXButton;
import hotDogExpress.MainApp;
import hotDogExpress.models.Product;
import hotDogExpress.util.Singleton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ConfirmCompraController implements Initializable{

    private Singleton app;

    private Stage dialogStage;
    private MainApp mainApp;
    private boolean okClicked;
    private List<Product> itens;

    private double totalGeral;

    @FXML
    private Label lblTotalGeral;

    @FXML
    private VBox vBoxBuyList;

    @FXML
    private JFXButton confirmCompra;

    @FXML
    private GridPane gridListCompras;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            this.app = Singleton.getInstance();
            totalGeral = 0;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleCompraConfirmed(ActionEvent event) {
        okClicked = true;
        dialogStage.close();
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    void handleBack(ActionEvent event) {
        dialogStage.close();
    }

    public void setItens(List<Product> listProd, double totalGeral) {
        gridListCompras.getChildren().clear();

        int count = 1;
        for(int i = 0; i < listProd.size(); i++){
            Product product = listProd.get(i);

            Label nameItem = new Label(product.getName());
            Label priceItem = new Label(app.format(product.getPrice()));

            GridPane.setConstraints(nameItem, 0, count);
            GridPane.setConstraints(priceItem, 1, count);

            Label item = new Label("Item");
            Label price = new Label("Valor");

            GridPane.setConstraints(item, 0, 0);
            GridPane.setConstraints(price, 1, 0);

            gridListCompras.getChildren().addAll(item, price, nameItem, priceItem);

            count++;
        }

        this.totalGeral = totalGeral;
        updateTotalGeral();
    }

    private void updateTotalGeral() {
        lblTotalGeral.setText(app.format(totalGeral));
    }
}
