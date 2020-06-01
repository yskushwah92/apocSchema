import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IInvoiceHeader } from 'app/shared/model/invoice-header.model';
import { InvoiceHeaderService } from './invoice-header.service';
import { InvoiceHeaderDeleteDialogComponent } from './invoice-header-delete-dialog.component';

@Component({
  selector: 'jhi-invoice-header',
  templateUrl: './invoice-header.component.html',
})
export class InvoiceHeaderComponent implements OnInit, OnDestroy {
  invoiceHeaders?: IInvoiceHeader[];
  eventSubscriber?: Subscription;

  constructor(
    protected invoiceHeaderService: InvoiceHeaderService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.invoiceHeaderService.query().subscribe((res: HttpResponse<IInvoiceHeader[]>) => (this.invoiceHeaders = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInInvoiceHeaders();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IInvoiceHeader): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInInvoiceHeaders(): void {
    this.eventSubscriber = this.eventManager.subscribe('invoiceHeaderListModification', () => this.loadAll());
  }

  delete(invoiceHeader: IInvoiceHeader): void {
    const modalRef = this.modalService.open(InvoiceHeaderDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.invoiceHeader = invoiceHeader;
  }
}
