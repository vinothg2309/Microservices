import { HttpClient } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-loan',
  templateUrl: './loan.component.html',
  styleUrls: ['./loan.component.css']
})
export class LoanComponent implements OnInit {

  @Input()
  customerId;
  loans = [];
  mortgages = [];
  loanInstanceId;
  loanInstancePort;
  mortgageInstanceId;
  mortgageInstancePort;
  newLoan = {
    'type': '', 'amount': '', 'tenure': '',
    'interest': '', 'customerNumber': ''
  };
  newMortgage = {
    'address': '', 'worth': '', 'loan': '',
    'emi': '', 'tenure': '', 'customerNumber': ''
  }
  constructor(private http: HttpClient) { }

  ngOnInit(): void { 
    this.fetchLoan();
   }
  fetchLoan() {
    const url=environment.baseUrl+ 'loan-service/fetchLoans/'+ this.customerId;
    this.http.get<any>(url)
      .subscribe(
        data => {
          console.log('GET Request is successful', data);
      if(data) {
                  this.loans = data.loans;
                  this.loanInstanceId = data.instanceId;
                  this.loanInstancePort = data.instancePort; 
                  this.mortgages = data.mortgageDetail.mortgages;
                  this.mortgageInstanceId = data.mortgageDetail.instanceId; 
                  this.mortgageInstancePort = data.mortgageDetail.instancePort;
                  //this.customerId;
                }
        },
        error => {
          console.log('Error', error);
        });
  }
  saveLoan() {
    const url = environment.baseUrl+ "loan-service/saveLoan";
    this.newLoan.customerNumber = this.customerId; this.http.post(url, this.newLoan)
      .subscribe(
        data => {
          console.log("POST Request is successful" , data);
          this.fetchLoan();
        },
        error => {
          console.log('Error', error);
        });
  }
  saveMortgage() {
    const url = environment.baseUrl + 'mortgage-service/saveMortgage'
    this.newMortgage.customerNumber = this.customerId;
    this.http.post(url, this.newMortgage)
      .subscribe(
        data => {
          console.log('POST Request is successful' , data);
          this.fetchLoan(); 
        },
      error => {
              console.log('Error', error);
            });
        }


}
