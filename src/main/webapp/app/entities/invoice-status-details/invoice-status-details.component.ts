import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IInvoiceStatusDetails } from 'app/shared/model/invoice-status-details.model';
import { InvoiceStatusDetailsService } from './invoice-status-details.service';
import { InvoiceStatusDetailsDeleteDialogComponent } from './invoice-status-details-delete-dialog.component';

@Component({
  selector: 'jhi-invoice-status-details',
  templateUrl: './invoice-status-details.component.html',
})
export class InvoiceStatusDetailsComponent implements OnInit, OnDestroy {
  invoiceStatusDetails?: IInvoiceStatusDetails[];
  eventSubscriber?: Subscription;

  constructor(
    protected invoiceStatusDetailsService: InvoiceStatusDetailsService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.invoiceStatusDetailsService
      .query()
      .subscribe((res: HttpResponse<IInvoiceStatusDetails[]>) => (this.invoiceStatusDetails = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInInvoiceStatusDetails();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IInvoiceStatusDetails): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInInvoiceStatusDetails(): void {
    this.eventSubscriber = this.eventManager.subscribe('invoiceStatusDetailsListModification', () => this.loadAll());
  }

  delete(invoiceStatusDetails: IInvoiceStatusDetails): void {
    const modalRef = this.modalService.open(InvoiceStatusDetailsDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.invoiceStatusDetails = invoiceStatusDetails;
  }
}
