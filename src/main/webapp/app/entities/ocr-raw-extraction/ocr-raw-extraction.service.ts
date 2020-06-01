import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IOCRRawExtraction } from 'app/shared/model/ocr-raw-extraction.model';

type EntityResponseType = HttpResponse<IOCRRawExtraction>;
type EntityArrayResponseType = HttpResponse<IOCRRawExtraction[]>;

@Injectable({ providedIn: 'root' })
export class OCRRawExtractionService {
  public resourceUrl = SERVER_API_URL + 'api/ocr-raw-extractions';

  constructor(protected http: HttpClient) {}

  create(oCRRawExtraction: IOCRRawExtraction): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(oCRRawExtraction);
    return this.http
      .post<IOCRRawExtraction>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(oCRRawExtraction: IOCRRawExtraction): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(oCRRawExtraction);
    return this.http
      .put<IOCRRawExtraction>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IOCRRawExtraction>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IOCRRawExtraction[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(oCRRawExtraction: IOCRRawExtraction): IOCRRawExtraction {
    const copy: IOCRRawExtraction = Object.assign({}, oCRRawExtraction, {
      createdAt: oCRRawExtraction.createdAt && oCRRawExtraction.createdAt.isValid() ? oCRRawExtraction.createdAt.toJSON() : undefined,
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
      res.body.forEach((oCRRawExtraction: IOCRRawExtraction) => {
        oCRRawExtraction.createdAt = oCRRawExtraction.createdAt ? moment(oCRRawExtraction.createdAt) : undefined;
      });
    }
    return res;
  }
}
