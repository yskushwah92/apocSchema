import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IPurchaseOrderLine, PurchaseOrderLine } from 'app/shared/model/purchase-order-line.model';
import { PurchaseOrderLineService } from './purchase-order-line.service';
import { PurchaseOrderLineComponent } from './purchase-order-line.component';
import { PurchaseOrderLineDetailComponent } from './purchase-order-line-detail.component';
import { PurchaseOrderLineUpdateComponent } from './purchase-order-line-update.component';

@Injectable({ providedIn: 'root' })
export class PurchaseOrderLineResolve implements Resolve<IPurchaseOrderLine> {
  constructor(private service: PurchaseOrderLineService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IPurchaseOrderLine> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((purchaseOrderLine: HttpResponse<PurchaseOrderLine>) => {
          if (purchaseOrderLine.body) {
            return of(purchaseOrderLine.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new PurchaseOrderLine());
  }
}

export const purchaseOrderLineRoute: Routes = [
  {
    path: '',
    component: PurchaseOrderLineComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appApp.purchaseOrderLine.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: PurchaseOrderLineDetailComponent,
    resolve: {
      purchaseOrderLine: PurchaseOrderLineResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appApp.purchaseOrderLine.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: PurchaseOrderLineUpdateComponent,
    resolve: {
      purchaseOrderLine: PurchaseOrderLineResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appApp.purchaseOrderLine.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: PurchaseOrderLineUpdateComponent,
    resolve: {
      purchaseOrderLine: PurchaseOrderLineResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appApp.purchaseOrderLine.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
