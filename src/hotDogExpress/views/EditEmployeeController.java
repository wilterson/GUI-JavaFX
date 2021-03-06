/**
 * Created by Wilterson on 17/06/2017.
 */

package hotDogExpress.views;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import hotDogExpress.MainApp;
import hotDogExpress.models.UserObservable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class EditEmployeeController implements Initializable{
    private Stage dialogStage;
    private MainApp mainApp;
    private UserObservable user;
    private boolean okClicked;

    @FXML
    private JFXTextField inputEmail;

    @FXML
    private JFXTextField inputName;

    @FXML
    private JFXPasswordField inputPassword;

    @FXML
    private JFXTextField inputCpf;

    @FXML
    private JFXTextField inputDepartment;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setUser(UserObservable user) {
        this.user = user;

        inputName.setText(user.getNome());
        inputEmail.setText(user.getEmail());
        inputPassword.setText(user.getSenha());
        inputCpf.setText(user.getCpf());
        inputDepartment.setText(user.getDepartment());
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    public void handleSaveEmployee(ActionEvent actionEvent) {
        if (editIsValid()) {

            user.setNome(inputName.getText());
            user.setEmail(inputEmail.getText());
            user.setCpf(inputCpf.getText());
            user.setSenha(inputPassword.getText());
            user.setDepartment(inputDepartment.getText());

            okClicked = true;
            dialogStage.close();
        }
    }

    private boolean editIsValid() {
        String message = "";

        if (inputName.getText().length() == 0) {
            message += "Nome não pode ser em branco!\n";
        }

        if (inputCpf.getText().length() == 0) {
            message += "CPF não pode ser em branco!\n";
        }

        if (inputEmail.getText().length() == 0) {
            message += "E-mail não pode ser em branco!\n";
        }

        if (inputPassword.getText().length() == 0) {
            message += "Senha não pode ser em branco!\n";
        }

        if (inputDepartment.getText().length() == 0) {
            message += "Departamento não pode ser em branco!\n";
        }

        if (message.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Formulário com Erros");
            alert.setHeaderText("Possui erros no formulário de edição de funcionário");
            alert.setContentText(message);
            alert.showAndWait();

            return false;
        }
    }

    public void handleBack(ActionEvent actionEvent) {
        dialogStage.close();
    }
}
