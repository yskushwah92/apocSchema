import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IOCRExtractionEngineInfo, OCRExtractionEngineInfo } from 'app/shared/model/ocr-extraction-engine-info.model';
import { OCRExtractionEngineInfoService } from './ocr-extraction-engine-info.service';
import { OCRExtractionEngineInfoComponent } from './ocr-extraction-engine-info.component';
import { OCRExtractionEngineInfoDetailComponent } from './ocr-extraction-engine-info-detail.component';
import { OCRExtractionEngineInfoUpdateComponent } from './ocr-extraction-engine-info-update.component';

@Injectable({ providedIn: 'root' })
export class OCRExtractionEngineInfoResolve implements Resolve<IOCRExtractionEngineInfo> {
  constructor(private service: OCRExtractionEngineInfoService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IOCRExtractionEngineInfo> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((oCRExtractionEngineInfo: HttpResponse<OCRExtractionEngineInfo>) => {
          if (oCRExtractionEngineInfo.body) {
            return of(oCRExtractionEngineInfo.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new OCRExtractionEngineInfo());
  }
}

export const oCRExtractionEngineInfoRoute: Routes = [
  {
    path: '',
    component: OCRExtractionEngineInfoComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appApp.oCRExtractionEngineInfo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: OCRExtractionEngineInfoDetailComponent,
    resolve: {
      oCRExtractionEngineInfo: OCRExtractionEngineInfoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appApp.oCRExtractionEngineInfo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: OCRExtractionEngineInfoUpdateComponent,
    resolve: {
      oCRExtractionEngineInfo: OCRExtractionEngineInfoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appApp.oCRExtractionEngineInfo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: OCRExtractionEngineInfoUpdateComponent,
    resolve: {
      oCRExtractionEngineInfo: OCRExtractionEngineInfoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appApp.oCRExtractionEngineInfo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
