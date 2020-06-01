import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IInvoiceHeaderTaxDetails } from 'app/shared/model/invoice-header-tax-details.model';

type EntityResponseType = HttpResponse<IInvoiceHeaderTaxDetails>;
type EntityArrayResponseType = HttpResponse<IInvoiceHeaderTaxDetails[]>;

@Injectable({ providedIn: 'root' })
export class InvoiceHeaderTaxDetailsService {
  public resourceUrl = SERVER_API_URL + 'api/invoice-header-tax-details';

  constructor(protected http: HttpClient) {}

  create(invoiceHeaderTaxDetails: IInvoiceHeaderTaxDetails): Observable<EntityResponseType> {
    return this.http.post<IInvoiceHeaderTaxDetails>(this.resourceUrl, invoiceHeaderTaxDetails, { observe: 'response' });
  }

  update(invoiceHeaderTaxDetails: IInvoiceHeaderTaxDetails): Observable<EntityResponseType> {
    return this.http.put<IInvoiceHeaderTaxDetails>(this.resourceUrl, invoiceHeaderTaxDetails, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IInvoiceHeaderTaxDetails>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IInvoiceHeaderTaxDetails[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
