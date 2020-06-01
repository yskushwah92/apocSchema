import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IFileSourceDetails } from 'app/shared/model/file-source-details.model';

type EntityResponseType = HttpResponse<IFileSourceDetails>;
type EntityArrayResponseType = HttpResponse<IFileSourceDetails[]>;

@Injectable({ providedIn: 'root' })
export class FileSourceDetailsService {
  public resourceUrl = SERVER_API_URL + 'api/file-source-details';

  constructor(protected http: HttpClient) {}

  create(fileSourceDetails: IFileSourceDetails): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(fileSourceDetails);
    return this.http
      .post<IFileSourceDetails>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(fileSourceDetails: IFileSourceDetails): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(fileSourceDetails);
    return this.http
      .put<IFileSourceDetails>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IFileSourceDetails>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IFileSourceDetails[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(fileSourceDetails: IFileSourceDetails): IFileSourceDetails {
    const copy: IFileSourceDetails = Object.assign({}, fileSourceDetails, {
      createdAt: fileSourceDetails.createdAt && fileSourceDetails.createdAt.isValid() ? fileSourceDetails.createdAt.toJSON() : undefined,
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
      res.body.forEach((fileSourceDetails: IFileSourceDetails) => {
        fileSourceDetails.createdAt = fileSourceDetails.createdAt ? moment(fileSourceDetails.createdAt) : undefined;
      });
    }
    return res;
  }
}
