import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IInvoiceLineItem } from 'app/shared/model/invoice-line-item.model';
import { InvoiceLineItemService } from './invoice-line-item.service';
import { InvoiceLineItemDeleteDialogComponent } from './invoice-line-item-delete-dialog.component';

@Component({
  selector: 'jhi-invoice-line-item',
  templateUrl: './invoice-line-item.component.html',
})
export class InvoiceLineItemComponent implements OnInit, OnDestroy {
  invoiceLineItems?: IInvoiceLineItem[];
  eventSubscriber?: Subscription;

  constructor(
    protected invoiceLineItemService: InvoiceLineItemService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.invoiceLineItemService.query().subscribe((res: HttpResponse<IInvoiceLineItem[]>) => (this.invoiceLineItems = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInInvoiceLineItems();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IInvoiceLineItem): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInInvoiceLineItems(): void {
    this.eventSubscriber = this.eventManager.subscribe('invoiceLineItemListModification', () => this.loadAll());
  }

  delete(invoiceLineItem: IInvoiceLineItem): void {
    const modalRef = this.modalService.open(InvoiceLineItemDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.invoiceLineItem = invoiceLineItem;
  }
}
