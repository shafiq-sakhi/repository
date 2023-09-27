/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class SimpleAlertController implements Initializable {


    @FXML
    private AnchorPane root;
    @FXML
    private Text lbl_message;
    CallBackInterface callBack;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    public void setOnButtonClicked(CallBackInterface callBack){
        this.callBack = callBack;
    }
    public void closeAlert(){
        callBack.closeDialog();
    }
    public void setMessage(String message) {
        lbl_message.setText(message);
    }
    

}
