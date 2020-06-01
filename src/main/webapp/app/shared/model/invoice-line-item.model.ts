import { Moment } from 'moment';
import { IPurchaseOrderLine } from 'app/shared/model/purchase-order-line.model';
import { IGLAccountDetails } from 'app/shared/model/gl-account-details.model';
import { IUser } from 'app/core/user/user.model';
import { IShippingLocation } from 'app/shared/model/shipping-location.model';
import { IProduct } from 'app/shared/model/product.model';
import { IGRNDetails } from 'app/shared/model/grn-details.model';
import { ICaseStatusDetails } from 'app/shared/model/case-status-details.model';
import { IInvoice } from 'app/shared/model/invoice.model';

export interface IInvoiceLineItem {
  id?: number;
  name?: string;
  expenseType?: string;
  distributionId?: string;
  lineNumber?: number;
  description?: string;
  quantity?: string;
  unitPrice?: number;
  taxRate?: number;
  taxDesc?: string;
  taxAmount?: number;
  lineAmount?: number;
  grossLineAmount?: number;
  confirmerId?: string;
  poRequestBy?: string;
  uom?: string;
  debitCreditIndicator?: string;
  availableQty?: number;
  additionalCostPerUnit?: number;
  taxBaseAmount?: number;
  invoiceQty?: number;
  invoiceUnitPrice?: number;
  vatTaxRate?: number;
  confirmerName?: string;
  availableExpectedvalue?: number;
  availableLimitValue?: string;
  expectedValue?: number;
  actualLimit?: number;
  overallLimit?: number;
  limitOrderExpiryDate?: Moment;
  limitOrderStartDate?: Moment;
  vatTaxCode?: string;
  internalOrderStatistical?: string;
  debitCreditValue?: number;
  deliveryNote?: string;
  reason?: string;
  createdAt?: Moment;
  createdBy?: string;
  purchaseOrderLine?: IPurchaseOrderLine;
  gLAccountDetails?: IGLAccountDetails;
  technicalApprover?: IUser;
  shippingLocation?: IShippingLocation;
  product?: IProduct;
  gRNDetails?: IGRNDetails;
  caseStatusDetails?: ICaseStatusDetails[];
  invoice?: IInvoice;
}

export class InvoiceLineItem implements IInvoiceLineItem {
  constructor(
    public id?: number,
    public name?: string,
    public expenseType?: string,
    public distributionId?: string,
    public lineNumber?: number,
    public description?: string,
    public quantity?: string,
    public unitPrice?: number,
    public taxRate?: number,
    public taxDesc?: string,
    public taxAmount?: number,
    public lineAmount?: number,
    public grossLineAmount?: number,
    public confirmerId?: string,
    public poRequestBy?: string,
    public uom?: string,
    public debitCreditIndicator?: string,
    public availableQty?: number,
    public additionalCostPerUnit?: number,
    public taxBaseAmount?: number,
    public invoiceQty?: number,
    public invoiceUnitPrice?: number,
    public vatTaxRate?: number,
    public confirmerName?: string,
    public availableExpectedvalue?: number,
    public availableLimitValue?: string,
    public expectedValue?: number,
    public actualLimit?: number,
    public overallLimit?: number,
    public limitOrderExpiryDate?: Moment,
    public limitOrderStartDate?: Moment,
    public vatTaxCode?: string,
    public internalOrderStatistical?: string,
    public debitCreditValue?: number,
    public deliveryNote?: string,
    public reason?: string,
    public createdAt?: Moment,
    public createdBy?: string,
    public purchaseOrderLine?: IPurchaseOrderLine,
    public gLAccountDetails?: IGLAccountDetails,
    public technicalApprover?: IUser,
    public shippingLocation?: IShippingLocation,
    public product?: IProduct,
    public gRNDetails?: IGRNDetails,
    public caseStatusDetails?: ICaseStatusDetails[],
    public invoice?: IInvoice
  ) {}
}
