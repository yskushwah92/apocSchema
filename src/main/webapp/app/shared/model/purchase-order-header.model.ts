import { Moment } from 'moment';
import { IPurchaseOrder } from 'app/shared/model/purchase-order.model';
import { POStatus } from 'app/shared/model/enumerations/po-status.model';

export interface IPurchaseOrderHeader {
  id?: number;
  serialNo?: string;
  amount?: number;
  type?: string;
  requestedBy?: string;
  creationDate?: Moment;
  receiptRequired?: boolean;
  status?: POStatus;
  companyCode?: string;
  currencyCode?: string;
  createdAt?: Moment;
  createdBy?: string;
  purchaseOrder?: IPurchaseOrder;
}

export class PurchaseOrderHeader implements IPurchaseOrderHeader {
  constructor(
    public id?: number,
    public serialNo?: string,
    public amount?: number,
    public type?: string,
    public requestedBy?: string,
    public creationDate?: Moment,
    public receiptRequired?: boolean,
    public status?: POStatus,
    public companyCode?: string,
    public currencyCode?: string,
    public createdAt?: Moment,
    public createdBy?: string,
    public purchaseOrder?: IPurchaseOrder
  ) {
    this.receiptRequired = this.receiptRequired || false;
  }
}
