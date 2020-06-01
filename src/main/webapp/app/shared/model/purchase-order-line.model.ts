import { Moment } from 'moment';
import { IOrganization } from 'app/shared/model/organization.model';
import { IVendor } from 'app/shared/model/vendor.model';
import { IGLAccountDetails } from 'app/shared/model/gl-account-details.model';
import { IInvoiceLineItem } from 'app/shared/model/invoice-line-item.model';
import { IPurchaseOrder } from 'app/shared/model/purchase-order.model';
import { Currency } from 'app/shared/model/enumerations/currency.model';

export interface IPurchaseOrderLine {
  id?: number;
  country?: string;
  articleNo?: string;
  qty?: number;
  receivedQty?: number;
  billedQty?: number;
  unitOfMeasurement?: string;
  currency?: Currency;
  lineAmount?: number;
  lineAmountExclTax?: number;
  unitPrice?: number;
  costCentre?: string;
  costCentreid?: string;
  articleDescription?: string;
  deliveryReceiptNo?: string;
  deliveryDate?: Moment;
  hsnCode?: string;
  tolerance?: string;
  tolerancePrice?: number;
  receiptReqd?: boolean;
  acceptedQty?: number;
  amountReceived?: number;
  availableQty?: number;
  createdAt?: Moment;
  createdBy?: string;
  organization?: IOrganization;
  vendor?: IVendor;
  gLAccountDetails?: IGLAccountDetails;
  invoiceLineItem?: IInvoiceLineItem;
  purchaseOrder?: IPurchaseOrder;
}

export class PurchaseOrderLine implements IPurchaseOrderLine {
  constructor(
    public id?: number,
    public country?: string,
    public articleNo?: string,
    public qty?: number,
    public receivedQty?: number,
    public billedQty?: number,
    public unitOfMeasurement?: string,
    public currency?: Currency,
    public lineAmount?: number,
    public lineAmountExclTax?: number,
    public unitPrice?: number,
    public costCentre?: string,
    public costCentreid?: string,
    public articleDescription?: string,
    public deliveryReceiptNo?: string,
    public deliveryDate?: Moment,
    public hsnCode?: string,
    public tolerance?: string,
    public tolerancePrice?: number,
    public receiptReqd?: boolean,
    public acceptedQty?: number,
    public amountReceived?: number,
    public availableQty?: number,
    public createdAt?: Moment,
    public createdBy?: string,
    public organization?: IOrganization,
    public vendor?: IVendor,
    public gLAccountDetails?: IGLAccountDetails,
    public invoiceLineItem?: IInvoiceLineItem,
    public purchaseOrder?: IPurchaseOrder
  ) {
    this.receiptReqd = this.receiptReqd || false;
  }
}
