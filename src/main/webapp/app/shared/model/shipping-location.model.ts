import { Moment } from 'moment';
import { IInvoiceLineItem } from 'app/shared/model/invoice-line-item.model';

export interface IShippingLocation {
  id?: number;
  name?: string;
  locationCode?: string;
  description?: string;
  address?: string;
  country?: string;
  postalCode?: string;
  createdAt?: Moment;
  createdBy?: string;
  invoiceLineItem?: IInvoiceLineItem;
}

export class ShippingLocation implements IShippingLocation {
  constructor(
    public id?: number,
    public name?: string,
    public locationCode?: string,
    public description?: string,
    public address?: string,
    public country?: string,
    public postalCode?: string,
    public createdAt?: Moment,
    public createdBy?: string,
    public invoiceLineItem?: IInvoiceLineItem
  ) {}
}
