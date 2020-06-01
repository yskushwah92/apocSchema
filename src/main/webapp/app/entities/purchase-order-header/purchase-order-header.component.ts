import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IPurchaseOrderHeader } from 'app/shared/model/purchase-order-header.model';
import { PurchaseOrderHeaderService } from './purchase-order-header.service';
import { PurchaseOrderHeaderDeleteDialogComponent } from './purchase-order-header-delete-dialog.component';

@Component({
  selector: 'jhi-purchase-order-header',
  templateUrl: './purchase-order-header.component.html',
})
export class PurchaseOrderHeaderComponent implements OnInit, OnDestroy {
  purchaseOrderHeaders?: IPurchaseOrderHeader[];
  eventSubscriber?: Subscription;

  constructor(
    protected purchaseOrderHeaderService: PurchaseOrderHeaderService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.purchaseOrderHeaderService
      .query()
      .subscribe((res: HttpResponse<IPurchaseOrderHeader[]>) => (this.purchaseOrderHeaders = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInPurchaseOrderHeaders();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IPurchaseOrderHeader): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInPurchaseOrderHeaders(): void {
    this.eventSubscriber = this.eventManager.subscribe('purchaseOrderHeaderListModification', () => this.loadAll());
  }

  delete(purchaseOrderHeader: IPurchaseOrderHeader): void {
    const modalRef = this.modalService.open(PurchaseOrderHeaderDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.purchaseOrderHeader = purchaseOrderHeader;
  }
}
