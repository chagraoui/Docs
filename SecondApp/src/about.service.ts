import {Injectable} from "@angular/core"


@Injectable()
export class AboutService {

info={
	nom:'mehdi',
	email:'mehdi.chagraoui@gmail.com',
	tel:53608534
	}

	comments=[
	{date:new Date(),message:"A"},
	{date:new Date(),message:"B"},
	{date:new Date(),message:"C"},
	];

	 commentaire={date:null,message:""};

addComment(c){
c.date=new Date();
this.comments.push(c);
this.commentaire.message="";
//this.comments=this.aboutService.getAllComments;

}

getAllComments(){
return this.comments;
}


getInfo() {
return this.info;
}

}
