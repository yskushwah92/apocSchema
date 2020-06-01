import { Moment } from 'moment';
import { IInvoiceLineItem } from 'app/shared/model/invoice-line-item.model';

export interface IProduct {
  id?: number;
  name?: string;
  description?: string;
  isActive?: boolean;
  createdAt?: Moment;
  createdBy?: string;
  invoiceLineItem?: IInvoiceLineItem;
}

export class Product implements IProduct {
  constructor(
    public id?: number,
    public name?: string,
    public description?: string,
    public isActive?: boolean,
    public createdAt?: Moment,
    public createdBy?: string,
    public invoiceLineItem?: IInvoiceLineItem
  ) {
    this.isActive = this.isActive || false;
  }
}
