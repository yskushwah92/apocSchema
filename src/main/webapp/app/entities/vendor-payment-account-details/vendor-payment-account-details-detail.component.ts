import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IVendorPaymentAccountDetails } from 'app/shared/model/vendor-payment-account-details.model';

@Component({
  selector: 'jhi-vendor-payment-account-details-detail',
  templateUrl: './vendor-payment-account-details-detail.component.html',
})
export class VendorPaymentAccountDetailsDetailComponent implements OnInit {
  vendorPaymentAccountDetails: IVendorPaymentAccountDetails | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(
      ({ vendorPaymentAccountDetails }) => (this.vendorPaymentAccountDetails = vendorPaymentAccountDetails)
    );
  }

  previousState(): void {
    window.history.back();
  }
}
