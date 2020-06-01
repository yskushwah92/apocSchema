import { Moment } from 'moment';
import { IModelInfo } from 'app/shared/model/model-info.model';
import { IInvoice } from 'app/shared/model/invoice.model';
import { Language } from 'app/shared/model/enumerations/language.model';
import { DocumentType } from 'app/shared/model/enumerations/document-type.model';

export interface IOCRRawExtraction {
  id?: number;
  name?: string;
  fieldName?: string;
  capturedfieldValue?: string;
  actualFieldValue?: string;
  language?: Language;
  documentType?: DocumentType;
  accuracy?: string;
  extractions?: string;
  createdAt?: Moment;
  createdBy?: string;
  modelInfo?: IModelInfo;
  invoice?: IInvoice;
}

export class OCRRawExtraction implements IOCRRawExtraction {
  constructor(
    public id?: number,
    public name?: string,
    public fieldName?: string,
    public capturedfieldValue?: string,
    public actualFieldValue?: string,
    public language?: Language,
    public documentType?: DocumentType,
    public accuracy?: string,
    public extractions?: string,
    public createdAt?: Moment,
    public createdBy?: string,
    public modelInfo?: IModelInfo,
    public invoice?: IInvoice
  ) {}
}
