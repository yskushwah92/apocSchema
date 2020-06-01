import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITaxCode, TaxCode } from 'app/shared/model/tax-code.model';
import { TaxCodeService } from './tax-code.service';
import { TaxCodeComponent } from './tax-code.component';
import { TaxCodeDetailComponent } from './tax-code-detail.component';
import { TaxCodeUpdateComponent } from './tax-code-update.component';

@Injectable({ providedIn: 'root' })
export class TaxCodeResolve implements Resolve<ITaxCode> {
  constructor(private service: TaxCodeService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITaxCode> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((taxCode: HttpResponse<TaxCode>) => {
          if (taxCode.body) {
            return of(taxCode.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TaxCode());
  }
}

export const taxCodeRoute: Routes = [
  {
    path: '',
    component: TaxCodeComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appApp.taxCode.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TaxCodeDetailComponent,
    resolve: {
      taxCode: TaxCodeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appApp.taxCode.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TaxCodeUpdateComponent,
    resolve: {
      taxCode: TaxCodeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appApp.taxCode.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TaxCodeUpdateComponent,
    resolve: {
      taxCode: TaxCodeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appApp.taxCode.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
