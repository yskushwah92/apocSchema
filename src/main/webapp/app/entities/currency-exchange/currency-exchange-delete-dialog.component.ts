import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICurrencyExchange } from 'app/shared/model/currency-exchange.model';
import { CurrencyExchangeService } from './currency-exchange.service';

@Component({
  templateUrl: './currency-exchange-delete-dialog.component.html',
})
export class CurrencyExchangeDeleteDialogComponent {
  currencyExchange?: ICurrencyExchange;

  constructor(
    protected currencyExchangeService: CurrencyExchangeService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.currencyExchangeService.delete(id).subscribe(() => {
      this.eventManager.broadcast('currencyExchangeListModification');
      this.activeModal.close();
    });
  }
}
