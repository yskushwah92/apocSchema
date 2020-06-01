import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IInvoiceHeader } from 'app/shared/model/invoice-header.model';

type EntityResponseType = HttpResponse<IInvoiceHeader>;
type EntityArrayResponseType = HttpResponse<IInvoiceHeader[]>;

@Injectable({ providedIn: 'root' })
export class InvoiceHeaderService {
  public resourceUrl = SERVER_API_URL + 'api/invoice-headers';

  constructor(protected http: HttpClient) {}

  create(invoiceHeader: IInvoiceHeader): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(invoiceHeader);
    return this.http
      .post<IInvoiceHeader>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(invoiceHeader: IInvoiceHeader): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(invoiceHeader);
    return this.http
      .put<IInvoiceHeader>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IInvoiceHeader>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IInvoiceHeader[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(invoiceHeader: IInvoiceHeader): IInvoiceHeader {
    const copy: IInvoiceHeader = Object.assign({}, invoiceHeader, {
      scanDate: invoiceHeader.scanDate && invoiceHeader.scanDate.isValid() ? invoiceHeader.scanDate.toJSON() : undefined,
      receivedDate: invoiceHeader.receivedDate && invoiceHeader.receivedDate.isValid() ? invoiceHeader.receivedDate.toJSON() : undefined,
      caseCreationDate:
        invoiceHeader.caseCreationDate && invoiceHeader.caseCreationDate.isValid() ? invoiceHeader.caseCreationDate.toJSON() : undefined,
      slaExpirationDate:
        invoiceHeader.slaExpirationDate && invoiceHeader.slaExpirationDate.isValid() ? invoiceHeader.slaExpirationDate.toJSON() : undefined,
      deliveryDate: invoiceHeader.deliveryDate && invoiceHeader.deliveryDate.isValid() ? invoiceHeader.deliveryDate.toJSON() : undefined,
      deliveryNoteNumber:
        invoiceHeader.deliveryNoteNumber && invoiceHeader.deliveryNoteNumber.isValid()
          ? invoiceHeader.deliveryNoteNumber.toJSON()
          : undefined,
      postingDate: invoiceHeader.postingDate && invoiceHeader.postingDate.isValid() ? invoiceHeader.postingDate.toJSON() : undefined,
      termDate: invoiceHeader.termDate && invoiceHeader.termDate.isValid() ? invoiceHeader.termDate.toJSON() : undefined,
      createdAt: invoiceHeader.createdAt && invoiceHeader.createdAt.isValid() ? invoiceHeader.createdAt.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.scanDate = res.body.scanDate ? moment(res.body.scanDate) : undefined;
      res.body.receivedDate = res.body.receivedDate ? moment(res.body.receivedDate) : undefined;
      res.body.caseCreationDate = res.body.caseCreationDate ? moment(res.body.caseCreationDate) : undefined;
      res.body.slaExpirationDate = res.body.slaExpirationDate ? moment(res.body.slaExpirationDate) : undefined;
      res.body.deliveryDate = res.body.deliveryDate ? moment(res.body.deliveryDate) : undefined;
      res.body.deliveryNoteNumber = res.body.deliveryNoteNumber ? moment(res.body.deliveryNoteNumber) : undefined;
      res.body.postingDate = res.body.postingDate ? moment(res.body.postingDate) : undefined;
      res.body.termDate = res.body.termDate ? moment(res.body.termDate) : undefined;
      res.body.createdAt = res.body.createdAt ? moment(res.body.createdAt) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((invoiceHeader: IInvoiceHeader) => {
        invoiceHeader.scanDate = invoiceHeader.scanDate ? moment(invoiceHeader.scanDate) : undefined;
        invoiceHeader.receivedDate = invoiceHeader.receivedDate ? moment(invoiceHeader.receivedDate) : undefined;
        invoiceHeader.caseCreationDate = invoiceHeader.caseCreationDate ? moment(invoiceHeader.caseCreationDate) : undefined;
        invoiceHeader.slaExpirationDate = invoiceHeader.slaExpirationDate ? moment(invoiceHeader.slaExpirationDate) : undefined;
        invoiceHeader.deliveryDate = invoiceHeader.deliveryDate ? moment(invoiceHeader.deliveryDate) : undefined;
        invoiceHeader.deliveryNoteNumber = invoiceHeader.deliveryNoteNumber ? moment(invoiceHeader.deliveryNoteNumber) : undefined;
        invoiceHeader.postingDate = invoiceHeader.postingDate ? moment(invoiceHeader.postingDate) : undefined;
        invoiceHeader.termDate = invoiceHeader.termDate ? moment(invoiceHeader.termDate) : undefined;
        invoiceHeader.createdAt = invoiceHeader.createdAt ? moment(invoiceHeader.createdAt) : undefined;
      });
    }
    return res;
  }
}
