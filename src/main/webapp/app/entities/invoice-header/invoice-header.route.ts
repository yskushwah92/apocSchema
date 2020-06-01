import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IInvoiceHeader, InvoiceHeader } from 'app/shared/model/invoice-header.model';
import { InvoiceHeaderService } from './invoice-header.service';
import { InvoiceHeaderComponent } from './invoice-header.component';
import { InvoiceHeaderDetailComponent } from './invoice-header-detail.component';
import { InvoiceHeaderUpdateComponent } from './invoice-header-update.component';

@Injectable({ providedIn: 'root' })
export class InvoiceHeaderResolve implements Resolve<IInvoiceHeader> {
  constructor(private service: InvoiceHeaderService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IInvoiceHeader> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((invoiceHeader: HttpResponse<InvoiceHeader>) => {
          if (invoiceHeader.body) {
            return of(invoiceHeader.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new InvoiceHeader());
  }
}

export const invoiceHeaderRoute: Routes = [
  {
    path: '',
    component: InvoiceHeaderComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appApp.invoiceHeader.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: InvoiceHeaderDetailComponent,
    resolve: {
      invoiceHeader: InvoiceHeaderResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appApp.invoiceHeader.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: InvoiceHeaderUpdateComponent,
    resolve: {
      invoiceHeader: InvoiceHeaderResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appApp.invoiceHeader.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: InvoiceHeaderUpdateComponent,
    resolve: {
      invoiceHeader: InvoiceHeaderResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appApp.invoiceHeader.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
