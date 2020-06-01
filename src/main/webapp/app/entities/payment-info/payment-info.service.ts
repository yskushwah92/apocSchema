import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IPaymentInfo } from 'app/shared/model/payment-info.model';

type EntityResponseType = HttpResponse<IPaymentInfo>;
type EntityArrayResponseType = HttpResponse<IPaymentInfo[]>;

@Injectable({ providedIn: 'root' })
export class PaymentInfoService {
  public resourceUrl = SERVER_API_URL + 'api/payment-infos';

  constructor(protected http: HttpClient) {}

  create(paymentInfo: IPaymentInfo): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(paymentInfo);
    return this.http
      .post<IPaymentInfo>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(paymentInfo: IPaymentInfo): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(paymentInfo);
    return this.http
      .put<IPaymentInfo>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IPaymentInfo>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IPaymentInfo[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(paymentInfo: IPaymentInfo): IPaymentInfo {
    const copy: IPaymentInfo = Object.assign({}, paymentInfo, {
      dueDate: paymentInfo.dueDate && paymentInfo.dueDate.isValid() ? paymentInfo.dueDate.toJSON() : undefined,
      dateOfApproval: paymentInfo.dateOfApproval && paymentInfo.dateOfApproval.isValid() ? paymentInfo.dateOfApproval.toJSON() : undefined,
      dateOfPayment: paymentInfo.dateOfPayment && paymentInfo.dateOfPayment.isValid() ? paymentInfo.dateOfPayment.toJSON() : undefined,
      checkDate: paymentInfo.checkDate && paymentInfo.checkDate.isValid() ? paymentInfo.checkDate.toJSON() : undefined,
      earlyPaymentDate:
        paymentInfo.earlyPaymentDate && paymentInfo.earlyPaymentDate.isValid() ? paymentInfo.earlyPaymentDate.toJSON() : undefined,
      paymentDocDate: paymentInfo.paymentDocDate && paymentInfo.paymentDocDate.isValid() ? paymentInfo.paymentDocDate.toJSON() : undefined,
      clearedDate: paymentInfo.clearedDate && paymentInfo.clearedDate.isValid() ? paymentInfo.clearedDate.toJSON() : undefined,
      createdAt: paymentInfo.createdAt && paymentInfo.createdAt.isValid() ? paymentInfo.createdAt.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dueDate = res.body.dueDate ? moment(res.body.dueDate) : undefined;
      res.body.dateOfApproval = res.body.dateOfApproval ? moment(res.body.dateOfApproval) : undefined;
      res.body.dateOfPayment = res.body.dateOfPayment ? moment(res.body.dateOfPayment) : undefined;
      res.body.checkDate = res.body.checkDate ? moment(res.body.checkDate) : undefined;
      res.body.earlyPaymentDate = res.body.earlyPaymentDate ? moment(res.body.earlyPaymentDate) : undefined;
      res.body.paymentDocDate = res.body.paymentDocDate ? moment(res.body.paymentDocDate) : undefined;
      res.body.clearedDate = res.body.clearedDate ? moment(res.body.clearedDate) : undefined;
      res.body.createdAt = res.body.createdAt ? moment(res.body.createdAt) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((paymentInfo: IPaymentInfo) => {
        paymentInfo.dueDate = paymentInfo.dueDate ? moment(paymentInfo.dueDate) : undefined;
        paymentInfo.dateOfApproval = paymentInfo.dateOfApproval ? moment(paymentInfo.dateOfApproval) : undefined;
        paymentInfo.dateOfPayment = paymentInfo.dateOfPayment ? moment(paymentInfo.dateOfPayment) : undefined;
        paymentInfo.checkDate = paymentInfo.checkDate ? moment(paymentInfo.checkDate) : undefined;
        paymentInfo.earlyPaymentDate = paymentInfo.earlyPaymentDate ? moment(paymentInfo.earlyPaymentDate) : undefined;
        paymentInfo.paymentDocDate = paymentInfo.paymentDocDate ? moment(paymentInfo.paymentDocDate) : undefined;
        paymentInfo.clearedDate = paymentInfo.clearedDate ? moment(paymentInfo.clearedDate) : undefined;
        paymentInfo.createdAt = paymentInfo.createdAt ? moment(paymentInfo.createdAt) : undefined;
      });
    }
    return res;
  }
}
