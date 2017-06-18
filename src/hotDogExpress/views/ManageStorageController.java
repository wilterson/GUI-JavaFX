/**
 * Created by Wilterson on 17/06/2017.
 */

package hotDogExpress.views;

import com.jfoenix.controls.JFXButton;
import hotDogExpress.MainApp;
import hotDogExpress.models.*;
import hotDogExpress.util.DateUtil;
import hotDogExpress.util.Singleton;
import javafx.beans.property.IntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.FileNotFoundException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class ManageStorageController implements Initializable{


    private Singleton app;

    private MainApp mainApp;

    private ObservableList<StorageObservable> listItens= FXCollections.observableArrayList();

    @FXML
    private TableView<StorageObservable> tableViewStorage;

    @FXML
    private TableColumn<StorageObservable, String> itemCodColumn;

    @FXML
    private TableColumn<StorageObservable, String> itemNameColumn;

    @FXML
    private TableColumn<StorageObservable, String> itemQtdColumn;

    @FXML
    private TableColumn<StorageObservable, String> itemStatusColumn;

    @FXML
    private Label lblCodItem;

    @FXML
    private Label lblPriceItem;

    @FXML
    private Label lblQtdItem;

    @FXML
    private Label lblNameItem;

    @FXML
    private Label lblStatusItem;

    @FXML
    private JFXButton btnRequest;

    @FXML
    private JFXButton btnBack;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            this.app = Singleton.getInstance();

            List<Storage> list = app.getItensStorage();
            for (Storage item : list) {
                listItens.add(new StorageObservable(item));
            }

            tableViewStorage.setItems(listItens);

            initializeTableItens();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void initializeTableItens() {
        itemNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        itemStatusColumn.setCellValueFactory(cellData -> cellData.getValue().statusProperty());

        showItemDetails(null);

        tableViewStorage.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showItemDetails(newValue));
    }

    private void showItemDetails(StorageObservable storage) {
        if (storage != null) {
            lblCodItem.setText(Integer.toString(storage.getCod()));
            lblNameItem.setText(storage.getName());
            lblPriceItem.setText(Double.toString(storage.getPrice()));
            lblQtdItem.setText(Integer.toString(storage.getQtd()));
            lblStatusItem.setText(storage.getStatus());
        } else {
            lblCodItem.setText("");
            lblNameItem.setText("");
            lblPriceItem.setText("");
            lblQtdItem.setText("");
            lblStatusItem.setText("");
        }
    }

    @FXML
    void handleRequest(ActionEvent event) throws FileNotFoundException {
        StorageObservable storageItem = new StorageObservable();
        boolean okClicked = mainApp.editStorageItem(storageItem);
        if (okClicked) {
            Storage itemTemp = new Storage();

            itemTemp.setCod(app.getLastInsertedIdStorage());
            itemTemp.setName(storageItem.getName());
            itemTemp.setPrice(storageItem.getPrice());
            itemTemp.setQtd(storageItem.getQtd());
            itemTemp.setStatus(storageItem.getStatus());

            List<Storage> storageItens = app.getItensStorage();
            storageItens.add(itemTemp);

            app.setStorageItens(storageItens);
            app.saveStorage(storageItens);
            insertItem();
        }
    }

    @FXML
    void handleApproveRequest(ActionEvent event) throws FileNotFoundException {
        StorageObservable item = tableViewStorage.getSelectionModel().getSelectedItem();
        if (item != null) {

            if(item.getStatus().equals("Ativo")){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Erro");
                alert.setHeaderText("Item já aprovado");
                alert.setContentText("Somente itens com o status Pendente podem ser aprovados.");
                alert.showAndWait();
            }

            showItemDetails(item);

            List<Storage> itens = app.getItensStorage();
            for (int i = 0; i < itens.size(); i++) {
                Storage itemTemp = itens.get(i);

                if (itemTemp.getCod() == item.getCod()) {
                    itemTemp.setStatus("Ativo");

                    itens.set(i, itemTemp);
                    break;
                }
            }

            app.setStorageItens(itens);
            app.saveStorage(itens);

            insertItem();

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erro");
            alert.setHeaderText("Nada Selecionado");
            alert.setContentText("Selecione um item para poder aprovar.");
            alert.showAndWait();
        }
    }

    private void insertItem() {
        listItens.clear();

        List<Storage> list = app.getItensStorage();
        for (Storage item : list) {
            listItens.add(new StorageObservable(item));
        }

        tableViewStorage.setItems(listItens);
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    void backHome(ActionEvent event) {
        mainApp.backHome();
    }
}
