package mainengine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.document.Document;

import model.Article;
import model.ArticleManager;
import searchengine.DocumentIndexer;
import searchengine.Searcher;

public class MainEngine implements IMainEngine{
	
	private static MainEngine mainengine;
	private ArticleManager articleManager;
	private ArrayList<Article> articleList;
	private ArrayList<Document> indexDocuments;
	private DocumentIndexer docindexer;
	private String absoluteIndexesDirPath ;
	private Searcher searcher;
	
	public MainEngine() {
		absoluteIndexesDirPath = System.getProperty("user.dir").concat("\\indexes");
		
		articleManager = ArticleManager.getArticleManagerInstance();
		docindexer = new DocumentIndexer(absoluteIndexesDirPath);
	}
	
	/** Singleton Pattern.
	 * 	Public static method for getting the MainEngine instance.
	 */
	public static MainEngine getMainEngineInstance() {
		if (mainengine == null)
			mainengine = new MainEngine();
		return mainengine;
	}
 
	
	@Override
	public ArrayList<Article> searchRelatedArticles(String searchText) {
		List<Article> foundArticles;
		
		searcher = new Searcher(absoluteIndexesDirPath);
		foundArticles = searcher.searchForDocument(searchText);
		System.out.printf("*** Found %d articles related to : %s\n", foundArticles.size(), searchText);
		
		return (ArrayList<Article>) foundArticles;
	}

	@Override
	public void initStoredArticles() {
		try {
			this.articleList = articleManager.convertTxtIntoArticles();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Document> createIndexDocuments() {
		ArrayList<Document> docs = new ArrayList<>();
		
		for(Article art : this.articleList) {
			Document doc = docindexer.createIndexDocument(art);
			try {
				docindexer.indexDocument(doc);
			} catch (Exception e) {
				e.printStackTrace();
			}
			docs.add(doc);
		}
		
		System.out.printf("_Indexing finished_\nTotal %d Document objects indexed\n", docs.size());
		return docs;
	}
	
	
	
	public ArrayList<Article> getArticles(){
		return articleList;
	}
	
	public DocumentIndexer getDocIndexer() {
		return docindexer;
	}
	
	

}
