import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-newcustomer',
  templateUrl: './newcustomer.component.html',
  styleUrls: ['./newcustomer.component.css']
})
export class NewcustomerComponent implements OnInit {

  newCustomer = {'address':'',"name":'',"contact": '','employment':'', 'age': ''};

  constructor(private http: HttpClient, private router: Router) { }

  ngOnInit(): void {
  }

  saveCustomer(){
    const url=environment.baseUrl+ 'customer-service/saveCustomer';
    this.http.post(url, this.newCustomer)
      .subscribe(data => {
        console.log("PUT Request is successful", data); 
        this.router.navigate(['/main']);
      },
        error => {
          console.log('Error', error);
        });

  }
}
