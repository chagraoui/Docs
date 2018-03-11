import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http'
@Injectable()
export class GaleryService {

  constructor(private http : HttpClient) { }


  search(motCle : String){
    // console.log(data.cle)
    return this.http.get("https://pixabay.com/api/?key=8317604-47c7cef044ad4af9c003df213&q="+motCle+"&per_page=5&page=1")

  }

}
