import { Moment } from 'moment';
import { IContactDetails } from 'app/shared/model/contact-details.model';
import { IVendor } from 'app/shared/model/vendor.model';
import { IPurchaseOrderLine } from 'app/shared/model/purchase-order-line.model';
import { Currency } from 'app/shared/model/enumerations/currency.model';

export interface IOrganization {
  id?: number;
  name?: string;
  organizationCode?: string;
  orgName?: string;
  countryKey?: string;
  financeSystem?: string;
  vatRegistrationNo?: string;
  currency?: Currency;
  language?: string;
  createdAt?: Moment;
  createdBy?: string;
  contactDetails?: IContactDetails;
  vendor?: IVendor;
  purchaseOrderLine?: IPurchaseOrderLine;
}

export class Organization implements IOrganization {
  constructor(
    public id?: number,
    public name?: string,
    public organizationCode?: string,
    public orgName?: string,
    public countryKey?: string,
    public financeSystem?: string,
    public vatRegistrationNo?: string,
    public currency?: Currency,
    public language?: string,
    public createdAt?: Moment,
    public createdBy?: string,
    public contactDetails?: IContactDetails,
    public vendor?: IVendor,
    public purchaseOrderLine?: IPurchaseOrderLine
  ) {}
}
