import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ICaseStatusDetails, CaseStatusDetails } from 'app/shared/model/case-status-details.model';
import { CaseStatusDetailsService } from './case-status-details.service';
import { IInvoiceLineItem } from 'app/shared/model/invoice-line-item.model';
import { InvoiceLineItemService } from 'app/entities/invoice-line-item/invoice-line-item.service';

@Component({
  selector: 'jhi-case-status-details-update',
  templateUrl: './case-status-details-update.component.html',
})
export class CaseStatusDetailsUpdateComponent implements OnInit {
  isSaving = false;
  invoicelineitems: IInvoiceLineItem[] = [];

  editForm = this.fb.group({
    id: [],
    status: [],
    assignee: [],
    comments: [],
    timeElapasedInCurrentStatus: [],
    createdAt: [],
    createdBy: [],
    invoiceLineItem: [],
  });

  constructor(
    protected caseStatusDetailsService: CaseStatusDetailsService,
    protected invoiceLineItemService: InvoiceLineItemService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ caseStatusDetails }) => {
      if (!caseStatusDetails.id) {
        const today = moment().startOf('day');
        caseStatusDetails.createdAt = today;
      }

      this.updateForm(caseStatusDetails);

      this.invoiceLineItemService.query().subscribe((res: HttpResponse<IInvoiceLineItem[]>) => (this.invoicelineitems = res.body || []));
    });
  }

  updateForm(caseStatusDetails: ICaseStatusDetails): void {
    this.editForm.patchValue({
      id: caseStatusDetails.id,
      status: caseStatusDetails.status,
      assignee: caseStatusDetails.assignee,
      comments: caseStatusDetails.comments,
      timeElapasedInCurrentStatus: caseStatusDetails.timeElapasedInCurrentStatus,
      createdAt: caseStatusDetails.createdAt ? caseStatusDetails.createdAt.format(DATE_TIME_FORMAT) : null,
      createdBy: caseStatusDetails.createdBy,
      invoiceLineItem: caseStatusDetails.invoiceLineItem,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const caseStatusDetails = this.createFromForm();
    if (caseStatusDetails.id !== undefined) {
      this.subscribeToSaveResponse(this.caseStatusDetailsService.update(caseStatusDetails));
    } else {
      this.subscribeToSaveResponse(this.caseStatusDetailsService.create(caseStatusDetails));
    }
  }

  private createFromForm(): ICaseStatusDetails {
    return {
      ...new CaseStatusDetails(),
      id: this.editForm.get(['id'])!.value,
      status: this.editForm.get(['status'])!.value,
      assignee: this.editForm.get(['assignee'])!.value,
      comments: this.editForm.get(['comments'])!.value,
      timeElapasedInCurrentStatus: this.editForm.get(['timeElapasedInCurrentStatus'])!.value,
      createdAt: this.editForm.get(['createdAt'])!.value ? moment(this.editForm.get(['createdAt'])!.value, DATE_TIME_FORMAT) : undefined,
      createdBy: this.editForm.get(['createdBy'])!.value,
      invoiceLineItem: this.editForm.get(['invoiceLineItem'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICaseStatusDetails>>): void {
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

  trackById(index: number, item: IInvoiceLineItem): any {
    return item.id;
  }
}
