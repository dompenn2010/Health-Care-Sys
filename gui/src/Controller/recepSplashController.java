package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

//import java.awt.event.ActionEvent;

public class recepSplashController {

    @FXML
    private Button managePatientButton;
    @FXML
    private void onManagePatientButtonClick(ActionEvent event) throws IOException {

        ViewRenderer.getInstance().patientManagement(event);

    }

}
