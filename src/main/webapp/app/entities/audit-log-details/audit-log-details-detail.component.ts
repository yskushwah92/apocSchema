import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAuditLogDetails } from 'app/shared/model/audit-log-details.model';

@Component({
  selector: 'jhi-audit-log-details-detail',
  templateUrl: './audit-log-details-detail.component.html',
})
export class AuditLogDetailsDetailComponent implements OnInit {
  auditLogDetails: IAuditLogDetails | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ auditLogDetails }) => (this.auditLogDetails = auditLogDetails));
  }

  previousState(): void {
    window.history.back();
  }
}
