import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IGRNDetails } from 'app/shared/model/grn-details.model';
import { GRNDetailsService } from './grn-details.service';

@Component({
  templateUrl: './grn-details-delete-dialog.component.html',
})
export class GRNDetailsDeleteDialogComponent {
  gRNDetails?: IGRNDetails;

  constructor(
    protected gRNDetailsService: GRNDetailsService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.gRNDetailsService.delete(id).subscribe(() => {
      this.eventManager.broadcast('gRNDetailsListModification');
      this.activeModal.close();
    });
  }
}
