import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IGLAccountDetails } from 'app/shared/model/gl-account-details.model';
import { GLAccountDetailsService } from './gl-account-details.service';

@Component({
  templateUrl: './gl-account-details-delete-dialog.component.html',
})
export class GLAccountDetailsDeleteDialogComponent {
  gLAccountDetails?: IGLAccountDetails;

  constructor(
    protected gLAccountDetailsService: GLAccountDetailsService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.gLAccountDetailsService.delete(id).subscribe(() => {
      this.eventManager.broadcast('gLAccountDetailsListModification');
      this.activeModal.close();
    });
  }
}
