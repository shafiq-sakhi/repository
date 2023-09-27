
package repository;

import com.jfoenix.controls.JFXDialog;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class LoginScreenController implements Initializable {

    Connection c;
    PreparedStatement ps;
    ResultSet rs;

    @FXML
    private TextField txt_userName;
    @FXML
    private TextField txt_password;
    @FXML
    private StackPane mainStack;
    private ContextMenu userNameCM = new ContextMenu();

 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setPredication();
    }

    @FXML
    private void login(ActionEvent event) {
        if (isValidateUser()) {
            String user = txt_userName.getText();
            String pass = txt_password.getText();
            ObservableList<User> users = FXCollections.observableArrayList();
            c = DB_Helper.getConnection();
            try {
                ps = c.prepareStatement("SELECT name,password FROM users");
                rs = ps.executeQuery();

                while (rs.next()) {
                    users.add(new User(0, rs.getString(1), rs.getString(2)));
                }

            } catch (SQLException ex) {
                Logger.getLogger(LoginScreenController.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (users.stream().anyMatch(ru -> ru.getName().equals(user))) {
                if (users.stream().filter(u -> u.getName().equals(user)).findFirst().get().getPass().equals(pass)) {
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                        Scene scene = new Scene(root);
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.show();
                        mainStack.getScene().getWindow().hide();
                    } catch (IOException ex) {
                        Logger.getLogger(LoginScreenController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    showAlertForNewUserValidation("رمز ورودی اشتباه است");
                }

            } else {
                showAlertForNewUserValidation("نام کاربری نادرست است");
            }

        }
    }

    public boolean isValidateUser() {
        if (txt_userName.getText().equals("")) {
            showAlertForNewUserValidation("لطفا نام را وارد کنید");
            return false;
        }
        if (txt_password.getText().equals("")) {
            showAlertForNewUserValidation("لطفا رمز عبور را وارد کنید");
            return false;
        }

        return true;
    }

    public void showAlertForNewUserValidation(String message) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SimpleAlert.fxml"));
            AnchorPane alert = (AnchorPane) loader.load();
            SimpleAlertController controller = loader.getController();
            controller.setMessage(message);
            JFXDialog dialog = new JFXDialog(mainStack, alert, JFXDialog.DialogTransition.TOP);

            controller.setOnButtonClicked(new CallBackInterface() {
                @Override
                public void closeDialog() {
                    dialog.close();
                }
            });
            dialog.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setPredication() {
        Set<String> userNames = new TreeSet<>();
        c = DB_Helper.getConnection();
        try {
            ps = c.prepareStatement("SELECT name FROM users");
            rs = ps.executeQuery();
            while (rs.next()) {
                userNames.add(rs.getString(1));
            }

            SmartTextField.bindAutoCompletion(txt_userName, userNameCM, userNames, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
