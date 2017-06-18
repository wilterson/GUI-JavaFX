package hotDogExpress;

import hotDogExpress.models.ProductObservable;
import hotDogExpress.models.StorageObservable;
import hotDogExpress.models.UserObservable;
import hotDogExpress.util.Singleton;
import hotDogExpress.views.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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

    public void iniClients() {
        try {
            // Carrega o person overview.
            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(MainApp.class.getResource("/hotDogExpress/views/Clients.fxml"));

            BorderPane mainWindow = (BorderPane) loader.load();
            rootLayout.setCenter(mainWindow);

            ClientController controller = loader.getController();
            controller.setMainApp(this);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void initEmployees() {
        try {
            // Carrega o person overview.
            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(MainApp.class.getResource("/hotDogExpress/views/Employees.fxml"));

            BorderPane mainWindow = (BorderPane) loader.load();
            rootLayout.setCenter(mainWindow);

            EmployeesController controller = loader.getController();
            controller.setMainApp(this);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public boolean editClient(UserObservable user) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/hotDogExpress/views/EditClient.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Cria o palco dialogStage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Editar Cliente");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(window);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);


            EditClientController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setMainApp(this);
            controller.setUser(user);

            // Mostra a janela e espera até o usuário fechar.
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;

    }

    public boolean editEmployee(UserObservable user) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/hotDogExpress/views/EditEmployee.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Cria o palco dialogStage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Editar Funcionário");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(window);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);


            EditEmployeeController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setMainApp(this);
            controller.setUser(user);

            // Mostra a janela e espera até o usuário fechar.
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public void initSignup() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/hotDogExpress/views/Signup.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Cria o palco dialogStage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Registrar");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(window);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);


            SignupController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setMainApp(this);

            // Mostra a janela e espera até o usuário fechar.
            dialogStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initManageCardapio() {
        try {
            // Carrega o person overview.
            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(MainApp.class.getResource("/hotDogExpress/views/ManageCardapio.fxml"));

            BorderPane mainWindow = (BorderPane) loader.load();
            rootLayout.setCenter(mainWindow);

            ManageCardapioController controller = loader.getController();
            controller.setMainApp(this);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public boolean editProduct(ProductObservable product) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/hotDogExpress/views/EditCardapioItem.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Cria o palco dialogStage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Inserir Novo Produto");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(window);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);


            EditCardapioItemController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setMainApp(this);
            controller.setProduct(product);

            // Mostra a janela e espera até o usuário fechar.
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public void initManageStorage(String type) {
        try {
            // Carrega o person overview.
            FXMLLoader loader = new FXMLLoader();

            if(type.equals("client")){
                loader.setLocation(MainApp.class.getResource("/hotDogExpress/views/ManageStorage.fxml"));
            }else{
                loader.setLocation(MainApp.class.getResource("/hotDogExpress/views/ManageStorageAdmin.fxml"));
            }

            BorderPane mainWindow = (BorderPane) loader.load();
            rootLayout.setCenter(mainWindow);

            ManageStorageController controller = loader.getController();
            controller.setMainApp(this);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public boolean editStorageItem(StorageObservable storageItem) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/hotDogExpress/views/EditStorageItem.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Cria o palco dialogStage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Inserir Novo Produto");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(window);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);


            EditStorageItemController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setMainApp(this);
            controller.setItem(storageItem);

            // Mostra a janela e espera até o usuário fechar.
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
}
