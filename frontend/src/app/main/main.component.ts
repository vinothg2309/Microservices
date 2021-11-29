import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';
import { CustomerService } from '../customer.service';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  customerList = [];
  searchString = '';

  constructor(private http: HttpClient, private customerService: CustomerService,
    private router: Router) { }
  ngOnInit(): void {
    this.search();
  }
  search() {
    let url = environment.baseUrl + "customer-service";
    if (this.searchString) {
      url = url + '/getCustomer/' + this.searchString;
    }
    else {
      url = url + '/fetchAllCustomers';
    }
    this.customerList = [];
    this.http.get<any>(url)
      .subscribe(
        data => {
          console.log('GET Request is successful', data);
          if (data) {
            if (this.searchString) {
              this.customerList.push(data);
            } else {
              this.customerList = data;
            }
          }
        },
        error => {
          console.log('Error', error);
        });
      }

      selectedCustomer(customer){
        this.customerService.setSelectedCustomerDetail(customer);
        this.router.navigateByUrl('/customerdetail/' + customer.id);
      }

  }
