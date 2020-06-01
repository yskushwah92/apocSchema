import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IInvoiceLineItem } from 'app/shared/model/invoice-line-item.model';

type EntityResponseType = HttpResponse<IInvoiceLineItem>;
type EntityArrayResponseType = HttpResponse<IInvoiceLineItem[]>;

@Injectable({ providedIn: 'root' })
export class InvoiceLineItemService {
  public resourceUrl = SERVER_API_URL + 'api/invoice-line-items';

  constructor(protected http: HttpClient) {}

  create(invoiceLineItem: IInvoiceLineItem): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(invoiceLineItem);
    return this.http
      .post<IInvoiceLineItem>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(invoiceLineItem: IInvoiceLineItem): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(invoiceLineItem);
    return this.http
      .put<IInvoiceLineItem>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IInvoiceLineItem>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IInvoiceLineItem[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(invoiceLineItem: IInvoiceLineItem): IInvoiceLineItem {
    const copy: IInvoiceLineItem = Object.assign({}, invoiceLineItem, {
      limitOrderExpiryDate:
        invoiceLineItem.limitOrderExpiryDate && invoiceLineItem.limitOrderExpiryDate.isValid()
          ? invoiceLineItem.limitOrderExpiryDate.toJSON()
          : undefined,
      limitOrderStartDate:
        invoiceLineItem.limitOrderStartDate && invoiceLineItem.limitOrderStartDate.isValid()
          ? invoiceLineItem.limitOrderStartDate.toJSON()
          : undefined,
      createdAt: invoiceLineItem.createdAt && invoiceLineItem.createdAt.isValid() ? invoiceLineItem.createdAt.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.limitOrderExpiryDate = res.body.limitOrderExpiryDate ? moment(res.body.limitOrderExpiryDate) : undefined;
      res.body.limitOrderStartDate = res.body.limitOrderStartDate ? moment(res.body.limitOrderStartDate) : undefined;
      res.body.createdAt = res.body.createdAt ? moment(res.body.createdAt) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((invoiceLineItem: IInvoiceLineItem) => {
        invoiceLineItem.limitOrderExpiryDate = invoiceLineItem.limitOrderExpiryDate
          ? moment(invoiceLineItem.limitOrderExpiryDate)
          : undefined;
        invoiceLineItem.limitOrderStartDate = invoiceLineItem.limitOrderStartDate ? moment(invoiceLineItem.limitOrderStartDate) : undefined;
        invoiceLineItem.createdAt = invoiceLineItem.createdAt ? moment(invoiceLineItem.createdAt) : undefined;
      });
    }
    return res;
  }
}
