import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ICaseStatusDetails, CaseStatusDetails } from 'app/shared/model/case-status-details.model';
import { CaseStatusDetailsService } from './case-status-details.service';
import { CaseStatusDetailsComponent } from './case-status-details.component';
import { CaseStatusDetailsDetailComponent } from './case-status-details-detail.component';
import { CaseStatusDetailsUpdateComponent } from './case-status-details-update.component';

@Injectable({ providedIn: 'root' })
export class CaseStatusDetailsResolve implements Resolve<ICaseStatusDetails> {
  constructor(private service: CaseStatusDetailsService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICaseStatusDetails> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((caseStatusDetails: HttpResponse<CaseStatusDetails>) => {
          if (caseStatusDetails.body) {
            return of(caseStatusDetails.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new CaseStatusDetails());
  }
}

export const caseStatusDetailsRoute: Routes = [
  {
    path: '',
    component: CaseStatusDetailsComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appApp.caseStatusDetails.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CaseStatusDetailsDetailComponent,
    resolve: {
      caseStatusDetails: CaseStatusDetailsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appApp.caseStatusDetails.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CaseStatusDetailsUpdateComponent,
    resolve: {
      caseStatusDetails: CaseStatusDetailsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appApp.caseStatusDetails.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: CaseStatusDetailsUpdateComponent,
    resolve: {
      caseStatusDetails: CaseStatusDetailsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appApp.caseStatusDetails.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
