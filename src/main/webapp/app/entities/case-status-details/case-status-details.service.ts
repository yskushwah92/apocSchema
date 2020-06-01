import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICaseStatusDetails } from 'app/shared/model/case-status-details.model';

type EntityResponseType = HttpResponse<ICaseStatusDetails>;
type EntityArrayResponseType = HttpResponse<ICaseStatusDetails[]>;

@Injectable({ providedIn: 'root' })
export class CaseStatusDetailsService {
  public resourceUrl = SERVER_API_URL + 'api/case-status-details';

  constructor(protected http: HttpClient) {}

  create(caseStatusDetails: ICaseStatusDetails): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(caseStatusDetails);
    return this.http
      .post<ICaseStatusDetails>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(caseStatusDetails: ICaseStatusDetails): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(caseStatusDetails);
    return this.http
      .put<ICaseStatusDetails>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ICaseStatusDetails>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ICaseStatusDetails[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(caseStatusDetails: ICaseStatusDetails): ICaseStatusDetails {
    const copy: ICaseStatusDetails = Object.assign({}, caseStatusDetails, {
      createdAt: caseStatusDetails.createdAt && caseStatusDetails.createdAt.isValid() ? caseStatusDetails.createdAt.toJSON() : undefined,
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
      res.body.forEach((caseStatusDetails: ICaseStatusDetails) => {
        caseStatusDetails.createdAt = caseStatusDetails.createdAt ? moment(caseStatusDetails.createdAt) : undefined;
      });
    }
    return res;
  }
}
