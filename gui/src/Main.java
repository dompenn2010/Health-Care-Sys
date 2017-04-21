import Controller.ViewRenderer;
import edu.utc.mhcpms.*;
import edu.utc.mhcpms.Database.FakePatientDatabase;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;

import static edu.utc.mhcpms.PatientAddress.State.TN;


public class Main extends Application {



    @Override
    public void start(Stage stage) throws Exception{

        //URL url = getClass().getResource("View/login.fxml");
        URL url = getClass().getResource("View/login.fxml");
        Parent root =FXMLLoader.load(url);
        stage.setTitle("");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        PatientAddress addy = new PatientAddress("Hello", "World", TN, 37410);

        IPatient p1 = (new Patient
                .PatientBuilder())
                .address(addy)
                .firstName("Danny")
                .lastName("Freeman")
                .insurance("BCBS")
                .phoneNumber("423-425-4000")
                .SSN("123-45-6789")
                .sex(PatientSexType.Male)
                .diagnosis(DiagnosisType.Bed_Wetting)
                .doctor("Yang")
                .history(new ArrayList<>())
                .prescriptions(new ArrayList<>())
                .treatments(new ArrayList<>())
                .build();

        IPatient p2 = (new Patient
                .PatientBuilder())
                .address(addy)
                .firstName("Patient")
                .lastName("potty")
                .insurance("Unum")
                .phoneNumber("423-425-4000")
                .SSN("123-45-6789")
                .sex(PatientSexType.Male)
                .diagnosis(DiagnosisType.Bed_Wetting)
                .doctor("Yang")
                .history(new ArrayList<>())
                .prescriptions(new ArrayList<>())
                .treatments(new ArrayList<>())
                .build();
        FakePatientDatabase.getInstance().CreatePatient(p1);
        FakePatientDatabase.getInstance().CreatePatient(p2);

        launch(args);

        /*
        try {
            IAccessProxy ap = new AuthenticationService().authenticate(Debug.ADMIN_NAME, Debug.ADMIN_PW);
            ap.getCurrentUser();
            ap.createUser();
            //ap.createUser(new I);

            ap.createUser(new User("Domnisarusus", Doctor, true), "securePassword");

        } catch (AuthenticationFailedException e) {
            e.printStackTrace();
        } catch (AccessProxyException e) {
            e.printStackTrace();
        }
        */

    }


}



