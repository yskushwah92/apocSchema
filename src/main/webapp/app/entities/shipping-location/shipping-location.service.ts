import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IShippingLocation } from 'app/shared/model/shipping-location.model';

type EntityResponseType = HttpResponse<IShippingLocation>;
type EntityArrayResponseType = HttpResponse<IShippingLocation[]>;

@Injectable({ providedIn: 'root' })
export class ShippingLocationService {
  public resourceUrl = SERVER_API_URL + 'api/shipping-locations';

  constructor(protected http: HttpClient) {}

  create(shippingLocation: IShippingLocation): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(shippingLocation);
    return this.http
      .post<IShippingLocation>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(shippingLocation: IShippingLocation): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(shippingLocation);
    return this.http
      .put<IShippingLocation>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IShippingLocation>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IShippingLocation[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(shippingLocation: IShippingLocation): IShippingLocation {
    const copy: IShippingLocation = Object.assign({}, shippingLocation, {
      createdAt: shippingLocation.createdAt && shippingLocation.createdAt.isValid() ? shippingLocation.createdAt.toJSON() : undefined,
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
      res.body.forEach((shippingLocation: IShippingLocation) => {
        shippingLocation.createdAt = shippingLocation.createdAt ? moment(shippingLocation.createdAt) : undefined;
      });
    }
    return res;
  }
}
