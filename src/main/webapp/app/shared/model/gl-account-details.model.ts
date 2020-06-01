import { Moment } from 'moment';
import { IVendor } from 'app/shared/model/vendor.model';
import { IPurchaseOrderLine } from 'app/shared/model/purchase-order-line.model';
import { IInvoiceLineItem } from 'app/shared/model/invoice-line-item.model';

export interface IGLAccountDetails {
  id?: number;
  accountNo?: string;
  country?: string;
  chartOfAccounts?: string;
  categoryId?: string;
  accountType?: string;
  isBalanceSheet?: boolean;
  textForCriterion?: string;
  someText?: string;
  taxCatTaxCode?: string;
  currencykey?: string;
  activeCOA?: string;
  activeCoCode?: string;
  postingWithoutTaxAllowed?: string;
  postingBlock?: string;
  postAtProfitCentre?: string;
  postAtCostCentre?: string;
  postAtSegment?: string;
  postAtInternalOrder?: string;
  isActive?: boolean;
  createdAt?: Moment;
  createdBy?: string;
  vendor?: IVendor;
  purchaseOrderLine?: IPurchaseOrderLine;
  invoiceLineItem?: IInvoiceLineItem;
}

export class GLAccountDetails implements IGLAccountDetails {
  constructor(
    public id?: number,
    public accountNo?: string,
    public country?: string,
    public chartOfAccounts?: string,
    public categoryId?: string,
    public accountType?: string,
    public isBalanceSheet?: boolean,
    public textForCriterion?: string,
    public someText?: string,
    public taxCatTaxCode?: string,
    public currencykey?: string,
    public activeCOA?: string,
    public activeCoCode?: string,
    public postingWithoutTaxAllowed?: string,
    public postingBlock?: string,
    public postAtProfitCentre?: string,
    public postAtCostCentre?: string,
    public postAtSegment?: string,
    public postAtInternalOrder?: string,
    public isActive?: boolean,
    public createdAt?: Moment,
    public createdBy?: string,
    public vendor?: IVendor,
    public purchaseOrderLine?: IPurchaseOrderLine,
    public invoiceLineItem?: IInvoiceLineItem
  ) {
    this.isBalanceSheet = this.isBalanceSheet || false;
    this.isActive = this.isActive || false;
  }
}
