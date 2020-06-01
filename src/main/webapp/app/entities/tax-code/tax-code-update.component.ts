import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ITaxCode, TaxCode } from 'app/shared/model/tax-code.model';
import { TaxCodeService } from './tax-code.service';

@Component({
  selector: 'jhi-tax-code-update',
  templateUrl: './tax-code-update.component.html',
})
export class TaxCodeUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    name: [],
    country: [],
    countryKey: [],
    taxCode: [],
    description: [],
    taxCodeDescription: [],
    taxRate: [],
    createdAt: [],
    createdBy: [],
  });

  constructor(protected taxCodeService: TaxCodeService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ taxCode }) => {
      if (!taxCode.id) {
        const today = moment().startOf('day');
        taxCode.createdAt = today;
      }

      this.updateForm(taxCode);
    });
  }

  updateForm(taxCode: ITaxCode): void {
    this.editForm.patchValue({
      id: taxCode.id,
      name: taxCode.name,
      country: taxCode.country,
      countryKey: taxCode.countryKey,
      taxCode: taxCode.taxCode,
      description: taxCode.description,
      taxCodeDescription: taxCode.taxCodeDescription,
      taxRate: taxCode.taxRate,
      createdAt: taxCode.createdAt ? taxCode.createdAt.format(DATE_TIME_FORMAT) : null,
      createdBy: taxCode.createdBy,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const taxCode = this.createFromForm();
    if (taxCode.id !== undefined) {
      this.subscribeToSaveResponse(this.taxCodeService.update(taxCode));
    } else {
      this.subscribeToSaveResponse(this.taxCodeService.create(taxCode));
    }
  }

  private createFromForm(): ITaxCode {
    return {
      ...new TaxCode(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      country: this.editForm.get(['country'])!.value,
      countryKey: this.editForm.get(['countryKey'])!.value,
      taxCode: this.editForm.get(['taxCode'])!.value,
      description: this.editForm.get(['description'])!.value,
      taxCodeDescription: this.editForm.get(['taxCodeDescription'])!.value,
      taxRate: this.editForm.get(['taxRate'])!.value,
      createdAt: this.editForm.get(['createdAt'])!.value ? moment(this.editForm.get(['createdAt'])!.value, DATE_TIME_FORMAT) : undefined,
      createdBy: this.editForm.get(['createdBy'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITaxCode>>): void {
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
