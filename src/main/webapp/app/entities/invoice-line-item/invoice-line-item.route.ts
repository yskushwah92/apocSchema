import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IInvoiceLineItem, InvoiceLineItem } from 'app/shared/model/invoice-line-item.model';
import { InvoiceLineItemService } from './invoice-line-item.service';
import { InvoiceLineItemComponent } from './invoice-line-item.component';
import { InvoiceLineItemDetailComponent } from './invoice-line-item-detail.component';
import { InvoiceLineItemUpdateComponent } from './invoice-line-item-update.component';

@Injectable({ providedIn: 'root' })
export class InvoiceLineItemResolve implements Resolve<IInvoiceLineItem> {
  constructor(private service: InvoiceLineItemService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IInvoiceLineItem> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((invoiceLineItem: HttpResponse<InvoiceLineItem>) => {
          if (invoiceLineItem.body) {
            return of(invoiceLineItem.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new InvoiceLineItem());
  }
}

export const invoiceLineItemRoute: Routes = [
  {
    path: '',
    component: InvoiceLineItemComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appApp.invoiceLineItem.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: InvoiceLineItemDetailComponent,
    resolve: {
      invoiceLineItem: InvoiceLineItemResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appApp.invoiceLineItem.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: InvoiceLineItemUpdateComponent,
    resolve: {
      invoiceLineItem: InvoiceLineItemResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appApp.invoiceLineItem.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: InvoiceLineItemUpdateComponent,
    resolve: {
      invoiceLineItem: InvoiceLineItemResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appApp.invoiceLineItem.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
