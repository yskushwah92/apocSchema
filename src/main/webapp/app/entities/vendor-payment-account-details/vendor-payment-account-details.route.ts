import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IVendorPaymentAccountDetails, VendorPaymentAccountDetails } from 'app/shared/model/vendor-payment-account-details.model';
import { VendorPaymentAccountDetailsService } from './vendor-payment-account-details.service';
import { VendorPaymentAccountDetailsComponent } from './vendor-payment-account-details.component';
import { VendorPaymentAccountDetailsDetailComponent } from './vendor-payment-account-details-detail.component';
import { VendorPaymentAccountDetailsUpdateComponent } from './vendor-payment-account-details-update.component';

@Injectable({ providedIn: 'root' })
export class VendorPaymentAccountDetailsResolve implements Resolve<IVendorPaymentAccountDetails> {
  constructor(private service: VendorPaymentAccountDetailsService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IVendorPaymentAccountDetails> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((vendorPaymentAccountDetails: HttpResponse<VendorPaymentAccountDetails>) => {
          if (vendorPaymentAccountDetails.body) {
            return of(vendorPaymentAccountDetails.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new VendorPaymentAccountDetails());
  }
}

export const vendorPaymentAccountDetailsRoute: Routes = [
  {
    path: '',
    component: VendorPaymentAccountDetailsComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appApp.vendorPaymentAccountDetails.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: VendorPaymentAccountDetailsDetailComponent,
    resolve: {
      vendorPaymentAccountDetails: VendorPaymentAccountDetailsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appApp.vendorPaymentAccountDetails.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: VendorPaymentAccountDetailsUpdateComponent,
    resolve: {
      vendorPaymentAccountDetails: VendorPaymentAccountDetailsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appApp.vendorPaymentAccountDetails.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: VendorPaymentAccountDetailsUpdateComponent,
    resolve: {
      vendorPaymentAccountDetails: VendorPaymentAccountDetailsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appApp.vendorPaymentAccountDetails.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
