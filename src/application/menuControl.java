package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class menuControl {

    @FXML
    private Button btn_single;

    @FXML
    private Button btn_stats;

    @FXML
    private Button btn_multi;

    @FXML
    void showStats(ActionEvent event) {

    }

    @FXML
    void startMultiGame(ActionEvent event) {
    	
    }


    @FXML
    private void startSingleGame(ActionEvent event) throws IOException {
        new gameWindow();        
    }
}
