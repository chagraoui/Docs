import { Component, OnInit } from '@angular/core';
import {ArticlesService} from "../articles.service";

@Component({
  selector: 'app-articles',
  templateUrl: './articles.component.html',
  styleUrls: ['./articles.component.css']
})
export class ArticlesComponent implements OnInit {

  articles:any ;

  article={
    idArticle:0,
    title:"",
    path:"",
    date:1565353535
  };

  constructor(private articlesService : ArticlesService) {

  }

  ngOnInit() {
    this.articlesService.getArticles()
    .subscribe(
      data=> {
        this.articles=data;
      })

  }



  onAddArticle(data){

    this.articlesService.saveArticle(data.title)
      .subscribe(res => {

        this.articlesService.getArticles()
          .subscribe(data=> {
              this.articles=data;
            })

  })

    this.article.title=""
}
