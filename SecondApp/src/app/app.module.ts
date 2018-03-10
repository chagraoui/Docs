import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import { AboutComponent } from './about/about.component';


import {FormsModule} from "@angular/forms";
import {AboutService} from '../about.service';
import {RouterModule, Routes} from "@angular/router";
import { ContactsComponent } from './contacts/contacts.component';

const routes: Routes = [
  {path:'about',component: AboutComponent },
  {path: 'contacts', component: ContactsComponent},
  {path: '', redirectTo: '/about',pathMatch: 'full'}
];


@NgModule({
  declarations: [
    AppComponent,
    AboutComponent,
    ContactsComponent
  ],
  imports: [
    BrowserModule, FormsModule,RouterModule.forRoot(routes)
  ],
  providers: [AboutService],
  bootstrap: [AppComponent]
})
export class AppModule { }
