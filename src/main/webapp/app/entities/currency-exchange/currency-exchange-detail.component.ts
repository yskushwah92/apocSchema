import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICurrencyExchange } from 'app/shared/model/currency-exchange.model';

@Component({
  selector: 'jhi-currency-exchange-detail',
  templateUrl: './currency-exchange-detail.component.html',
})
export class CurrencyExchangeDetailComponent implements OnInit {
  currencyExchange: ICurrencyExchange | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ currencyExchange }) => (this.currencyExchange = currencyExchange));
  }

  previousState(): void {
    window.history.back();
  }
}
