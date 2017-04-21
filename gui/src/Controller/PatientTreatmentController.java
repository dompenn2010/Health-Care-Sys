package Controller;

import edu.utc.mhcpms.Access.AccessProxyException;
import edu.utc.mhcpms.Treatment;
import edu.utc.mhcpms.IPatient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Date;

/**
 * Created by Dom on 4/18/16.
 */
public class PatientTreatmentController implements Initializable {

    private IPatient patientToPreload;

    @FXML
    private TextField patientName;

    @FXML
    private TextArea treatmentEntry;

    @FXML
    private DatePicker startDate;

    @FXML
    private DatePicker endDate;


    @FXML
    public void cancel(ActionEvent event) throws IOException {

        ViewRenderer.getInstance().init(event, ViewRenderer.getInstance().getCurrentProxy());
    }

    public void makeChanges(ActionEvent event){


        Date sDate = Date.from(startDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date eDate = Date.from(endDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());

        Treatment entry = new Treatment(sDate, eDate, treatmentEntry.getText());


        try {
            ViewRenderer.getInstance().getCurrentProxy().addTreatment(patientToPreload, entry);
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
