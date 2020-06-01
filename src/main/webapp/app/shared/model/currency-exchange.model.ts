import { Moment } from 'moment';
import { Currency } from 'app/shared/model/enumerations/currency.model';

export interface ICurrencyExchange {
  id?: number;
  country?: string;
  fromCurrency?: Currency;
  toCurrency?: Currency;
  validfrom?: Moment;
  exchangeRate?: number;
  exchangeType?: string;
  ratioFrom?: string;
  ratioTo?: string;
  isActive?: boolean;
  isDeleted?: boolean;
  createdAt?: Moment;
  createdBy?: string;
}

export class CurrencyExchange implements ICurrencyExchange {
  constructor(
    public id?: number,
    public country?: string,
    public fromCurrency?: Currency,
    public toCurrency?: Currency,
    public validfrom?: Moment,
    public exchangeRate?: number,
    public exchangeType?: string,
    public ratioFrom?: string,
    public ratioTo?: string,
    public isActive?: boolean,
    public isDeleted?: boolean,
    public createdAt?: Moment,
    public createdBy?: string
  ) {
    this.isActive = this.isActive || false;
    this.isDeleted = this.isDeleted || false;
  }
}
