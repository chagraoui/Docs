import { Component, OnInit } from '@angular/core';
import {HttpClient} from '@angular/common/http'
// import "rxjs/add/operator/map"
import {GaleryService} from '../galery.service'
@Component({
  selector: 'app-gallery',
  templateUrl: './gallery.component.html',
  styleUrls: ['./gallery.component.css']
})
export class GalleryComponent implements OnInit {
  page : any;
  constructor(private galeryservice : GaleryService) { }

  ngOnInit() {
  }

  onSearch(data){
    this.galeryservice.search(data.cle)
      .subscribe(data2=>{
        // console.log(data2);
        this.page=data2;

    console.log(this.page)
  })
  }
}
