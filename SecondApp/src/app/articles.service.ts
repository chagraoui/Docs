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




  constructor(private http : HttpClient) {
  }


  getArticles(){
    return this.http.get("http://localhost:7080/documentation/articles")

  }


  saveArticle(title){


this.article.idArticle=null;
this.article.path="/tmp"
this.article.title=title;

this.http.post("http://localhost:7080/documentation/articles/save",this.article)
  .subscribe(res => {
      console.log(res);
    },
    err => {
      console.log("Error occured");
    }
  );
  }


  getOneArticle(){
    return this.article;
  }


}
