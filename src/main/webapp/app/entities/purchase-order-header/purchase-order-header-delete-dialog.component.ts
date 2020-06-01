import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPurchaseOrderHeader } from 'app/shared/model/purchase-order-header.model';
import { PurchaseOrderHeaderService } from './purchase-order-header.service';

@Component({
  templateUrl: './purchase-order-header-delete-dialog.component.html',
})
export class PurchaseOrderHeaderDeleteDialogComponent {
  purchaseOrderHeader?: IPurchaseOrderHeader;

  constructor(
    protected purchaseOrderHeaderService: PurchaseOrderHeaderService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.purchaseOrderHeaderService.delete(id).subscribe(() => {
      this.eventManager.broadcast('purchaseOrderHeaderListModification');
      this.activeModal.close();
    });
  }
}
