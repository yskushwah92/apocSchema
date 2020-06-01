import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IInvoiceHeader, InvoiceHeader } from 'app/shared/model/invoice-header.model';
import { InvoiceHeaderService } from './invoice-header.service';
import { IGRNDetails } from 'app/shared/model/grn-details.model';
import { GRNDetailsService } from 'app/entities/grn-details/grn-details.service';

@Component({
  selector: 'jhi-invoice-header-update',
  templateUrl: './invoice-header-update.component.html',
})
export class InvoiceHeaderUpdateComponent implements OnInit {
  isSaving = false;
  grndetails: IGRNDetails[] = [];

  editForm = this.fb.group({
    id: [],
    companyCode: [],
    legalEntityName: [],
    comments: [],
    grossAmount: [],
    netAmount: [],
    currency: [],
    country: [],
    countryKey: [],
    languageKey: [],
    erpReferenceNumber: [],
    poBoxCode: [],
    scanDate: [],
    receivedDate: [],
    caseCreationDate: [],
    numberOfScannedPages: [],
    sla: [],
    slaExpirationDate: [],
    tradingPartner: [],
    deliveryDate: [],
    deliveryNoteNumber: [],
    recepientEmail: [],
    isCaseClose: [],
    ocrRequired: [],
    barcode: [],
    functionalCurrency: [],
    reportingCurrency: [],
    approverRequired: [],
    siteCode: [],
    sortCode: [],
    discount: [],
    additionalCharges: [],
    sumLineAmount: [],
    conversionRate: [],
    paymentCurrency: [],
    liabilityAccount: [],
    postingDate: [],
    termDate: [],
    shippingAmount: [],
    createdAt: [],
    createdBy: [],
    gRNDetails: [],
  });

  constructor(
    protected invoiceHeaderService: InvoiceHeaderService,
    protected gRNDetailsService: GRNDetailsService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ invoiceHeader }) => {
      if (!invoiceHeader.id) {
        const today = moment().startOf('day');
        invoiceHeader.scanDate = today;
        invoiceHeader.receivedDate = today;
        invoiceHeader.caseCreationDate = today;
        invoiceHeader.slaExpirationDate = today;
        invoiceHeader.deliveryDate = today;
        invoiceHeader.deliveryNoteNumber = today;
        invoiceHeader.postingDate = today;
        invoiceHeader.termDate = today;
        invoiceHeader.createdAt = today;
      }

      this.updateForm(invoiceHeader);

      this.gRNDetailsService
        .query({ filter: 'invoiceheader-is-null' })
        .pipe(
          map((res: HttpResponse<IGRNDetails[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IGRNDetails[]) => {
          if (!invoiceHeader.gRNDetails || !invoiceHeader.gRNDetails.id) {
            this.grndetails = resBody;
          } else {
            this.gRNDetailsService
              .find(invoiceHeader.gRNDetails.id)
              .pipe(
                map((subRes: HttpResponse<IGRNDetails>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IGRNDetails[]) => (this.grndetails = concatRes));
          }
        });
    });
  }

  updateForm(invoiceHeader: IInvoiceHeader): void {
    this.editForm.patchValue({
      id: invoiceHeader.id,
      companyCode: invoiceHeader.companyCode,
      legalEntityName: invoiceHeader.legalEntityName,
      comments: invoiceHeader.comments,
      grossAmount: invoiceHeader.grossAmount,
      netAmount: invoiceHeader.netAmount,
      currency: invoiceHeader.currency,
      country: invoiceHeader.country,
      countryKey: invoiceHeader.countryKey,
      languageKey: invoiceHeader.languageKey,
      erpReferenceNumber: invoiceHeader.erpReferenceNumber,
      poBoxCode: invoiceHeader.poBoxCode,
      scanDate: invoiceHeader.scanDate ? invoiceHeader.scanDate.format(DATE_TIME_FORMAT) : null,
      receivedDate: invoiceHeader.receivedDate ? invoiceHeader.receivedDate.format(DATE_TIME_FORMAT) : null,
      caseCreationDate: invoiceHeader.caseCreationDate ? invoiceHeader.caseCreationDate.format(DATE_TIME_FORMAT) : null,
      numberOfScannedPages: invoiceHeader.numberOfScannedPages,
      sla: invoiceHeader.sla,
      slaExpirationDate: invoiceHeader.slaExpirationDate ? invoiceHeader.slaExpirationDate.format(DATE_TIME_FORMAT) : null,
      tradingPartner: invoiceHeader.tradingPartner,
      deliveryDate: invoiceHeader.deliveryDate ? invoiceHeader.deliveryDate.format(DATE_TIME_FORMAT) : null,
      deliveryNoteNumber: invoiceHeader.deliveryNoteNumber ? invoiceHeader.deliveryNoteNumber.format(DATE_TIME_FORMAT) : null,
      recepientEmail: invoiceHeader.recepientEmail,
      isCaseClose: invoiceHeader.isCaseClose,
      ocrRequired: invoiceHeader.ocrRequired,
      barcode: invoiceHeader.barcode,
      functionalCurrency: invoiceHeader.functionalCurrency,
      reportingCurrency: invoiceHeader.reportingCurrency,
      approverRequired: invoiceHeader.approverRequired,
      siteCode: invoiceHeader.siteCode,
      sortCode: invoiceHeader.sortCode,
      discount: invoiceHeader.discount,
      additionalCharges: invoiceHeader.additionalCharges,
      sumLineAmount: invoiceHeader.sumLineAmount,
      conversionRate: invoiceHeader.conversionRate,
      paymentCurrency: invoiceHeader.paymentCurrency,
      liabilityAccount: invoiceHeader.liabilityAccount,
      postingDate: invoiceHeader.postingDate ? invoiceHeader.postingDate.format(DATE_TIME_FORMAT) : null,
      termDate: invoiceHeader.termDate ? invoiceHeader.termDate.format(DATE_TIME_FORMAT) : null,
      shippingAmount: invoiceHeader.shippingAmount,
      createdAt: invoiceHeader.createdAt ? invoiceHeader.createdAt.format(DATE_TIME_FORMAT) : null,
      createdBy: invoiceHeader.createdBy,
      gRNDetails: invoiceHeader.gRNDetails,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const invoiceHeader = this.createFromForm();
    if (invoiceHeader.id !== undefined) {
      this.subscribeToSaveResponse(this.invoiceHeaderService.update(invoiceHeader));
    } else {
      this.subscribeToSaveResponse(this.invoiceHeaderService.create(invoiceHeader));
    }
  }

  private createFromForm(): IInvoiceHeader {
    return {
      ...new InvoiceHeader(),
      id: this.editForm.get(['id'])!.value,
      companyCode: this.editForm.get(['companyCode'])!.value,
      legalEntityName: this.editForm.get(['legalEntityName'])!.value,
      comments: this.editForm.get(['comments'])!.value,
      grossAmount: this.editForm.get(['grossAmount'])!.value,
      netAmount: this.editForm.get(['netAmount'])!.value,
      currency: this.editForm.get(['currency'])!.value,
      country: this.editForm.get(['country'])!.value,
      countryKey: this.editForm.get(['countryKey'])!.value,
      languageKey: this.editForm.get(['languageKey'])!.value,
      erpReferenceNumber: this.editForm.get(['erpReferenceNumber'])!.value,
      poBoxCode: this.editForm.get(['poBoxCode'])!.value,
      scanDate: this.editForm.get(['scanDate'])!.value ? moment(this.editForm.get(['scanDate'])!.value, DATE_TIME_FORMAT) : undefined,
      receivedDate: this.editForm.get(['receivedDate'])!.value
        ? moment(this.editForm.get(['receivedDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      caseCreationDate: this.editForm.get(['caseCreationDate'])!.value
        ? moment(this.editForm.get(['caseCreationDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      numberOfScannedPages: this.editForm.get(['numberOfScannedPages'])!.value,
      sla: this.editForm.get(['sla'])!.value,
      slaExpirationDate: this.editForm.get(['slaExpirationDate'])!.value
        ? moment(this.editForm.get(['slaExpirationDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      tradingPartner: this.editForm.get(['tradingPartner'])!.value,
      deliveryDate: this.editForm.get(['deliveryDate'])!.value
        ? moment(this.editForm.get(['deliveryDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      deliveryNoteNumber: this.editForm.get(['deliveryNoteNumber'])!.value
        ? moment(this.editForm.get(['deliveryNoteNumber'])!.value, DATE_TIME_FORMAT)
        : undefined,
      recepientEmail: this.editForm.get(['recepientEmail'])!.value,
      isCaseClose: this.editForm.get(['isCaseClose'])!.value,
      ocrRequired: this.editForm.get(['ocrRequired'])!.value,
      barcode: this.editForm.get(['barcode'])!.value,
      functionalCurrency: this.editForm.get(['functionalCurrency'])!.value,
      reportingCurrency: this.editForm.get(['reportingCurrency'])!.value,
      approverRequired: this.editForm.get(['approverRequired'])!.value,
      siteCode: this.editForm.get(['siteCode'])!.value,
      sortCode: this.editForm.get(['sortCode'])!.value,
      discount: this.editForm.get(['discount'])!.value,
      additionalCharges: this.editForm.get(['additionalCharges'])!.value,
      sumLineAmount: this.editForm.get(['sumLineAmount'])!.value,
      conversionRate: this.editForm.get(['conversionRate'])!.value,
      paymentCurrency: this.editForm.get(['paymentCurrency'])!.value,
      liabilityAccount: this.editForm.get(['liabilityAccount'])!.value,
      postingDate: this.editForm.get(['postingDate'])!.value
        ? moment(this.editForm.get(['postingDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      termDate: this.editForm.get(['termDate'])!.value ? moment(this.editForm.get(['termDate'])!.value, DATE_TIME_FORMAT) : undefined,
      shippingAmount: this.editForm.get(['shippingAmount'])!.value,
      createdAt: this.editForm.get(['createdAt'])!.value ? moment(this.editForm.get(['createdAt'])!.value, DATE_TIME_FORMAT) : undefined,
      createdBy: this.editForm.get(['createdBy'])!.value,
      gRNDetails: this.editForm.get(['gRNDetails'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IInvoiceHeader>>): void {
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

  trackById(index: number, item: IGRNDetails): any {
    return item.id;
  }
}
