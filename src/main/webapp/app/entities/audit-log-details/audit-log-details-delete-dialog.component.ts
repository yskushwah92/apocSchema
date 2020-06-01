import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAuditLogDetails } from 'app/shared/model/audit-log-details.model';
import { AuditLogDetailsService } from './audit-log-details.service';

@Component({
  templateUrl: './audit-log-details-delete-dialog.component.html',
})
export class AuditLogDetailsDeleteDialogComponent {
  auditLogDetails?: IAuditLogDetails;

  constructor(
    protected auditLogDetailsService: AuditLogDetailsService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.auditLogDetailsService.delete(id).subscribe(() => {
      this.eventManager.broadcast('auditLogDetailsListModification');
      this.activeModal.close();
    });
  }
}
