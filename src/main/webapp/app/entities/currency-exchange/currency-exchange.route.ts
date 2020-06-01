import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ICurrencyExchange, CurrencyExchange } from 'app/shared/model/currency-exchange.model';
import { CurrencyExchangeService } from './currency-exchange.service';
import { CurrencyExchangeComponent } from './currency-exchange.component';
import { CurrencyExchangeDetailComponent } from './currency-exchange-detail.component';
import { CurrencyExchangeUpdateComponent } from './currency-exchange-update.component';

@Injectable({ providedIn: 'root' })
export class CurrencyExchangeResolve implements Resolve<ICurrencyExchange> {
  constructor(private service: CurrencyExchangeService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICurrencyExchange> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((currencyExchange: HttpResponse<CurrencyExchange>) => {
          if (currencyExchange.body) {
            return of(currencyExchange.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new CurrencyExchange());
  }
}

export const currencyExchangeRoute: Routes = [
  {
    path: '',
    component: CurrencyExchangeComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appApp.currencyExchange.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CurrencyExchangeDetailComponent,
    resolve: {
      currencyExchange: CurrencyExchangeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appApp.currencyExchange.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CurrencyExchangeUpdateComponent,
    resolve: {
      currencyExchange: CurrencyExchangeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appApp.currencyExchange.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: CurrencyExchangeUpdateComponent,
    resolve: {
      currencyExchange: CurrencyExchangeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appApp.currencyExchange.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
