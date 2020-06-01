import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IInvoiceStatusDetails, InvoiceStatusDetails } from 'app/shared/model/invoice-status-details.model';
import { InvoiceStatusDetailsService } from './invoice-status-details.service';
import { InvoiceStatusDetailsComponent } from './invoice-status-details.component';
import { InvoiceStatusDetailsDetailComponent } from './invoice-status-details-detail.component';
import { InvoiceStatusDetailsUpdateComponent } from './invoice-status-details-update.component';

@Injectable({ providedIn: 'root' })
export class InvoiceStatusDetailsResolve implements Resolve<IInvoiceStatusDetails> {
  constructor(private service: InvoiceStatusDetailsService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IInvoiceStatusDetails> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((invoiceStatusDetails: HttpResponse<InvoiceStatusDetails>) => {
          if (invoiceStatusDetails.body) {
            return of(invoiceStatusDetails.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new InvoiceStatusDetails());
  }
}

export const invoiceStatusDetailsRoute: Routes = [
  {
    path: '',
    component: InvoiceStatusDetailsComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appApp.invoiceStatusDetails.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: InvoiceStatusDetailsDetailComponent,
    resolve: {
      invoiceStatusDetails: InvoiceStatusDetailsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appApp.invoiceStatusDetails.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: InvoiceStatusDetailsUpdateComponent,
    resolve: {
      invoiceStatusDetails: InvoiceStatusDetailsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appApp.invoiceStatusDetails.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: InvoiceStatusDetailsUpdateComponent,
    resolve: {
      invoiceStatusDetails: InvoiceStatusDetailsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appApp.invoiceStatusDetails.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
