import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IGLAccountDetails, GLAccountDetails } from 'app/shared/model/gl-account-details.model';
import { GLAccountDetailsService } from './gl-account-details.service';

@Component({
  selector: 'jhi-gl-account-details-update',
  templateUrl: './gl-account-details-update.component.html',
})
export class GLAccountDetailsUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    accountNo: [],
    country: [],
    chartOfAccounts: [],
    categoryId: [],
    accountType: [],
    isBalanceSheet: [],
    textForCriterion: [],
    someText: [],
    taxCatTaxCode: [],
    currencykey: [],
    activeCOA: [],
    activeCoCode: [],
    postingWithoutTaxAllowed: [],
    postingBlock: [],
    postAtProfitCentre: [],
    postAtCostCentre: [],
    postAtSegment: [],
    postAtInternalOrder: [],
    isActive: [],
    createdAt: [],
    createdBy: [],
  });

  constructor(
    protected gLAccountDetailsService: GLAccountDetailsService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ gLAccountDetails }) => {
      if (!gLAccountDetails.id) {
        const today = moment().startOf('day');
        gLAccountDetails.createdAt = today;
      }

      this.updateForm(gLAccountDetails);
    });
  }

  updateForm(gLAccountDetails: IGLAccountDetails): void {
    this.editForm.patchValue({
      id: gLAccountDetails.id,
      accountNo: gLAccountDetails.accountNo,
      country: gLAccountDetails.country,
      chartOfAccounts: gLAccountDetails.chartOfAccounts,
      categoryId: gLAccountDetails.categoryId,
      accountType: gLAccountDetails.accountType,
      isBalanceSheet: gLAccountDetails.isBalanceSheet,
      textForCriterion: gLAccountDetails.textForCriterion,
      someText: gLAccountDetails.someText,
      taxCatTaxCode: gLAccountDetails.taxCatTaxCode,
      currencykey: gLAccountDetails.currencykey,
      activeCOA: gLAccountDetails.activeCOA,
      activeCoCode: gLAccountDetails.activeCoCode,
      postingWithoutTaxAllowed: gLAccountDetails.postingWithoutTaxAllowed,
      postingBlock: gLAccountDetails.postingBlock,
      postAtProfitCentre: gLAccountDetails.postAtProfitCentre,
      postAtCostCentre: gLAccountDetails.postAtCostCentre,
      postAtSegment: gLAccountDetails.postAtSegment,
      postAtInternalOrder: gLAccountDetails.postAtInternalOrder,
      isActive: gLAccountDetails.isActive,
      createdAt: gLAccountDetails.createdAt ? gLAccountDetails.createdAt.format(DATE_TIME_FORMAT) : null,
      createdBy: gLAccountDetails.createdBy,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const gLAccountDetails = this.createFromForm();
    if (gLAccountDetails.id !== undefined) {
      this.subscribeToSaveResponse(this.gLAccountDetailsService.update(gLAccountDetails));
    } else {
      this.subscribeToSaveResponse(this.gLAccountDetailsService.create(gLAccountDetails));
    }
  }

  private createFromForm(): IGLAccountDetails {
    return {
      ...new GLAccountDetails(),
      id: this.editForm.get(['id'])!.value,
      accountNo: this.editForm.get(['accountNo'])!.value,
      country: this.editForm.get(['country'])!.value,
      chartOfAccounts: this.editForm.get(['chartOfAccounts'])!.value,
      categoryId: this.editForm.get(['categoryId'])!.value,
      accountType: this.editForm.get(['accountType'])!.value,
      isBalanceSheet: this.editForm.get(['isBalanceSheet'])!.value,
      textForCriterion: this.editForm.get(['textForCriterion'])!.value,
      someText: this.editForm.get(['someText'])!.value,
      taxCatTaxCode: this.editForm.get(['taxCatTaxCode'])!.value,
      currencykey: this.editForm.get(['currencykey'])!.value,
      activeCOA: this.editForm.get(['activeCOA'])!.value,
      activeCoCode: this.editForm.get(['activeCoCode'])!.value,
      postingWithoutTaxAllowed: this.editForm.get(['postingWithoutTaxAllowed'])!.value,
      postingBlock: this.editForm.get(['postingBlock'])!.value,
      postAtProfitCentre: this.editForm.get(['postAtProfitCentre'])!.value,
      postAtCostCentre: this.editForm.get(['postAtCostCentre'])!.value,
      postAtSegment: this.editForm.get(['postAtSegment'])!.value,
      postAtInternalOrder: this.editForm.get(['postAtInternalOrder'])!.value,
      isActive: this.editForm.get(['isActive'])!.value,
      createdAt: this.editForm.get(['createdAt'])!.value ? moment(this.editForm.get(['createdAt'])!.value, DATE_TIME_FORMAT) : undefined,
      createdBy: this.editForm.get(['createdBy'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IGLAccountDetails>>): void {
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
