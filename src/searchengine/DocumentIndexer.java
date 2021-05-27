package searchengine;

import java.nio.file.Paths;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.document.Field;

import model.Article;

public class DocumentIndexer {
	   private String indexDirectory;
	   
	   public DocumentIndexer(String indexDir)
	   {
	      indexDirectory = indexDir;
	      IOUtils.makeSureDirectoriesExist(indexDir);
	   }
	   
	   public String getIndexDir() {
		   return this.indexDirectory;
	   }
	   
	   public void deleteAllIndexes() throws Exception
	   {
	      IndexWriter writer = null;
	      try
	      {
	         Directory indexWriteToDir = 
	               FSDirectory.open(Paths.get(indexDirectory));
	         
	         writer = new IndexWriter(indexWriteToDir, new IndexWriterConfig());
	         writer.deleteAll();
	         writer.flush();
	         writer.commit();
	      }
	      finally
	      {
	         if (writer != null)
	         {
	            writer.close();
	         }
	      }
	   }
	   
	   public void deleteDocument(String docId) throws Exception
	   {
	      IndexWriter writer = null;
	      try
	      {
	         Directory indexWriteToDir = 
	               FSDirectory.open(Paths.get(indexDirectory));
	         
	         writer = new IndexWriter(indexWriteToDir, new IndexWriterConfig());
	         writer.deleteDocuments(new Term("DOCID", docId));
	         writer.flush();
	         writer.commit();
	      }
	      finally
	      {
	         if (writer != null)
	         {
	            writer.close();
	         }
	      }
	   }
	   
	   public void indexDocument(Document docToAdd) throws Exception
	   {
	      IndexWriter writer = null;
	      try
	      {
	         Directory indexWriteToDir = 
	               FSDirectory.open(Paths.get(indexDirectory));
	         
	         writer = new IndexWriter(indexWriteToDir, new IndexWriterConfig());
	         writer.addDocument(docToAdd);
	         writer.flush();
	         writer.commit();
	      }
	      finally
	      {
	         if (writer != null)
	         {
	            writer.close();
	         }
	      }
	   }
	   
	   public void updateDocumentIndex(String docId, Article article)
	      throws Exception
	   {
	      Document docidxToUpdate = createIndexDocument(article);
	      IndexWriter writer = null;
	      try
	      {
	         Directory indexWriteToDir = 
	               FSDirectory.open(Paths.get(indexDirectory));
	         
	         writer = new IndexWriter(indexWriteToDir, new IndexWriterConfig());
	         writer.updateDocument(new Term("DOCID",  docId), docidxToUpdate);
	         writer.flush();
	         writer.commit();
	      }
	      finally
	      {
	         if (writer != null)
	         {
	            writer.close();
	         }
	      }
	   }
	   
	   public Document createIndexDocument(Article articleToAdd)
	   {
	      Document retVal = new Document();
	      
	      IndexableField authorNameField = new TextField("AUTHOR", articleToAdd.getAuthor(), Field.Store.YES);
	      IndexableField titleField = new TextField("TITLE", articleToAdd.getTitle(), Field.Store.YES);
	      IndexableField textField = new TextField("TEXT", articleToAdd.getText(), Field.Store.YES);
	      IndexableField dateField = new TextField("DATE", articleToAdd.getDate(), Field.Store.YES);
	      IndexableField domainField = new TextField("DOMAIN", articleToAdd.getDomain(), Field.Store.YES);
	      IndexableField urlField = new TextField("URL", articleToAdd.getUrl(), Field.Store.YES);
	      IndexableField topicAreaField = new TextField("TOPIC_AREA", articleToAdd.getTopicArea(), Field.Store.YES);

	      
	      
	      retVal.add(authorNameField);
	      retVal.add(titleField);
	      retVal.add(textField);
	      retVal.add(dateField);
	      retVal.add(domainField);
	      retVal.add(urlField);
	      retVal.add(topicAreaField);

	      
	      return retVal;
	   }
	   
	   
}
