import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IInvoiceHeaderTaxDetails } from 'app/shared/model/invoice-header-tax-details.model';
import { InvoiceHeaderTaxDetailsService } from './invoice-header-tax-details.service';
import { InvoiceHeaderTaxDetailsDeleteDialogComponent } from './invoice-header-tax-details-delete-dialog.component';

@Component({
  selector: 'jhi-invoice-header-tax-details',
  templateUrl: './invoice-header-tax-details.component.html',
})
export class InvoiceHeaderTaxDetailsComponent implements OnInit, OnDestroy {
  invoiceHeaderTaxDetails?: IInvoiceHeaderTaxDetails[];
  eventSubscriber?: Subscription;

  constructor(
    protected invoiceHeaderTaxDetailsService: InvoiceHeaderTaxDetailsService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.invoiceHeaderTaxDetailsService
      .query()
      .subscribe((res: HttpResponse<IInvoiceHeaderTaxDetails[]>) => (this.invoiceHeaderTaxDetails = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInInvoiceHeaderTaxDetails();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IInvoiceHeaderTaxDetails): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInInvoiceHeaderTaxDetails(): void {
    this.eventSubscriber = this.eventManager.subscribe('invoiceHeaderTaxDetailsListModification', () => this.loadAll());
  }

  delete(invoiceHeaderTaxDetails: IInvoiceHeaderTaxDetails): void {
    const modalRef = this.modalService.open(InvoiceHeaderTaxDetailsDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.invoiceHeaderTaxDetails = invoiceHeaderTaxDetails;
  }
}
