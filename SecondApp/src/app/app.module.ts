import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import { AboutComponent } from './about/about.component';


import {FormsModule} from "@angular/forms";
import {AboutService} from '../about.service';
@NgModule({
  declarations: [
    AppComponent,
    AboutComponent
  ],
  imports: [
    BrowserModule, FormsModule
  ],
  providers: [AboutService],
  bootstrap: [AppComponent]
})
export class AppModule { }
