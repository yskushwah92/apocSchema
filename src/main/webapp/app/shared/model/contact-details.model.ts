import { Moment } from 'moment';
import { IVendor } from 'app/shared/model/vendor.model';
import { IOrganization } from 'app/shared/model/organization.model';

export interface IContactDetails {
  id?: number;
  street?: string;
  zipCode?: string;
  city?: string;
  district?: string;
  poBox?: string;
  poBoxCode?: string;
  country?: string;
  telephoneNo?: string;
  faxNumber?: string;
  email?: string;
  createdAt?: Moment;
  createdBy?: string;
  vendor?: IVendor;
  organization?: IOrganization;
}

export class ContactDetails implements IContactDetails {
  constructor(
    public id?: number,
    public street?: string,
    public zipCode?: string,
    public city?: string,
    public district?: string,
    public poBox?: string,
    public poBoxCode?: string,
    public country?: string,
    public telephoneNo?: string,
    public faxNumber?: string,
    public email?: string,
    public createdAt?: Moment,
    public createdBy?: string,
    public vendor?: IVendor,
    public organization?: IOrganization
  ) {}
}
