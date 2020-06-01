import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IVendor } from 'app/shared/model/vendor.model';

type EntityResponseType = HttpResponse<IVendor>;
type EntityArrayResponseType = HttpResponse<IVendor[]>;

@Injectable({ providedIn: 'root' })
export class VendorService {
  public resourceUrl = SERVER_API_URL + 'api/vendors';

  constructor(protected http: HttpClient) {}

  create(vendor: IVendor): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(vendor);
    return this.http
      .post<IVendor>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(vendor: IVendor): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(vendor);
    return this.http
      .put<IVendor>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IVendor>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IVendor[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(vendor: IVendor): IVendor {
    const copy: IVendor = Object.assign({}, vendor, {
      createdAt: vendor.createdAt && vendor.createdAt.isValid() ? vendor.createdAt.toJSON() : undefined,
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
      res.body.forEach((vendor: IVendor) => {
        vendor.createdAt = vendor.createdAt ? moment(vendor.createdAt) : undefined;
      });
    }
    return res;
  }
}
