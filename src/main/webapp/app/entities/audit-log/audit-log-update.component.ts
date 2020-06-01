import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IAuditLog, AuditLog } from 'app/shared/model/audit-log.model';
import { AuditLogService } from './audit-log.service';
import { IInvoice } from 'app/shared/model/invoice.model';
import { InvoiceService } from 'app/entities/invoice/invoice.service';

@Component({
  selector: 'jhi-audit-log-update',
  templateUrl: './audit-log-update.component.html',
})
export class AuditLogUpdateComponent implements OnInit {
  isSaving = false;
  invoices: IInvoice[] = [];

  editForm = this.fb.group({
    id: [],
    activityName: [],
    activityCreationDate: [],
    activityStartTime: [],
    activityEndTime: [],
    assignedDate: [],
    user: [],
    status: [],
    reason: [],
    comments: [],
    completeOn: [],
    duration: [],
    createdAt: [],
    createdBy: [],
    invoice: [],
  });

  constructor(
    protected auditLogService: AuditLogService,
    protected invoiceService: InvoiceService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ auditLog }) => {
      if (!auditLog.id) {
        const today = moment().startOf('day');
        auditLog.activityCreationDate = today;
        auditLog.activityStartTime = today;
        auditLog.activityEndTime = today;
        auditLog.assignedDate = today;
        auditLog.completeOn = today;
        auditLog.createdAt = today;
      }

      this.updateForm(auditLog);

      this.invoiceService.query().subscribe((res: HttpResponse<IInvoice[]>) => (this.invoices = res.body || []));
    });
  }

  updateForm(auditLog: IAuditLog): void {
    this.editForm.patchValue({
      id: auditLog.id,
      activityName: auditLog.activityName,
      activityCreationDate: auditLog.activityCreationDate ? auditLog.activityCreationDate.format(DATE_TIME_FORMAT) : null,
      activityStartTime: auditLog.activityStartTime ? auditLog.activityStartTime.format(DATE_TIME_FORMAT) : null,
      activityEndTime: auditLog.activityEndTime ? auditLog.activityEndTime.format(DATE_TIME_FORMAT) : null,
      assignedDate: auditLog.assignedDate ? auditLog.assignedDate.format(DATE_TIME_FORMAT) : null,
      user: auditLog.user,
      status: auditLog.status,
      reason: auditLog.reason,
      comments: auditLog.comments,
      completeOn: auditLog.completeOn ? auditLog.completeOn.format(DATE_TIME_FORMAT) : null,
      duration: auditLog.duration,
      createdAt: auditLog.createdAt ? auditLog.createdAt.format(DATE_TIME_FORMAT) : null,
      createdBy: auditLog.createdBy,
      invoice: auditLog.invoice,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const auditLog = this.createFromForm();
    if (auditLog.id !== undefined) {
      this.subscribeToSaveResponse(this.auditLogService.update(auditLog));
    } else {
      this.subscribeToSaveResponse(this.auditLogService.create(auditLog));
    }
  }

  private createFromForm(): IAuditLog {
    return {
      ...new AuditLog(),
      id: this.editForm.get(['id'])!.value,
      activityName: this.editForm.get(['activityName'])!.value,
      activityCreationDate: this.editForm.get(['activityCreationDate'])!.value
        ? moment(this.editForm.get(['activityCreationDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      activityStartTime: this.editForm.get(['activityStartTime'])!.value
        ? moment(this.editForm.get(['activityStartTime'])!.value, DATE_TIME_FORMAT)
        : undefined,
      activityEndTime: this.editForm.get(['activityEndTime'])!.value
        ? moment(this.editForm.get(['activityEndTime'])!.value, DATE_TIME_FORMAT)
        : undefined,
      assignedDate: this.editForm.get(['assignedDate'])!.value
        ? moment(this.editForm.get(['assignedDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      user: this.editForm.get(['user'])!.value,
      status: this.editForm.get(['status'])!.value,
      reason: this.editForm.get(['reason'])!.value,
      comments: this.editForm.get(['comments'])!.value,
      completeOn: this.editForm.get(['completeOn'])!.value ? moment(this.editForm.get(['completeOn'])!.value, DATE_TIME_FORMAT) : undefined,
      duration: this.editForm.get(['duration'])!.value,
      createdAt: this.editForm.get(['createdAt'])!.value ? moment(this.editForm.get(['createdAt'])!.value, DATE_TIME_FORMAT) : undefined,
      createdBy: this.editForm.get(['createdBy'])!.value,
      invoice: this.editForm.get(['invoice'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAuditLog>>): void {
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

  trackById(index: number, item: IInvoice): any {
    return item.id;
  }
}
