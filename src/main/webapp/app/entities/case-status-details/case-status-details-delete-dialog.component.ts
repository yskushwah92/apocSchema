import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICaseStatusDetails } from 'app/shared/model/case-status-details.model';
import { CaseStatusDetailsService } from './case-status-details.service';

@Component({
  templateUrl: './case-status-details-delete-dialog.component.html',
})
export class CaseStatusDetailsDeleteDialogComponent {
  caseStatusDetails?: ICaseStatusDetails;

  constructor(
    protected caseStatusDetailsService: CaseStatusDetailsService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.caseStatusDetailsService.delete(id).subscribe(() => {
      this.eventManager.broadcast('caseStatusDetailsListModification');
      this.activeModal.close();
    });
  }
}
