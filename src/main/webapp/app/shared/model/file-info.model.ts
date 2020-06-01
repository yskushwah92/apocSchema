import { Moment } from 'moment';
import { IFileSourceDetails } from 'app/shared/model/file-source-details.model';
import { IInvoice } from 'app/shared/model/invoice.model';
import { FileSource } from 'app/shared/model/enumerations/file-source.model';
import { FileType } from 'app/shared/model/enumerations/file-type.model';
import { DocumentType } from 'app/shared/model/enumerations/document-type.model';
import { DocumentCategory } from 'app/shared/model/enumerations/document-category.model';

export interface IFileInfo {
  id?: number;
  source?: FileSource;
  sourceDetails?: string;
  senderMailId?: string;
  filePath?: string;
  fileName?: string;
  reason?: string;
  fileExtension?: string;
  type?: FileType;
  documentType?: DocumentType;
  documentCategory?: DocumentCategory;
  clientId?: string;
  clientDetails?: string;
  protocol?: string;
  createdAt?: Moment;
  createdBy?: string;
  fileInfos?: IFileInfo[];
  fileSourceDetails?: IFileSourceDetails[];
  invoice?: IInvoice;
  fileInfo?: IFileInfo;
}

export class FileInfo implements IFileInfo {
  constructor(
    public id?: number,
    public source?: FileSource,
    public sourceDetails?: string,
    public senderMailId?: string,
    public filePath?: string,
    public fileName?: string,
    public reason?: string,
    public fileExtension?: string,
    public type?: FileType,
    public documentType?: DocumentType,
    public documentCategory?: DocumentCategory,
    public clientId?: string,
    public clientDetails?: string,
    public protocol?: string,
    public createdAt?: Moment,
    public createdBy?: string,
    public fileInfos?: IFileInfo[],
    public fileSourceDetails?: IFileSourceDetails[],
    public invoice?: IInvoice,
    public fileInfo?: IFileInfo
  ) {}
}
