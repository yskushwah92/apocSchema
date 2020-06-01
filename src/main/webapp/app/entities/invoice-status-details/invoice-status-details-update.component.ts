import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IInvoiceStatusDetails, InvoiceStatusDetails } from 'app/shared/model/invoice-status-details.model';
import { InvoiceStatusDetailsService } from './invoice-status-details.service';
import { IInvoice } from 'app/shared/model/invoice.model';
import { InvoiceService } from 'app/entities/invoice/invoice.service';

@Component({
  selector: 'jhi-invoice-status-details-update',
  templateUrl: './invoice-status-details-update.component.html',
})
export class InvoiceStatusDetailsUpdateComponent implements OnInit {
  isSaving = false;
  invoices: IInvoice[] = [];

  editForm = this.fb.group({
    id: [],
    name: [],
    currentStatus: [],
    statusReason: [],
    createdAt: [],
    createdBy: [],
    invoice: [],
  });

  constructor(
    protected invoiceStatusDetailsService: InvoiceStatusDetailsService,
    protected invoiceService: InvoiceService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ invoiceStatusDetails }) => {
      if (!invoiceStatusDetails.id) {
        const today = moment().startOf('day');
        invoiceStatusDetails.createdAt = today;
      }

      this.updateForm(invoiceStatusDetails);

      this.invoiceService.query().subscribe((res: HttpResponse<IInvoice[]>) => (this.invoices = res.body || []));
    });
  }

  updateForm(invoiceStatusDetails: IInvoiceStatusDetails): void {
    this.editForm.patchValue({
      id: invoiceStatusDetails.id,
      name: invoiceStatusDetails.name,
      currentStatus: invoiceStatusDetails.currentStatus,
      statusReason: invoiceStatusDetails.statusReason,
      createdAt: invoiceStatusDetails.createdAt ? invoiceStatusDetails.createdAt.format(DATE_TIME_FORMAT) : null,
      createdBy: invoiceStatusDetails.createdBy,
      invoice: invoiceStatusDetails.invoice,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const invoiceStatusDetails = this.createFromForm();
    if (invoiceStatusDetails.id !== undefined) {
      this.subscribeToSaveResponse(this.invoiceStatusDetailsService.update(invoiceStatusDetails));
    } else {
      this.subscribeToSaveResponse(this.invoiceStatusDetailsService.create(invoiceStatusDetails));
    }
  }

  private createFromForm(): IInvoiceStatusDetails {
    return {
      ...new InvoiceStatusDetails(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      currentStatus: this.editForm.get(['currentStatus'])!.value,
      statusReason: this.editForm.get(['statusReason'])!.value,
      createdAt: this.editForm.get(['createdAt'])!.value ? moment(this.editForm.get(['createdAt'])!.value, DATE_TIME_FORMAT) : undefined,
      createdBy: this.editForm.get(['createdBy'])!.value,
      invoice: this.editForm.get(['invoice'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IInvoiceStatusDetails>>): void {
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
