package hotDogExpress.views;

import hotDogExpress.MainApp;
import hotDogExpress.models.User;
import hotDogExpress.models.UserObservable;
import hotDogExpress.util.Singleton;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class SignupController implements Initializable{

    private MainApp mainApp;
    private Stage dialogStage;
    private Singleton app;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            this.app = Singleton.getInstance();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
}
