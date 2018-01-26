package org.tux.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tux.entites.Article;
@Repository
public interface ArticleRepository extends JpaRepository <Article,String>  {
	
	

}
