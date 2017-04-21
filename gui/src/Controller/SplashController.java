package Controller;

import edu.utc.mhcpms.Access.AccessProxyException;
import edu.utc.mhcpms.Access.AuthenticationFailedException;
import edu.utc.mhcpms.Access.AuthenticationService;
import edu.utc.mhcpms.Access.IAccessProxy;
import edu.utc.mhcpms.AccessType;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javax.swing.text.View;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

//import java.awt.event.ActionEvent;

public class SplashController implements Initializable{

    private AccessType accessType;

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    @FXML
    private Button editPatientButton;

    @FXML
    private Button newPatientButton;

    @FXML
    private Button newUserButton;

    @FXML
    private Button editUserButton;


    @FXML
    private void onNewPatientButtonClick(ActionEvent event) throws IOException {

        ViewRenderer.getInstance().patientManagement(event);

    }
    @FXML
    private void onNewUserButtonClick(ActionEvent event) throws IOException {

        ViewRenderer.getInstance().userManagement(event);

    }

    @FXML
    private void onEditPatientButtonClick(ActionEvent event) throws IOException {

        ViewRenderer.getInstance().patientSearch(event, accessType);

    }

    @FXML
    private void onEditUserButtonClick(ActionEvent event) throws IOException {

        ViewRenderer.getInstance().userSearch(event);

    }

    @FXML
    private void onExitButtonClick(ActionEvent event){

    //    Alert alert = new Alert(Alert.AlertType.WARNING, "Don't go!!!!!");

        //alert.showAndWait()
          //      .filter(response -> response == ButtonType.OK)
            //    .ifPresent(response -> {
                        Platform.exit();
              //  });
    }

    @FXML
    public void setAccessType(AccessType accessType){
        this.accessType = accessType;

        switch (accessType){
            case Doctor:
                newPatientButton.setDisable(true);
                newUserButton.setDisable(true);
                editUserButton.setDisable(true);
                break;
            case Nurse:
                newPatientButton.setDisable(true);
                newUserButton.setDisable(true);
                editUserButton.setDisable(true);
                break;
            case Receptionist:
                newUserButton.setDisable(true);
                editUserButton.setDisable(true);
                break;
            case Admin:
                newPatientButton.setDisable(true);
                editPatientButton.setDisable(true);
                break;
        }
    }

    @FXML
    private void onLogoutButtonClick(ActionEvent event){

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to logout?");

        alert.showAndWait()
                .filter(response -> response == ButtonType.OK)
                .ifPresent(response -> {
                    try {
                        ViewRenderer.getInstance().logout(event);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            System.out.println("1");
            ViewRenderer.getInstance().getCurrentProxy().getPatients().forEach(iPatient -> System.out.println(iPatient.toString()));
            //System.out.println(ViewRenderer.getInstance().getCurrentProxy().getPatients().forEach( iPatient -> ));
        } catch (AccessProxyException e) {
            e.printStackTrace();
        }
    }
}
