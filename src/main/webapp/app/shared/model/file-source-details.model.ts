import { Moment } from 'moment';
import { IFileInfo } from 'app/shared/model/file-info.model';

export interface IFileSourceDetails {
  id?: number;
  name?: string;
  apiKey?: string;
  userName?: string;
  password?: string;
  protocol?: string;
  url?: string;
  createdAt?: Moment;
  createdBy?: string;
  fileInfo?: IFileInfo;
}

export class FileSourceDetails implements IFileSourceDetails {
  constructor(
    public id?: number,
    public name?: string,
    public apiKey?: string,
    public userName?: string,
    public password?: string,
    public protocol?: string,
    public url?: string,
    public createdAt?: Moment,
    public createdBy?: string,
    public fileInfo?: IFileInfo
  ) {}
}
