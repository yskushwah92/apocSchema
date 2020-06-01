import { Moment } from 'moment';
import { IInvoice } from 'app/shared/model/invoice.model';
import { PaymentMode } from 'app/shared/model/enumerations/payment-mode.model';
import { PaymentStatus } from 'app/shared/model/enumerations/payment-status.model';
import { PaymentType } from 'app/shared/model/enumerations/payment-type.model';
import { ApprovalStatus } from 'app/shared/model/enumerations/approval-status.model';
import { Currency } from 'app/shared/model/enumerations/currency.model';

export interface IPaymentInfo {
  id?: number;
  name?: string;
  terms?: string;
  mode?: PaymentMode;
  dueDate?: Moment;
  status?: PaymentStatus;
  paymentChannel?: string;
  type?: PaymentType;
  approvalStatus?: ApprovalStatus;
  dateOfApproval?: Moment;
  dateOfPayment?: Moment;
  paymentReferenceNumber?: string;
  checkDate?: Moment;
  checkNumber?: string;
  checkAmount?: number;
  earlyPaymentDate?: Moment;
  earlyPaymentDiscount?: string;
  earlyPaymentAmount?: number;
  earlyPaymentRemarks?: string;
  paymentDocDate?: Moment;
  paymentDocNo?: string;
  paymentAmount?: number;
  discountTaken?: number;
  discountLost?: number;
  paymentCurrency?: Currency;
  invoiceBaseAmount?: number;
  clearedDate?: Moment;
  clearedAmount?: number;
  transactionId?: string;
  createdAt?: Moment;
  createdBy?: string;
  invoice?: IInvoice;
  invoice?: IInvoice;
}

export class PaymentInfo implements IPaymentInfo {
  constructor(
    public id?: number,
    public name?: string,
    public terms?: string,
    public mode?: PaymentMode,
    public dueDate?: Moment,
    public status?: PaymentStatus,
    public paymentChannel?: string,
    public type?: PaymentType,
    public approvalStatus?: ApprovalStatus,
    public dateOfApproval?: Moment,
    public dateOfPayment?: Moment,
    public paymentReferenceNumber?: string,
    public checkDate?: Moment,
    public checkNumber?: string,
    public checkAmount?: number,
    public earlyPaymentDate?: Moment,
    public earlyPaymentDiscount?: string,
    public earlyPaymentAmount?: number,
    public earlyPaymentRemarks?: string,
    public paymentDocDate?: Moment,
    public paymentDocNo?: string,
    public paymentAmount?: number,
    public discountTaken?: number,
    public discountLost?: number,
    public paymentCurrency?: Currency,
    public invoiceBaseAmount?: number,
    public clearedDate?: Moment,
    public clearedAmount?: number,
    public transactionId?: string,
    public createdAt?: Moment,
    public createdBy?: string,
    public invoice?: IInvoice,
    public invoice?: IInvoice
  ) {}
}
