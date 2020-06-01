import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IOCRRawExtraction, OCRRawExtraction } from 'app/shared/model/ocr-raw-extraction.model';
import { OCRRawExtractionService } from './ocr-raw-extraction.service';
import { OCRRawExtractionComponent } from './ocr-raw-extraction.component';
import { OCRRawExtractionDetailComponent } from './ocr-raw-extraction-detail.component';
import { OCRRawExtractionUpdateComponent } from './ocr-raw-extraction-update.component';

@Injectable({ providedIn: 'root' })
export class OCRRawExtractionResolve implements Resolve<IOCRRawExtraction> {
  constructor(private service: OCRRawExtractionService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IOCRRawExtraction> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((oCRRawExtraction: HttpResponse<OCRRawExtraction>) => {
          if (oCRRawExtraction.body) {
            return of(oCRRawExtraction.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new OCRRawExtraction());
  }
}

export const oCRRawExtractionRoute: Routes = [
  {
    path: '',
    component: OCRRawExtractionComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appApp.oCRRawExtraction.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: OCRRawExtractionDetailComponent,
    resolve: {
      oCRRawExtraction: OCRRawExtractionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appApp.oCRRawExtraction.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: OCRRawExtractionUpdateComponent,
    resolve: {
      oCRRawExtraction: OCRRawExtractionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appApp.oCRRawExtraction.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: OCRRawExtractionUpdateComponent,
    resolve: {
      oCRRawExtraction: OCRRawExtractionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appApp.oCRRawExtraction.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
