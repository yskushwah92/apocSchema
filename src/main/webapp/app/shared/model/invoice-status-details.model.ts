import { Moment } from 'moment';
import { IInvoice } from 'app/shared/model/invoice.model';

export interface IInvoiceStatusDetails {
  id?: number;
  name?: string;
  currentStatus?: string;
  statusReason?: string;
  createdAt?: Moment;
  createdBy?: string;
  invoice?: IInvoice;
}

export class InvoiceStatusDetails implements IInvoiceStatusDetails {
  constructor(
    public id?: number,
    public name?: string,
    public currentStatus?: string,
    public statusReason?: string,
    public createdAt?: Moment,
    public createdBy?: string,
    public invoice?: IInvoice
  ) {}
}
