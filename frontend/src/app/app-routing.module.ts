import { CommonModule } from '@angular/common';
import { NgModule } from "@angular/core";
import { RouterModule, Routes } from '@angular/router';
import { MainComponent } from './main/main.component';
import { CustomerdetailsComponent } from './customerdetails/customerdetails.component';
import { NewcustomerComponent } from './newcustomer/newcustomer.component';

const appRoutes: Routes = [
  { path: 'main', component: MainComponent },
  { path: 'newcustomer', component: NewcustomerComponent },
  { path: 'customerdetail/:id', component: CustomerdetailsComponent},
  { path: '', redirectTo: '/main', pathMatch: "full" }]

@NgModule(
  {
    imports: [
      CommonModule, 
      RouterModule.forRoot( appRoutes, { enableTracing: true })
    ],
  exports: [RouterModule]
  })
  export class AppRoutingModule {

  }
