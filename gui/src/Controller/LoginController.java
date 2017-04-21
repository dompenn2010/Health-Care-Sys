package Controller;
import Model.UserModel;
import com.sun.org.apache.xml.internal.security.Init;
import edu.utc.mhcpms.Access.AccessProxyException;
import edu.utc.mhcpms.Access.AuthenticationFailedException;
import edu.utc.mhcpms.Access.AuthenticationService;
import edu.utc.mhcpms.Access.IAccessProxy;
import edu.utc.mhcpms.Debug;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
//import java.awt.event.ActionEvent;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable{

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button loginButton;
    @FXML
    private void onLoginButtonClick(ActionEvent event) throws IOException, AccessProxyException {

        IAccessProxy ap = null;
        try {

            ap = new AuthenticationService().authenticate(username.getText(), password.getText());

            ViewRenderer.getInstance().init(event, ap);

        } catch (AuthenticationFailedException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.show();

            username.clear();
            password.clear();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("I initialized!");
    }
}
