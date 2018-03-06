import { Component, OnInit } from '@angular/core';
import {AboutService} from '../../about.service';

@Component({
  selector: 'app-about',
  templateUrl: './about.component.html',
  styleUrls: ['./about.component.css']
})
export class AboutComponent implements OnInit {



	commentaire={date:null,message:""};
	infos:{
	  nom:string,
    email:string,
    tel:number
  };
	comments=[];

  constructor(private aboutService:AboutService) {
  this.infos=this.aboutService.getInfo();
  this.comments=this.aboutService.getAllComments();
   }

  ngOnInit() {
  }

onAddCommentaire(c){
this.aboutService.addComment(c);
this.commentaire.message="";
}
}
