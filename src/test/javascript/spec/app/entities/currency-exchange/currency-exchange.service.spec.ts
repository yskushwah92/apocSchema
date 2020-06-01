import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { CurrencyExchangeService } from 'app/entities/currency-exchange/currency-exchange.service';
import { ICurrencyExchange, CurrencyExchange } from 'app/shared/model/currency-exchange.model';
import { Currency } from 'app/shared/model/enumerations/currency.model';

describe('Service Tests', () => {
  describe('CurrencyExchange Service', () => {
    let injector: TestBed;
    let service: CurrencyExchangeService;
    let httpMock: HttpTestingController;
    let elemDefault: ICurrencyExchange;
    let expectedResult: ICurrencyExchange | ICurrencyExchange[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(CurrencyExchangeService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new CurrencyExchange(
        0,
        'AAAAAAA',
        Currency.INDIAN_RUPEE,
        Currency.INDIAN_RUPEE,
        currentDate,
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        false,
        false,
        currentDate,
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            validfrom: currentDate.format(DATE_TIME_FORMAT),
            createdAt: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a CurrencyExchange', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            validfrom: currentDate.format(DATE_TIME_FORMAT),
            createdAt: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            validfrom: currentDate,
            createdAt: currentDate,
          },
          returnedFromService
        );

        service.create(new CurrencyExchange()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a CurrencyExchange', () => {
        const returnedFromService = Object.assign(
          {
            country: 'BBBBBB',
            fromCurrency: 'BBBBBB',
            toCurrency: 'BBBBBB',
            validfrom: currentDate.format(DATE_TIME_FORMAT),
            exchangeRate: 1,
            exchangeType: 'BBBBBB',
            ratioFrom: 'BBBBBB',
            ratioTo: 'BBBBBB',
            isActive: true,
            isDeleted: true,
            createdAt: currentDate.format(DATE_TIME_FORMAT),
            createdBy: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            validfrom: currentDate,
            createdAt: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of CurrencyExchange', () => {
        const returnedFromService = Object.assign(
          {
            country: 'BBBBBB',
            fromCurrency: 'BBBBBB',
            toCurrency: 'BBBBBB',
            validfrom: currentDate.format(DATE_TIME_FORMAT),
            exchangeRate: 1,
            exchangeType: 'BBBBBB',
            ratioFrom: 'BBBBBB',
            ratioTo: 'BBBBBB',
            isActive: true,
            isDeleted: true,
            createdAt: currentDate.format(DATE_TIME_FORMAT),
            createdBy: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            validfrom: currentDate,
            createdAt: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a CurrencyExchange', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
