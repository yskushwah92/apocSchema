import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAuditLog } from 'app/shared/model/audit-log.model';
import { AuditLogService } from './audit-log.service';

@Component({
  templateUrl: './audit-log-delete-dialog.component.html',
})
export class AuditLogDeleteDialogComponent {
  auditLog?: IAuditLog;

  constructor(protected auditLogService: AuditLogService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.auditLogService.delete(id).subscribe(() => {
      this.eventManager.broadcast('auditLogListModification');
      this.activeModal.close();
    });
  }
}
