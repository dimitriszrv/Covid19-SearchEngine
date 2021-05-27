package controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Pagination;
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
    private Pagination pageSelectionBtn;
    
    
    @FXML
    private TextArea articleTextArea;
    
    public SearchWindowController(ArrayList<Article> foundArticles) {
    	this.foundArticles = foundArticles;
    	
    	articleObservableList = FXCollections.observableArrayList();
    	
    	for(Article art : this.foundArticles)
    		articleObservableList.add(art);
    

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
    void dateSortingOnAction(ActionEvent event) {
    	
    	SortedList<Article> sortedList = new SortedList(foundArticles.u);
        listview.setItems(sortedList);
    }
	


}
