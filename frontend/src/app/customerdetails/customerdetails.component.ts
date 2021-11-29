import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CustomerService } from '../customer.service';

@Component({
  selector: 'app-customerdetails',
  templateUrl: './customerdetails.component.html',
  styleUrls: ['./customerdetails.component.css']
})
export class CustomerdetailsComponent implements OnInit {

  selectedCustomer;
  selectedTab;
  selectedCustomerId;

  constructor(private customerService: CustomerService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.selectedTab = 'deposit';
    this.selectedCustomer = this.customerService.getSelectedCustomerDetail();
    this.selectedCustomerId = this.route.snapshot
      .paramMap.get('id'); 
  }
  fetchDeposit() {
    this.selectedTab = 'deposit';
  }
  fetchLoan() {
    this.selectedTab = 'loan';
  }

}
