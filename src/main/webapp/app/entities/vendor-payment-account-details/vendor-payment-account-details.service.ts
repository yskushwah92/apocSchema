import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IVendorPaymentAccountDetails } from 'app/shared/model/vendor-payment-account-details.model';

type EntityResponseType = HttpResponse<IVendorPaymentAccountDetails>;
type EntityArrayResponseType = HttpResponse<IVendorPaymentAccountDetails[]>;

@Injectable({ providedIn: 'root' })
export class VendorPaymentAccountDetailsService {
  public resourceUrl = SERVER_API_URL + 'api/vendor-payment-account-details';

  constructor(protected http: HttpClient) {}

  create(vendorPaymentAccountDetails: IVendorPaymentAccountDetails): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(vendorPaymentAccountDetails);
    return this.http
      .post<IVendorPaymentAccountDetails>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(vendorPaymentAccountDetails: IVendorPaymentAccountDetails): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(vendorPaymentAccountDetails);
    return this.http
      .put<IVendorPaymentAccountDetails>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IVendorPaymentAccountDetails>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IVendorPaymentAccountDetails[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(vendorPaymentAccountDetails: IVendorPaymentAccountDetails): IVendorPaymentAccountDetails {
    const copy: IVendorPaymentAccountDetails = Object.assign({}, vendorPaymentAccountDetails, {
      createdAt:
        vendorPaymentAccountDetails.createdAt && vendorPaymentAccountDetails.createdAt.isValid()
          ? vendorPaymentAccountDetails.createdAt.toJSON()
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
      res.body.forEach((vendorPaymentAccountDetails: IVendorPaymentAccountDetails) => {
        vendorPaymentAccountDetails.createdAt = vendorPaymentAccountDetails.createdAt
          ? moment(vendorPaymentAccountDetails.createdAt)
          : undefined;
      });
    }
    return res;
  }
}
