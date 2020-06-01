import { Moment } from 'moment';
import { IContactDetails } from 'app/shared/model/contact-details.model';
import { IOrganization } from 'app/shared/model/organization.model';
import { IGLAccountDetails } from 'app/shared/model/gl-account-details.model';
import { IInvoice } from 'app/shared/model/invoice.model';
import { IVendorPaymentAccountDetails } from 'app/shared/model/vendor-payment-account-details.model';
import { IPurchaseOrderLine } from 'app/shared/model/purchase-order-line.model';
import { IPurchaseOrder } from 'app/shared/model/purchase-order.model';

export interface IVendor {
  id?: number;
  name?: string;
  displayName?: string;
  vendorNumber?: string;
  paymentTerms?: string;
  type?: string;
  iln?: string;
  taxIdNO?: string;
  gstRegistrationNumber?: string;
  gstStatus?: string;
  vatIdNo?: string;
  clerkId?: string;
  status?: string;
  vatRegistrationNumber?: string;
  prefferredModeOfCommunication?: string;
  pointOfContact?: string;
  preferredModeOfPayment?: string;
  createdAt?: Moment;
  createdBy?: string;
  contactDetails?: IContactDetails;
  organization?: IOrganization;
  gLAccountDetails?: IGLAccountDetails;
  invoices?: IInvoice[];
  vendorPaymentAccountDetails?: IVendorPaymentAccountDetails[];
  purchaseOrderLine?: IPurchaseOrderLine;
  purchaseOrders?: IPurchaseOrder[];
}

export class Vendor implements IVendor {
  constructor(
    public id?: number,
    public name?: string,
    public displayName?: string,
    public vendorNumber?: string,
    public paymentTerms?: string,
    public type?: string,
    public iln?: string,
    public taxIdNO?: string,
    public gstRegistrationNumber?: string,
    public gstStatus?: string,
    public vatIdNo?: string,
    public clerkId?: string,
    public status?: string,
    public vatRegistrationNumber?: string,
    public prefferredModeOfCommunication?: string,
    public pointOfContact?: string,
    public preferredModeOfPayment?: string,
    public createdAt?: Moment,
    public createdBy?: string,
    public contactDetails?: IContactDetails,
    public organization?: IOrganization,
    public gLAccountDetails?: IGLAccountDetails,
    public invoices?: IInvoice[],
    public vendorPaymentAccountDetails?: IVendorPaymentAccountDetails[],
    public purchaseOrderLine?: IPurchaseOrderLine,
    public purchaseOrders?: IPurchaseOrder[]
  ) {}
}
