/**
 * Created by Wilterson on 18/06/2017.
 */

package hotDogExpress.views;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import hotDogExpress.MainApp;
import hotDogExpress.models.StorageObservable;
import hotDogExpress.util.Singleton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditStorageItemController implements Initializable{

    private Stage dialogStage;

    private MainApp mainApp;

    private boolean okClicked;

    private Singleton app;

    @FXML
    private JFXTextField inputPrice;

    @FXML
    private JFXButton btnRequest;

    @FXML
    private JFXButton btnBack;

    @FXML
    private JFXTextField inputName;

    @FXML
    private JFXTextField inputQtd;

    private StorageObservable item;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            this.app = Singleton.getInstance();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void handleRequest(ActionEvent event) {
        if(editIsValid()) {
            item.setName(inputName.getText());
            item.setPrice(Double.parseDouble(inputPrice.getText()));
            item.setQtd(Integer.parseInt(inputQtd.getText()));
            item.setStatus("Pendente");

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

        if (inputQtd.getText().length() == 0) {
            message += "Quantidade não pode ser em branco!\n";
        }

        if (message.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Formulário com Erros");
            alert.setHeaderText("Possui erros no formulário de requisição");
            alert.setContentText(message);
            alert.showAndWait();

            return false;
        }
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

    public void setItem(StorageObservable item) {
        this.item = item;

        inputName.setText(item.getName());
        inputQtd.setText(Integer.toString(item.getQtd()));
        inputPrice.setText(Double.toString(item.getPrice()));
    }
}
