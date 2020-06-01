import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IPurchaseOrderHeader, PurchaseOrderHeader } from 'app/shared/model/purchase-order-header.model';
import { PurchaseOrderHeaderService } from './purchase-order-header.service';

@Component({
  selector: 'jhi-purchase-order-header-update',
  templateUrl: './purchase-order-header-update.component.html',
})
export class PurchaseOrderHeaderUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    serialNo: [],
    amount: [],
    type: [],
    requestedBy: [],
    creationDate: [],
    receiptRequired: [],
    status: [],
    companyCode: [],
    currencyCode: [],
    createdAt: [],
    createdBy: [],
  });

  constructor(
    protected purchaseOrderHeaderService: PurchaseOrderHeaderService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ purchaseOrderHeader }) => {
      if (!purchaseOrderHeader.id) {
        const today = moment().startOf('day');
        purchaseOrderHeader.creationDate = today;
        purchaseOrderHeader.createdAt = today;
      }

      this.updateForm(purchaseOrderHeader);
    });
  }

  updateForm(purchaseOrderHeader: IPurchaseOrderHeader): void {
    this.editForm.patchValue({
      id: purchaseOrderHeader.id,
      serialNo: purchaseOrderHeader.serialNo,
      amount: purchaseOrderHeader.amount,
      type: purchaseOrderHeader.type,
      requestedBy: purchaseOrderHeader.requestedBy,
      creationDate: purchaseOrderHeader.creationDate ? purchaseOrderHeader.creationDate.format(DATE_TIME_FORMAT) : null,
      receiptRequired: purchaseOrderHeader.receiptRequired,
      status: purchaseOrderHeader.status,
      companyCode: purchaseOrderHeader.companyCode,
      currencyCode: purchaseOrderHeader.currencyCode,
      createdAt: purchaseOrderHeader.createdAt ? purchaseOrderHeader.createdAt.format(DATE_TIME_FORMAT) : null,
      createdBy: purchaseOrderHeader.createdBy,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const purchaseOrderHeader = this.createFromForm();
    if (purchaseOrderHeader.id !== undefined) {
      this.subscribeToSaveResponse(this.purchaseOrderHeaderService.update(purchaseOrderHeader));
    } else {
      this.subscribeToSaveResponse(this.purchaseOrderHeaderService.create(purchaseOrderHeader));
    }
  }

  private createFromForm(): IPurchaseOrderHeader {
    return {
      ...new PurchaseOrderHeader(),
      id: this.editForm.get(['id'])!.value,
      serialNo: this.editForm.get(['serialNo'])!.value,
      amount: this.editForm.get(['amount'])!.value,
      type: this.editForm.get(['type'])!.value,
      requestedBy: this.editForm.get(['requestedBy'])!.value,
      creationDate: this.editForm.get(['creationDate'])!.value
        ? moment(this.editForm.get(['creationDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      receiptRequired: this.editForm.get(['receiptRequired'])!.value,
      status: this.editForm.get(['status'])!.value,
      companyCode: this.editForm.get(['companyCode'])!.value,
      currencyCode: this.editForm.get(['currencyCode'])!.value,
      createdAt: this.editForm.get(['createdAt'])!.value ? moment(this.editForm.get(['createdAt'])!.value, DATE_TIME_FORMAT) : undefined,
      createdBy: this.editForm.get(['createdBy'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPurchaseOrderHeader>>): void {
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
}
