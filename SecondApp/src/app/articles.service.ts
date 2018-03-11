import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable()
export class ArticlesService {

  constructor(private http : HttpClient) {

  }


  getArticles(){
    return this.http.get("http://localhost:7080/documentation/articles")

  }
}
