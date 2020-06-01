import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ICurrencyExchange, CurrencyExchange } from 'app/shared/model/currency-exchange.model';
import { CurrencyExchangeService } from './currency-exchange.service';

@Component({
  selector: 'jhi-currency-exchange-update',
  templateUrl: './currency-exchange-update.component.html',
})
export class CurrencyExchangeUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    country: [],
    fromCurrency: [],
    toCurrency: [],
    validfrom: [],
    exchangeRate: [],
    exchangeType: [],
    ratioFrom: [],
    ratioTo: [],
    isActive: [],
    isDeleted: [],
    createdAt: [],
    createdBy: [],
  });

  constructor(
    protected currencyExchangeService: CurrencyExchangeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ currencyExchange }) => {
      if (!currencyExchange.id) {
        const today = moment().startOf('day');
        currencyExchange.validfrom = today;
        currencyExchange.createdAt = today;
      }

      this.updateForm(currencyExchange);
    });
  }

  updateForm(currencyExchange: ICurrencyExchange): void {
    this.editForm.patchValue({
      id: currencyExchange.id,
      country: currencyExchange.country,
      fromCurrency: currencyExchange.fromCurrency,
      toCurrency: currencyExchange.toCurrency,
      validfrom: currencyExchange.validfrom ? currencyExchange.validfrom.format(DATE_TIME_FORMAT) : null,
      exchangeRate: currencyExchange.exchangeRate,
      exchangeType: currencyExchange.exchangeType,
      ratioFrom: currencyExchange.ratioFrom,
      ratioTo: currencyExchange.ratioTo,
      isActive: currencyExchange.isActive,
      isDeleted: currencyExchange.isDeleted,
      createdAt: currencyExchange.createdAt ? currencyExchange.createdAt.format(DATE_TIME_FORMAT) : null,
      createdBy: currencyExchange.createdBy,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const currencyExchange = this.createFromForm();
    if (currencyExchange.id !== undefined) {
      this.subscribeToSaveResponse(this.currencyExchangeService.update(currencyExchange));
    } else {
      this.subscribeToSaveResponse(this.currencyExchangeService.create(currencyExchange));
    }
  }

  private createFromForm(): ICurrencyExchange {
    return {
      ...new CurrencyExchange(),
      id: this.editForm.get(['id'])!.value,
      country: this.editForm.get(['country'])!.value,
      fromCurrency: this.editForm.get(['fromCurrency'])!.value,
      toCurrency: this.editForm.get(['toCurrency'])!.value,
      validfrom: this.editForm.get(['validfrom'])!.value ? moment(this.editForm.get(['validfrom'])!.value, DATE_TIME_FORMAT) : undefined,
      exchangeRate: this.editForm.get(['exchangeRate'])!.value,
      exchangeType: this.editForm.get(['exchangeType'])!.value,
      ratioFrom: this.editForm.get(['ratioFrom'])!.value,
      ratioTo: this.editForm.get(['ratioTo'])!.value,
      isActive: this.editForm.get(['isActive'])!.value,
      isDeleted: this.editForm.get(['isDeleted'])!.value,
      createdAt: this.editForm.get(['createdAt'])!.value ? moment(this.editForm.get(['createdAt'])!.value, DATE_TIME_FORMAT) : undefined,
      createdBy: this.editForm.get(['createdBy'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICurrencyExchange>>): void {
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
