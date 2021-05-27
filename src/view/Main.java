package view;

import controllers.MainWindowsController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import mainengine.MainEngine;
import searchengine.DocumentIndexer;

public class Main extends Application{
	private MainEngine mainengine;
	
	public static void main(String[] args) {
        launch(args);  
    } 
	
	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("MainAppWindow.fxml"));
		MainWindowsController maincontroller = new MainWindowsController();
		loader.setController(maincontroller);
	
		Parent root = loader.load();
		
		mainengine = MainEngine.getMainEngineInstance();
		mainengine.initStoredArticles();
		mainengine.createIndexDocuments();
		
		
		// Main scene
		Image logo = new Image("/view/graphics/covid-19.png");
		
		Scene sce = new Scene(root, 1200, 600);
		stage.setTitle("Covid-19 Search Engine");
		stage.setScene(sce);
		stage.setResizable(false);
		stage.getIcons().add(logo);
		stage.show();
		
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			
			@Override
			public void handle(WindowEvent arg0) {
				DocumentIndexer indx = mainengine.getDocIndexer();
				try {
					indx.deleteAllIndexes();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	
	
}
