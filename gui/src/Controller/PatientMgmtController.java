package Controller;

import Model.UserModel;
import edu.utc.mhcpms.*;
import edu.utc.mhcpms.Access.AccessProxyException;
import edu.utc.mhcpms.Access.AuthenticationFailedException;
import edu.utc.mhcpms.Access.AuthenticationService;
import edu.utc.mhcpms.Access.IAccessProxy;
import edu.utc.mhcpms.Database.FakePatientDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;
import java.util.UUID;

import static edu.utc.mhcpms.AccessType.Doctor;
import static edu.utc.mhcpms.AccessType.Nurse;
import static edu.utc.mhcpms.DiagnosisType.ADHD;
import static edu.utc.mhcpms.DiagnosisType.Amnesia;
import static edu.utc.mhcpms.PatientAddress.State.TN;
import static edu.utc.mhcpms.PatientSexType.Male;

/**
 * Created by Dom on 4/18/16.
 */
public class PatientMgmtController implements Initializable {

    private IPatient patientToPreload;

    // Four below make up the address
    @FXML ComboBox treatments;
    @FXML ComboBox prescriptions;
    @FXML ComboBox history;


    @FXML TextField patientID;
    @FXML
    private TextField firstLine;
    @FXML
    private ComboBox state;
    @FXML
    private TextField city;
    @FXML
    private TextField zipCode;

    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField insurance;

    // Three below make up the phone number
    @FXML
    private TextField centralOfficeCode;
    @FXML
    private TextField areaCode;
    @FXML
    private TextField lineNumber;

    // Three below make up the SSN
    @FXML
    private TextField firstGroup;
    @FXML
    private TextField secondGroup;
    @FXML
    private TextField thirdGroup;

    @FXML
    private ComboBox sex;
    @FXML
    private ComboBox diagnosis;
    @FXML
    private TextField doctor;

    @FXML
    public void cancel(ActionEvent event) throws IOException {

        ViewRenderer.getInstance().init(event, ViewRenderer.getInstance().getCurrentProxy());

    }


    @FXML
    public void createPatient(){


            String fl = firstLine.getText();
            String c = city.getText();

            PatientAddress.State s = (PatientAddress.State) state.getValue(); //??? Gonna have to play with this)
            int zc = Integer.parseInt(zipCode.getText());

            PatientAddress addy = new PatientAddress(fl, c, s, zc);


        if (patientToPreload != null){
            PatientInfoDTO dto = new PatientInfoDTO(patientToPreload.getPatientInfo());
            dto.setAddress(addy);
            dto.setFirstName(firstName.getText());
            dto.setLastName(lastName.getText());
            dto.setInsurance(insurance.getText());
            dto.setPhoneNumber(centralOfficeCode.getText().trim() + "-" +
                                areaCode.getText().trim() +  "-" +
                                    lineNumber.getText().trim());
            try {
                ViewRenderer.getInstance().getCurrentProxy().updatePatientInfo(patientToPreload, dto);
            } catch (AccessProxyException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.show();
            }

        }else {

            IPatient p1 = (new Patient
                    .PatientBuilder())
                    .address(addy)
                    .firstName(firstName.getText())
                    .lastName(lastName.getText())
                    .insurance(insurance.getText())
                    .phoneNumber(
                            Integer.parseInt(centralOfficeCode.getText()),
                            Integer.parseInt(areaCode.getText()),
                            Integer.parseInt(lineNumber.getText()))
                    .SSN(
                            Integer.parseInt(firstGroup.getText()),
                            Integer.parseInt(secondGroup.getText()),
                            Integer.parseInt(thirdGroup.getText()))
                    .sex((PatientSexType) sex.getValue())
                    .diagnosis((DiagnosisType) diagnosis.getValue())
                    .doctor(doctor.getText())
                    .history(new ArrayList())
                    .prescriptions(new ArrayList())
                    .treatments(new ArrayList())
                    .build();

            FakePatientDatabase.getInstance().CreatePatient(p1);
        }
            //ViewRenderer.getInstance().getCurrentProxy().createPatient(p1);
        }

    public void setPatientToPreload(IPatient patientToPreload)throws AccessProxyException{
        this.patientToPreload = patientToPreload;



        patientID.setText(patientToPreload.getID().toString());
        patientID.setDisable(true);

        firstLine.setText(patientToPreload.getPatientInfo().getAddress().getFirstLine());
        state.setValue(patientToPreload.getPatientInfo().getAddress().getState());
        city.setText(patientToPreload.getPatientInfo().getAddress().getCity());
        zipCode.setText(Integer.toString(patientToPreload.getPatientInfo().getAddress().getZipCode()));
        firstName.setText(patientToPreload.getPatientInfo().getFirstName());
        lastName.setText(patientToPreload.getPatientInfo().getLastName());
        insurance.setText(patientToPreload.getPatientInfo().getInsurance());

        // Three below make up the phone number
        centralOfficeCode.setText(patientToPreload.getPatientInfo().getPhoneNumber().substring(0,3));
        areaCode.setText(patientToPreload.getPatientInfo().getPhoneNumber().substring(4,7));
        lineNumber.setText(patientToPreload.getPatientInfo().getPhoneNumber().substring(8));

        // Disable
        firstGroup.setText("\u2022\u2022\u2022");
        secondGroup.setText("\u2022\u2022");
        thirdGroup.setText(patientToPreload.getPatientInfo().getSSN().substring(7));


        firstGroup.setDisable(true);
        secondGroup.setDisable(true);
        thirdGroup.setDisable(true);

        sex.setValue(patientToPreload.getPatientMedicalInfo().getSex());
        diagnosis.setValue(patientToPreload.getPatientMedicalInfo().getDiagnosis());
        doctor.setText(patientToPreload.getPatientMedicalInfo().getDoctor());

        ////////////
        Collection<HistoryEntry> historyEntries = patientToPreload.getPatientMedicalInfo().getHistory();

        // New String ArrayList
        ArrayList<String> historyList = new ArrayList<>();

        // Put the name of each IUser into the String ArrayList
        historyEntries.forEach(entry-> historyList.add(entry.getEntry()));

        // Make that String ArrayList of names the Observable List
        ObservableList<String> list = FXCollections.observableArrayList(historyList);



        history.setItems(list);
        history.setEditable(false);
        /////////////

        ////////////
        Collection<Treatment> treatmentEntries = patientToPreload.getPatientMedicalInfo().getTreatments();

        // New String ArrayList
        ArrayList<String> treatmentList = new ArrayList<>();

        // Put the name of each IUser into the String ArrayList
        treatmentEntries.forEach(entry-> treatmentList.add(entry.getDescription()));

        // Make that String ArrayList of names the Observable List
        ObservableList<String> tlist = FXCollections.observableArrayList(treatmentList);



        treatments.setItems(list);
        treatments.setEditable(false);
        /////////////

    }


    public void initialize(URL location, ResourceBundle resources) {


        patientID.setText("Will be generated upon completion.");
        patientID.setDisable(true);

        PatientSexType[] types =  PatientSexType.values();
        ArrayList<PatientSexType> sexTypes = new ArrayList<>();

        for (int i = 0; i < types.length; i++) {
            sexTypes.add(types[i]);
        }
        ObservableList<PatientSexType> list = FXCollections.observableArrayList(sexTypes);
        sex.setItems(list);

///////////////////////////////////////////////////////////////////////////////////

        DiagnosisType[] diagnosisTypes = DiagnosisType.values();
        ArrayList<DiagnosisType> dTypes = new ArrayList<>();

        for (int i = 0; i < types.length; i++) {
            dTypes.add(diagnosisTypes[i]);
        }
        ObservableList<DiagnosisType> dlist = FXCollections.observableArrayList(dTypes);
        diagnosis.setItems(dlist);

///////////////////////////////////////////////////////////////////////////////////

        PatientAddress.State[] stateTypes = PatientAddress.State.values();
        ArrayList<PatientAddress.State> sTypes = new ArrayList<>();

        for (int i = 0; i < types.length; i++) {
            sTypes.add(stateTypes[i]);
        }
        ObservableList<PatientAddress.State> slist = FXCollections.observableArrayList(sTypes);
        state.setItems(slist);

        prescriptions.setDisable(true);
        //history.setDisable(true);


/*
treatment *** TextField
prescription  *** look at
history
        */
    }
}
