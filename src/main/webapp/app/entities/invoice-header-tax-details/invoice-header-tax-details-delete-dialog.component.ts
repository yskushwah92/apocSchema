import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IInvoiceHeaderTaxDetails } from 'app/shared/model/invoice-header-tax-details.model';
import { InvoiceHeaderTaxDetailsService } from './invoice-header-tax-details.service';

@Component({
  templateUrl: './invoice-header-tax-details-delete-dialog.component.html',
})
export class InvoiceHeaderTaxDetailsDeleteDialogComponent {
  invoiceHeaderTaxDetails?: IInvoiceHeaderTaxDetails;

  constructor(
    protected invoiceHeaderTaxDetailsService: InvoiceHeaderTaxDetailsService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.invoiceHeaderTaxDetailsService.delete(id).subscribe(() => {
      this.eventManager.broadcast('invoiceHeaderTaxDetailsListModification');
      this.activeModal.close();
    });
  }
}
