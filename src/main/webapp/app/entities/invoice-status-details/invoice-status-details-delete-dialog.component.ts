import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IInvoiceStatusDetails } from 'app/shared/model/invoice-status-details.model';
import { InvoiceStatusDetailsService } from './invoice-status-details.service';

@Component({
  templateUrl: './invoice-status-details-delete-dialog.component.html',
})
export class InvoiceStatusDetailsDeleteDialogComponent {
  invoiceStatusDetails?: IInvoiceStatusDetails;

  constructor(
    protected invoiceStatusDetailsService: InvoiceStatusDetailsService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.invoiceStatusDetailsService.delete(id).subscribe(() => {
      this.eventManager.broadcast('invoiceStatusDetailsListModification');
      this.activeModal.close();
    });
  }
}
