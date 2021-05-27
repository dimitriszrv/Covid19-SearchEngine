package controllers;

import java.io.File;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.TextAlignment;
import model.Article;

public class ArticleListViewCell extends ListCell<Article>{
    @FXML
    private Label titleLabel;

    @FXML
    private Label authorTitle;

    @FXML
    private Pane pane;
    
    @FXML
    private Label topicAreaLabel;

    @FXML
    private Label dateLabel;
    
    @FXML
    private ImageView topicAreaIcon;
    
    private FXMLLoader mLLoader;

    @Override
    protected void updateItem(Article article, boolean empty) {
        super.updateItem(article, empty);

        if(empty || article == null) {
            setText(null);
            setGraphic(null);

        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("/view/ListCell.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }            
            titleLabel.setWrapText(true);
            titleLabel.setTextAlignment(TextAlignment.JUSTIFY);
            titleLabel.setText(article.getTitle());
            
            authorTitle.setWrapText(true);
            authorTitle.setTextAlignment(TextAlignment.JUSTIFY);
            authorTitle.setText(article.getAuthor());

            dateLabel.setText(article.getDate());
            topicAreaLabel.setText(article.getTopicArea());
            
            
            String workingDirectory = System.getProperty("user.dir");
        	String absolutePath = workingDirectory + "\\src\\view\\graphics\\"+ article.getTopicArea()+".png";
        	
        	File file = new File(absolutePath);
            Image image = new Image(file.toURI().toString());
            topicAreaIcon.setImage(image);
            
            
            setText(null);
            setGraphic(pane);
        }

    }
}
