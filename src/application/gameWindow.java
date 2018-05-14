package application;

import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class gameWindow extends Stage {
    Stage stage;
    mainControl game_Cont;
    
    public gameWindow() throws IOException{
        stage = this;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GameFrame.fxml"));
        
        AnchorPane root = (AnchorPane) loader.load();
        game_Cont = (mainControl) loader.getController();
        game_Cont.initGame();
        Scene scene = new Scene(root);

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                try {
                switch (event.getCode()) {
                    case UP: game_Cont.moveShipUp(); break;   
                    case DOWN:  game_Cont.moveShipDown(); break;
                    case LEFT:  game_Cont.moveShipLeft(); break;
                    case RIGHT: game_Cont.moveShipRight(); break;
                }
                //System.out.println("CurrShip.l�nge: "+game_Cont.getCurrShip().getLength());

	    		} catch (FileNotFoundException e) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();
	    		}
            };
        });

        stage.setScene(scene);
        stage.show();
    }
        
    /*
	@Override
	public void keyPressed(KeyEvent arg0) {
		System.out.println(arg0.getKeyCode());
		if ((arg0.getKeyCode()==KeyEvent.VK_UP)||(arg0.getKeyChar()==KeyEvent.VK_KP_UP))
		{
			game_Cont.moveShipUp();
			//setValues();
		}
		
	}*/
}
