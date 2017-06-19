/**
 * Created by Wilterson on 19/06/2017.
 */

package hotDogExpress.views;

import hotDogExpress.MainApp;
import hotDogExpress.models.Sell;
import hotDogExpress.models.User;
import hotDogExpress.util.Singleton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;

import java.io.FileNotFoundException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class SellsReportController implements Initializable{

    private Singleton app;

    private MainApp mainApp;

    private List<Sell> sells = new ArrayList<>();

    private List<User> clients = new ArrayList<>();

    @FXML
    private BarChart<String, Double> clientsChart;

    @FXML
    private CategoryAxis xClient;

    @FXML
    private NumberAxis yClient;

    @FXML
    private LineChart<String, Number> monthChart;

    @FXML
    private CategoryAxis xMonth;

    @FXML
    private NumberAxis yMonth;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            this.app = Singleton.getInstance();

            this.sells = app.getSells();
            this.clients = app.getUsersClients();

            initializeChartClients();
            initializeChartMonth();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void initializeChartClients() {
        XYChart.Series<String, Double> set = new XYChart.Series<>();

        for (User client : clients) {

            List<Sell> sellsClient = new ArrayList<>();
            double total = 0.0;

            for (Sell sellClient : sells){
                if (sellClient.getBuyer().getId() == client.getId()){
                    sellsClient.add(sellClient);
                }
            }

            for (Sell sell : sellsClient){
                total += sell.getTotal();
            }

            set.getData().add(new XYChart.Data(client.getNome(), total));
        }

        clientsChart.getData().addAll(set);
    }

    private void initializeChartMonth() {
        XYChart.Series<String, Number> set = new XYChart.Series<>();

        int[] months = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};

        for(Integer month : months){

            int total = 0;
            String monthName = "";

            for (Sell sell : sells){
                if (sell.getDate().getMonthValue() == month){
                    total++;
                    monthName = sell.getDate().getMonth().toString();
                }
            }

            set.getData().add(new XYChart.Data(monthName, total));
        }

        monthChart.getData().addAll(set);
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    void handleBack(ActionEvent event) {
        mainApp.backHome();
    }
}

