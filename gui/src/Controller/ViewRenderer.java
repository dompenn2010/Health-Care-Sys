package Controller;

import edu.utc.mhcpms.Access.AccessProxyException;
import edu.utc.mhcpms.Access.IAccessProxy;
import edu.utc.mhcpms.AccessType;
import edu.utc.mhcpms.IPatient;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static edu.utc.mhcpms.AccessType.*;


/**
 * Created by Sir on 4/19/16.
 */
public class ViewRenderer {
    private static ViewRenderer ourInstance = new ViewRenderer();

    private IAccessProxy currentProxy;

    public static ViewRenderer getInstance() {
        return ourInstance;
    }

    private ViewRenderer() {

    }

    public void init(ActionEvent event, IAccessProxy currentProxy) throws IOException{
        this.currentProxy = currentProxy;

            switch (currentProxy.getCurrentUser().getAccessType()) {
                case Admin:
                    splash(event, currentProxy.getCurrentUser().getAccessType());
                    break;
                case Nurse:
                    splash(event, currentProxy.getCurrentUser().getAccessType());
                    break;
                case Doctor:
                    splash(event, currentProxy.getCurrentUser().getAccessType());
                    break;
                case Receptionist:
                    splash(event, currentProxy.getCurrentUser().getAccessType());
                    break;
            }
    }

    public IAccessProxy getCurrentProxy(){
        return currentProxy;
    }

    public void logout(ActionEvent event) throws IOException {

        currentProxy = null;
        URL myURL = getClass().getClassLoader().getResource("View/login.fxml");
        Parent home_page_parent =  FXMLLoader.load(myURL);
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.setResizable(false);
        app_stage.show();
    }

    public void userSearch(ActionEvent event) throws IOException {

        URL myURL = getClass().getClassLoader().getResource("View/userSearch.fxml");
        Parent home_page_parent =  FXMLLoader.load(myURL);
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }

    public void patientSearch(ActionEvent event, AccessType accessType) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        URL myURL = getClass().getClassLoader().getResource("View/patientSearch.fxml");
        loader.setLocation(myURL);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        Parent home_page_parent = loader.load(myURL.openStream());
        PatientSearchController patientSearchController = loader.getController();
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);

        patientSearchController.setAccessType(accessType);

        app_stage.show();
    }

    public void splash(ActionEvent event, AccessType accessType) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        URL myURL = getClass().getClassLoader().getResource("View/Splash.fxml");
        loader.setLocation(myURL);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        Parent home_page_parent = loader.load(myURL.openStream());
        SplashController splashController = loader.getController();
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);

        splashController.setAccessType(accessType);

        app_stage.show();
    }

    // If we need preloading...
    public void userManagement(ActionEvent event, String userToPreload) throws IOException, AccessProxyException{
        FXMLLoader loader = new FXMLLoader();
        URL myURL = getClass().getClassLoader().getResource("View/userManagement.fxml");
        loader.setLocation(myURL);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        Parent home_page_parent = loader.load(myURL.openStream());
        UserMgmtController userMgmtController = loader.getController();
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);

        userMgmtController.setUserToPreload(userToPreload);

        app_stage.show();
    }

    public void userManagement(ActionEvent event) throws IOException {

        URL myURL = getClass().getClassLoader().getResource("View/userManagement.fxml");
        Parent home_page_parent = FXMLLoader.load(myURL);
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }

    public void patientManagement(ActionEvent event, IPatient patientToPreload) throws IOException, AccessProxyException{
        FXMLLoader loader = new FXMLLoader();
        URL myURL = getClass().getClassLoader().getResource("View/patientManagement.fxml");
        loader.setLocation(myURL);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        Parent home_page_parent = loader.load(myURL.openStream());
        PatientMgmtController patientMgmtController = loader.getController();
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);

        patientMgmtController.setPatientToPreload(patientToPreload);

        app_stage.show();
    }

    public void patientManagement(ActionEvent event) throws IOException {

        URL myURL = getClass().getClassLoader().getResource("View/patientManagement.fxml");
        Parent home_page_parent =  FXMLLoader.load(myURL);
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }

    public void patientHistory(ActionEvent event, IPatient patientToPreload) throws IOException, AccessProxyException{
        FXMLLoader loader = new FXMLLoader();
        URL myURL = getClass().getClassLoader().getResource("View/patientHistory.fxml");
        loader.setLocation(myURL);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        Parent home_page_parent = loader.load(myURL.openStream());
        PatientHistoryController patientHistoryController = loader.getController();
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);

        patientHistoryController.setPatientToPreload(patientToPreload);

        app_stage.show();
    }

    public void patientTreatment(ActionEvent event, IPatient patientToPreload) throws IOException, AccessProxyException{
        FXMLLoader loader = new FXMLLoader();
        URL myURL = getClass().getClassLoader().getResource("View/patientTreatment.fxml");
        loader.setLocation(myURL);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        Parent home_page_parent = loader.load(myURL.openStream());
        PatientTreatmentController patientTreatmentController = loader.getController();
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);

        patientTreatmentController.setPatientToPreload(patientToPreload);

        app_stage.show();
    }

}
