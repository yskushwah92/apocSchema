import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IInvoiceHeaderTaxDetails, InvoiceHeaderTaxDetails } from 'app/shared/model/invoice-header-tax-details.model';
import { InvoiceHeaderTaxDetailsService } from './invoice-header-tax-details.service';

@Component({
  selector: 'jhi-invoice-header-tax-details-update',
  templateUrl: './invoice-header-tax-details-update.component.html',
})
export class InvoiceHeaderTaxDetailsUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    taxCode: [],
    taxRate: [],
    taxAmount: [],
    taxDescription: [],
    taxDisplay: [],
    whtApplicable: [],
    whtGrpId: [],
    whtType: [],
    whtCode: [],
    whtAmount: [],
    unplannedDeliveryTaxRate: [],
    unplannedDeliveryTaxAmount: [],
  });

  constructor(
    protected invoiceHeaderTaxDetailsService: InvoiceHeaderTaxDetailsService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ invoiceHeaderTaxDetails }) => {
      this.updateForm(invoiceHeaderTaxDetails);
    });
  }

  updateForm(invoiceHeaderTaxDetails: IInvoiceHeaderTaxDetails): void {
    this.editForm.patchValue({
      id: invoiceHeaderTaxDetails.id,
      taxCode: invoiceHeaderTaxDetails.taxCode,
      taxRate: invoiceHeaderTaxDetails.taxRate,
      taxAmount: invoiceHeaderTaxDetails.taxAmount,
      taxDescription: invoiceHeaderTaxDetails.taxDescription,
      taxDisplay: invoiceHeaderTaxDetails.taxDisplay,
      whtApplicable: invoiceHeaderTaxDetails.whtApplicable,
      whtGrpId: invoiceHeaderTaxDetails.whtGrpId,
      whtType: invoiceHeaderTaxDetails.whtType,
      whtCode: invoiceHeaderTaxDetails.whtCode,
      whtAmount: invoiceHeaderTaxDetails.whtAmount,
      unplannedDeliveryTaxRate: invoiceHeaderTaxDetails.unplannedDeliveryTaxRate,
      unplannedDeliveryTaxAmount: invoiceHeaderTaxDetails.unplannedDeliveryTaxAmount,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const invoiceHeaderTaxDetails = this.createFromForm();
    if (invoiceHeaderTaxDetails.id !== undefined) {
      this.subscribeToSaveResponse(this.invoiceHeaderTaxDetailsService.update(invoiceHeaderTaxDetails));
    } else {
      this.subscribeToSaveResponse(this.invoiceHeaderTaxDetailsService.create(invoiceHeaderTaxDetails));
    }
  }

  private createFromForm(): IInvoiceHeaderTaxDetails {
    return {
      ...new InvoiceHeaderTaxDetails(),
      id: this.editForm.get(['id'])!.value,
      taxCode: this.editForm.get(['taxCode'])!.value,
      taxRate: this.editForm.get(['taxRate'])!.value,
      taxAmount: this.editForm.get(['taxAmount'])!.value,
      taxDescription: this.editForm.get(['taxDescription'])!.value,
      taxDisplay: this.editForm.get(['taxDisplay'])!.value,
      whtApplicable: this.editForm.get(['whtApplicable'])!.value,
      whtGrpId: this.editForm.get(['whtGrpId'])!.value,
      whtType: this.editForm.get(['whtType'])!.value,
      whtCode: this.editForm.get(['whtCode'])!.value,
      whtAmount: this.editForm.get(['whtAmount'])!.value,
      unplannedDeliveryTaxRate: this.editForm.get(['unplannedDeliveryTaxRate'])!.value,
      unplannedDeliveryTaxAmount: this.editForm.get(['unplannedDeliveryTaxAmount'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IInvoiceHeaderTaxDetails>>): void {
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
