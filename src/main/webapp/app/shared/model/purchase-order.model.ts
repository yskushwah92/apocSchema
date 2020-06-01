import { Moment } from 'moment';
import { IPurchaseOrderHeader } from 'app/shared/model/purchase-order-header.model';
import { IGRNDetails } from 'app/shared/model/grn-details.model';
import { IPurchaseOrderLine } from 'app/shared/model/purchase-order-line.model';
import { IInvoice } from 'app/shared/model/invoice.model';
import { IVendor } from 'app/shared/model/vendor.model';
import { INotificationInfo } from 'app/shared/model/notification-info.model';

export interface IPurchaseOrder {
  id?: number;
  serialNo?: string;
  header?: string;
  unit?: string;
  issuer?: string;
  issuerDetails?: string;
  creationDate?: Moment;
  price?: string;
  status?: string;
  createdBy?: string;
  purchaseOrderHeader?: IPurchaseOrderHeader;
  gRNDetails?: IGRNDetails;
  purchaseOrderLines?: IPurchaseOrderLine[];
  invoices?: IInvoice[];
  vendors?: IVendor[];
  notificationInfo?: INotificationInfo;
}

export class PurchaseOrder implements IPurchaseOrder {
  constructor(
    public id?: number,
    public serialNo?: string,
    public header?: string,
    public unit?: string,
    public issuer?: string,
    public issuerDetails?: string,
    public creationDate?: Moment,
    public price?: string,
    public status?: string,
    public createdBy?: string,
    public purchaseOrderHeader?: IPurchaseOrderHeader,
    public gRNDetails?: IGRNDetails,
    public purchaseOrderLines?: IPurchaseOrderLine[],
    public invoices?: IInvoice[],
    public vendors?: IVendor[],
    public notificationInfo?: INotificationInfo
  ) {}
}
