package testing;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;

import org.apache.lucene.document.Document;
import org.junit.Before;
import org.junit.Test;

import model.Article;
import searchengine.DocumentIndexer;

public class DocumentIndexerTest {
	private DocumentIndexer indexer;
	private Article art;
	private Document doc;
	private File tempFile;
	private String path;

	
	@Before
	public void setUp() throws Exception {
		path = "C:\\Users\\vagge\\Desktop\\Covid19_SearchEngine\\indexes";
		indexer = new DocumentIndexer(path);
		art = new Article("Covid", "George Ali", "Covid research","20/10/22");
		doc = new Document();
		assertEquals(indexer.getIndexDir(), path);
	}

	@Test
	public void testIndexDocument() throws Exception {
		indexer.indexDocument(doc);
		
		tempFile = new File(path);
		assertNotNull(tempFile);

	}


	
	@Test
	public void testCreateIndexDocument() {
		doc = indexer.createIndexDocument(art);
		
		assertNotNull(doc);
		System.out.println(doc.getFields());
		System.out.println(doc.toString());
	}
	

}
