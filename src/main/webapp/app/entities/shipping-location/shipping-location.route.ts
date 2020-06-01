import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IShippingLocation, ShippingLocation } from 'app/shared/model/shipping-location.model';
import { ShippingLocationService } from './shipping-location.service';
import { ShippingLocationComponent } from './shipping-location.component';
import { ShippingLocationDetailComponent } from './shipping-location-detail.component';
import { ShippingLocationUpdateComponent } from './shipping-location-update.component';

@Injectable({ providedIn: 'root' })
export class ShippingLocationResolve implements Resolve<IShippingLocation> {
  constructor(private service: ShippingLocationService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IShippingLocation> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((shippingLocation: HttpResponse<ShippingLocation>) => {
          if (shippingLocation.body) {
            return of(shippingLocation.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ShippingLocation());
  }
}

export const shippingLocationRoute: Routes = [
  {
    path: '',
    component: ShippingLocationComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appApp.shippingLocation.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ShippingLocationDetailComponent,
    resolve: {
      shippingLocation: ShippingLocationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appApp.shippingLocation.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ShippingLocationUpdateComponent,
    resolve: {
      shippingLocation: ShippingLocationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appApp.shippingLocation.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ShippingLocationUpdateComponent,
    resolve: {
      shippingLocation: ShippingLocationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appApp.shippingLocation.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
