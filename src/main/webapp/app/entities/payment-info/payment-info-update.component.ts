import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IPaymentInfo, PaymentInfo } from 'app/shared/model/payment-info.model';
import { PaymentInfoService } from './payment-info.service';
import { IInvoice } from 'app/shared/model/invoice.model';
import { InvoiceService } from 'app/entities/invoice/invoice.service';

type SelectableEntity = IPaymentInfo | IInvoice;

@Component({
  selector: 'jhi-payment-info-update',
  templateUrl: './payment-info-update.component.html',
})
export class PaymentInfoUpdateComponent implements OnInit {
  isSaving = false;
  paymentinfos: IPaymentInfo[] = [];
  invoices: IInvoice[] = [];

  editForm = this.fb.group({
    id: [],
    name: [],
    terms: [],
    mode: [],
    dueDate: [],
    status: [],
    paymentChannel: [],
    type: [],
    approvalStatus: [],
    dateOfApproval: [],
    dateOfPayment: [],
    paymentReferenceNumber: [],
    checkDate: [],
    checkNumber: [],
    checkAmount: [],
    earlyPaymentDate: [],
    earlyPaymentDiscount: [],
    earlyPaymentAmount: [],
    earlyPaymentRemarks: [],
    paymentDocDate: [],
    paymentDocNo: [],
    paymentAmount: [],
    discountTaken: [],
    discountLost: [],
    paymentCurrency: [],
    invoiceBaseAmount: [],
    clearedDate: [],
    clearedAmount: [],
    transactionId: [],
    createdAt: [],
    createdBy: [],
    paymentInfo: [],
    invoice: [],
  });

  constructor(
    protected paymentInfoService: PaymentInfoService,
    protected invoiceService: InvoiceService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ paymentInfo }) => {
      if (!paymentInfo.id) {
        const today = moment().startOf('day');
        paymentInfo.dueDate = today;
        paymentInfo.dateOfApproval = today;
        paymentInfo.dateOfPayment = today;
        paymentInfo.checkDate = today;
        paymentInfo.earlyPaymentDate = today;
        paymentInfo.paymentDocDate = today;
        paymentInfo.clearedDate = today;
        paymentInfo.createdAt = today;
      }

      this.updateForm(paymentInfo);

      this.paymentInfoService
        .query({ filter: 'paymentinfo-is-null' })
        .pipe(
          map((res: HttpResponse<IPaymentInfo[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IPaymentInfo[]) => {
          if (!paymentInfo.paymentInfo || !paymentInfo.paymentInfo.id) {
            this.paymentinfos = resBody;
          } else {
            this.paymentInfoService
              .find(paymentInfo.paymentInfo.id)
              .pipe(
                map((subRes: HttpResponse<IPaymentInfo>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IPaymentInfo[]) => (this.paymentinfos = concatRes));
          }
        });

      this.invoiceService.query().subscribe((res: HttpResponse<IInvoice[]>) => (this.invoices = res.body || []));
    });
  }

  updateForm(paymentInfo: IPaymentInfo): void {
    this.editForm.patchValue({
      id: paymentInfo.id,
      name: paymentInfo.name,
      terms: paymentInfo.terms,
      mode: paymentInfo.mode,
      dueDate: paymentInfo.dueDate ? paymentInfo.dueDate.format(DATE_TIME_FORMAT) : null,
      status: paymentInfo.status,
      paymentChannel: paymentInfo.paymentChannel,
      type: paymentInfo.type,
      approvalStatus: paymentInfo.approvalStatus,
      dateOfApproval: paymentInfo.dateOfApproval ? paymentInfo.dateOfApproval.format(DATE_TIME_FORMAT) : null,
      dateOfPayment: paymentInfo.dateOfPayment ? paymentInfo.dateOfPayment.format(DATE_TIME_FORMAT) : null,
      paymentReferenceNumber: paymentInfo.paymentReferenceNumber,
      checkDate: paymentInfo.checkDate ? paymentInfo.checkDate.format(DATE_TIME_FORMAT) : null,
      checkNumber: paymentInfo.checkNumber,
      checkAmount: paymentInfo.checkAmount,
      earlyPaymentDate: paymentInfo.earlyPaymentDate ? paymentInfo.earlyPaymentDate.format(DATE_TIME_FORMAT) : null,
      earlyPaymentDiscount: paymentInfo.earlyPaymentDiscount,
      earlyPaymentAmount: paymentInfo.earlyPaymentAmount,
      earlyPaymentRemarks: paymentInfo.earlyPaymentRemarks,
      paymentDocDate: paymentInfo.paymentDocDate ? paymentInfo.paymentDocDate.format(DATE_TIME_FORMAT) : null,
      paymentDocNo: paymentInfo.paymentDocNo,
      paymentAmount: paymentInfo.paymentAmount,
      discountTaken: paymentInfo.discountTaken,
      discountLost: paymentInfo.discountLost,
      paymentCurrency: paymentInfo.paymentCurrency,
      invoiceBaseAmount: paymentInfo.invoiceBaseAmount,
      clearedDate: paymentInfo.clearedDate ? paymentInfo.clearedDate.format(DATE_TIME_FORMAT) : null,
      clearedAmount: paymentInfo.clearedAmount,
      transactionId: paymentInfo.transactionId,
      createdAt: paymentInfo.createdAt ? paymentInfo.createdAt.format(DATE_TIME_FORMAT) : null,
      createdBy: paymentInfo.createdBy,
      paymentInfo: paymentInfo.paymentInfo,
      invoice: paymentInfo.invoice,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const paymentInfo = this.createFromForm();
    if (paymentInfo.id !== undefined) {
      this.subscribeToSaveResponse(this.paymentInfoService.update(paymentInfo));
    } else {
      this.subscribeToSaveResponse(this.paymentInfoService.create(paymentInfo));
    }
  }

  private createFromForm(): IPaymentInfo {
    return {
      ...new PaymentInfo(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      terms: this.editForm.get(['terms'])!.value,
      mode: this.editForm.get(['mode'])!.value,
      dueDate: this.editForm.get(['dueDate'])!.value ? moment(this.editForm.get(['dueDate'])!.value, DATE_TIME_FORMAT) : undefined,
      status: this.editForm.get(['status'])!.value,
      paymentChannel: this.editForm.get(['paymentChannel'])!.value,
      type: this.editForm.get(['type'])!.value,
      approvalStatus: this.editForm.get(['approvalStatus'])!.value,
      dateOfApproval: this.editForm.get(['dateOfApproval'])!.value
        ? moment(this.editForm.get(['dateOfApproval'])!.value, DATE_TIME_FORMAT)
        : undefined,
      dateOfPayment: this.editForm.get(['dateOfPayment'])!.value
        ? moment(this.editForm.get(['dateOfPayment'])!.value, DATE_TIME_FORMAT)
        : undefined,
      paymentReferenceNumber: this.editForm.get(['paymentReferenceNumber'])!.value,
      checkDate: this.editForm.get(['checkDate'])!.value ? moment(this.editForm.get(['checkDate'])!.value, DATE_TIME_FORMAT) : undefined,
      checkNumber: this.editForm.get(['checkNumber'])!.value,
      checkAmount: this.editForm.get(['checkAmount'])!.value,
      earlyPaymentDate: this.editForm.get(['earlyPaymentDate'])!.value
        ? moment(this.editForm.get(['earlyPaymentDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      earlyPaymentDiscount: this.editForm.get(['earlyPaymentDiscount'])!.value,
      earlyPaymentAmount: this.editForm.get(['earlyPaymentAmount'])!.value,
      earlyPaymentRemarks: this.editForm.get(['earlyPaymentRemarks'])!.value,
      paymentDocDate: this.editForm.get(['paymentDocDate'])!.value
        ? moment(this.editForm.get(['paymentDocDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      paymentDocNo: this.editForm.get(['paymentDocNo'])!.value,
      paymentAmount: this.editForm.get(['paymentAmount'])!.value,
      discountTaken: this.editForm.get(['discountTaken'])!.value,
      discountLost: this.editForm.get(['discountLost'])!.value,
      paymentCurrency: this.editForm.get(['paymentCurrency'])!.value,
      invoiceBaseAmount: this.editForm.get(['invoiceBaseAmount'])!.value,
      clearedDate: this.editForm.get(['clearedDate'])!.value
        ? moment(this.editForm.get(['clearedDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      clearedAmount: this.editForm.get(['clearedAmount'])!.value,
      transactionId: this.editForm.get(['transactionId'])!.value,
      createdAt: this.editForm.get(['createdAt'])!.value ? moment(this.editForm.get(['createdAt'])!.value, DATE_TIME_FORMAT) : undefined,
      createdBy: this.editForm.get(['createdBy'])!.value,
      paymentInfo: this.editForm.get(['paymentInfo'])!.value,
      invoice: this.editForm.get(['invoice'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPaymentInfo>>): void {
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

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
