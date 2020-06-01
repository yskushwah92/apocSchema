import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IMailBox, MailBox } from 'app/shared/model/mail-box.model';
import { MailBoxService } from './mail-box.service';
import { MailBoxComponent } from './mail-box.component';
import { MailBoxDetailComponent } from './mail-box-detail.component';
import { MailBoxUpdateComponent } from './mail-box-update.component';

@Injectable({ providedIn: 'root' })
export class MailBoxResolve implements Resolve<IMailBox> {
  constructor(private service: MailBoxService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IMailBox> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((mailBox: HttpResponse<MailBox>) => {
          if (mailBox.body) {
            return of(mailBox.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new MailBox());
  }
}

export const mailBoxRoute: Routes = [
  {
    path: '',
    component: MailBoxComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appApp.mailBox.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: MailBoxDetailComponent,
    resolve: {
      mailBox: MailBoxResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appApp.mailBox.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: MailBoxUpdateComponent,
    resolve: {
      mailBox: MailBoxResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appApp.mailBox.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: MailBoxUpdateComponent,
    resolve: {
      mailBox: MailBoxResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appApp.mailBox.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
