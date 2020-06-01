import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IGLAccountDetails, GLAccountDetails } from 'app/shared/model/gl-account-details.model';
import { GLAccountDetailsService } from './gl-account-details.service';
import { GLAccountDetailsComponent } from './gl-account-details.component';
import { GLAccountDetailsDetailComponent } from './gl-account-details-detail.component';
import { GLAccountDetailsUpdateComponent } from './gl-account-details-update.component';

@Injectable({ providedIn: 'root' })
export class GLAccountDetailsResolve implements Resolve<IGLAccountDetails> {
  constructor(private service: GLAccountDetailsService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IGLAccountDetails> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((gLAccountDetails: HttpResponse<GLAccountDetails>) => {
          if (gLAccountDetails.body) {
            return of(gLAccountDetails.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new GLAccountDetails());
  }
}

export const gLAccountDetailsRoute: Routes = [
  {
    path: '',
    component: GLAccountDetailsComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appApp.gLAccountDetails.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: GLAccountDetailsDetailComponent,
    resolve: {
      gLAccountDetails: GLAccountDetailsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appApp.gLAccountDetails.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: GLAccountDetailsUpdateComponent,
    resolve: {
      gLAccountDetails: GLAccountDetailsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appApp.gLAccountDetails.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: GLAccountDetailsUpdateComponent,
    resolve: {
      gLAccountDetails: GLAccountDetailsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appApp.gLAccountDetails.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
