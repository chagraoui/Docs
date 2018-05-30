package org.tux.controller;


import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.tux.dao.ArticleRepository;
import org.tux.entites.Article;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
@RequestMapping(value="/articles")
@Transactional
@Api(value = "article controller", description = "Operations for users management")
public class ArticleController {

	/**
	 * logger
	 */
	private Logger logger = Logger.getLogger(ArticleController.class);
	
	

	@Autowired
	ArticleRepository articleRepository;

    @ApiOperation(value = "get all articles", notes = "get articles list")
	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<Article>  getAllArticle(){
		
		logger.info("get ALL Articles");
		return (List<Article>) articleRepository.findAll();
	}

    @ApiOperation(value = "save Article", notes = "save Article method")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Article  saveArticle(@RequestBody  Article article){
		
		logger.info("save Article");
		articleRepository.save(article);
		return article;
	}

}
