import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IShippingLocation } from 'app/shared/model/shipping-location.model';
import { ShippingLocationService } from './shipping-location.service';

@Component({
  templateUrl: './shipping-location-delete-dialog.component.html',
})
export class ShippingLocationDeleteDialogComponent {
  shippingLocation?: IShippingLocation;

  constructor(
    protected shippingLocationService: ShippingLocationService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.shippingLocationService.delete(id).subscribe(() => {
      this.eventManager.broadcast('shippingLocationListModification');
      this.activeModal.close();
    });
  }
}
