import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  selectedCustomerDetail = {};
  constructor() { }
  setSelectedCustomerDetail(customer){
    this.selectedCustomerDetail = customer;
  }
  getSelectedCustomerDetail() {
    return this.selectedCustomerDetail;
  }
}
