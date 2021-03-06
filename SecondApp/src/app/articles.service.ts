import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable()
export class ArticlesService {

  article={
    idArticle:0,
    title:"",
    path:"",
    date:1565353535

  };



  articles:any ;
  constructor(private http : HttpClient) {
  }


  getArticles(){
    return this.http.get("http://localhost:7080/documentation/articles")

  }


  saveArticle(title){


this.article.idArticle=null;
this.article.path="/tmp"
this.article.title=title;

return this.http.post("http://localhost:7080/documentation/articles/save",this.article)
  }


  getOneArticle(){
    return this.article;
  }



  getALLArticles(){
  return this.articles;
  }



  postdata(){
  return  this.http.post("http://localhost:7080/documentation/articles/save",this.article)
  }


}
