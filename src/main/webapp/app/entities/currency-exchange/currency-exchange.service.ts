import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICurrencyExchange } from 'app/shared/model/currency-exchange.model';

type EntityResponseType = HttpResponse<ICurrencyExchange>;
type EntityArrayResponseType = HttpResponse<ICurrencyExchange[]>;

@Injectable({ providedIn: 'root' })
export class CurrencyExchangeService {
  public resourceUrl = SERVER_API_URL + 'api/currency-exchanges';

  constructor(protected http: HttpClient) {}

  create(currencyExchange: ICurrencyExchange): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(currencyExchange);
    return this.http
      .post<ICurrencyExchange>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(currencyExchange: ICurrencyExchange): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(currencyExchange);
    return this.http
      .put<ICurrencyExchange>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ICurrencyExchange>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ICurrencyExchange[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(currencyExchange: ICurrencyExchange): ICurrencyExchange {
    const copy: ICurrencyExchange = Object.assign({}, currencyExchange, {
      validfrom: currencyExchange.validfrom && currencyExchange.validfrom.isValid() ? currencyExchange.validfrom.toJSON() : undefined,
      createdAt: currencyExchange.createdAt && currencyExchange.createdAt.isValid() ? currencyExchange.createdAt.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.validfrom = res.body.validfrom ? moment(res.body.validfrom) : undefined;
      res.body.createdAt = res.body.createdAt ? moment(res.body.createdAt) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((currencyExchange: ICurrencyExchange) => {
        currencyExchange.validfrom = currencyExchange.validfrom ? moment(currencyExchange.validfrom) : undefined;
        currencyExchange.createdAt = currencyExchange.createdAt ? moment(currencyExchange.createdAt) : undefined;
      });
    }
    return res;
  }
}
