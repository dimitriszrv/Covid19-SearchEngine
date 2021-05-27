package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ArticleManager {
	private ArrayList<Article> processedArticles;
	private String dirPath;
	private static ArticleManager articleManager;
	
	public ArticleManager() {
		processedArticles = new ArrayList<>();
		dirPath = "\\covid19_articles";
	}
	
	public static ArticleManager getArticleManagerInstance() {
		if (articleManager == null)
			articleManager = new ArticleManager();
		return articleManager;
	}
	
	
	public ArrayList<Article> convertTxtIntoArticles() throws Exception {
		String absoluteDirPath = System.getProperty("user.dir").concat(dirPath);
		
		File dirFolder = new File(absoluteDirPath);
	    File filesList[] = dirFolder.listFiles();
	    
	    for(File fl : filesList) {
	    	if(fl.toString().endsWith(".txt")) {
	    		File file = new File(fl.toString());
	    		Article article = parseTxtFile(file);
	    		
	    		processedArticles.add(article);
	    	}
	    }
	    System.out.println("Total txt file processed into Article objects : "+processedArticles.size());
	    
	    return processedArticles;
	}
	
	
	private Article parseTxtFile(File txtFile) throws Exception {
	    BufferedReader reader = new BufferedReader(new FileReader(txtFile));
	    
	    ArrayList<String> txtData = new ArrayList<String>();
	    String line;
	    while((line=reader.readLine())!=null) {
	    	txtData.add(line);
	    }
	    reader.close();
	    
	    String author = txtData.get(0);
		String date = txtData.get(1);
		String domain = txtData.get(2);
		String title = txtData.get(3);
		String url = txtData.get(4);
		String topicArea = txtData.get(5);
		String content = txtData.get(6);
		   
		Article article = new Article(author,date,domain,title,url,topicArea,content);
		
		return article;
	}
	
	
	public String getDirPath() {
		return dirPath;
	}
	
	public void setDirPath(String dirPath) {
		this.dirPath = dirPath;
	}
	
	
}
