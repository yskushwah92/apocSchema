import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IInvoice, Invoice } from 'app/shared/model/invoice.model';
import { InvoiceService } from './invoice.service';
import { IFileInfo } from 'app/shared/model/file-info.model';
import { FileInfoService } from 'app/entities/file-info/file-info.service';
import { IModelInfo } from 'app/shared/model/model-info.model';
import { ModelInfoService } from 'app/entities/model-info/model-info.service';
import { INotificationInfo } from 'app/shared/model/notification-info.model';
import { NotificationInfoService } from 'app/entities/notification-info/notification-info.service';
import { IPaymentInfo } from 'app/shared/model/payment-info.model';
import { PaymentInfoService } from 'app/entities/payment-info/payment-info.service';
import { IInvoiceHeader } from 'app/shared/model/invoice-header.model';
import { InvoiceHeaderService } from 'app/entities/invoice-header/invoice-header.service';
import { IVendor } from 'app/shared/model/vendor.model';
import { VendorService } from 'app/entities/vendor/vendor.service';
import { IPurchaseOrder } from 'app/shared/model/purchase-order.model';
import { PurchaseOrderService } from 'app/entities/purchase-order/purchase-order.service';

type SelectableEntity = IFileInfo | IModelInfo | INotificationInfo | IPaymentInfo | IInvoiceHeader | IVendor | IInvoice | IPurchaseOrder;

@Component({
  selector: 'jhi-invoice-update',
  templateUrl: './invoice-update.component.html',
})
export class InvoiceUpdateComponent implements OnInit {
  isSaving = false;
  fileinfos: IFileInfo[] = [];
  modelinfos: IModelInfo[] = [];
  notificationinfos: INotificationInfo[] = [];
  paymentinfos: IPaymentInfo[] = [];
  invoiceheaders: IInvoiceHeader[] = [];
  vendors: IVendor[] = [];
  invoices: IInvoice[] = [];
  purchaseorders: IPurchaseOrder[] = [];

  editForm = this.fb.group({
    id: [],
    name: [],
    serialNo: [],
    invoiceNo: [],
    referenceNo: [],
    caseNo: [],
    description: [],
    priority: [],
    type: [],
    dateOfCreation: [],
    sellerName: [],
    amountFunctional: [],
    amountReporting: [],
    price: [],
    issuer: [],
    tax: [],
    inclusiveOfTax: [],
    status: [],
    gstn: [],
    address: [],
    category: [],
    contactNo: [],
    email: [],
    prefferredModeOfCommunication: [],
    pointOfContact: [],
    bankAccDetails: [],
    upiDetails: [],
    preferredModeOfPayment: [],
    createdAt: [],
    createdBy: [],
    fileInfo: [],
    modelInfo: [],
    notificationInfo: [],
    paymentInfo: [],
    invoiceHeader: [],
    vendor: [],
    invoice: [],
    purchaseOrder: [],
  });

  constructor(
    protected invoiceService: InvoiceService,
    protected fileInfoService: FileInfoService,
    protected modelInfoService: ModelInfoService,
    protected notificationInfoService: NotificationInfoService,
    protected paymentInfoService: PaymentInfoService,
    protected invoiceHeaderService: InvoiceHeaderService,
    protected vendorService: VendorService,
    protected purchaseOrderService: PurchaseOrderService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ invoice }) => {
      if (!invoice.id) {
        const today = moment().startOf('day');
        invoice.dateOfCreation = today;
        invoice.createdAt = today;
      }

      this.updateForm(invoice);

      this.fileInfoService
        .query({ filter: 'invoice-is-null' })
        .pipe(
          map((res: HttpResponse<IFileInfo[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IFileInfo[]) => {
          if (!invoice.fileInfo || !invoice.fileInfo.id) {
            this.fileinfos = resBody;
          } else {
            this.fileInfoService
              .find(invoice.fileInfo.id)
              .pipe(
                map((subRes: HttpResponse<IFileInfo>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IFileInfo[]) => (this.fileinfos = concatRes));
          }
        });

      this.modelInfoService
        .query({ filter: 'invoice-is-null' })
        .pipe(
          map((res: HttpResponse<IModelInfo[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IModelInfo[]) => {
          if (!invoice.modelInfo || !invoice.modelInfo.id) {
            this.modelinfos = resBody;
          } else {
            this.modelInfoService
              .find(invoice.modelInfo.id)
              .pipe(
                map((subRes: HttpResponse<IModelInfo>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IModelInfo[]) => (this.modelinfos = concatRes));
          }
        });

      this.notificationInfoService
        .query({ filter: 'invoice-is-null' })
        .pipe(
          map((res: HttpResponse<INotificationInfo[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: INotificationInfo[]) => {
          if (!invoice.notificationInfo || !invoice.notificationInfo.id) {
            this.notificationinfos = resBody;
          } else {
            this.notificationInfoService
              .find(invoice.notificationInfo.id)
              .pipe(
                map((subRes: HttpResponse<INotificationInfo>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: INotificationInfo[]) => (this.notificationinfos = concatRes));
          }
        });

      this.paymentInfoService
        .query({ filter: 'invoice-is-null' })
        .pipe(
          map((res: HttpResponse<IPaymentInfo[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IPaymentInfo[]) => {
          if (!invoice.paymentInfo || !invoice.paymentInfo.id) {
            this.paymentinfos = resBody;
          } else {
            this.paymentInfoService
              .find(invoice.paymentInfo.id)
              .pipe(
                map((subRes: HttpResponse<IPaymentInfo>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IPaymentInfo[]) => (this.paymentinfos = concatRes));
          }
        });

      this.invoiceHeaderService
        .query({ filter: 'invoice-is-null' })
        .pipe(
          map((res: HttpResponse<IInvoiceHeader[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IInvoiceHeader[]) => {
          if (!invoice.invoiceHeader || !invoice.invoiceHeader.id) {
            this.invoiceheaders = resBody;
          } else {
            this.invoiceHeaderService
              .find(invoice.invoiceHeader.id)
              .pipe(
                map((subRes: HttpResponse<IInvoiceHeader>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IInvoiceHeader[]) => (this.invoiceheaders = concatRes));
          }
        });

      this.vendorService.query().subscribe((res: HttpResponse<IVendor[]>) => (this.vendors = res.body || []));

      this.invoiceService.query().subscribe((res: HttpResponse<IInvoice[]>) => (this.invoices = res.body || []));

      this.purchaseOrderService.query().subscribe((res: HttpResponse<IPurchaseOrder[]>) => (this.purchaseorders = res.body || []));
    });
  }

  updateForm(invoice: IInvoice): void {
    this.editForm.patchValue({
      id: invoice.id,
      name: invoice.name,
      serialNo: invoice.serialNo,
      invoiceNo: invoice.invoiceNo,
      referenceNo: invoice.referenceNo,
      caseNo: invoice.caseNo,
      description: invoice.description,
      priority: invoice.priority,
      type: invoice.type,
      dateOfCreation: invoice.dateOfCreation ? invoice.dateOfCreation.format(DATE_TIME_FORMAT) : null,
      sellerName: invoice.sellerName,
      amountFunctional: invoice.amountFunctional,
      amountReporting: invoice.amountReporting,
      price: invoice.price,
      issuer: invoice.issuer,
      tax: invoice.tax,
      inclusiveOfTax: invoice.inclusiveOfTax,
      status: invoice.status,
      gstn: invoice.gstn,
      address: invoice.address,
      category: invoice.category,
      contactNo: invoice.contactNo,
      email: invoice.email,
      prefferredModeOfCommunication: invoice.prefferredModeOfCommunication,
      pointOfContact: invoice.pointOfContact,
      bankAccDetails: invoice.bankAccDetails,
      upiDetails: invoice.upiDetails,
      preferredModeOfPayment: invoice.preferredModeOfPayment,
      createdAt: invoice.createdAt ? invoice.createdAt.format(DATE_TIME_FORMAT) : null,
      createdBy: invoice.createdBy,
      fileInfo: invoice.fileInfo,
      modelInfo: invoice.modelInfo,
      notificationInfo: invoice.notificationInfo,
      paymentInfo: invoice.paymentInfo,
      invoiceHeader: invoice.invoiceHeader,
      vendor: invoice.vendor,
      invoice: invoice.invoice,
      purchaseOrder: invoice.purchaseOrder,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const invoice = this.createFromForm();
    if (invoice.id !== undefined) {
      this.subscribeToSaveResponse(this.invoiceService.update(invoice));
    } else {
      this.subscribeToSaveResponse(this.invoiceService.create(invoice));
    }
  }

  private createFromForm(): IInvoice {
    return {
      ...new Invoice(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      serialNo: this.editForm.get(['serialNo'])!.value,
      invoiceNo: this.editForm.get(['invoiceNo'])!.value,
      referenceNo: this.editForm.get(['referenceNo'])!.value,
      caseNo: this.editForm.get(['caseNo'])!.value,
      description: this.editForm.get(['description'])!.value,
      priority: this.editForm.get(['priority'])!.value,
      type: this.editForm.get(['type'])!.value,
      dateOfCreation: this.editForm.get(['dateOfCreation'])!.value
        ? moment(this.editForm.get(['dateOfCreation'])!.value, DATE_TIME_FORMAT)
        : undefined,
      sellerName: this.editForm.get(['sellerName'])!.value,
      amountFunctional: this.editForm.get(['amountFunctional'])!.value,
      amountReporting: this.editForm.get(['amountReporting'])!.value,
      price: this.editForm.get(['price'])!.value,
      issuer: this.editForm.get(['issuer'])!.value,
      tax: this.editForm.get(['tax'])!.value,
      inclusiveOfTax: this.editForm.get(['inclusiveOfTax'])!.value,
      status: this.editForm.get(['status'])!.value,
      gstn: this.editForm.get(['gstn'])!.value,
      address: this.editForm.get(['address'])!.value,
      category: this.editForm.get(['category'])!.value,
      contactNo: this.editForm.get(['contactNo'])!.value,
      email: this.editForm.get(['email'])!.value,
      prefferredModeOfCommunication: this.editForm.get(['prefferredModeOfCommunication'])!.value,
      pointOfContact: this.editForm.get(['pointOfContact'])!.value,
      bankAccDetails: this.editForm.get(['bankAccDetails'])!.value,
      upiDetails: this.editForm.get(['upiDetails'])!.value,
      preferredModeOfPayment: this.editForm.get(['preferredModeOfPayment'])!.value,
      createdAt: this.editForm.get(['createdAt'])!.value ? moment(this.editForm.get(['createdAt'])!.value, DATE_TIME_FORMAT) : undefined,
      createdBy: this.editForm.get(['createdBy'])!.value,
      fileInfo: this.editForm.get(['fileInfo'])!.value,
      modelInfo: this.editForm.get(['modelInfo'])!.value,
      notificationInfo: this.editForm.get(['notificationInfo'])!.value,
      paymentInfo: this.editForm.get(['paymentInfo'])!.value,
      invoiceHeader: this.editForm.get(['invoiceHeader'])!.value,
      vendor: this.editForm.get(['vendor'])!.value,
      invoice: this.editForm.get(['invoice'])!.value,
      purchaseOrder: this.editForm.get(['purchaseOrder'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IInvoice>>): void {
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
