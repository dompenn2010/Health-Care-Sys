package Controller;

import edu.utc.mhcpms.*;
import edu.utc.mhcpms.Access.AccessProxyException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javax.swing.text.View;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Dom on 4/18/16.
 */
public class PatientHistoryController implements Initializable {

    private IPatient patientToPreload;

    @FXML
    private TextField patientName;

    @FXML
    private TextArea historyEntry;

    @FXML
    public void cancel(ActionEvent event) throws IOException {

        ViewRenderer.getInstance().init(event, ViewRenderer.getInstance().getCurrentProxy());
    }

    public void makeChanges(ActionEvent event){


        HistoryEntry entry = new HistoryEntry(historyEntry.getText());

        try {
            ViewRenderer.getInstance().getCurrentProxy().addHistoryEntry(patientToPreload, entry);
        } catch (AccessProxyException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.show();
        }

        /*

        if (userToPreload == null) {
            try {
                // This probably won't give us the correct AccessType ENUM yet
                IUser user = new User(userName.getText(), userType.getValue(), accountStatus.isSelected());

                ViewRenderer.getInstance().getCurrentProxy().createUser(user, password.getText());
            } catch (AccessProxyException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.show();
            }
        }else {
            try {
                ViewRenderer.getInstance().getCurrentProxy().updateUser(new User(userToPreload, userType.getValue(), accountStatus.isSelected()));
            } catch (AccessProxyException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.show();
            }
        }*/
    }

    public void setPatientToPreload(IPatient patientToPreload)throws AccessProxyException{
        this.patientToPreload = patientToPreload;

        patientName.setText(patientToPreload.getPatientInfo().getFirstName() + " " + patientToPreload.getPatientInfo().getLastName());
        patientName.setDisable(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
