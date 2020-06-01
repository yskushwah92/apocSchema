import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IOCRRawExtraction, OCRRawExtraction } from 'app/shared/model/ocr-raw-extraction.model';
import { OCRRawExtractionService } from './ocr-raw-extraction.service';
import { IInvoice } from 'app/shared/model/invoice.model';
import { InvoiceService } from 'app/entities/invoice/invoice.service';

@Component({
  selector: 'jhi-ocr-raw-extraction-update',
  templateUrl: './ocr-raw-extraction-update.component.html',
})
export class OCRRawExtractionUpdateComponent implements OnInit {
  isSaving = false;
  invoices: IInvoice[] = [];

  editForm = this.fb.group({
    id: [],
    name: [],
    fieldName: [],
    capturedfieldValue: [],
    actualFieldValue: [],
    language: [],
    documentType: [],
    accuracy: [],
    extractions: [],
    createdAt: [],
    createdBy: [],
    invoice: [],
  });

  constructor(
    protected oCRRawExtractionService: OCRRawExtractionService,
    protected invoiceService: InvoiceService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ oCRRawExtraction }) => {
      if (!oCRRawExtraction.id) {
        const today = moment().startOf('day');
        oCRRawExtraction.createdAt = today;
      }

      this.updateForm(oCRRawExtraction);

      this.invoiceService.query().subscribe((res: HttpResponse<IInvoice[]>) => (this.invoices = res.body || []));
    });
  }

  updateForm(oCRRawExtraction: IOCRRawExtraction): void {
    this.editForm.patchValue({
      id: oCRRawExtraction.id,
      name: oCRRawExtraction.name,
      fieldName: oCRRawExtraction.fieldName,
      capturedfieldValue: oCRRawExtraction.capturedfieldValue,
      actualFieldValue: oCRRawExtraction.actualFieldValue,
      language: oCRRawExtraction.language,
      documentType: oCRRawExtraction.documentType,
      accuracy: oCRRawExtraction.accuracy,
      extractions: oCRRawExtraction.extractions,
      createdAt: oCRRawExtraction.createdAt ? oCRRawExtraction.createdAt.format(DATE_TIME_FORMAT) : null,
      createdBy: oCRRawExtraction.createdBy,
      invoice: oCRRawExtraction.invoice,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const oCRRawExtraction = this.createFromForm();
    if (oCRRawExtraction.id !== undefined) {
      this.subscribeToSaveResponse(this.oCRRawExtractionService.update(oCRRawExtraction));
    } else {
      this.subscribeToSaveResponse(this.oCRRawExtractionService.create(oCRRawExtraction));
    }
  }

  private createFromForm(): IOCRRawExtraction {
    return {
      ...new OCRRawExtraction(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      fieldName: this.editForm.get(['fieldName'])!.value,
      capturedfieldValue: this.editForm.get(['capturedfieldValue'])!.value,
      actualFieldValue: this.editForm.get(['actualFieldValue'])!.value,
      language: this.editForm.get(['language'])!.value,
      documentType: this.editForm.get(['documentType'])!.value,
      accuracy: this.editForm.get(['accuracy'])!.value,
      extractions: this.editForm.get(['extractions'])!.value,
      createdAt: this.editForm.get(['createdAt'])!.value ? moment(this.editForm.get(['createdAt'])!.value, DATE_TIME_FORMAT) : undefined,
      createdBy: this.editForm.get(['createdBy'])!.value,
      invoice: this.editForm.get(['invoice'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOCRRawExtraction>>): void {
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
