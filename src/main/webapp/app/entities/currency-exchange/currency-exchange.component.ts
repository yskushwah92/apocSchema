import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ICurrencyExchange } from 'app/shared/model/currency-exchange.model';
import { CurrencyExchangeService } from './currency-exchange.service';
import { CurrencyExchangeDeleteDialogComponent } from './currency-exchange-delete-dialog.component';

@Component({
  selector: 'jhi-currency-exchange',
  templateUrl: './currency-exchange.component.html',
})
export class CurrencyExchangeComponent implements OnInit, OnDestroy {
  currencyExchanges?: ICurrencyExchange[];
  eventSubscriber?: Subscription;

  constructor(
    protected currencyExchangeService: CurrencyExchangeService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.currencyExchangeService.query().subscribe((res: HttpResponse<ICurrencyExchange[]>) => (this.currencyExchanges = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInCurrencyExchanges();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ICurrencyExchange): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInCurrencyExchanges(): void {
    this.eventSubscriber = this.eventManager.subscribe('currencyExchangeListModification', () => this.loadAll());
  }

  delete(currencyExchange: ICurrencyExchange): void {
    const modalRef = this.modalService.open(CurrencyExchangeDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.currencyExchange = currencyExchange;
  }
}
