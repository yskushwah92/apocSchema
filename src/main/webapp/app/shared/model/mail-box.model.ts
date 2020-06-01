import { Moment } from 'moment';
import { ICommunicationConfiguration } from 'app/shared/model/communication-configuration.model';

export interface IMailBox {
  id?: number;
  name?: string;
  server?: string;
  address?: string;
  displayName?: string;
  createdAt?: Moment;
  createdBy?: string;
  communicationConfiguration?: ICommunicationConfiguration;
}

export class MailBox implements IMailBox {
  constructor(
    public id?: number,
    public name?: string,
    public server?: string,
    public address?: string,
    public displayName?: string,
    public createdAt?: Moment,
    public createdBy?: string,
    public communicationConfiguration?: ICommunicationConfiguration
  ) {}
}
