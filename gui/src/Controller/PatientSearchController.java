package Controller;

import edu.utc.mhcpms.Access.AccessProxyException;
import edu.utc.mhcpms.AccessType;
import edu.utc.mhcpms.IPatient;
import edu.utc.mhcpms.IUser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

/**
 * Created by Dom on 4/18/16.
 */
public class PatientSearchController implements Initializable {

    private AccessType accessType;

    @FXML
    private Button search;
    @FXML
    private Button cancel;
    @FXML
    private Button history;
    @FXML
    private Button treatment;
    @FXML
    private Button prescriptions;

    @FXML
    private ChoiceBox patientList;

    @FXML
    public void cancel(ActionEvent event) throws IOException {

        ViewRenderer.getInstance().init(event, ViewRenderer.getInstance().getCurrentProxy());

    }

    @FXML
    public void search(ActionEvent event) throws AccessProxyException, IOException {

        ViewRenderer.getInstance().patientManagement(event, (IPatient) patientList.getValue());
    }

    @FXML
    public void history(ActionEvent event) throws AccessProxyException, IOException {

        ViewRenderer.getInstance().patientHistory(event, (IPatient) patientList.getValue());
    }

    @FXML
    public void treatment(ActionEvent event) throws AccessProxyException, IOException {

        ViewRenderer.getInstance().patientTreatment(event, (IPatient) patientList.getValue());
    }

    @FXML
    public void setAccessType(AccessType accessType) {
        this.accessType = accessType;

        switch (accessType) {
            case Doctor:
                prescriptions.setDisable(true);

                break;
            case Nurse:
                treatment.setDisable(true);
                prescriptions.setDisable(true);
                break;
            case Receptionist:
                history.setDisable(true);
                treatment.setDisable(true);
                prescriptions.setDisable(true);
                break;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            // Grab all IUsers
            Collection<IPatient> patients = ViewRenderer.getInstance().getCurrentProxy().getPatients();

            // New String ArrayList
            ArrayList<String> patientNames = new ArrayList<>();

            // Put the name of each IUser into the String ArrayList
            patients.forEach(patient-> patientNames.add(patient.getPatientInfo().getFirstName() + " " + patient.getPatientInfo().getLastName()));

            // Make that String ArrayList of names the Observable List
            //ObservableList<String> list = FXCollections.observableArrayList(patientNames);

            ObservableList<IPatient> list = FXCollections.observableArrayList(ViewRenderer.getInstance().getCurrentProxy().getPatients());

            patientList.setItems(list);

        } catch (AccessProxyException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "...How did you get here?");
            alert.show();
        }
    }
}
