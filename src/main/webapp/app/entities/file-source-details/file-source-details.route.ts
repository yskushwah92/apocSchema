import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IFileSourceDetails, FileSourceDetails } from 'app/shared/model/file-source-details.model';
import { FileSourceDetailsService } from './file-source-details.service';
import { FileSourceDetailsComponent } from './file-source-details.component';
import { FileSourceDetailsDetailComponent } from './file-source-details-detail.component';
import { FileSourceDetailsUpdateComponent } from './file-source-details-update.component';

@Injectable({ providedIn: 'root' })
export class FileSourceDetailsResolve implements Resolve<IFileSourceDetails> {
  constructor(private service: FileSourceDetailsService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IFileSourceDetails> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((fileSourceDetails: HttpResponse<FileSourceDetails>) => {
          if (fileSourceDetails.body) {
            return of(fileSourceDetails.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new FileSourceDetails());
  }
}

export const fileSourceDetailsRoute: Routes = [
  {
    path: '',
    component: FileSourceDetailsComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appApp.fileSourceDetails.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: FileSourceDetailsDetailComponent,
    resolve: {
      fileSourceDetails: FileSourceDetailsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appApp.fileSourceDetails.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: FileSourceDetailsUpdateComponent,
    resolve: {
      fileSourceDetails: FileSourceDetailsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appApp.fileSourceDetails.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: FileSourceDetailsUpdateComponent,
    resolve: {
      fileSourceDetails: FileSourceDetailsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'appApp.fileSourceDetails.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
