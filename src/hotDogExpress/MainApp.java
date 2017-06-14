package hotDogExpress;

import hotDogExpress.util.Singleton;
import hotDogExpress.views.HomeController;
import hotDogExpress.views.LoginController;
import hotDogExpress.views.RootLayoutController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;

import static javafx.application.Application.launch;

/**
 * Created by Wilterson on 05/06/2017.
 */
public class MainApp extends Application {

    private Stage window;
    private Singleton app;
    private BorderPane rootLayout;
    private RootLayoutController rootController;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.window = primaryStage;
        this.window.setTitle("Hot Dog Express");

        showLogin();
    }

    private void showLogin() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/hotDogExpress/views/Login.fxml"));
            AnchorPane page = loader.load();

            window.setTitle("Login");

            Scene scene = new Scene(page);
            window.setScene(scene);

            LoginController controller = loader.getController();
            controller.setWindow(window);
            controller.setMainApp(this);

            window.showAndWait();

            showHome();
            if (controller.isLogged()) {
                app.setUserActive(controller.getUserActive());
                System.out.println("LOGGED!!!");
                initRootLayout();
                showHome();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showHome() {
        try {
            FXMLLoader loader = new FXMLLoader();

            if(app.getUserActive().getRole().equals("admin")) {
                loader.setLocation(MainApp.class.getResource("/hotDogExpress/views/MenuAdmin.fxml"));
            }else if(app.getUserActive().getRole().equals("employee")) {
                loader.setLocation(MainApp.class.getResource("/hotDogExpress/views/MenuEmployee.fxml"));
            }else{
                loader.setLocation(MainApp.class.getResource("/hotDogExpress/views/MenuClient.fxml"));
            }

            AnchorPane homeWindow = loader.load();
            rootLayout.setCenter(homeWindow);
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
}
