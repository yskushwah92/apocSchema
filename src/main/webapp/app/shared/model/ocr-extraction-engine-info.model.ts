import { Moment } from 'moment';
import { IModelInfo } from 'app/shared/model/model-info.model';

export interface IOCRExtractionEngineInfo {
  id?: number;
  name?: string;
  userName?: string;
  password?: string;
  apiKey?: string;
  createdAt?: Moment;
  createdBy?: string;
  modelInfo?: IModelInfo;
}

export class OCRExtractionEngineInfo implements IOCRExtractionEngineInfo {
  constructor(
    public id?: number,
    public name?: string,
    public userName?: string,
    public password?: string,
    public apiKey?: string,
    public createdAt?: Moment,
    public createdBy?: string,
    public modelInfo?: IModelInfo
  ) {}
}
