/**
 * Created by Wilterson on 19/06/2017.
 */
package hotDogExpress.views;

import hotDogExpress.MainApp;
import hotDogExpress.models.Product;
import hotDogExpress.models.SystemLog;
import hotDogExpress.util.DateUtil;
import hotDogExpress.util.Singleton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SystemLogController implements Initializable{

    private MainApp mainApp;

    private Singleton app;

    private List<SystemLog> logs = new ArrayList<>();

    @FXML
    private GridPane gridSystemLog;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            this.app = Singleton.getInstance();

            this.logs = app.getLogs();

            initializeLog();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void initializeLog() {
        gridSystemLog.getChildren().clear();

        int count = 1;
        for (SystemLog log : logs) {
            Label data = new Label(DateUtil.format(log.getDateAcao()));
            Label acao = new Label(log.getAcao());
            Label nomeUser = new Label(log.getUser().getNome());
            Label cod = new Label(Integer.toString(log.getCod()));

            GridPane.setConstraints(cod, 0, count);
            GridPane.setConstraints(acao, 1, count);
            GridPane.setConstraints(nomeUser, 2, count);
            GridPane.setConstraints(data, 3, count);

            Label codLabel = new Label("Cód");
            Label acaoLabel = new Label("Ação");
            Label nomeLabel = new Label("Usuário");
            Label dateLabel = new Label("Data");

            GridPane.setConstraints(codLabel, 0, 0);
            GridPane.setConstraints(acaoLabel, 1, 0);
            GridPane.setConstraints(nomeLabel, 2, 0);
            GridPane.setConstraints(dateLabel, 3, 0);

            gridSystemLog.getChildren().addAll(data, dateLabel, acao, acaoLabel, cod, codLabel, nomeUser, nomeLabel);

            count++;
        }
    }

    @FXML
    void handleBack(ActionEvent event) {
        mainApp.backHome();
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}
