import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITaxCode } from 'app/shared/model/tax-code.model';

type EntityResponseType = HttpResponse<ITaxCode>;
type EntityArrayResponseType = HttpResponse<ITaxCode[]>;

@Injectable({ providedIn: 'root' })
export class TaxCodeService {
  public resourceUrl = SERVER_API_URL + 'api/tax-codes';

  constructor(protected http: HttpClient) {}

  create(taxCode: ITaxCode): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(taxCode);
    return this.http
      .post<ITaxCode>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(taxCode: ITaxCode): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(taxCode);
    return this.http
      .put<ITaxCode>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ITaxCode>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ITaxCode[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(taxCode: ITaxCode): ITaxCode {
    const copy: ITaxCode = Object.assign({}, taxCode, {
      createdAt: taxCode.createdAt && taxCode.createdAt.isValid() ? taxCode.createdAt.toJSON() : undefined,
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
      res.body.forEach((taxCode: ITaxCode) => {
        taxCode.createdAt = taxCode.createdAt ? moment(taxCode.createdAt) : undefined;
      });
    }
    return res;
  }
}
