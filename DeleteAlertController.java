
package repository;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;


public class DeleteAlertController implements Initializable {

    
    DeleteCallBack callBack;
    @FXML
    private AnchorPane root;
    @FXML
    private Text lbl_deleteMessage;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    public void setMessage(String message){
        lbl_deleteMessage.setText(message);
    }
    public void setCallBack(DeleteCallBack callBack){
        this.callBack = callBack;
    }
    @FXML
    public void clickYes(){
        callBack.onDeleteAlertResult();
    }
    @FXML
    public void clickNo(){
        callBack.closeOpenedDialog();
    }
}
