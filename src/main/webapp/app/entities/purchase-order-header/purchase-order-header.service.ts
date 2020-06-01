import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IPurchaseOrderHeader } from 'app/shared/model/purchase-order-header.model';

type EntityResponseType = HttpResponse<IPurchaseOrderHeader>;
type EntityArrayResponseType = HttpResponse<IPurchaseOrderHeader[]>;

@Injectable({ providedIn: 'root' })
export class PurchaseOrderHeaderService {
  public resourceUrl = SERVER_API_URL + 'api/purchase-order-headers';

  constructor(protected http: HttpClient) {}

  create(purchaseOrderHeader: IPurchaseOrderHeader): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(purchaseOrderHeader);
    return this.http
      .post<IPurchaseOrderHeader>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(purchaseOrderHeader: IPurchaseOrderHeader): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(purchaseOrderHeader);
    return this.http
      .put<IPurchaseOrderHeader>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IPurchaseOrderHeader>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IPurchaseOrderHeader[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(purchaseOrderHeader: IPurchaseOrderHeader): IPurchaseOrderHeader {
    const copy: IPurchaseOrderHeader = Object.assign({}, purchaseOrderHeader, {
      creationDate:
        purchaseOrderHeader.creationDate && purchaseOrderHeader.creationDate.isValid()
          ? purchaseOrderHeader.creationDate.toJSON()
          : undefined,
      createdAt:
        purchaseOrderHeader.createdAt && purchaseOrderHeader.createdAt.isValid() ? purchaseOrderHeader.createdAt.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.creationDate = res.body.creationDate ? moment(res.body.creationDate) : undefined;
      res.body.createdAt = res.body.createdAt ? moment(res.body.createdAt) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((purchaseOrderHeader: IPurchaseOrderHeader) => {
        purchaseOrderHeader.creationDate = purchaseOrderHeader.creationDate ? moment(purchaseOrderHeader.creationDate) : undefined;
        purchaseOrderHeader.createdAt = purchaseOrderHeader.createdAt ? moment(purchaseOrderHeader.createdAt) : undefined;
      });
    }
    return res;
  }
}
