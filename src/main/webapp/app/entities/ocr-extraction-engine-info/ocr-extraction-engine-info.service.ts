import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IOCRExtractionEngineInfo } from 'app/shared/model/ocr-extraction-engine-info.model';

type EntityResponseType = HttpResponse<IOCRExtractionEngineInfo>;
type EntityArrayResponseType = HttpResponse<IOCRExtractionEngineInfo[]>;

@Injectable({ providedIn: 'root' })
export class OCRExtractionEngineInfoService {
  public resourceUrl = SERVER_API_URL + 'api/ocr-extraction-engine-infos';

  constructor(protected http: HttpClient) {}

  create(oCRExtractionEngineInfo: IOCRExtractionEngineInfo): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(oCRExtractionEngineInfo);
    return this.http
      .post<IOCRExtractionEngineInfo>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(oCRExtractionEngineInfo: IOCRExtractionEngineInfo): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(oCRExtractionEngineInfo);
    return this.http
      .put<IOCRExtractionEngineInfo>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IOCRExtractionEngineInfo>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IOCRExtractionEngineInfo[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(oCRExtractionEngineInfo: IOCRExtractionEngineInfo): IOCRExtractionEngineInfo {
    const copy: IOCRExtractionEngineInfo = Object.assign({}, oCRExtractionEngineInfo, {
      createdAt:
        oCRExtractionEngineInfo.createdAt && oCRExtractionEngineInfo.createdAt.isValid()
          ? oCRExtractionEngineInfo.createdAt.toJSON()
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
      res.body.forEach((oCRExtractionEngineInfo: IOCRExtractionEngineInfo) => {
        oCRExtractionEngineInfo.createdAt = oCRExtractionEngineInfo.createdAt ? moment(oCRExtractionEngineInfo.createdAt) : undefined;
      });
    }
    return res;
  }
}
