import { Moment } from 'moment';
import { IGRNDetails } from 'app/shared/model/grn-details.model';
import { IInvoice } from 'app/shared/model/invoice.model';
import { Currency } from 'app/shared/model/enumerations/currency.model';

export interface IInvoiceHeader {
  id?: number;
  companyCode?: string;
  legalEntityName?: string;
  comments?: string;
  grossAmount?: string;
  netAmount?: string;
  currency?: Currency;
  country?: string;
  countryKey?: string;
  languageKey?: string;
  erpReferenceNumber?: string;
  poBoxCode?: string;
  scanDate?: Moment;
  receivedDate?: Moment;
  caseCreationDate?: Moment;
  numberOfScannedPages?: string;
  sla?: string;
  slaExpirationDate?: Moment;
  tradingPartner?: string;
  deliveryDate?: Moment;
  deliveryNoteNumber?: Moment;
  recepientEmail?: string;
  isCaseClose?: string;
  ocrRequired?: boolean;
  barcode?: string;
  functionalCurrency?: Currency;
  reportingCurrency?: Currency;
  approverRequired?: string;
  siteCode?: string;
  sortCode?: string;
  discount?: number;
  additionalCharges?: number;
  sumLineAmount?: number;
  conversionRate?: number;
  paymentCurrency?: Currency;
  liabilityAccount?: string;
  postingDate?: Moment;
  termDate?: Moment;
  shippingAmount?: number;
  createdAt?: Moment;
  createdBy?: string;
  gRNDetails?: IGRNDetails;
  invoice?: IInvoice;
}

export class InvoiceHeader implements IInvoiceHeader {
  constructor(
    public id?: number,
    public companyCode?: string,
    public legalEntityName?: string,
    public comments?: string,
    public grossAmount?: string,
    public netAmount?: string,
    public currency?: Currency,
    public country?: string,
    public countryKey?: string,
    public languageKey?: string,
    public erpReferenceNumber?: string,
    public poBoxCode?: string,
    public scanDate?: Moment,
    public receivedDate?: Moment,
    public caseCreationDate?: Moment,
    public numberOfScannedPages?: string,
    public sla?: string,
    public slaExpirationDate?: Moment,
    public tradingPartner?: string,
    public deliveryDate?: Moment,
    public deliveryNoteNumber?: Moment,
    public recepientEmail?: string,
    public isCaseClose?: string,
    public ocrRequired?: boolean,
    public barcode?: string,
    public functionalCurrency?: Currency,
    public reportingCurrency?: Currency,
    public approverRequired?: string,
    public siteCode?: string,
    public sortCode?: string,
    public discount?: number,
    public additionalCharges?: number,
    public sumLineAmount?: number,
    public conversionRate?: number,
    public paymentCurrency?: Currency,
    public liabilityAccount?: string,
    public postingDate?: Moment,
    public termDate?: Moment,
    public shippingAmount?: number,
    public createdAt?: Moment,
    public createdBy?: string,
    public gRNDetails?: IGRNDetails,
    public invoice?: IInvoice
  ) {
    this.ocrRequired = this.ocrRequired || false;
  }
}
