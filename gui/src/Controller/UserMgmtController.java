package Controller;

import Model.UserModel;
import edu.utc.mhcpms.*;
import edu.utc.mhcpms.Access.AccessProxyException;
import edu.utc.mhcpms.Access.AuthenticationFailedException;
import edu.utc.mhcpms.Access.AuthenticationService;
import edu.utc.mhcpms.Access.IAccessProxy;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;
import static edu.utc.mhcpms.AccessType.Doctor;
import static edu.utc.mhcpms.AccessType.Nurse;

/**
 * Created by Dom on 4/18/16.
 */
public class UserMgmtController implements Initializable {

    private String userToPreload;

    @FXML
    private TextField userName;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField passwordConfirm;

    @FXML
    private RadioButton accountStatus;

    @FXML
    private ChoiceBox<AccessType> userType;

    @FXML
    public void cancel(ActionEvent event) throws IOException {

        ViewRenderer.getInstance().init(event, ViewRenderer.getInstance().getCurrentProxy());
    }

    public void makeChanges(ActionEvent event){


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
        }
    }

    public void setUserToPreload(String userToPreload)throws AccessProxyException{
        this.userToPreload = userToPreload;


        userName.setText(ViewRenderer.getInstance().getCurrentProxy().getUser(userToPreload).getUserName());
        userName.setDisable(true);
        password.setDisable(true);
        passwordConfirm.setDisable(true);
        accountStatus.setSelected(ViewRenderer.getInstance().getCurrentProxy().getUser(userToPreload).isEnabled());
        userType.setValue(ViewRenderer.getInstance().getCurrentProxy().getUser(userToPreload).getAccessType());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        AccessType[] types =  AccessType.values();


        ArrayList<AccessType> accessTypes = new ArrayList<>();

        for (int i = 0; i < types.length; i++) {
            accessTypes.add(types[i]);
        }

        ObservableList<AccessType> list = FXCollections.observableArrayList(accessTypes);

        userType.setItems(list);
    }
}
