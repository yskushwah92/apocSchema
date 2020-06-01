import { Moment } from 'moment';
import { IVendor } from 'app/shared/model/vendor.model';
import { PaymentAccountType } from 'app/shared/model/enumerations/payment-account-type.model';
import { PaymentMethod } from 'app/shared/model/enumerations/payment-method.model';

export interface IVendorPaymentAccountDetails {
  id?: number;
  name?: string;
  type?: PaymentAccountType;
  method?: PaymentMethod;
  accountNumber?: string;
  accountName?: string;
  accountCode?: string;
  accountKey?: string;
  bankAccountType?: string;
  iban?: string;
  isActive?: boolean;
  createdAt?: Moment;
  createdBy?: string;
  vendor?: IVendor;
}

export class VendorPaymentAccountDetails implements IVendorPaymentAccountDetails {
  constructor(
    public id?: number,
    public name?: string,
    public type?: PaymentAccountType,
    public method?: PaymentMethod,
    public accountNumber?: string,
    public accountName?: string,
    public accountCode?: string,
    public accountKey?: string,
    public bankAccountType?: string,
    public iban?: string,
    public isActive?: boolean,
    public createdAt?: Moment,
    public createdBy?: string,
    public vendor?: IVendor
  ) {
    this.isActive = this.isActive || false;
  }
}
