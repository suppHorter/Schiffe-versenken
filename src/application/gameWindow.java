package application;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class gameWindow extends Stage{
    Stage stage;
    public gameWindow() throws IOException{
        stage = this;
         Parent root = FXMLLoader.load(getClass().getResource("GameFrame.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
}
