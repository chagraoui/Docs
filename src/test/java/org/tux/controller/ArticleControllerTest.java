package org.tux.controller;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.tux.config.PersistenceConfig;
import org.tux.config.WebInit;
import org.tux.dao.ArticleRepository;
import org.tux.entites.Article;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebInit.class, PersistenceConfig.class})
@WebAppConfiguration
public class ArticleControllerTest {
	
	
	@Mock
	ArticleController articleController;
	
	
	
	@Autowired	
	ArticleRepository articleRepository;
	
	@Test
	public void  saveArticle(){
		Date date= new Date();
		Article article=new Article(1,"10", date, "Tmp");
		articleRepository.save(article);
		assertEquals("insertion OK", article.getIdArticle(),1);
		
	}

}
