import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { INotificationInfo, NotificationInfo } from 'app/shared/model/notification-info.model';
import { NotificationInfoService } from './notification-info.service';
import { IPurchaseOrder } from 'app/shared/model/purchase-order.model';
import { PurchaseOrderService } from 'app/entities/purchase-order/purchase-order.service';

@Component({
  selector: 'jhi-notification-info-update',
  templateUrl: './notification-info-update.component.html',
})
export class NotificationInfoUpdateComponent implements OnInit {
  isSaving = false;
  purchaseorders: IPurchaseOrder[] = [];

  editForm = this.fb.group({
    id: [],
    sender: [],
    receiver: [],
    mode: [],
    message: [],
    status: [],
    createdAt: [],
    createdBy: [],
    purchaseOrder: [],
  });

  constructor(
    protected notificationInfoService: NotificationInfoService,
    protected purchaseOrderService: PurchaseOrderService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ notificationInfo }) => {
      if (!notificationInfo.id) {
        const today = moment().startOf('day');
        notificationInfo.createdAt = today;
      }

      this.updateForm(notificationInfo);

      this.purchaseOrderService
        .query({ filter: 'notificationinfo-is-null' })
        .pipe(
          map((res: HttpResponse<IPurchaseOrder[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IPurchaseOrder[]) => {
          if (!notificationInfo.purchaseOrder || !notificationInfo.purchaseOrder.id) {
            this.purchaseorders = resBody;
          } else {
            this.purchaseOrderService
              .find(notificationInfo.purchaseOrder.id)
              .pipe(
                map((subRes: HttpResponse<IPurchaseOrder>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IPurchaseOrder[]) => (this.purchaseorders = concatRes));
          }
        });
    });
  }

  updateForm(notificationInfo: INotificationInfo): void {
    this.editForm.patchValue({
      id: notificationInfo.id,
      sender: notificationInfo.sender,
      receiver: notificationInfo.receiver,
      mode: notificationInfo.mode,
      message: notificationInfo.message,
      status: notificationInfo.status,
      createdAt: notificationInfo.createdAt ? notificationInfo.createdAt.format(DATE_TIME_FORMAT) : null,
      createdBy: notificationInfo.createdBy,
      purchaseOrder: notificationInfo.purchaseOrder,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const notificationInfo = this.createFromForm();
    if (notificationInfo.id !== undefined) {
      this.subscribeToSaveResponse(this.notificationInfoService.update(notificationInfo));
    } else {
      this.subscribeToSaveResponse(this.notificationInfoService.create(notificationInfo));
    }
  }

  private createFromForm(): INotificationInfo {
    return {
      ...new NotificationInfo(),
      id: this.editForm.get(['id'])!.value,
      sender: this.editForm.get(['sender'])!.value,
      receiver: this.editForm.get(['receiver'])!.value,
      mode: this.editForm.get(['mode'])!.value,
      message: this.editForm.get(['message'])!.value,
      status: this.editForm.get(['status'])!.value,
      createdAt: this.editForm.get(['createdAt'])!.value ? moment(this.editForm.get(['createdAt'])!.value, DATE_TIME_FORMAT) : undefined,
      createdBy: this.editForm.get(['createdBy'])!.value,
      purchaseOrder: this.editForm.get(['purchaseOrder'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<INotificationInfo>>): void {
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

  trackById(index: number, item: IPurchaseOrder): any {
    return item.id;
  }
}
