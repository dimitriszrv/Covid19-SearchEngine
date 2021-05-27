package testing;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.document.Document;
import org.junit.Before;
import org.junit.Test;

import model.Article;
import searchengine.DocumentIndexer;
import searchengine.Searcher;

public class SearcherTest {

	private Searcher searcher;
	private String path;
	private DocumentIndexer indexer;
	private Article art;
	private Document doc;
	
	@Before
	public void setUp() throws Exception {
		path = "C:\\Users\\vagge\\Desktop\\Covid19_SearchEngine\\indexes";

		indexer = new DocumentIndexer(path);
		art = new Article("Covid", "George Ali", "Covid research","20/10/22");
		doc = new Document();
		doc = indexer.createIndexDocument(art);

		indexer.indexDocument(doc);

		searcher = new Searcher(path);
	}

	@Test
	public void testSearcher() {
		assertNotNull(searcher);
		assertEquals(searcher.getDir(), path);
	}

	@Test
	public void testSearchForDocument() {
		String strToSearch = "covid";
		List<Article> foundArt ;
		
		System.out.println("Searching for : "+strToSearch);
		
		foundArt = searcher.searchForDocument(strToSearch);
		assertNotNull(foundArt);
		assertEquals(1, foundArt.size());
		if (foundArt != null)
	      {
	         for (Article doc : foundArt)
	         {
	            System.out.println("------------------------------");
	            System.out.println("Found document...");
	            System.out.println("Title: " + doc.getTitle());
	            System.out.println("Author: " + doc.getAuthor());
	            System.out.println("Content: " + doc.getText());
	            System.out.println("------------------------------");
	         }
	      }
	}
	

}
