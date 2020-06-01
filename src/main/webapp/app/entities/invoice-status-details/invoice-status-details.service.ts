import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IInvoiceStatusDetails } from 'app/shared/model/invoice-status-details.model';

type EntityResponseType = HttpResponse<IInvoiceStatusDetails>;
type EntityArrayResponseType = HttpResponse<IInvoiceStatusDetails[]>;

@Injectable({ providedIn: 'root' })
export class InvoiceStatusDetailsService {
  public resourceUrl = SERVER_API_URL + 'api/invoice-status-details';

  constructor(protected http: HttpClient) {}

  create(invoiceStatusDetails: IInvoiceStatusDetails): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(invoiceStatusDetails);
    return this.http
      .post<IInvoiceStatusDetails>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(invoiceStatusDetails: IInvoiceStatusDetails): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(invoiceStatusDetails);
    return this.http
      .put<IInvoiceStatusDetails>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IInvoiceStatusDetails>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IInvoiceStatusDetails[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(invoiceStatusDetails: IInvoiceStatusDetails): IInvoiceStatusDetails {
    const copy: IInvoiceStatusDetails = Object.assign({}, invoiceStatusDetails, {
      createdAt:
        invoiceStatusDetails.createdAt && invoiceStatusDetails.createdAt.isValid() ? invoiceStatusDetails.createdAt.toJSON() : undefined,
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
      res.body.forEach((invoiceStatusDetails: IInvoiceStatusDetails) => {
        invoiceStatusDetails.createdAt = invoiceStatusDetails.createdAt ? moment(invoiceStatusDetails.createdAt) : undefined;
      });
    }
    return res;
  }
}
