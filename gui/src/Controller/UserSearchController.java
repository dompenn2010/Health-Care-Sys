package Controller;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import edu.utc.mhcpms.Access.AccessProxyException;
import edu.utc.mhcpms.IPatient;
import edu.utc.mhcpms.IUser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

/**
 * Created by Dom on 4/18/16.
 */
public class UserSearchController implements Initializable {

    @FXML
    private ChoiceBox userList;

    @FXML
    public void cancel(ActionEvent event) throws IOException {

        ViewRenderer.getInstance().init(event, ViewRenderer.getInstance().getCurrentProxy());

    }

    @FXML
    public void search(ActionEvent event) throws AccessProxyException, IOException {

        ViewRenderer.getInstance().userManagement(event, userList.getValue().toString());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            // Grab all IUsers
            Collection<IUser> users = ViewRenderer.getInstance().getCurrentProxy().getUsers();

            // New String ArrayList
            ArrayList<String> userNames = new ArrayList<>();

            // Put the name of each IUser into the String ArrayList
            users.forEach(user-> userNames.add(user.getUserName()));

            // Make that String ArrayList of names the Observable List
            ObservableList<String> list = FXCollections.observableArrayList(userNames);

            userList.setItems(list);

        } catch (AccessProxyException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "...How did you get here?");
            alert.show();
        }
    }
}
