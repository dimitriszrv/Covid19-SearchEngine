package mainengine;

import java.util.ArrayList;

import model.Article;

public interface IMainEngine {
	
	public ArrayList<Article> searchRelatedArticles(String searchText);
	
	public void initStoredArticles();
	
}
