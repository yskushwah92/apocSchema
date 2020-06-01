import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ICommunicationConfiguration, CommunicationConfiguration } from 'app/shared/model/communication-configuration.model';
import { CommunicationConfigurationService } from './communication-configuration.service';
import { CommunicationConfigurationComponent } from './communication-configuration.component';
import { CommunicationConfigurationDetailComponent } from './communication-configuration-detail.component';
import { CommunicationConfigurationUpdateComponent } from './communication-configuration-update.component';

@Injectable({ providedIn: 'root' })
export class CommunicationConfigurationResolve implements Resolve<ICommunicationConfiguration> {
  constructor(private service: CommunicationConfigurationService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICommunicationConfiguration> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((communicationConfiguration: HttpResponse<CommunicationConfiguration>) => {
          if (communicationConfiguration.body) {
            return of(communicationConfiguration.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new CommunicationConfiguration());
  }
}

export const communicationConfigurationRoute: Routes = [
  {
    path: '',
    component: CommunicationConfigurationComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appApp.communicationConfiguration.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CommunicationConfigurationDetailComponent,
    resolve: {
      communicationConfiguration: CommunicationConfigurationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appApp.communicationConfiguration.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CommunicationConfigurationUpdateComponent,
    resolve: {
      communicationConfiguration: CommunicationConfigurationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appApp.communicationConfiguration.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: CommunicationConfigurationUpdateComponent,
    resolve: {
      communicationConfiguration: CommunicationConfigurationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appApp.communicationConfiguration.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
