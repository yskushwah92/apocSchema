import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPurchaseOrderLine } from 'app/shared/model/purchase-order-line.model';
import { PurchaseOrderLineService } from './purchase-order-line.service';

@Component({
  templateUrl: './purchase-order-line-delete-dialog.component.html',
})
export class PurchaseOrderLineDeleteDialogComponent {
  purchaseOrderLine?: IPurchaseOrderLine;

  constructor(
    protected purchaseOrderLineService: PurchaseOrderLineService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.purchaseOrderLineService.delete(id).subscribe(() => {
      this.eventManager.broadcast('purchaseOrderLineListModification');
      this.activeModal.close();
    });
  }
}
