import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IInvoiceHeaderTaxDetails, InvoiceHeaderTaxDetails } from 'app/shared/model/invoice-header-tax-details.model';
import { InvoiceHeaderTaxDetailsService } from './invoice-header-tax-details.service';
import { InvoiceHeaderTaxDetailsComponent } from './invoice-header-tax-details.component';
import { InvoiceHeaderTaxDetailsDetailComponent } from './invoice-header-tax-details-detail.component';
import { InvoiceHeaderTaxDetailsUpdateComponent } from './invoice-header-tax-details-update.component';

@Injectable({ providedIn: 'root' })
export class InvoiceHeaderTaxDetailsResolve implements Resolve<IInvoiceHeaderTaxDetails> {
  constructor(private service: InvoiceHeaderTaxDetailsService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IInvoiceHeaderTaxDetails> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((invoiceHeaderTaxDetails: HttpResponse<InvoiceHeaderTaxDetails>) => {
          if (invoiceHeaderTaxDetails.body) {
            return of(invoiceHeaderTaxDetails.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new InvoiceHeaderTaxDetails());
  }
}

export const invoiceHeaderTaxDetailsRoute: Routes = [
  {
    path: '',
    component: InvoiceHeaderTaxDetailsComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appApp.invoiceHeaderTaxDetails.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: InvoiceHeaderTaxDetailsDetailComponent,
    resolve: {
      invoiceHeaderTaxDetails: InvoiceHeaderTaxDetailsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appApp.invoiceHeaderTaxDetails.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: InvoiceHeaderTaxDetailsUpdateComponent,
    resolve: {
      invoiceHeaderTaxDetails: InvoiceHeaderTaxDetailsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appApp.invoiceHeaderTaxDetails.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: InvoiceHeaderTaxDetailsUpdateComponent,
    resolve: {
      invoiceHeaderTaxDetails: InvoiceHeaderTaxDetailsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appApp.invoiceHeaderTaxDetails.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
