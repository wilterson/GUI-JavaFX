package hotDogExpress;

import hotDogExpress.util.Singleton;
import hotDogExpress.views.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

import static javafx.application.Application.launch;

/**
 * Created by Wilterson on 05/06/2017.
 */
public class MainApp extends Application implements Initializable{

    private Stage window;
    private Singleton app = Singleton.getInstance();
    private BorderPane rootLayout;
    private RootLayoutController rootController;

    public MainApp() throws FileNotFoundException {
        app = Singleton.getInstance();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.window = primaryStage;
        this.window.setTitle("Hot Dog Express");

        showLogin();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            this.app = Singleton.getInstance();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void showLogin() {
        try {
            // Carrega o person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/hotDogExpress/views/Login.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Cria o palco dialogStage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Login");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(window);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Define a pessoa no controller.
            LoginController controller = loader.getController();
            controller.setWindow(dialogStage);
            controller.setMainApp(this);

            // Mostra a janela e espera até o usuário fechar.
            dialogStage.showAndWait();

            if (controller.isLogged()) {
                initRootLayout();
                showHome();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showHome() {
        try {
            // Carrega o person overview.
            FXMLLoader loader = new FXMLLoader();

            if(app.getUserActive().getRole().equals("admin")){
                loader.setLocation(MainApp.class.getResource("/hotDogExpress/views/MenuAdmin.fxml"));

            }else if(app.getUserActive().getRole().equals("employee")){
                loader.setLocation(MainApp.class.getResource("/hotDogExpress/views/MenuEmployee.fxml"));

            }else{
                loader.setLocation(MainApp.class.getResource("/hotDogExpress/views/MenuClient.fxml"));
            }

            BorderPane mainWindow = (BorderPane) loader.load();
            rootLayout.setCenter(mainWindow);

            HomeController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/hotDogExpress/views/RootLayout.fxml"));
            rootLayout = loader.load();

            rootController = loader.getController();
            rootController.setMainApp(this);

            Scene scene = new Scene(rootLayout);
            window.setScene(scene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        launch(args);
    }

    public void logout() {
        window.hide();
        showLogin();
    }

    public void initCardapio() throws IOException {
        try {
            // Carrega o person overview.
            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(MainApp.class.getResource("/hotDogExpress/views/Cardapio.fxml"));

            BorderPane mainWindow = (BorderPane) loader.load();
            rootLayout.setCenter(mainWindow);

            CardapioController controller = loader.getController();
            controller.setMainApp(this);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void backHome() {
        showHome();
    }
}
