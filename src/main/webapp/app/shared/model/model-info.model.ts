import { Moment } from 'moment';
import { IOCRRawExtraction } from 'app/shared/model/ocr-raw-extraction.model';
import { IInvoice } from 'app/shared/model/invoice.model';
import { IOCRExtractionEngineInfo } from 'app/shared/model/ocr-extraction-engine-info.model';

export interface IModelInfo {
  id?: number;
  name?: string;
  version?: string;
  location?: string;
  executionScript?: string;
  createdAt?: Moment;
  createdBy?: string;
  oCRRawExtraction?: IOCRRawExtraction;
  invoice?: IInvoice;
  oCRExtractionEngineInfo?: IOCRExtractionEngineInfo;
}

export class ModelInfo implements IModelInfo {
  constructor(
    public id?: number,
    public name?: string,
    public version?: string,
    public location?: string,
    public executionScript?: string,
    public createdAt?: Moment,
    public createdBy?: string,
    public oCRRawExtraction?: IOCRRawExtraction,
    public invoice?: IInvoice,
    public oCRExtractionEngineInfo?: IOCRExtractionEngineInfo
  ) {}
}
