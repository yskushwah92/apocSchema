import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IAuditLog } from 'app/shared/model/audit-log.model';
import { AuditLogService } from './audit-log.service';
import { AuditLogDeleteDialogComponent } from './audit-log-delete-dialog.component';

@Component({
  selector: 'jhi-audit-log',
  templateUrl: './audit-log.component.html',
})
export class AuditLogComponent implements OnInit, OnDestroy {
  auditLogs?: IAuditLog[];
  eventSubscriber?: Subscription;

  constructor(protected auditLogService: AuditLogService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.auditLogService.query().subscribe((res: HttpResponse<IAuditLog[]>) => (this.auditLogs = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInAuditLogs();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IAuditLog): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInAuditLogs(): void {
    this.eventSubscriber = this.eventManager.subscribe('auditLogListModification', () => this.loadAll());
  }

  delete(auditLog: IAuditLog): void {
    const modalRef = this.modalService.open(AuditLogDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.auditLog = auditLog;
  }
}
