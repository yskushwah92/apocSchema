import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IModelInfo } from 'app/shared/model/model-info.model';

type EntityResponseType = HttpResponse<IModelInfo>;
type EntityArrayResponseType = HttpResponse<IModelInfo[]>;

@Injectable({ providedIn: 'root' })
export class ModelInfoService {
  public resourceUrl = SERVER_API_URL + 'api/model-infos';

  constructor(protected http: HttpClient) {}

  create(modelInfo: IModelInfo): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(modelInfo);
    return this.http
      .post<IModelInfo>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(modelInfo: IModelInfo): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(modelInfo);
    return this.http
      .put<IModelInfo>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IModelInfo>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IModelInfo[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(modelInfo: IModelInfo): IModelInfo {
    const copy: IModelInfo = Object.assign({}, modelInfo, {
      createdAt: modelInfo.createdAt && modelInfo.createdAt.isValid() ? modelInfo.createdAt.toJSON() : undefined,
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
      res.body.forEach((modelInfo: IModelInfo) => {
        modelInfo.createdAt = modelInfo.createdAt ? moment(modelInfo.createdAt) : undefined;
      });
    }
    return res;
  }
}
