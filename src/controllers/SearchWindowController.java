package controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import model.Article;

public class SearchWindowController implements Initializable{
	private ObservableList<Article> articleObservableList;
	private ArrayList<Article> foundArticles;
	

    @FXML
    private ListView<Article> listview;

    
    @FXML
    private TextArea articleTextArea;
    
    private int numOfShowingArticles;
    
    public SearchWindowController(ArrayList<Article> foundArticles) {
    	this.foundArticles = foundArticles;
    	
    	articleObservableList = FXCollections.observableArrayList();
    	
    	numOfShowingArticles = 0;
    	for(int i = numOfShowingArticles; i < numOfShowingArticles+10; i++)
    		articleObservableList.add(foundArticles.get(i));
    	
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		listview.setItems(articleObservableList);
		listview.setCellFactory(new Callback<ListView<Article>, ListCell<Article>>() {

			@Override
			public ListCell<Article> call(ListView<Article> arg0) {
				return new ArticleListViewCell();
			}
			

		});
	}
	
    @FXML
    void listviewOnMouseClick(MouseEvent arg0) {
    	Article art = listview.getSelectionModel().getSelectedItem();
    	articleTextArea.setWrapText(true);
    	articleTextArea.setText(art.getText());
    }
	
	public void setFoundArticles(ArrayList<Article> foundArticles) {
		this.foundArticles = foundArticles;
	}
	
	
    @FXML
    void ascendingSortDateOnAction(ActionEvent event) {
    	Collections.sort(foundArticles);
    	numOfShowingArticles = 0;
    	showTenArticles();
    }

    @FXML
    void descendingSortDateOnAction(ActionEvent event) {
    	Collections.sort(foundArticles, Collections.reverseOrder());
    	numOfShowingArticles = 0;
    	showTenArticles();
    }
    
    public void showTenArticles() {
    	articleObservableList.removeAll(articleObservableList);
    	for(int i = numOfShowingArticles; i < numOfShowingArticles+10; i++)
    		articleObservableList.add(foundArticles.get(i));
		
    	listview.setItems(articleObservableList);

    }
    

    @FXML
    void previousOnAction(ActionEvent event) {
    	if(numOfShowingArticles<10)
    		return;
    	else {
    		numOfShowingArticles-=10;
    		showTenArticles();
    	}
    }

    @FXML
    void nextOnAction(ActionEvent event) {
    	if(foundArticles.size()<numOfShowingArticles)
    		return;
    	else {
        	numOfShowingArticles+=10;
        	showTenArticles();
    	}
    		
    }
	


}
