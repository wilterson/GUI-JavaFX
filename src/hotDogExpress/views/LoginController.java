/**
 * Created by Wilterson on 05/06/2017.
 */

package hotDogExpress.views;

import com.jfoenix.controls.*;
import hotDogExpress.MainApp;
import hotDogExpress.models.User;
import hotDogExpress.util.Singleton;
import javafx.fxml.FXML;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class LoginController implements Initializable{

    private MainApp mainApp;
    private Singleton app;
    private User userActive;
    private Stage window;
    private boolean isLogged;

    //Lista de usuários do sistema
    private List<User> users;

    Alert alertError;

    @FXML
    public JFXButton buttonRegistrar;

    @FXML
    public JFXButton buttonForgotPassword;

    @FXML
    private JFXTextField loginTextField;

    @FXML
    private JFXPasswordField passwordTextField;

    @FXML
    private JFXButton buttonLogin;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            this.isLogged = false;

            this.app = Singleton.getInstance();

            this.users = new ArrayList<>();

            alertError = new Alert(Alert.AlertType.ERROR);

            userActive = null;
        } catch (FileNotFoundException e) {
            System.out.println("Erro Inicialização: \n");
            e.printStackTrace();
        }
    }

    @FXML
    public void login(ActionEvent actionEvent) {
        if(doLogin()){
            this.isLogged = true;
            window.close();
        }
    }

    private boolean doLogin(){
        boolean check = false;
        String email = loginTextField.getText();
        String password = passwordTextField.getText();
        users = app.getUsers();

        String message = "";

        if (email.length() == 0) {
            message += "Login não pode ficar em branco.\n";
        }
        if (password.length() == 0) {
            message += "Password não pode ficar em branco\n";
        }


        for (int i=0; i < users.size(); i++){
            User user = users.get(i);

            if( (email.equals(user.getEmail())) && password.equals(user.getSenha())){
                check = true;
                setUserActive(user);
                break;
            }
        }

        if (!check){
            message += "Email e/ou senha incorretos.\n";
            alertError.setTitle("Erro na Autenticação");
            alertError.setHeaderText("Erro!! Tente novamente!");
            alertError.setContentText(message);
            alertError.showAndWait();
        }else{
            return true;
        }

        return false;
    }

    public boolean isLogged(){
        return this.isLogged;
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    void showForgotPassword(ActionEvent event) {
        System.out.println("ForgotPass");
    }

    @FXML
    void showSignup(ActionEvent event) {
        System.out.println("Signup");
    }

    private void setUserActive(User userActive) {
        app.setUserActive(userActive);
    }

    public void setWindow(Stage windows) {
        this.window = windows;
    }

}
