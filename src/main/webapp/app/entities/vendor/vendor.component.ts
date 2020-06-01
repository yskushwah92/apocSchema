import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IVendor } from 'app/shared/model/vendor.model';
import { VendorService } from './vendor.service';
import { VendorDeleteDialogComponent } from './vendor-delete-dialog.component';

@Component({
  selector: 'jhi-vendor',
  templateUrl: './vendor.component.html',
})
export class VendorComponent implements OnInit, OnDestroy {
  vendors?: IVendor[];
  eventSubscriber?: Subscription;

  constructor(protected vendorService: VendorService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.vendorService.query().subscribe((res: HttpResponse<IVendor[]>) => (this.vendors = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInVendors();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IVendor): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInVendors(): void {
    this.eventSubscriber = this.eventManager.subscribe('vendorListModification', () => this.loadAll());
  }

  delete(vendor: IVendor): void {
    const modalRef = this.modalService.open(VendorDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.vendor = vendor;
  }
}
