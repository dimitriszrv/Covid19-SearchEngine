package searchengine;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.QueryBuilder;

import model.Article;

public class Searcher {
	private String indexDirectory;
	private TopDocs allFound;  
	private IndexSearcher searcher;
	
	public Searcher(String indexDir)
	{
		indexDirectory = indexDir;
	}
	   
	public String getDir() {
		return indexDirectory;
	}
	
	public List<Article> searchForDocument(String searchVal)
	{
		List<Article> retVal = new ArrayList<>();
	      
	    try
	    {
	    	Directory dirOfIndexes = 
	    			FSDirectory.open(Paths.get(indexDirectory));
	         
	        searcher = new IndexSearcher(DirectoryReader.open(dirOfIndexes));
	         
	        QueryBuilder bldr = new QueryBuilder(new StandardAnalyzer());
	        Query q1 = bldr.createPhraseQuery("TITLE", searchVal);
	        Query q2 = bldr.createPhraseQuery("TEXT", searchVal);
	         
	        BooleanQuery.Builder chainQryBldr = new BooleanQuery.Builder();
	        chainQryBldr.add(q1, Occur.SHOULD);
	        chainQryBldr.add(q2, Occur.SHOULD);
	         
	        BooleanQuery finalQry = chainQryBldr.build();
	         	        
	        allFound = searcher.search(finalQry, 100);
	        if (allFound.scoreDocs != null)
	        {
	        	for (ScoreDoc doc : allFound.scoreDocs)
	            {
	        		System.out.println("Score: " + doc.score);
	               
	        		int docidx = doc.doc;
	        		Document docRetrieved = searcher.doc(docidx);
	        		if (docRetrieved != null)
	        		{
	        			Article docToAdd = new Article();

	        			IndexableField field = docRetrieved.getField("TITLE");
	        			if (field != null)
	        			{
	        				docToAdd.setTitle(field.stringValue());
	        			}

	        			field = docRetrieved.getField("TEXT");
	        			if (field != null)
	        			{
	        				docToAdd.setText(field.stringValue());
	        			}
	        			
	        			field = docRetrieved.getField("AUTHOR");
	        			if (field != null)
	        			{
	        				docToAdd.setAuthors(field.stringValue());
	        			}
	        			
	        			field = docRetrieved.getField("DATE");
	        			if (field != null)
	        			{
	        				docToAdd.setDate(field.stringValue());
	        			}
	        			
	        			field = docRetrieved.getField("TOPIC_AREA");
	        			if (field != null)
	        			{
	        				docToAdd.setTopicArea(field.stringValue());
	        			}
	        			
	        			retVal.add(docToAdd);
	               
	        		}
	            }   
	        }
	    }
	    catch (Exception ex)
	    {
	    	ex.printStackTrace();
	    }
	      
	    return retVal;
	}
	
}

