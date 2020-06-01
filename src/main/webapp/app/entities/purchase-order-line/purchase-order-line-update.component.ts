import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IPurchaseOrderLine, PurchaseOrderLine } from 'app/shared/model/purchase-order-line.model';
import { PurchaseOrderLineService } from './purchase-order-line.service';
import { IOrganization } from 'app/shared/model/organization.model';
import { OrganizationService } from 'app/entities/organization/organization.service';
import { IVendor } from 'app/shared/model/vendor.model';
import { VendorService } from 'app/entities/vendor/vendor.service';
import { IGLAccountDetails } from 'app/shared/model/gl-account-details.model';
import { GLAccountDetailsService } from 'app/entities/gl-account-details/gl-account-details.service';
import { IPurchaseOrder } from 'app/shared/model/purchase-order.model';
import { PurchaseOrderService } from 'app/entities/purchase-order/purchase-order.service';

type SelectableEntity = IOrganization | IVendor | IGLAccountDetails | IPurchaseOrder;

@Component({
  selector: 'jhi-purchase-order-line-update',
  templateUrl: './purchase-order-line-update.component.html',
})
export class PurchaseOrderLineUpdateComponent implements OnInit {
  isSaving = false;
  organizations: IOrganization[] = [];
  vendors: IVendor[] = [];
  glaccountdetails: IGLAccountDetails[] = [];
  purchaseorders: IPurchaseOrder[] = [];

  editForm = this.fb.group({
    id: [],
    country: [],
    articleNo: [],
    qty: [],
    receivedQty: [],
    billedQty: [],
    unitOfMeasurement: [],
    currency: [],
    lineAmount: [],
    lineAmountExclTax: [],
    unitPrice: [],
    costCentre: [],
    costCentreid: [],
    articleDescription: [],
    deliveryReceiptNo: [],
    deliveryDate: [],
    hsnCode: [],
    tolerance: [],
    tolerancePrice: [],
    receiptReqd: [],
    acceptedQty: [],
    amountReceived: [],
    availableQty: [],
    createdAt: [],
    createdBy: [],
    organization: [],
    vendor: [],
    gLAccountDetails: [],
    purchaseOrder: [],
  });

  constructor(
    protected purchaseOrderLineService: PurchaseOrderLineService,
    protected organizationService: OrganizationService,
    protected vendorService: VendorService,
    protected gLAccountDetailsService: GLAccountDetailsService,
    protected purchaseOrderService: PurchaseOrderService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ purchaseOrderLine }) => {
      if (!purchaseOrderLine.id) {
        const today = moment().startOf('day');
        purchaseOrderLine.deliveryDate = today;
        purchaseOrderLine.createdAt = today;
      }

      this.updateForm(purchaseOrderLine);

      this.organizationService
        .query({ filter: 'purchaseorderline-is-null' })
        .pipe(
          map((res: HttpResponse<IOrganization[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IOrganization[]) => {
          if (!purchaseOrderLine.organization || !purchaseOrderLine.organization.id) {
            this.organizations = resBody;
          } else {
            this.organizationService
              .find(purchaseOrderLine.organization.id)
              .pipe(
                map((subRes: HttpResponse<IOrganization>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IOrganization[]) => (this.organizations = concatRes));
          }
        });

      this.vendorService
        .query({ filter: 'purchaseorderline-is-null' })
        .pipe(
          map((res: HttpResponse<IVendor[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IVendor[]) => {
          if (!purchaseOrderLine.vendor || !purchaseOrderLine.vendor.id) {
            this.vendors = resBody;
          } else {
            this.vendorService
              .find(purchaseOrderLine.vendor.id)
              .pipe(
                map((subRes: HttpResponse<IVendor>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IVendor[]) => (this.vendors = concatRes));
          }
        });

      this.gLAccountDetailsService
        .query({ filter: 'purchaseorderline-is-null' })
        .pipe(
          map((res: HttpResponse<IGLAccountDetails[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IGLAccountDetails[]) => {
          if (!purchaseOrderLine.gLAccountDetails || !purchaseOrderLine.gLAccountDetails.id) {
            this.glaccountdetails = resBody;
          } else {
            this.gLAccountDetailsService
              .find(purchaseOrderLine.gLAccountDetails.id)
              .pipe(
                map((subRes: HttpResponse<IGLAccountDetails>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IGLAccountDetails[]) => (this.glaccountdetails = concatRes));
          }
        });

      this.purchaseOrderService.query().subscribe((res: HttpResponse<IPurchaseOrder[]>) => (this.purchaseorders = res.body || []));
    });
  }

  updateForm(purchaseOrderLine: IPurchaseOrderLine): void {
    this.editForm.patchValue({
      id: purchaseOrderLine.id,
      country: purchaseOrderLine.country,
      articleNo: purchaseOrderLine.articleNo,
      qty: purchaseOrderLine.qty,
      receivedQty: purchaseOrderLine.receivedQty,
      billedQty: purchaseOrderLine.billedQty,
      unitOfMeasurement: purchaseOrderLine.unitOfMeasurement,
      currency: purchaseOrderLine.currency,
      lineAmount: purchaseOrderLine.lineAmount,
      lineAmountExclTax: purchaseOrderLine.lineAmountExclTax,
      unitPrice: purchaseOrderLine.unitPrice,
      costCentre: purchaseOrderLine.costCentre,
      costCentreid: purchaseOrderLine.costCentreid,
      articleDescription: purchaseOrderLine.articleDescription,
      deliveryReceiptNo: purchaseOrderLine.deliveryReceiptNo,
      deliveryDate: purchaseOrderLine.deliveryDate ? purchaseOrderLine.deliveryDate.format(DATE_TIME_FORMAT) : null,
      hsnCode: purchaseOrderLine.hsnCode,
      tolerance: purchaseOrderLine.tolerance,
      tolerancePrice: purchaseOrderLine.tolerancePrice,
      receiptReqd: purchaseOrderLine.receiptReqd,
      acceptedQty: purchaseOrderLine.acceptedQty,
      amountReceived: purchaseOrderLine.amountReceived,
      availableQty: purchaseOrderLine.availableQty,
      createdAt: purchaseOrderLine.createdAt ? purchaseOrderLine.createdAt.format(DATE_TIME_FORMAT) : null,
      createdBy: purchaseOrderLine.createdBy,
      organization: purchaseOrderLine.organization,
      vendor: purchaseOrderLine.vendor,
      gLAccountDetails: purchaseOrderLine.gLAccountDetails,
      purchaseOrder: purchaseOrderLine.purchaseOrder,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const purchaseOrderLine = this.createFromForm();
    if (purchaseOrderLine.id !== undefined) {
      this.subscribeToSaveResponse(this.purchaseOrderLineService.update(purchaseOrderLine));
    } else {
      this.subscribeToSaveResponse(this.purchaseOrderLineService.create(purchaseOrderLine));
    }
  }

  private createFromForm(): IPurchaseOrderLine {
    return {
      ...new PurchaseOrderLine(),
      id: this.editForm.get(['id'])!.value,
      country: this.editForm.get(['country'])!.value,
      articleNo: this.editForm.get(['articleNo'])!.value,
      qty: this.editForm.get(['qty'])!.value,
      receivedQty: this.editForm.get(['receivedQty'])!.value,
      billedQty: this.editForm.get(['billedQty'])!.value,
      unitOfMeasurement: this.editForm.get(['unitOfMeasurement'])!.value,
      currency: this.editForm.get(['currency'])!.value,
      lineAmount: this.editForm.get(['lineAmount'])!.value,
      lineAmountExclTax: this.editForm.get(['lineAmountExclTax'])!.value,
      unitPrice: this.editForm.get(['unitPrice'])!.value,
      costCentre: this.editForm.get(['costCentre'])!.value,
      costCentreid: this.editForm.get(['costCentreid'])!.value,
      articleDescription: this.editForm.get(['articleDescription'])!.value,
      deliveryReceiptNo: this.editForm.get(['deliveryReceiptNo'])!.value,
      deliveryDate: this.editForm.get(['deliveryDate'])!.value
        ? moment(this.editForm.get(['deliveryDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      hsnCode: this.editForm.get(['hsnCode'])!.value,
      tolerance: this.editForm.get(['tolerance'])!.value,
      tolerancePrice: this.editForm.get(['tolerancePrice'])!.value,
      receiptReqd: this.editForm.get(['receiptReqd'])!.value,
      acceptedQty: this.editForm.get(['acceptedQty'])!.value,
      amountReceived: this.editForm.get(['amountReceived'])!.value,
      availableQty: this.editForm.get(['availableQty'])!.value,
      createdAt: this.editForm.get(['createdAt'])!.value ? moment(this.editForm.get(['createdAt'])!.value, DATE_TIME_FORMAT) : undefined,
      createdBy: this.editForm.get(['createdBy'])!.value,
      organization: this.editForm.get(['organization'])!.value,
      vendor: this.editForm.get(['vendor'])!.value,
      gLAccountDetails: this.editForm.get(['gLAccountDetails'])!.value,
      purchaseOrder: this.editForm.get(['purchaseOrder'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPurchaseOrderLine>>): void {
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
