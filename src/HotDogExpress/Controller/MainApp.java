package HotDogExpress.Controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Wilterson on 05/06/2017.
 */
public class MainApp extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/HotDogExpress/Views/Login.fxml"));
            Scene scene =  new Scene(root, 400, 488);
            scene.getStylesheets().add(getClass().getResource("/HotDogExpress/Resources/style.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
