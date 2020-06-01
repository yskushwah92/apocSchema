import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IVendorPaymentAccountDetails, VendorPaymentAccountDetails } from 'app/shared/model/vendor-payment-account-details.model';
import { VendorPaymentAccountDetailsService } from './vendor-payment-account-details.service';
import { IVendor } from 'app/shared/model/vendor.model';
import { VendorService } from 'app/entities/vendor/vendor.service';

@Component({
  selector: 'jhi-vendor-payment-account-details-update',
  templateUrl: './vendor-payment-account-details-update.component.html',
})
export class VendorPaymentAccountDetailsUpdateComponent implements OnInit {
  isSaving = false;
  vendors: IVendor[] = [];

  editForm = this.fb.group({
    id: [],
    name: [],
    type: [],
    method: [],
    accountNumber: [],
    accountName: [],
    accountCode: [],
    accountKey: [],
    bankAccountType: [],
    iban: [],
    isActive: [],
    createdAt: [],
    createdBy: [],
    vendor: [],
  });

  constructor(
    protected vendorPaymentAccountDetailsService: VendorPaymentAccountDetailsService,
    protected vendorService: VendorService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ vendorPaymentAccountDetails }) => {
      if (!vendorPaymentAccountDetails.id) {
        const today = moment().startOf('day');
        vendorPaymentAccountDetails.createdAt = today;
      }

      this.updateForm(vendorPaymentAccountDetails);

      this.vendorService.query().subscribe((res: HttpResponse<IVendor[]>) => (this.vendors = res.body || []));
    });
  }

  updateForm(vendorPaymentAccountDetails: IVendorPaymentAccountDetails): void {
    this.editForm.patchValue({
      id: vendorPaymentAccountDetails.id,
      name: vendorPaymentAccountDetails.name,
      type: vendorPaymentAccountDetails.type,
      method: vendorPaymentAccountDetails.method,
      accountNumber: vendorPaymentAccountDetails.accountNumber,
      accountName: vendorPaymentAccountDetails.accountName,
      accountCode: vendorPaymentAccountDetails.accountCode,
      accountKey: vendorPaymentAccountDetails.accountKey,
      bankAccountType: vendorPaymentAccountDetails.bankAccountType,
      iban: vendorPaymentAccountDetails.iban,
      isActive: vendorPaymentAccountDetails.isActive,
      createdAt: vendorPaymentAccountDetails.createdAt ? vendorPaymentAccountDetails.createdAt.format(DATE_TIME_FORMAT) : null,
      createdBy: vendorPaymentAccountDetails.createdBy,
      vendor: vendorPaymentAccountDetails.vendor,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const vendorPaymentAccountDetails = this.createFromForm();
    if (vendorPaymentAccountDetails.id !== undefined) {
      this.subscribeToSaveResponse(this.vendorPaymentAccountDetailsService.update(vendorPaymentAccountDetails));
    } else {
      this.subscribeToSaveResponse(this.vendorPaymentAccountDetailsService.create(vendorPaymentAccountDetails));
    }
  }

  private createFromForm(): IVendorPaymentAccountDetails {
    return {
      ...new VendorPaymentAccountDetails(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      type: this.editForm.get(['type'])!.value,
      method: this.editForm.get(['method'])!.value,
      accountNumber: this.editForm.get(['accountNumber'])!.value,
      accountName: this.editForm.get(['accountName'])!.value,
      accountCode: this.editForm.get(['accountCode'])!.value,
      accountKey: this.editForm.get(['accountKey'])!.value,
      bankAccountType: this.editForm.get(['bankAccountType'])!.value,
      iban: this.editForm.get(['iban'])!.value,
      isActive: this.editForm.get(['isActive'])!.value,
      createdAt: this.editForm.get(['createdAt'])!.value ? moment(this.editForm.get(['createdAt'])!.value, DATE_TIME_FORMAT) : undefined,
      createdBy: this.editForm.get(['createdBy'])!.value,
      vendor: this.editForm.get(['vendor'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IVendorPaymentAccountDetails>>): void {
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

  trackById(index: number, item: IVendor): any {
    return item.id;
  }
}
