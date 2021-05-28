package view;

import java.io.File;

import controllers.MainWindowsController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import mainengine.MainEngine;

public class Main extends Application{
	private MainEngine mainengine;
	
	public static void main(String[] args) {
        launch(args);  
    } 
	
	@Override
	public void start(Stage stage) throws Exception {
		String workingDirectory = System.getProperty("user.dir");
		String indexesDirPath = workingDirectory.concat("\\indexes");
		File indexesDir = new File(indexesDirPath);
		
		deleteDirContents(indexesDir);
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("MainAppWindow.fxml"));
		MainWindowsController maincontroller = new MainWindowsController();
		loader.setController(maincontroller);
	
		Parent root = loader.load();
		
		// Main scene
		Image logo = new Image("/view/graphics/covid-19.png");
		
		Scene sce = new Scene(root, 1200, 600);
		stage.setTitle("Covid-19 Search Engine");
		stage.setScene(sce);
		stage.setResizable(false);
		stage.getIcons().add(logo);
		stage.show();
		
		mainengine = MainEngine.getMainEngineInstance();
		mainengine.initStoredArticles();

		long startTime = System.currentTimeMillis();
		mainengine.createIndexDocuments();
		long estimatedTime = System.currentTimeMillis() - startTime;
	
		System.out.printf("Ellapsed time for indexing Docs : " +((estimatedTime/1000)%60)+" sec\n");
		
	}
	
	
	private void deleteDirContents(File folder) {
		File[] files = folder.listFiles();
	    if(files!=null) { //some JVMs return null for empty dirs
	        for(File f: files) {
	            if(f.isDirectory()) {
	                deleteDirContents(f);
	            } else {
	                f.delete();
	            }
	        }
	    }
	}
	
}
