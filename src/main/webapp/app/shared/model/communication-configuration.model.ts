import { Moment } from 'moment';
import { IMailBox } from 'app/shared/model/mail-box.model';
import { CommunicationMode } from 'app/shared/model/enumerations/communication-mode.model';

export interface ICommunicationConfiguration {
  id?: number;
  priority?: string;
  retries?: string;
  mode?: CommunicationMode;
  name?: string;
  createdAt?: Moment;
  createdBy?: string;
  mailBox?: IMailBox;
}

export class CommunicationConfiguration implements ICommunicationConfiguration {
  constructor(
    public id?: number,
    public priority?: string,
    public retries?: string,
    public mode?: CommunicationMode,
    public name?: string,
    public createdAt?: Moment,
    public createdBy?: string,
    public mailBox?: IMailBox
  ) {}
}
