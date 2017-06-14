/**
 * Created by Wilterson on 13/06/2017.
 */

package hotDogExpress.views;

import hotDogExpress.MainApp;
import hotDogExpress.models.User;
import hotDogExpress.util.Singleton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class RootLayoutController implements Initializable {

    private Singleton app;

    private Stage window;

    @FXML
    private Label labelUserName;

    @FXML
    private Label labelUserEmail;
    private MainApp mainApp;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            this.app = Singleton.getInstance();
            this.labelUserEmail.setText(app.getUserActive().getEmail());
            this.labelUserName.setText(app.getUserActive().getNome());
            setWelcomeMessage();
        } catch (FileNotFoundException e) {
            System.out.println("Erro na Inicialização: \n");
            e.printStackTrace();
        }
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setWelcomeMessage(){
        labelUserName.setText(app.getUserActive().getNome());
        labelUserEmail.setText(app.getUserActive().getEmail());
    }

    public void logout(ActionEvent actionEvent) {
        app.setUserActive(new User(0, null, null, null, null, null, null, null));
        mainApp.logout();
    }
}
