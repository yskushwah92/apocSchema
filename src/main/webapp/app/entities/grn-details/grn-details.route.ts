import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IGRNDetails, GRNDetails } from 'app/shared/model/grn-details.model';
import { GRNDetailsService } from './grn-details.service';
import { GRNDetailsComponent } from './grn-details.component';
import { GRNDetailsDetailComponent } from './grn-details-detail.component';
import { GRNDetailsUpdateComponent } from './grn-details-update.component';

@Injectable({ providedIn: 'root' })
export class GRNDetailsResolve implements Resolve<IGRNDetails> {
  constructor(private service: GRNDetailsService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IGRNDetails> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((gRNDetails: HttpResponse<GRNDetails>) => {
          if (gRNDetails.body) {
            return of(gRNDetails.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new GRNDetails());
  }
}

export const gRNDetailsRoute: Routes = [
  {
    path: '',
    component: GRNDetailsComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appApp.gRNDetails.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: GRNDetailsDetailComponent,
    resolve: {
      gRNDetails: GRNDetailsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appApp.gRNDetails.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: GRNDetailsUpdateComponent,
    resolve: {
      gRNDetails: GRNDetailsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appApp.gRNDetails.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: GRNDetailsUpdateComponent,
    resolve: {
      gRNDetails: GRNDetailsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appApp.gRNDetails.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
