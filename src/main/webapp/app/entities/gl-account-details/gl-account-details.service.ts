import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IGLAccountDetails } from 'app/shared/model/gl-account-details.model';

type EntityResponseType = HttpResponse<IGLAccountDetails>;
type EntityArrayResponseType = HttpResponse<IGLAccountDetails[]>;

@Injectable({ providedIn: 'root' })
export class GLAccountDetailsService {
  public resourceUrl = SERVER_API_URL + 'api/gl-account-details';

  constructor(protected http: HttpClient) {}

  create(gLAccountDetails: IGLAccountDetails): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(gLAccountDetails);
    return this.http
      .post<IGLAccountDetails>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(gLAccountDetails: IGLAccountDetails): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(gLAccountDetails);
    return this.http
      .put<IGLAccountDetails>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IGLAccountDetails>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IGLAccountDetails[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(gLAccountDetails: IGLAccountDetails): IGLAccountDetails {
    const copy: IGLAccountDetails = Object.assign({}, gLAccountDetails, {
      createdAt: gLAccountDetails.createdAt && gLAccountDetails.createdAt.isValid() ? gLAccountDetails.createdAt.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.createdAt = res.body.createdAt ? moment(res.body.createdAt) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((gLAccountDetails: IGLAccountDetails) => {
        gLAccountDetails.createdAt = gLAccountDetails.createdAt ? moment(gLAccountDetails.createdAt) : undefined;
      });
    }
    return res;
  }
}
