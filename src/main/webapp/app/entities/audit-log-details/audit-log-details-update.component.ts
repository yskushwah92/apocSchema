import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IAuditLogDetails, AuditLogDetails } from 'app/shared/model/audit-log-details.model';
import { AuditLogDetailsService } from './audit-log-details.service';
import { IAuditLog } from 'app/shared/model/audit-log.model';
import { AuditLogService } from 'app/entities/audit-log/audit-log.service';

@Component({
  selector: 'jhi-audit-log-details-update',
  templateUrl: './audit-log-details-update.component.html',
})
export class AuditLogDetailsUpdateComponent implements OnInit {
  isSaving = false;
  auditlogs: IAuditLog[] = [];

  editForm = this.fb.group({
    id: [],
    assignedBy: [],
    actor: [],
    status: [],
    statusCode: [],
    reason: [],
    reasonCode: [],
    createdOn: [],
    comments: [],
    delegate: [],
    delegatedFlag: [],
    metSLA: [],
    createdAt: [],
    createdBy: [],
    auditLog: [],
  });

  constructor(
    protected auditLogDetailsService: AuditLogDetailsService,
    protected auditLogService: AuditLogService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ auditLogDetails }) => {
      if (!auditLogDetails.id) {
        const today = moment().startOf('day');
        auditLogDetails.createdOn = today;
        auditLogDetails.createdAt = today;
      }

      this.updateForm(auditLogDetails);

      this.auditLogService.query().subscribe((res: HttpResponse<IAuditLog[]>) => (this.auditlogs = res.body || []));
    });
  }

  updateForm(auditLogDetails: IAuditLogDetails): void {
    this.editForm.patchValue({
      id: auditLogDetails.id,
      assignedBy: auditLogDetails.assignedBy,
      actor: auditLogDetails.actor,
      status: auditLogDetails.status,
      statusCode: auditLogDetails.statusCode,
      reason: auditLogDetails.reason,
      reasonCode: auditLogDetails.reasonCode,
      createdOn: auditLogDetails.createdOn ? auditLogDetails.createdOn.format(DATE_TIME_FORMAT) : null,
      comments: auditLogDetails.comments,
      delegate: auditLogDetails.delegate,
      delegatedFlag: auditLogDetails.delegatedFlag,
      metSLA: auditLogDetails.metSLA,
      createdAt: auditLogDetails.createdAt ? auditLogDetails.createdAt.format(DATE_TIME_FORMAT) : null,
      createdBy: auditLogDetails.createdBy,
      auditLog: auditLogDetails.auditLog,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const auditLogDetails = this.createFromForm();
    if (auditLogDetails.id !== undefined) {
      this.subscribeToSaveResponse(this.auditLogDetailsService.update(auditLogDetails));
    } else {
      this.subscribeToSaveResponse(this.auditLogDetailsService.create(auditLogDetails));
    }
  }

  private createFromForm(): IAuditLogDetails {
    return {
      ...new AuditLogDetails(),
      id: this.editForm.get(['id'])!.value,
      assignedBy: this.editForm.get(['assignedBy'])!.value,
      actor: this.editForm.get(['actor'])!.value,
      status: this.editForm.get(['status'])!.value,
      statusCode: this.editForm.get(['statusCode'])!.value,
      reason: this.editForm.get(['reason'])!.value,
      reasonCode: this.editForm.get(['reasonCode'])!.value,
      createdOn: this.editForm.get(['createdOn'])!.value ? moment(this.editForm.get(['createdOn'])!.value, DATE_TIME_FORMAT) : undefined,
      comments: this.editForm.get(['comments'])!.value,
      delegate: this.editForm.get(['delegate'])!.value,
      delegatedFlag: this.editForm.get(['delegatedFlag'])!.value,
      metSLA: this.editForm.get(['metSLA'])!.value,
      createdAt: this.editForm.get(['createdAt'])!.value ? moment(this.editForm.get(['createdAt'])!.value, DATE_TIME_FORMAT) : undefined,
      createdBy: this.editForm.get(['createdBy'])!.value,
      auditLog: this.editForm.get(['auditLog'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAuditLogDetails>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: IAuditLog): any {
    return item.id;
  }
}
