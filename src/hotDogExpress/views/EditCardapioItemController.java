/**
 * Created by Wilterson on 17/06/2017.
 */

package hotDogExpress.views;

import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import hotDogExpress.MainApp;
import hotDogExpress.models.ProductObservable;
import hotDogExpress.models.ProductType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class EditCardapioItemController {

    private Stage dialogStage;
    private MainApp mainApp;
    private ProductObservable product;
    private boolean okClicked;

    @FXML
    private JFXTextField inputName;

    @FXML
    private JFXTextField inputPrice;

    @FXML
    private JFXRadioButton inputTypeFood;

    @FXML
    private JFXRadioButton inputTypeDrink;

    @FXML
    private ToggleGroup productType;

    @FXML
    void handleSaveItem(ActionEvent event) {
        if (editIsValid()) {

            product.setName(inputName.getText());
            product.setPrice(Double.parseDouble(inputPrice.getText()));

            if(inputTypeDrink.isSelected()){
                product.setType(ProductType.drink);
            }else{
                product.setType(ProductType.food);
            }

            okClicked = true;
            dialogStage.close();
        }
    }

    private boolean editIsValid() {
        String message = "";

        if (inputName.getText().length() == 0) {
            message += "Nome não pode ser em branco!\n";
        }

        if (inputPrice.getText().length() == 0) {
            message += "Preço não pode ser em branco!\n";
        }

        if (!isDouble(inputPrice, inputPrice.getText())) {
            message += "Preço precisa ser um número!\n";
        }

//        if (productType.getSelectedToggle().getUserData().toString().equals("")) {
//            message += "Tipo de Produto não pode ser em branco!\n";
//        }

        if (message.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Formulário com Erros");
            alert.setHeaderText("Possui erros no formulário de edição de produto");
            alert.setContentText(message);
            alert.showAndWait();

            return false;
        }
    }

    private boolean isDouble(JFXTextField inputPrice, String text) {
        try{
            double price = Double.parseDouble(text);
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setProduct(ProductObservable product) {
        this.product = product;

        inputName.setText(product.getName());
        inputPrice.setText(Double.toString(product.getPrice()));
//        productType.selectToggle();
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    void handleBack(ActionEvent event) {
        dialogStage.close();
    }
}
