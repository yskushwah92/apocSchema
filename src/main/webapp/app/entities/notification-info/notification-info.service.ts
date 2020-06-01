import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { INotificationInfo } from 'app/shared/model/notification-info.model';

type EntityResponseType = HttpResponse<INotificationInfo>;
type EntityArrayResponseType = HttpResponse<INotificationInfo[]>;

@Injectable({ providedIn: 'root' })
export class NotificationInfoService {
  public resourceUrl = SERVER_API_URL + 'api/notification-infos';

  constructor(protected http: HttpClient) {}

  create(notificationInfo: INotificationInfo): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(notificationInfo);
    return this.http
      .post<INotificationInfo>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(notificationInfo: INotificationInfo): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(notificationInfo);
    return this.http
      .put<INotificationInfo>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<INotificationInfo>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<INotificationInfo[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(notificationInfo: INotificationInfo): INotificationInfo {
    const copy: INotificationInfo = Object.assign({}, notificationInfo, {
      createdAt: notificationInfo.createdAt && notificationInfo.createdAt.isValid() ? notificationInfo.createdAt.toJSON() : undefined,
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
      res.body.forEach((notificationInfo: INotificationInfo) => {
        notificationInfo.createdAt = notificationInfo.createdAt ? moment(notificationInfo.createdAt) : undefined;
      });
    }
    return res;
  }
}
