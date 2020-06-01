import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IPurchaseOrderLine } from 'app/shared/model/purchase-order-line.model';

type EntityResponseType = HttpResponse<IPurchaseOrderLine>;
type EntityArrayResponseType = HttpResponse<IPurchaseOrderLine[]>;

@Injectable({ providedIn: 'root' })
export class PurchaseOrderLineService {
  public resourceUrl = SERVER_API_URL + 'api/purchase-order-lines';

  constructor(protected http: HttpClient) {}

  create(purchaseOrderLine: IPurchaseOrderLine): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(purchaseOrderLine);
    return this.http
      .post<IPurchaseOrderLine>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(purchaseOrderLine: IPurchaseOrderLine): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(purchaseOrderLine);
    return this.http
      .put<IPurchaseOrderLine>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IPurchaseOrderLine>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IPurchaseOrderLine[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(purchaseOrderLine: IPurchaseOrderLine): IPurchaseOrderLine {
    const copy: IPurchaseOrderLine = Object.assign({}, purchaseOrderLine, {
      deliveryDate:
        purchaseOrderLine.deliveryDate && purchaseOrderLine.deliveryDate.isValid() ? purchaseOrderLine.deliveryDate.toJSON() : undefined,
      createdAt: purchaseOrderLine.createdAt && purchaseOrderLine.createdAt.isValid() ? purchaseOrderLine.createdAt.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.deliveryDate = res.body.deliveryDate ? moment(res.body.deliveryDate) : undefined;
      res.body.createdAt = res.body.createdAt ? moment(res.body.createdAt) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((purchaseOrderLine: IPurchaseOrderLine) => {
        purchaseOrderLine.deliveryDate = purchaseOrderLine.deliveryDate ? moment(purchaseOrderLine.deliveryDate) : undefined;
        purchaseOrderLine.createdAt = purchaseOrderLine.createdAt ? moment(purchaseOrderLine.createdAt) : undefined;
      });
    }
    return res;
  }
}
