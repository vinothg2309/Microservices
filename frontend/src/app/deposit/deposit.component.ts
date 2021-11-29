import { HttpClient } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-deposit',
  templateUrl: './deposit.component.html',
  styleUrls: ['./deposit.component.css']
})
export class DepositComponent implements OnInit {

  @Input() customerId;
  instanceId;
  instancePort;
  newDeposit = {
    'accountNumber': '', 'amount': '', 'customer': '', 'instanceId': '', 'instancePort': '', 'customerNumber': ''
  };
  deposits = [];
  constructor(private http: HttpClient) { }
  ngOnInit(): void {
    this.fetchDeposit();
  }

  fetchDeposit() {
    const url = environment.baseUrl + 'deposit-service/fetchDeposits/' + this.customerId;
    this.http.get<any>(url).subscribe(
      data => {
        console.log("GET Request is successful", data);
        if (data) {
          this.deposits = data.deposits;
          this.instanceId = data.instanceId;
          this.instancePort = data.instancePort;
        }
      }, error => {
        console.log('Error', error);
      });

  }

  saveDeposit() {
    const url = environment.baseUrl + 'deposit-service/saveDeposit';
    this.newDeposit.customerNumber = this.customerId;
    this.http.post(url, this.newDeposit)
      .subscribe(
        data => {
          console.log('POST Request is successful' , data);
          this.fetchDeposit();
        }, error => {
        console.log('Error', error);
      });
  }
}
