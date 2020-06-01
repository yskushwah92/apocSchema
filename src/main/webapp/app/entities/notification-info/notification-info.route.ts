import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { INotificationInfo, NotificationInfo } from 'app/shared/model/notification-info.model';
import { NotificationInfoService } from './notification-info.service';
import { NotificationInfoComponent } from './notification-info.component';
import { NotificationInfoDetailComponent } from './notification-info-detail.component';
import { NotificationInfoUpdateComponent } from './notification-info-update.component';

@Injectable({ providedIn: 'root' })
export class NotificationInfoResolve implements Resolve<INotificationInfo> {
  constructor(private service: NotificationInfoService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<INotificationInfo> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((notificationInfo: HttpResponse<NotificationInfo>) => {
          if (notificationInfo.body) {
            return of(notificationInfo.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new NotificationInfo());
  }
}

export const notificationInfoRoute: Routes = [
  {
    path: '',
    component: NotificationInfoComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appApp.notificationInfo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: NotificationInfoDetailComponent,
    resolve: {
      notificationInfo: NotificationInfoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appApp.notificationInfo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: NotificationInfoUpdateComponent,
    resolve: {
      notificationInfo: NotificationInfoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appApp.notificationInfo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: NotificationInfoUpdateComponent,
    resolve: {
      notificationInfo: NotificationInfoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appApp.notificationInfo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
