import { Moment } from 'moment';
import { IFileInfo } from 'app/shared/model/file-info.model';
import { IModelInfo } from 'app/shared/model/model-info.model';
import { INotificationInfo } from 'app/shared/model/notification-info.model';
import { IPaymentInfo } from 'app/shared/model/payment-info.model';
import { IInvoiceHeader } from 'app/shared/model/invoice-header.model';
import { IOCRRawExtraction } from 'app/shared/model/ocr-raw-extraction.model';
import { IInvoiceLineItem } from 'app/shared/model/invoice-line-item.model';
import { IInvoiceStatusDetails } from 'app/shared/model/invoice-status-details.model';
import { IAuditLog } from 'app/shared/model/audit-log.model';
import { IAssignment } from 'app/shared/model/assignment.model';
import { IVendor } from 'app/shared/model/vendor.model';
import { IPurchaseOrder } from 'app/shared/model/purchase-order.model';
import { Priority } from 'app/shared/model/enumerations/priority.model';
import { InvoiceType } from 'app/shared/model/enumerations/invoice-type.model';
import { InvoiceStatus } from 'app/shared/model/enumerations/invoice-status.model';

export interface IInvoice {
  id?: number;
  name?: string;
  serialNo?: string;
  invoiceNo?: string;
  referenceNo?: string;
  caseNo?: string;
  description?: string;
  priority?: Priority;
  type?: InvoiceType;
  dateOfCreation?: Moment;
  sellerName?: string;
  amountFunctional?: number;
  amountReporting?: number;
  price?: string;
  issuer?: string;
  tax?: string;
  inclusiveOfTax?: boolean;
  status?: InvoiceStatus;
  gstn?: string;
  address?: string;
  category?: string;
  contactNo?: string;
  email?: string;
  prefferredModeOfCommunication?: string;
  pointOfContact?: string;
  bankAccDetails?: string;
  upiDetails?: string;
  preferredModeOfPayment?: string;
  createdAt?: Moment;
  createdBy?: string;
  fileInfo?: IFileInfo;
  modelInfo?: IModelInfo;
  notificationInfo?: INotificationInfo;
  paymentInfo?: IPaymentInfo;
  invoiceHeader?: IInvoiceHeader;
  oCRRawExtractions?: IOCRRawExtraction[];
  invoices?: IInvoice[];
  invoiceLineItems?: IInvoiceLineItem[];
  invoiceStatusDetails?: IInvoiceStatusDetails[];
  paymentInfos?: IPaymentInfo[];
  auditLogs?: IAuditLog[];
  assignments?: IAssignment[];
  vendor?: IVendor;
  invoice?: IInvoice;
  purchaseOrder?: IPurchaseOrder;
}

export class Invoice implements IInvoice {
  constructor(
    public id?: number,
    public name?: string,
    public serialNo?: string,
    public invoiceNo?: string,
    public referenceNo?: string,
    public caseNo?: string,
    public description?: string,
    public priority?: Priority,
    public type?: InvoiceType,
    public dateOfCreation?: Moment,
    public sellerName?: string,
    public amountFunctional?: number,
    public amountReporting?: number,
    public price?: string,
    public issuer?: string,
    public tax?: string,
    public inclusiveOfTax?: boolean,
    public status?: InvoiceStatus,
    public gstn?: string,
    public address?: string,
    public category?: string,
    public contactNo?: string,
    public email?: string,
    public prefferredModeOfCommunication?: string,
    public pointOfContact?: string,
    public bankAccDetails?: string,
    public upiDetails?: string,
    public preferredModeOfPayment?: string,
    public createdAt?: Moment,
    public createdBy?: string,
    public fileInfo?: IFileInfo,
    public modelInfo?: IModelInfo,
    public notificationInfo?: INotificationInfo,
    public paymentInfo?: IPaymentInfo,
    public invoiceHeader?: IInvoiceHeader,
    public oCRRawExtractions?: IOCRRawExtraction[],
    public invoices?: IInvoice[],
    public invoiceLineItems?: IInvoiceLineItem[],
    public invoiceStatusDetails?: IInvoiceStatusDetails[],
    public paymentInfos?: IPaymentInfo[],
    public auditLogs?: IAuditLog[],
    public assignments?: IAssignment[],
    public vendor?: IVendor,
    public invoice?: IInvoice,
    public purchaseOrder?: IPurchaseOrder
  ) {
    this.inclusiveOfTax = this.inclusiveOfTax || false;
  }
}
