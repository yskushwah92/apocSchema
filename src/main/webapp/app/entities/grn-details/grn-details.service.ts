import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IGRNDetails } from 'app/shared/model/grn-details.model';

type EntityResponseType = HttpResponse<IGRNDetails>;
type EntityArrayResponseType = HttpResponse<IGRNDetails[]>;

@Injectable({ providedIn: 'root' })
export class GRNDetailsService {
  public resourceUrl = SERVER_API_URL + 'api/grn-details';

  constructor(protected http: HttpClient) {}

  create(gRNDetails: IGRNDetails): Observable<EntityResponseType> {
    return this.http.post<IGRNDetails>(this.resourceUrl, gRNDetails, { observe: 'response' });
  }

  update(gRNDetails: IGRNDetails): Observable<EntityResponseType> {
    return this.http.put<IGRNDetails>(this.resourceUrl, gRNDetails, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IGRNDetails>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IGRNDetails[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
