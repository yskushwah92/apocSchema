import { Moment } from 'moment';
import { IPurchaseOrder } from 'app/shared/model/purchase-order.model';
import { IInvoice } from 'app/shared/model/invoice.model';

export interface INotificationInfo {
  id?: number;
  sender?: string;
  receiver?: string;
  mode?: string;
  message?: string;
  status?: string;
  createdAt?: Moment;
  createdBy?: string;
  purchaseOrder?: IPurchaseOrder;
  invoice?: IInvoice;
}

export class NotificationInfo implements INotificationInfo {
  constructor(
    public id?: number,
    public sender?: string,
    public receiver?: string,
    public mode?: string,
    public message?: string,
    public status?: string,
    public createdAt?: Moment,
    public createdBy?: string,
    public purchaseOrder?: IPurchaseOrder,
    public invoice?: IInvoice
  ) {}
}
