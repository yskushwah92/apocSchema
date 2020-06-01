import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IInvoiceLineItem } from 'app/shared/model/invoice-line-item.model';
import { InvoiceLineItemService } from './invoice-line-item.service';

@Component({
  templateUrl: './invoice-line-item-delete-dialog.component.html',
})
export class InvoiceLineItemDeleteDialogComponent {
  invoiceLineItem?: IInvoiceLineItem;

  constructor(
    protected invoiceLineItemService: InvoiceLineItemService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.invoiceLineItemService.delete(id).subscribe(() => {
      this.eventManager.broadcast('invoiceLineItemListModification');
      this.activeModal.close();
    });
  }
}
