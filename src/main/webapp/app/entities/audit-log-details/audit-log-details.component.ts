import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IAuditLogDetails } from 'app/shared/model/audit-log-details.model';
import { AuditLogDetailsService } from './audit-log-details.service';
import { AuditLogDetailsDeleteDialogComponent } from './audit-log-details-delete-dialog.component';

@Component({
  selector: 'jhi-audit-log-details',
  templateUrl: './audit-log-details.component.html',
})
export class AuditLogDetailsComponent implements OnInit, OnDestroy {
  auditLogDetails?: IAuditLogDetails[];
  eventSubscriber?: Subscription;

  constructor(
    protected auditLogDetailsService: AuditLogDetailsService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.auditLogDetailsService.query().subscribe((res: HttpResponse<IAuditLogDetails[]>) => (this.auditLogDetails = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInAuditLogDetails();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IAuditLogDetails): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInAuditLogDetails(): void {
    this.eventSubscriber = this.eventManager.subscribe('auditLogDetailsListModification', () => this.loadAll());
  }

  delete(auditLogDetails: IAuditLogDetails): void {
    const modalRef = this.modalService.open(AuditLogDetailsDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.auditLogDetails = auditLogDetails;
  }
}
