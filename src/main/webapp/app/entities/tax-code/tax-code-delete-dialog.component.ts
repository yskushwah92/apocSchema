import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITaxCode } from 'app/shared/model/tax-code.model';
import { TaxCodeService } from './tax-code.service';

@Component({
  templateUrl: './tax-code-delete-dialog.component.html',
})
export class TaxCodeDeleteDialogComponent {
  taxCode?: ITaxCode;

  constructor(protected taxCodeService: TaxCodeService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.taxCodeService.delete(id).subscribe(() => {
      this.eventManager.broadcast('taxCodeListModification');
      this.activeModal.close();
    });
  }
}
