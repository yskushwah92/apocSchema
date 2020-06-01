import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IPurchaseOrder, PurchaseOrder } from 'app/shared/model/purchase-order.model';
import { PurchaseOrderService } from './purchase-order.service';
import { IPurchaseOrderHeader } from 'app/shared/model/purchase-order-header.model';
import { PurchaseOrderHeaderService } from 'app/entities/purchase-order-header/purchase-order-header.service';
import { IGRNDetails } from 'app/shared/model/grn-details.model';
import { GRNDetailsService } from 'app/entities/grn-details/grn-details.service';
import { IVendor } from 'app/shared/model/vendor.model';
import { VendorService } from 'app/entities/vendor/vendor.service';

type SelectableEntity = IPurchaseOrderHeader | IGRNDetails | IVendor;

@Component({
  selector: 'jhi-purchase-order-update',
  templateUrl: './purchase-order-update.component.html',
})
export class PurchaseOrderUpdateComponent implements OnInit {
  isSaving = false;
  purchaseorderheaders: IPurchaseOrderHeader[] = [];
  grndetails: IGRNDetails[] = [];
  vendors: IVendor[] = [];

  editForm = this.fb.group({
    id: [],
    serialNo: [],
    header: [],
    unit: [],
    issuer: [],
    issuerDetails: [],
    creationDate: [],
    price: [],
    status: [],
    createdBy: [],
    purchaseOrderHeader: [],
    gRNDetails: [],
    vendors: [],
  });

  constructor(
    protected purchaseOrderService: PurchaseOrderService,
    protected purchaseOrderHeaderService: PurchaseOrderHeaderService,
    protected gRNDetailsService: GRNDetailsService,
    protected vendorService: VendorService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ purchaseOrder }) => {
      if (!purchaseOrder.id) {
        const today = moment().startOf('day');
        purchaseOrder.creationDate = today;
      }

      this.updateForm(purchaseOrder);

      this.purchaseOrderHeaderService
        .query({ filter: 'purchaseorder-is-null' })
        .pipe(
          map((res: HttpResponse<IPurchaseOrderHeader[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IPurchaseOrderHeader[]) => {
          if (!purchaseOrder.purchaseOrderHeader || !purchaseOrder.purchaseOrderHeader.id) {
            this.purchaseorderheaders = resBody;
          } else {
            this.purchaseOrderHeaderService
              .find(purchaseOrder.purchaseOrderHeader.id)
              .pipe(
                map((subRes: HttpResponse<IPurchaseOrderHeader>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IPurchaseOrderHeader[]) => (this.purchaseorderheaders = concatRes));
          }
        });

      this.gRNDetailsService
        .query({ filter: 'purchaseorder-is-null' })
        .pipe(
          map((res: HttpResponse<IGRNDetails[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IGRNDetails[]) => {
          if (!purchaseOrder.gRNDetails || !purchaseOrder.gRNDetails.id) {
            this.grndetails = resBody;
          } else {
            this.gRNDetailsService
              .find(purchaseOrder.gRNDetails.id)
              .pipe(
                map((subRes: HttpResponse<IGRNDetails>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IGRNDetails[]) => (this.grndetails = concatRes));
          }
        });

      this.vendorService.query().subscribe((res: HttpResponse<IVendor[]>) => (this.vendors = res.body || []));
    });
  }

  updateForm(purchaseOrder: IPurchaseOrder): void {
    this.editForm.patchValue({
      id: purchaseOrder.id,
      serialNo: purchaseOrder.serialNo,
      header: purchaseOrder.header,
      unit: purchaseOrder.unit,
      issuer: purchaseOrder.issuer,
      issuerDetails: purchaseOrder.issuerDetails,
      creationDate: purchaseOrder.creationDate ? purchaseOrder.creationDate.format(DATE_TIME_FORMAT) : null,
      price: purchaseOrder.price,
      status: purchaseOrder.status,
      createdBy: purchaseOrder.createdBy,
      purchaseOrderHeader: purchaseOrder.purchaseOrderHeader,
      gRNDetails: purchaseOrder.gRNDetails,
      vendors: purchaseOrder.vendors,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const purchaseOrder = this.createFromForm();
    if (purchaseOrder.id !== undefined) {
      this.subscribeToSaveResponse(this.purchaseOrderService.update(purchaseOrder));
    } else {
      this.subscribeToSaveResponse(this.purchaseOrderService.create(purchaseOrder));
    }
  }

  private createFromForm(): IPurchaseOrder {
    return {
      ...new PurchaseOrder(),
      id: this.editForm.get(['id'])!.value,
      serialNo: this.editForm.get(['serialNo'])!.value,
      header: this.editForm.get(['header'])!.value,
      unit: this.editForm.get(['unit'])!.value,
      issuer: this.editForm.get(['issuer'])!.value,
      issuerDetails: this.editForm.get(['issuerDetails'])!.value,
      creationDate: this.editForm.get(['creationDate'])!.value
        ? moment(this.editForm.get(['creationDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      price: this.editForm.get(['price'])!.value,
      status: this.editForm.get(['status'])!.value,
      createdBy: this.editForm.get(['createdBy'])!.value,
      purchaseOrderHeader: this.editForm.get(['purchaseOrderHeader'])!.value,
      gRNDetails: this.editForm.get(['gRNDetails'])!.value,
      vendors: this.editForm.get(['vendors'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPurchaseOrder>>): void {
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

  getSelected(selectedVals: IVendor[], option: IVendor): IVendor {
    if (selectedVals) {
      for (let i = 0; i < selectedVals.length; i++) {
        if (option.id === selectedVals[i].id) {
          return selectedVals[i];
        }
      }
    }
    return option;
  }
}
