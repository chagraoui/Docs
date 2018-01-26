package org.tux.entites;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "article")
public class Article {

	@Id
	private Integer idArticle;
	private String title ;
	private Date date ;
	private String path ;
	
	public int getIdArticle() {
		return idArticle;
	}
	public void setIdArticle(int idArticle) {
		this.idArticle = idArticle;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}

	
	public Article(int i, String title, Date date, String path) {
		super();
		this.idArticle = i;
		this.title = title;
		this.date = date;
		this.path = path;
	}
	public Article() {
		super();
	}
	public Article(String title, Date date, String path) {
		super();
		this.title = title;
		this.date = date;
		this.path = path;
	}
	
	
	
	
	
	
}
