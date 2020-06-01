import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IInvoiceHeader } from 'app/shared/model/invoice-header.model';
import { InvoiceHeaderService } from './invoice-header.service';

@Component({
  templateUrl: './invoice-header-delete-dialog.component.html',
})
export class InvoiceHeaderDeleteDialogComponent {
  invoiceHeader?: IInvoiceHeader;

  constructor(
    protected invoiceHeaderService: InvoiceHeaderService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.invoiceHeaderService.delete(id).subscribe(() => {
      this.eventManager.broadcast('invoiceHeaderListModification');
      this.activeModal.close();
    });
  }
}
