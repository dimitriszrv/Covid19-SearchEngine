package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import mainengine.MainEngine;
import model.Article;

public class MainWindowsController implements Initializable{
	private ArrayList<Article> foundArticles;
	private ArrayList<String> searchHistory;
	
    @FXML
    private Label historyLabel;
    
	@FXML
	private TextField searchBar;
	
    @FXML
    private ImageView searchImage;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		searchHistory = new ArrayList<>();
	}
	
    @FXML
    void searchOnMouseClicked(MouseEvent event) throws IOException {
    	if(searchBar.getText().equals("")) {
    		System.out.println("Nothing to search");
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AlertWindow.fxml"));    	
    		AlertWindowController alertController = new AlertWindowController("Search Error",
    																		"Enter word to search",
    																		"\\src\\view\\graphics\\warning.png");
    		loader.setController(alertController);
    		Parent root = loader.load();
    	
    		Image logo = new Image("/view/graphics/covid-19.png");
    		
    		// Main scene
    		Stage stage = new Stage();
    		Scene sce = new Scene(root, 549, 160);
    		stage.setTitle("Warning message");
    		stage.getIcons().add(logo);
    		stage.setScene(sce);

    		//stage.setResizable(false);
    		stage.show();
    		
    	}
    	else {
    		System.out.printf("\n__ SEARCHING __\n{ %s }\n",searchBar.getText());
    		searchHistory.add(searchBar.getText());
    		updateHistory();
    		
    		long startTime = System.currentTimeMillis();
    		this.foundArticles = MainEngine.getMainEngineInstance().searchRelatedArticles(searchBar.getText());
    		long estimatedTime = System.currentTimeMillis() - startTime;
    		
    		System.out.println("Ellapsed time for searching : "+((estimatedTime/1000)%60)+" sec");
    		
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/SearchWindow.fxml"));    	
    		SearchWindowController searchCntrller = new SearchWindowController(foundArticles);
    		
    		
    		loader.setController(searchCntrller);
    		Parent root = loader.load();
    		
    		
    		
    		Image logo = new Image("/view/graphics/covid-19.png");
    		
    		// Main scene
    		Stage stage = new Stage();
    		//Scene sce = new Scene(root, 1300, 800);
    		stage.setTitle("Covid-19 Related Articles Results");
    		stage.getIcons().add(logo);
    		stage.setScene(new Scene(root));
    		//stage.setMaximized(true);
    		//stage.setResizable(false);
    		stage.show();
    	}
    
    }
	
	private void updateHistory() {
		String historyString = " ";
		for(String search : searchHistory) {
			historyString += search + "  ";
		}
		
		historyLabel.setText(historyString);
	}
    
}
