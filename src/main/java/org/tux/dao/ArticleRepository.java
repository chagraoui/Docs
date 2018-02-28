package org.tux.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.tux.entites.Article;
@Repository
public interface ArticleRepository extends CrudRepository <Article,Integer>  {
	
	

}
