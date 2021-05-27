package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Article implements Comparable<Article>{
	private String author;
	private String date;
	private String domain;
	private String title;
	private String url;
	private String topicArea;
	private String text;
	
	
	public Article() {}
	
	public Article(String title, String author, String text, String date) {
		this.title = title;
		this.author = author;
		this.text = text;
		this.date = date;
	}
	
	public Article(String author, String date, String domain, String title, String url, String topicArea, String text) {
		super();
		this.author = author;
		this.date = date;
		this.domain = domain;
		this.title = title;
		this.url = url;
		this.topicArea = topicArea;
		this.text = text;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthors(String author) {
		this.author = author;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	
	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTopicArea() {
		return topicArea;
	}

	public void setTopicArea(String topicArea) {
		this.topicArea = topicArea;
	}

	public Date createDate() throws Exception {
		Date dt = new SimpleDateFormat("yyyy-MM-dd").parse(this.date);
		return dt;
	}
	
	public boolean validate()
	{
		return (author != null && author.length() > 0) 
	      && (title != null && title.length() > 0)
	      && (date != null && date.length() > 0)
	      && (text != null && text.length() > 0);
	}

	@Override
	public int compareTo(Article article2) {
		Date dt1 = null;
		try {
			dt1 = this.createDate();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Date dt2 = null;
		try {
			dt2 = article2.createDate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dt1.compareTo(dt2);
	}
	
}
