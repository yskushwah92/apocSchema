import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IAuditLog, AuditLog } from 'app/shared/model/audit-log.model';
import { AuditLogService } from './audit-log.service';
import { AuditLogComponent } from './audit-log.component';
import { AuditLogDetailComponent } from './audit-log-detail.component';
import { AuditLogUpdateComponent } from './audit-log-update.component';

@Injectable({ providedIn: 'root' })
export class AuditLogResolve implements Resolve<IAuditLog> {
  constructor(private service: AuditLogService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IAuditLog> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((auditLog: HttpResponse<AuditLog>) => {
          if (auditLog.body) {
            return of(auditLog.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new AuditLog());
  }
}

export const auditLogRoute: Routes = [
  {
    path: '',
    component: AuditLogComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appApp.auditLog.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: AuditLogDetailComponent,
    resolve: {
      auditLog: AuditLogResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appApp.auditLog.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: AuditLogUpdateComponent,
    resolve: {
      auditLog: AuditLogResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appApp.auditLog.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: AuditLogUpdateComponent,
    resolve: {
      auditLog: AuditLogResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appApp.auditLog.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
