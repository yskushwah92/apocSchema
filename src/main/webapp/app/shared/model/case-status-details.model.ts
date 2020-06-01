import { Moment } from 'moment';
import { IInvoiceLineItem } from 'app/shared/model/invoice-line-item.model';

export interface ICaseStatusDetails {
  id?: number;
  status?: string;
  assignee?: string;
  comments?: string;
  timeElapasedInCurrentStatus?: string;
  createdAt?: Moment;
  createdBy?: string;
  invoiceLineItem?: IInvoiceLineItem;
}

export class CaseStatusDetails implements ICaseStatusDetails {
  constructor(
    public id?: number,
    public status?: string,
    public assignee?: string,
    public comments?: string,
    public timeElapasedInCurrentStatus?: string,
    public createdAt?: Moment,
    public createdBy?: string,
    public invoiceLineItem?: IInvoiceLineItem
  ) {}
}
