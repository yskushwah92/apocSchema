import { IInvoiceHeader } from 'app/shared/model/invoice-header.model';
import { IPurchaseOrder } from 'app/shared/model/purchase-order.model';
import { IInvoiceLineItem } from 'app/shared/model/invoice-line-item.model';
import { GRNType } from 'app/shared/model/enumerations/grn-type.model';

export interface IGRNDetails {
  id?: number;
  grnNumber?: string;
  grnLineNumber?: string;
  grnQuantity?: number;
  grnType?: GRNType;
  deliveryNote?: string;
  isOpen?: boolean;
  invoiceHeader?: IInvoiceHeader;
  purchaseOrder?: IPurchaseOrder;
  invoiceLineItem?: IInvoiceLineItem;
}

export class GRNDetails implements IGRNDetails {
  constructor(
    public id?: number,
    public grnNumber?: string,
    public grnLineNumber?: string,
    public grnQuantity?: number,
    public grnType?: GRNType,
    public deliveryNote?: string,
    public isOpen?: boolean,
    public invoiceHeader?: IInvoiceHeader,
    public purchaseOrder?: IPurchaseOrder,
    public invoiceLineItem?: IInvoiceLineItem
  ) {
    this.isOpen = this.isOpen || false;
  }
}
