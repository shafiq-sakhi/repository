
package repository;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Repository extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        System.setProperty("prism.txt","t2k");
        System.setProperty("prism.lcdtext", "false");
        Parent root = FXMLLoader.load(getClass().getResource("SimpleAlert.fxml"));
        
        Scene scene = new Scene(root,Color.TRANSPARENT);
        
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
    
}
