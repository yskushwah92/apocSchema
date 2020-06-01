import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICommunicationConfiguration } from 'app/shared/model/communication-configuration.model';

type EntityResponseType = HttpResponse<ICommunicationConfiguration>;
type EntityArrayResponseType = HttpResponse<ICommunicationConfiguration[]>;

@Injectable({ providedIn: 'root' })
export class CommunicationConfigurationService {
  public resourceUrl = SERVER_API_URL + 'api/communication-configurations';

  constructor(protected http: HttpClient) {}

  create(communicationConfiguration: ICommunicationConfiguration): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(communicationConfiguration);
    return this.http
      .post<ICommunicationConfiguration>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(communicationConfiguration: ICommunicationConfiguration): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(communicationConfiguration);
    return this.http
      .put<ICommunicationConfiguration>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ICommunicationConfiguration>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ICommunicationConfiguration[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(communicationConfiguration: ICommunicationConfiguration): ICommunicationConfiguration {
    const copy: ICommunicationConfiguration = Object.assign({}, communicationConfiguration, {
      createdAt:
        communicationConfiguration.createdAt && communicationConfiguration.createdAt.isValid()
          ? communicationConfiguration.createdAt.toJSON()
          : undefined,
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
      res.body.forEach((communicationConfiguration: ICommunicationConfiguration) => {
        communicationConfiguration.createdAt = communicationConfiguration.createdAt
          ? moment(communicationConfiguration.createdAt)
          : undefined;
      });
    }
    return res;
  }
}
