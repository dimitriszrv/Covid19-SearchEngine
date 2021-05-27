package testing;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import model.Article;
import model.ArticleManager;

public class ArticleManagerTest {

	private ArticleManager articleManager;
	
	
	@Before
	public void setUp() throws Exception {
		articleManager = ArticleManager.getArticleManagerInstance();
		articleManager.setDirPath("\\covid19_articles");
		System.out.println(articleManager.getDirPath());
	}


	@Test
	public void testConvertTxtIntoArticles() throws Exception {
		ArrayList<Article> r = articleManager.convertTxtIntoArticles();
	}

}
