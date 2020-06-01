import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IInvoiceLineItem, InvoiceLineItem } from 'app/shared/model/invoice-line-item.model';
import { InvoiceLineItemService } from './invoice-line-item.service';
import { IPurchaseOrderLine } from 'app/shared/model/purchase-order-line.model';
import { PurchaseOrderLineService } from 'app/entities/purchase-order-line/purchase-order-line.service';
import { IGLAccountDetails } from 'app/shared/model/gl-account-details.model';
import { GLAccountDetailsService } from 'app/entities/gl-account-details/gl-account-details.service';
import { IUser } from 'app/core/user/user.model';
import { UserService } from 'app/core/user/user.service';
import { IShippingLocation } from 'app/shared/model/shipping-location.model';
import { ShippingLocationService } from 'app/entities/shipping-location/shipping-location.service';
import { IProduct } from 'app/shared/model/product.model';
import { ProductService } from 'app/entities/product/product.service';
import { IGRNDetails } from 'app/shared/model/grn-details.model';
import { GRNDetailsService } from 'app/entities/grn-details/grn-details.service';
import { IInvoice } from 'app/shared/model/invoice.model';
import { InvoiceService } from 'app/entities/invoice/invoice.service';

type SelectableEntity = IPurchaseOrderLine | IGLAccountDetails | IUser | IShippingLocation | IProduct | IGRNDetails | IInvoice;

@Component({
  selector: 'jhi-invoice-line-item-update',
  templateUrl: './invoice-line-item-update.component.html',
})
export class InvoiceLineItemUpdateComponent implements OnInit {
  isSaving = false;
  purchaseorderlines: IPurchaseOrderLine[] = [];
  glaccountdetails: IGLAccountDetails[] = [];
  users: IUser[] = [];
  shippinglocations: IShippingLocation[] = [];
  products: IProduct[] = [];
  grndetails: IGRNDetails[] = [];
  invoices: IInvoice[] = [];

  editForm = this.fb.group({
    id: [],
    name: [],
    expenseType: [],
    distributionId: [],
    lineNumber: [],
    description: [],
    quantity: [],
    unitPrice: [],
    taxRate: [],
    taxDesc: [],
    taxAmount: [],
    lineAmount: [],
    grossLineAmount: [],
    confirmerId: [],
    poRequestBy: [],
    uom: [],
    debitCreditIndicator: [],
    availableQty: [],
    additionalCostPerUnit: [],
    taxBaseAmount: [],
    invoiceQty: [],
    invoiceUnitPrice: [],
    vatTaxRate: [],
    confirmerName: [],
    availableExpectedvalue: [],
    availableLimitValue: [],
    expectedValue: [],
    actualLimit: [],
    overallLimit: [],
    limitOrderExpiryDate: [],
    limitOrderStartDate: [],
    vatTaxCode: [],
    internalOrderStatistical: [],
    debitCreditValue: [],
    deliveryNote: [],
    reason: [],
    createdAt: [],
    createdBy: [],
    purchaseOrderLine: [],
    gLAccountDetails: [],
    technicalApprover: [],
    shippingLocation: [],
    product: [],
    gRNDetails: [],
    invoice: [],
  });

  constructor(
    protected invoiceLineItemService: InvoiceLineItemService,
    protected purchaseOrderLineService: PurchaseOrderLineService,
    protected gLAccountDetailsService: GLAccountDetailsService,
    protected userService: UserService,
    protected shippingLocationService: ShippingLocationService,
    protected productService: ProductService,
    protected gRNDetailsService: GRNDetailsService,
    protected invoiceService: InvoiceService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ invoiceLineItem }) => {
      if (!invoiceLineItem.id) {
        const today = moment().startOf('day');
        invoiceLineItem.limitOrderExpiryDate = today;
        invoiceLineItem.limitOrderStartDate = today;
        invoiceLineItem.createdAt = today;
      }

      this.updateForm(invoiceLineItem);

      this.purchaseOrderLineService
        .query({ filter: 'invoicelineitem-is-null' })
        .pipe(
          map((res: HttpResponse<IPurchaseOrderLine[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IPurchaseOrderLine[]) => {
          if (!invoiceLineItem.purchaseOrderLine || !invoiceLineItem.purchaseOrderLine.id) {
            this.purchaseorderlines = resBody;
          } else {
            this.purchaseOrderLineService
              .find(invoiceLineItem.purchaseOrderLine.id)
              .pipe(
                map((subRes: HttpResponse<IPurchaseOrderLine>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IPurchaseOrderLine[]) => (this.purchaseorderlines = concatRes));
          }
        });

      this.gLAccountDetailsService
        .query({ filter: 'invoicelineitem-is-null' })
        .pipe(
          map((res: HttpResponse<IGLAccountDetails[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IGLAccountDetails[]) => {
          if (!invoiceLineItem.gLAccountDetails || !invoiceLineItem.gLAccountDetails.id) {
            this.glaccountdetails = resBody;
          } else {
            this.gLAccountDetailsService
              .find(invoiceLineItem.gLAccountDetails.id)
              .pipe(
                map((subRes: HttpResponse<IGLAccountDetails>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IGLAccountDetails[]) => (this.glaccountdetails = concatRes));
          }
        });

      this.userService.query().subscribe((res: HttpResponse<IUser[]>) => (this.users = res.body || []));

      this.shippingLocationService
        .query({ filter: 'invoicelineitem-is-null' })
        .pipe(
          map((res: HttpResponse<IShippingLocation[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IShippingLocation[]) => {
          if (!invoiceLineItem.shippingLocation || !invoiceLineItem.shippingLocation.id) {
            this.shippinglocations = resBody;
          } else {
            this.shippingLocationService
              .find(invoiceLineItem.shippingLocation.id)
              .pipe(
                map((subRes: HttpResponse<IShippingLocation>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IShippingLocation[]) => (this.shippinglocations = concatRes));
          }
        });

      this.productService
        .query({ filter: 'invoicelineitem-is-null' })
        .pipe(
          map((res: HttpResponse<IProduct[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IProduct[]) => {
          if (!invoiceLineItem.product || !invoiceLineItem.product.id) {
            this.products = resBody;
          } else {
            this.productService
              .find(invoiceLineItem.product.id)
              .pipe(
                map((subRes: HttpResponse<IProduct>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IProduct[]) => (this.products = concatRes));
          }
        });

      this.gRNDetailsService
        .query({ filter: 'invoicelineitem-is-null' })
        .pipe(
          map((res: HttpResponse<IGRNDetails[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IGRNDetails[]) => {
          if (!invoiceLineItem.gRNDetails || !invoiceLineItem.gRNDetails.id) {
            this.grndetails = resBody;
          } else {
            this.gRNDetailsService
              .find(invoiceLineItem.gRNDetails.id)
              .pipe(
                map((subRes: HttpResponse<IGRNDetails>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IGRNDetails[]) => (this.grndetails = concatRes));
          }
        });

      this.invoiceService.query().subscribe((res: HttpResponse<IInvoice[]>) => (this.invoices = res.body || []));
    });
  }

  updateForm(invoiceLineItem: IInvoiceLineItem): void {
    this.editForm.patchValue({
      id: invoiceLineItem.id,
      name: invoiceLineItem.name,
      expenseType: invoiceLineItem.expenseType,
      distributionId: invoiceLineItem.distributionId,
      lineNumber: invoiceLineItem.lineNumber,
      description: invoiceLineItem.description,
      quantity: invoiceLineItem.quantity,
      unitPrice: invoiceLineItem.unitPrice,
      taxRate: invoiceLineItem.taxRate,
      taxDesc: invoiceLineItem.taxDesc,
      taxAmount: invoiceLineItem.taxAmount,
      lineAmount: invoiceLineItem.lineAmount,
      grossLineAmount: invoiceLineItem.grossLineAmount,
      confirmerId: invoiceLineItem.confirmerId,
      poRequestBy: invoiceLineItem.poRequestBy,
      uom: invoiceLineItem.uom,
      debitCreditIndicator: invoiceLineItem.debitCreditIndicator,
      availableQty: invoiceLineItem.availableQty,
      additionalCostPerUnit: invoiceLineItem.additionalCostPerUnit,
      taxBaseAmount: invoiceLineItem.taxBaseAmount,
      invoiceQty: invoiceLineItem.invoiceQty,
      invoiceUnitPrice: invoiceLineItem.invoiceUnitPrice,
      vatTaxRate: invoiceLineItem.vatTaxRate,
      confirmerName: invoiceLineItem.confirmerName,
      availableExpectedvalue: invoiceLineItem.availableExpectedvalue,
      availableLimitValue: invoiceLineItem.availableLimitValue,
      expectedValue: invoiceLineItem.expectedValue,
      actualLimit: invoiceLineItem.actualLimit,
      overallLimit: invoiceLineItem.overallLimit,
      limitOrderExpiryDate: invoiceLineItem.limitOrderExpiryDate ? invoiceLineItem.limitOrderExpiryDate.format(DATE_TIME_FORMAT) : null,
      limitOrderStartDate: invoiceLineItem.limitOrderStartDate ? invoiceLineItem.limitOrderStartDate.format(DATE_TIME_FORMAT) : null,
      vatTaxCode: invoiceLineItem.vatTaxCode,
      internalOrderStatistical: invoiceLineItem.internalOrderStatistical,
      debitCreditValue: invoiceLineItem.debitCreditValue,
      deliveryNote: invoiceLineItem.deliveryNote,
      reason: invoiceLineItem.reason,
      createdAt: invoiceLineItem.createdAt ? invoiceLineItem.createdAt.format(DATE_TIME_FORMAT) : null,
      createdBy: invoiceLineItem.createdBy,
      purchaseOrderLine: invoiceLineItem.purchaseOrderLine,
      gLAccountDetails: invoiceLineItem.gLAccountDetails,
      technicalApprover: invoiceLineItem.technicalApprover,
      shippingLocation: invoiceLineItem.shippingLocation,
      product: invoiceLineItem.product,
      gRNDetails: invoiceLineItem.gRNDetails,
      invoice: invoiceLineItem.invoice,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const invoiceLineItem = this.createFromForm();
    if (invoiceLineItem.id !== undefined) {
      this.subscribeToSaveResponse(this.invoiceLineItemService.update(invoiceLineItem));
    } else {
      this.subscribeToSaveResponse(this.invoiceLineItemService.create(invoiceLineItem));
    }
  }

  private createFromForm(): IInvoiceLineItem {
    return {
      ...new InvoiceLineItem(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      expenseType: this.editForm.get(['expenseType'])!.value,
      distributionId: this.editForm.get(['distributionId'])!.value,
      lineNumber: this.editForm.get(['lineNumber'])!.value,
      description: this.editForm.get(['description'])!.value,
      quantity: this.editForm.get(['quantity'])!.value,
      unitPrice: this.editForm.get(['unitPrice'])!.value,
      taxRate: this.editForm.get(['taxRate'])!.value,
      taxDesc: this.editForm.get(['taxDesc'])!.value,
      taxAmount: this.editForm.get(['taxAmount'])!.value,
      lineAmount: this.editForm.get(['lineAmount'])!.value,
      grossLineAmount: this.editForm.get(['grossLineAmount'])!.value,
      confirmerId: this.editForm.get(['confirmerId'])!.value,
      poRequestBy: this.editForm.get(['poRequestBy'])!.value,
      uom: this.editForm.get(['uom'])!.value,
      debitCreditIndicator: this.editForm.get(['debitCreditIndicator'])!.value,
      availableQty: this.editForm.get(['availableQty'])!.value,
      additionalCostPerUnit: this.editForm.get(['additionalCostPerUnit'])!.value,
      taxBaseAmount: this.editForm.get(['taxBaseAmount'])!.value,
      invoiceQty: this.editForm.get(['invoiceQty'])!.value,
      invoiceUnitPrice: this.editForm.get(['invoiceUnitPrice'])!.value,
      vatTaxRate: this.editForm.get(['vatTaxRate'])!.value,
      confirmerName: this.editForm.get(['confirmerName'])!.value,
      availableExpectedvalue: this.editForm.get(['availableExpectedvalue'])!.value,
      availableLimitValue: this.editForm.get(['availableLimitValue'])!.value,
      expectedValue: this.editForm.get(['expectedValue'])!.value,
      actualLimit: this.editForm.get(['actualLimit'])!.value,
      overallLimit: this.editForm.get(['overallLimit'])!.value,
      limitOrderExpiryDate: this.editForm.get(['limitOrderExpiryDate'])!.value
        ? moment(this.editForm.get(['limitOrderExpiryDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      limitOrderStartDate: this.editForm.get(['limitOrderStartDate'])!.value
        ? moment(this.editForm.get(['limitOrderStartDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      vatTaxCode: this.editForm.get(['vatTaxCode'])!.value,
      internalOrderStatistical: this.editForm.get(['internalOrderStatistical'])!.value,
      debitCreditValue: this.editForm.get(['debitCreditValue'])!.value,
      deliveryNote: this.editForm.get(['deliveryNote'])!.value,
      reason: this.editForm.get(['reason'])!.value,
      createdAt: this.editForm.get(['createdAt'])!.value ? moment(this.editForm.get(['createdAt'])!.value, DATE_TIME_FORMAT) : undefined,
      createdBy: this.editForm.get(['createdBy'])!.value,
      purchaseOrderLine: this.editForm.get(['purchaseOrderLine'])!.value,
      gLAccountDetails: this.editForm.get(['gLAccountDetails'])!.value,
      technicalApprover: this.editForm.get(['technicalApprover'])!.value,
      shippingLocation: this.editForm.get(['shippingLocation'])!.value,
      product: this.editForm.get(['product'])!.value,
      gRNDetails: this.editForm.get(['gRNDetails'])!.value,
      invoice: this.editForm.get(['invoice'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IInvoiceLineItem>>): void {
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
