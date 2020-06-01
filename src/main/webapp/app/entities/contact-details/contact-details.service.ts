import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IContactDetails } from 'app/shared/model/contact-details.model';

type EntityResponseType = HttpResponse<IContactDetails>;
type EntityArrayResponseType = HttpResponse<IContactDetails[]>;

@Injectable({ providedIn: 'root' })
export class ContactDetailsService {
  public resourceUrl = SERVER_API_URL + 'api/contact-details';

  constructor(protected http: HttpClient) {}

  create(contactDetails: IContactDetails): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(contactDetails);
    return this.http
      .post<IContactDetails>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(contactDetails: IContactDetails): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(contactDetails);
    return this.http
      .put<IContactDetails>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IContactDetails>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IContactDetails[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(contactDetails: IContactDetails): IContactDetails {
    const copy: IContactDetails = Object.assign({}, contactDetails, {
      createdAt: contactDetails.createdAt && contactDetails.createdAt.isValid() ? contactDetails.createdAt.toJSON() : undefined,
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
      res.body.forEach((contactDetails: IContactDetails) => {
        contactDetails.createdAt = contactDetails.createdAt ? moment(contactDetails.createdAt) : undefined;
      });
    }
    return res;
  }
}
