import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IPurchaseOrderLine } from 'app/shared/model/purchase-order-line.model';
import { PurchaseOrderLineService } from './purchase-order-line.service';
import { PurchaseOrderLineDeleteDialogComponent } from './purchase-order-line-delete-dialog.component';

@Component({
  selector: 'jhi-purchase-order-line',
  templateUrl: './purchase-order-line.component.html',
})
export class PurchaseOrderLineComponent implements OnInit, OnDestroy {
  purchaseOrderLines?: IPurchaseOrderLine[];
  eventSubscriber?: Subscription;

  constructor(
    protected purchaseOrderLineService: PurchaseOrderLineService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.purchaseOrderLineService
      .query()
      .subscribe((res: HttpResponse<IPurchaseOrderLine[]>) => (this.purchaseOrderLines = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInPurchaseOrderLines();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IPurchaseOrderLine): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInPurchaseOrderLines(): void {
    this.eventSubscriber = this.eventManager.subscribe('purchaseOrderLineListModification', () => this.loadAll());
  }

  delete(purchaseOrderLine: IPurchaseOrderLine): void {
    const modalRef = this.modalService.open(PurchaseOrderLineDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.purchaseOrderLine = purchaseOrderLine;
  }
}
