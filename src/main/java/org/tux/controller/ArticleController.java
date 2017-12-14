package org.tux.controller;


import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.tux.dao.ArticleRepository;
import org.tux.entites.Article;

@RestController
@RequestMapping(value="/articles")
public class ArticleController {

	/**
	 * logger
	 */
	private Logger logger = Logger.getLogger(ArticleController.class);
	
	

	@Autowired
	ArticleRepository articleRepository;



	@RequestMapping(value = "", method = RequestMethod.GET)
	public Article  saveArticle(){
		
		logger.info("sav eArticle");
		Date date= new Date();
		Article article=new Article(1,"10", date, "/Tmp");
		articleRepository.save(article);
		return article;
	}
	
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<Article>  getAllArticle(){
		
		logger.info("getALLArticle");
		return articleRepository.findAll();
	}
	
}
