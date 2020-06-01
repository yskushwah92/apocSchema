import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IAuditLogDetails } from 'app/shared/model/audit-log-details.model';

type EntityResponseType = HttpResponse<IAuditLogDetails>;
type EntityArrayResponseType = HttpResponse<IAuditLogDetails[]>;

@Injectable({ providedIn: 'root' })
export class AuditLogDetailsService {
  public resourceUrl = SERVER_API_URL + 'api/audit-log-details';

  constructor(protected http: HttpClient) {}

  create(auditLogDetails: IAuditLogDetails): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(auditLogDetails);
    return this.http
      .post<IAuditLogDetails>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(auditLogDetails: IAuditLogDetails): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(auditLogDetails);
    return this.http
      .put<IAuditLogDetails>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IAuditLogDetails>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IAuditLogDetails[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(auditLogDetails: IAuditLogDetails): IAuditLogDetails {
    const copy: IAuditLogDetails = Object.assign({}, auditLogDetails, {
      createdOn: auditLogDetails.createdOn && auditLogDetails.createdOn.isValid() ? auditLogDetails.createdOn.toJSON() : undefined,
      createdAt: auditLogDetails.createdAt && auditLogDetails.createdAt.isValid() ? auditLogDetails.createdAt.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.createdOn = res.body.createdOn ? moment(res.body.createdOn) : undefined;
      res.body.createdAt = res.body.createdAt ? moment(res.body.createdAt) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((auditLogDetails: IAuditLogDetails) => {
        auditLogDetails.createdOn = auditLogDetails.createdOn ? moment(auditLogDetails.createdOn) : undefined;
        auditLogDetails.createdAt = auditLogDetails.createdAt ? moment(auditLogDetails.createdAt) : undefined;
      });
    }
    return res;
  }
}
