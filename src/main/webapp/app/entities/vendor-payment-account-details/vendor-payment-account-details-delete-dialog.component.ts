import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IVendorPaymentAccountDetails } from 'app/shared/model/vendor-payment-account-details.model';
import { VendorPaymentAccountDetailsService } from './vendor-payment-account-details.service';

@Component({
  templateUrl: './vendor-payment-account-details-delete-dialog.component.html',
})
export class VendorPaymentAccountDetailsDeleteDialogComponent {
  vendorPaymentAccountDetails?: IVendorPaymentAccountDetails;

  constructor(
    protected vendorPaymentAccountDetailsService: VendorPaymentAccountDetailsService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.vendorPaymentAccountDetailsService.delete(id).subscribe(() => {
      this.eventManager.broadcast('vendorPaymentAccountDetailsListModification');
      this.activeModal.close();
    });
  }
}
