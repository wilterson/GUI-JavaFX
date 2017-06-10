/**
 * Created by Wilterson on 05/06/2017.
 */

package HotDogExpress.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable{

    @FXML
    public JFXButton buttonRegistrar;

    @FXML
    public JFXButton buttonForgotPassword;

    @FXML
    private JFXTextField loginTextField;

    @FXML
    private JFXTextField passwordTextField;

    @FXML
    private JFXButton buttonLogin;

    @FXML
    void Login(ActionEvent event) {
        String email = loginTextField.getText();
        String password = passwordTextField.getText();


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        RequiredFieldValidator validator = new RequiredFieldValidator();

        loginTextField.getValidators().add(validator);
        validator.setMessage("Campo Obrigatório");

        passwordTextField.getValidators().add(validator);
        validator.setMessage("Campo Obrigatório");

        loginTextField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue){
                    loginTextField.validate();
                }
            }
        });

        passwordTextField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue){
                    passwordTextField.validate();
                }
            }
        });
    }

    @FXML
    void showForgotPassword(ActionEvent event) {
        System.out.println("ForgotPass");
    }

    @FXML
    void showSignup(ActionEvent event) {
        System.out.println("Signup");
    }


}
