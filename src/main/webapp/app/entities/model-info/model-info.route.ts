import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IModelInfo, ModelInfo } from 'app/shared/model/model-info.model';
import { ModelInfoService } from './model-info.service';
import { ModelInfoComponent } from './model-info.component';
import { ModelInfoDetailComponent } from './model-info-detail.component';
import { ModelInfoUpdateComponent } from './model-info-update.component';

@Injectable({ providedIn: 'root' })
export class ModelInfoResolve implements Resolve<IModelInfo> {
  constructor(private service: ModelInfoService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IModelInfo> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((modelInfo: HttpResponse<ModelInfo>) => {
          if (modelInfo.body) {
            return of(modelInfo.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ModelInfo());
  }
}

export const modelInfoRoute: Routes = [
  {
    path: '',
    component: ModelInfoComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appApp.modelInfo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ModelInfoDetailComponent,
    resolve: {
      modelInfo: ModelInfoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appApp.modelInfo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ModelInfoUpdateComponent,
    resolve: {
      modelInfo: ModelInfoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appApp.modelInfo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ModelInfoUpdateComponent,
    resolve: {
      modelInfo: ModelInfoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appApp.modelInfo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
