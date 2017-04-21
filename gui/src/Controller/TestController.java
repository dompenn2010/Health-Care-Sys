package Controller;

import Model.UserModel;
import edu.utc.mhcpms.Access.AccessProxyException;
import edu.utc.mhcpms.Access.AuthenticationFailedException;
import edu.utc.mhcpms.Access.AuthenticationService;
import edu.utc.mhcpms.Access.IAccessProxy;
import edu.utc.mhcpms.Debug;
import edu.utc.mhcpms.IUser;
import edu.utc.mhcpms.Patient;
import edu.utc.mhcpms.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

import static edu.utc.mhcpms.AccessType.Doctor;
import static edu.utc.mhcpms.AccessType.Nurse;

/**
 * Created by Sir on 4/18/16.
 */
public class TestController implements Initializable {

/*
    private void methodThatOpensNewScene(ActionEvent event) throws IOException{
        Parent home_page_parent =  FXMLLoader.load(myURL);
        // FXMLLoader.load(getClass().getResource("View/Test.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }
    */

    public void testMeth() throws AuthenticationFailedException, AccessProxyException {
/*
            IAccessProxy ap = null;
            ap = new AuthenticationService().authenticate(Debug.ADMIN_NAME, Debug.ADMIN_PW);

            ap.createUser(new User("Testie", Nurse, true), "passwordie");

            IUser us = new User("John", Doctor,true);

            ap.createPatient( new Patient.PatientBuilder( ));
    */
    /*
        IAccessProxy ap = null;
        try {
            ap = new AuthenticationService().authenticate(Debug.ADMIN_NAME, Debug.ADMIN_PW);
        } catch (AuthenticationFailedException e1) {
            e1.printStackTrace();
        }


        ap.getCurrentUser();


        try {
            ap.createUser(new User("Domnisarusus", Doctor, true), "securePassword");
        } catch (AccessProxyException e1) {
            e1.printStackTrace();
        }

*/

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
