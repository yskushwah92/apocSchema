import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IAuditLog } from 'app/shared/model/audit-log.model';

type EntityResponseType = HttpResponse<IAuditLog>;
type EntityArrayResponseType = HttpResponse<IAuditLog[]>;

@Injectable({ providedIn: 'root' })
export class AuditLogService {
  public resourceUrl = SERVER_API_URL + 'api/audit-logs';

  constructor(protected http: HttpClient) {}

  create(auditLog: IAuditLog): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(auditLog);
    return this.http
      .post<IAuditLog>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(auditLog: IAuditLog): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(auditLog);
    return this.http
      .put<IAuditLog>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IAuditLog>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IAuditLog[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(auditLog: IAuditLog): IAuditLog {
    const copy: IAuditLog = Object.assign({}, auditLog, {
      activityCreationDate:
        auditLog.activityCreationDate && auditLog.activityCreationDate.isValid() ? auditLog.activityCreationDate.toJSON() : undefined,
      activityStartTime:
        auditLog.activityStartTime && auditLog.activityStartTime.isValid() ? auditLog.activityStartTime.toJSON() : undefined,
      activityEndTime: auditLog.activityEndTime && auditLog.activityEndTime.isValid() ? auditLog.activityEndTime.toJSON() : undefined,
      assignedDate: auditLog.assignedDate && auditLog.assignedDate.isValid() ? auditLog.assignedDate.toJSON() : undefined,
      completeOn: auditLog.completeOn && auditLog.completeOn.isValid() ? auditLog.completeOn.toJSON() : undefined,
      createdAt: auditLog.createdAt && auditLog.createdAt.isValid() ? auditLog.createdAt.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.activityCreationDate = res.body.activityCreationDate ? moment(res.body.activityCreationDate) : undefined;
      res.body.activityStartTime = res.body.activityStartTime ? moment(res.body.activityStartTime) : undefined;
      res.body.activityEndTime = res.body.activityEndTime ? moment(res.body.activityEndTime) : undefined;
      res.body.assignedDate = res.body.assignedDate ? moment(res.body.assignedDate) : undefined;
      res.body.completeOn = res.body.completeOn ? moment(res.body.completeOn) : undefined;
      res.body.createdAt = res.body.createdAt ? moment(res.body.createdAt) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((auditLog: IAuditLog) => {
        auditLog.activityCreationDate = auditLog.activityCreationDate ? moment(auditLog.activityCreationDate) : undefined;
        auditLog.activityStartTime = auditLog.activityStartTime ? moment(auditLog.activityStartTime) : undefined;
        auditLog.activityEndTime = auditLog.activityEndTime ? moment(auditLog.activityEndTime) : undefined;
        auditLog.assignedDate = auditLog.assignedDate ? moment(auditLog.assignedDate) : undefined;
        auditLog.completeOn = auditLog.completeOn ? moment(auditLog.completeOn) : undefined;
        auditLog.createdAt = auditLog.createdAt ? moment(auditLog.createdAt) : undefined;
      });
    }
    return res;
  }
}
