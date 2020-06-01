import { Moment } from 'moment';

export interface ITaxCode {
  id?: number;
  name?: string;
  country?: string;
  countryKey?: string;
  taxCode?: string;
  description?: string;
  taxCodeDescription?: string;
  taxRate?: number;
  createdAt?: Moment;
  createdBy?: string;
}

export class TaxCode implements ITaxCode {
  constructor(
    public id?: number,
    public name?: string,
    public country?: string,
    public countryKey?: string,
    public taxCode?: string,
    public description?: string,
    public taxCodeDescription?: string,
    public taxRate?: number,
    public createdAt?: Moment,
    public createdBy?: string
  ) {}
}
