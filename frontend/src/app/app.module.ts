import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CustomerdetailsComponent } from './customerdetails/customerdetails.component';
import { DepositComponent } from './deposit/deposit.component';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import {HttpClientModule} from '@angular/common/http';
import { CustomerService } from './customer.service';
import { MainComponent } from './main/main.component';
import { NewcustomerComponent } from './newcustomer/newcustomer.component';
import { LoanComponent } from './loan/loan.component';

@NgModule({
  declarations: [
    AppComponent,
    MainComponent, 
    NewcustomerComponent, 
    CustomerdetailsComponent,
    DepositComponent,
    LoanComponent
  ],
    imports: [
      BrowserModule, 
      FormsModule,
      AppRoutingModule,
      HttpClientModule
    ],
    exports: [
      RouterModule
    ],
    providers: [
      CustomerService
    ],
    bootstrap: [AppComponent]
  })
export class AppModule { }