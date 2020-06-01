import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IPurchaseOrderHeader, PurchaseOrderHeader } from 'app/shared/model/purchase-order-header.model';
import { PurchaseOrderHeaderService } from './purchase-order-header.service';
import { PurchaseOrderHeaderComponent } from './purchase-order-header.component';
import { PurchaseOrderHeaderDetailComponent } from './purchase-order-header-detail.component';
import { PurchaseOrderHeaderUpdateComponent } from './purchase-order-header-update.component';

@Injectable({ providedIn: 'root' })
export class PurchaseOrderHeaderResolve implements Resolve<IPurchaseOrderHeader> {
  constructor(private service: PurchaseOrderHeaderService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IPurchaseOrderHeader> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((purchaseOrderHeader: HttpResponse<PurchaseOrderHeader>) => {
          if (purchaseOrderHeader.body) {
            return of(purchaseOrderHeader.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new PurchaseOrderHeader());
  }
}

export const purchaseOrderHeaderRoute: Routes = [
  {
    path: '',
    component: PurchaseOrderHeaderComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appApp.purchaseOrderHeader.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: PurchaseOrderHeaderDetailComponent,
    resolve: {
      purchaseOrderHeader: PurchaseOrderHeaderResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appApp.purchaseOrderHeader.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: PurchaseOrderHeaderUpdateComponent,
    resolve: {
      purchaseOrderHeader: PurchaseOrderHeaderResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appApp.purchaseOrderHeader.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: PurchaseOrderHeaderUpdateComponent,
    resolve: {
      purchaseOrderHeader: PurchaseOrderHeaderResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appApp.purchaseOrderHeader.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
