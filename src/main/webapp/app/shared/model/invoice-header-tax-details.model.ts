export interface IInvoiceHeaderTaxDetails {
  id?: number;
  taxCode?: string;
  taxRate?: number;
  taxAmount?: number;
  taxDescription?: string;
  taxDisplay?: string;
  whtApplicable?: string;
  whtGrpId?: string;
  whtType?: string;
  whtCode?: string;
  whtAmount?: number;
  unplannedDeliveryTaxRate?: number;
  unplannedDeliveryTaxAmount?: number;
}

export class InvoiceHeaderTaxDetails implements IInvoiceHeaderTaxDetails {
  constructor(
    public id?: number,
    public taxCode?: string,
    public taxRate?: number,
    public taxAmount?: number,
    public taxDescription?: string,
    public taxDisplay?: string,
    public whtApplicable?: string,
    public whtGrpId?: string,
    public whtType?: string,
    public whtCode?: string,
    public whtAmount?: number,
    public unplannedDeliveryTaxRate?: number,
    public unplannedDeliveryTaxAmount?: number
  ) {}
}
