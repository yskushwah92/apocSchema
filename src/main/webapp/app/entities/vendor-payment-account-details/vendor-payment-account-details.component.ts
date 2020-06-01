import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IVendorPaymentAccountDetails } from 'app/shared/model/vendor-payment-account-details.model';
import { VendorPaymentAccountDetailsService } from './vendor-payment-account-details.service';
import { VendorPaymentAccountDetailsDeleteDialogComponent } from './vendor-payment-account-details-delete-dialog.component';

@Component({
  selector: 'jhi-vendor-payment-account-details',
  templateUrl: './vendor-payment-account-details.component.html',
})
export class VendorPaymentAccountDetailsComponent implements OnInit, OnDestroy {
  vendorPaymentAccountDetails?: IVendorPaymentAccountDetails[];
  eventSubscriber?: Subscription;

  constructor(
    protected vendorPaymentAccountDetailsService: VendorPaymentAccountDetailsService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.vendorPaymentAccountDetailsService
      .query()
      .subscribe((res: HttpResponse<IVendorPaymentAccountDetails[]>) => (this.vendorPaymentAccountDetails = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInVendorPaymentAccountDetails();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IVendorPaymentAccountDetails): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInVendorPaymentAccountDetails(): void {
    this.eventSubscriber = this.eventManager.subscribe('vendorPaymentAccountDetailsListModification', () => this.loadAll());
  }

  delete(vendorPaymentAccountDetails: IVendorPaymentAccountDetails): void {
    const modalRef = this.modalService.open(VendorPaymentAccountDetailsDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.vendorPaymentAccountDetails = vendorPaymentAccountDetails;
  }
}
