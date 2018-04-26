package application;
	
import java.io.IOException;

import application.Main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;


public class Main extends Application {

    private Stage primaryStage;
    private FlowPane rootLayout;

    private menuControl menuC;
    private mainControl mainC;
    
    
    @Override
    public void start(Stage primaryStage) 
    {
	    this.primaryStage = primaryStage;
	    this.primaryStage.setTitle("Schiffe versenken");
	    try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("MainMenu.fxml"));
            rootLayout = (FlowPane) loader.load();
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
    
	public static void main(String[] args) {
		launch(args);
	}
}
