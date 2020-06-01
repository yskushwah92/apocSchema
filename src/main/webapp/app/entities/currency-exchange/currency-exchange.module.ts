import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AppSharedModule } from 'app/shared/shared.module';
import { CurrencyExchangeComponent } from './currency-exchange.component';
import { CurrencyExchangeDetailComponent } from './currency-exchange-detail.component';
import { CurrencyExchangeUpdateComponent } from './currency-exchange-update.component';
import { CurrencyExchangeDeleteDialogComponent } from './currency-exchange-delete-dialog.component';
import { currencyExchangeRoute } from './currency-exchange.route';

@NgModule({
  imports: [AppSharedModule, RouterModule.forChild(currencyExchangeRoute)],
  declarations: [
    CurrencyExchangeComponent,
    CurrencyExchangeDetailComponent,
    CurrencyExchangeUpdateComponent,
    CurrencyExchangeDeleteDialogComponent,
  ],
  entryComponents: [CurrencyExchangeDeleteDialogComponent],
})
export class AppCurrencyExchangeModule {}
