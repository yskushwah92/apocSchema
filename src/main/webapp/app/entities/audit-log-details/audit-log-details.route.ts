import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IAuditLogDetails, AuditLogDetails } from 'app/shared/model/audit-log-details.model';
import { AuditLogDetailsService } from './audit-log-details.service';
import { AuditLogDetailsComponent } from './audit-log-details.component';
import { AuditLogDetailsDetailComponent } from './audit-log-details-detail.component';
import { AuditLogDetailsUpdateComponent } from './audit-log-details-update.component';

@Injectable({ providedIn: 'root' })
export class AuditLogDetailsResolve implements Resolve<IAuditLogDetails> {
  constructor(private service: AuditLogDetailsService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IAuditLogDetails> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((auditLogDetails: HttpResponse<AuditLogDetails>) => {
          if (auditLogDetails.body) {
            return of(auditLogDetails.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new AuditLogDetails());
  }
}

export const auditLogDetailsRoute: Routes = [
  {
    path: '',
    component: AuditLogDetailsComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appApp.auditLogDetails.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: AuditLogDetailsDetailComponent,
    resolve: {
      auditLogDetails: AuditLogDetailsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appApp.auditLogDetails.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: AuditLogDetailsUpdateComponent,
    resolve: {
      auditLogDetails: AuditLogDetailsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appApp.auditLogDetails.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: AuditLogDetailsUpdateComponent,
    resolve: {
      auditLogDetails: AuditLogDetailsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appApp.auditLogDetails.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
