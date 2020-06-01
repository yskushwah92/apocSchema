import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { InvoiceLineItemService } from 'app/entities/invoice-line-item/invoice-line-item.service';
import { IInvoiceLineItem, InvoiceLineItem } from 'app/shared/model/invoice-line-item.model';

describe('Service Tests', () => {
  describe('InvoiceLineItem Service', () => {
    let injector: TestBed;
    let service: InvoiceLineItemService;
    let httpMock: HttpTestingController;
    let elemDefault: IInvoiceLineItem;
    let expectedResult: IInvoiceLineItem | IInvoiceLineItem[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(InvoiceLineItemService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new InvoiceLineItem(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        'AAAAAAA',
        'AAAAAAA',
        0,
        0,
        'AAAAAAA',
        0,
        0,
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        0,
        0,
        0,
        0,
        0,
        'AAAAAAA',
        0,
        'AAAAAAA',
        0,
        0,
        0,
        currentDate,
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        0,
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            limitOrderExpiryDate: currentDate.format(DATE_TIME_FORMAT),
            limitOrderStartDate: currentDate.format(DATE_TIME_FORMAT),
            createdAt: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a InvoiceLineItem', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            limitOrderExpiryDate: currentDate.format(DATE_TIME_FORMAT),
            limitOrderStartDate: currentDate.format(DATE_TIME_FORMAT),
            createdAt: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            limitOrderExpiryDate: currentDate,
            limitOrderStartDate: currentDate,
            createdAt: currentDate,
          },
          returnedFromService
        );

        service.create(new InvoiceLineItem()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a InvoiceLineItem', () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            expenseType: 'BBBBBB',
            distributionId: 'BBBBBB',
            lineNumber: 1,
            description: 'BBBBBB',
            quantity: 'BBBBBB',
            unitPrice: 1,
            taxRate: 1,
            taxDesc: 'BBBBBB',
            taxAmount: 1,
            lineAmount: 1,
            grossLineAmount: 1,
            confirmerId: 'BBBBBB',
            poRequestBy: 'BBBBBB',
            uom: 'BBBBBB',
            debitCreditIndicator: 'BBBBBB',
            availableQty: 1,
            additionalCostPerUnit: 1,
            taxBaseAmount: 1,
            invoiceQty: 1,
            invoiceUnitPrice: 1,
            vatTaxRate: 1,
            confirmerName: 'BBBBBB',
            availableExpectedvalue: 1,
            availableLimitValue: 'BBBBBB',
            expectedValue: 1,
            actualLimit: 1,
            overallLimit: 1,
            limitOrderExpiryDate: currentDate.format(DATE_TIME_FORMAT),
            limitOrderStartDate: currentDate.format(DATE_TIME_FORMAT),
            vatTaxCode: 'BBBBBB',
            internalOrderStatistical: 'BBBBBB',
            debitCreditValue: 1,
            deliveryNote: 'BBBBBB',
            reason: 'BBBBBB',
            createdAt: currentDate.format(DATE_TIME_FORMAT),
            createdBy: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            limitOrderExpiryDate: currentDate,
            limitOrderStartDate: currentDate,
            createdAt: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of InvoiceLineItem', () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            expenseType: 'BBBBBB',
            distributionId: 'BBBBBB',
            lineNumber: 1,
            description: 'BBBBBB',
            quantity: 'BBBBBB',
            unitPrice: 1,
            taxRate: 1,
            taxDesc: 'BBBBBB',
            taxAmount: 1,
            lineAmount: 1,
            grossLineAmount: 1,
            confirmerId: 'BBBBBB',
            poRequestBy: 'BBBBBB',
            uom: 'BBBBBB',
            debitCreditIndicator: 'BBBBBB',
            availableQty: 1,
            additionalCostPerUnit: 1,
            taxBaseAmount: 1,
            invoiceQty: 1,
            invoiceUnitPrice: 1,
            vatTaxRate: 1,
            confirmerName: 'BBBBBB',
            availableExpectedvalue: 1,
            availableLimitValue: 'BBBBBB',
            expectedValue: 1,
            actualLimit: 1,
            overallLimit: 1,
            limitOrderExpiryDate: currentDate.format(DATE_TIME_FORMAT),
            limitOrderStartDate: currentDate.format(DATE_TIME_FORMAT),
            vatTaxCode: 'BBBBBB',
            internalOrderStatistical: 'BBBBBB',
            debitCreditValue: 1,
            deliveryNote: 'BBBBBB',
            reason: 'BBBBBB',
            createdAt: currentDate.format(DATE_TIME_FORMAT),
            createdBy: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            limitOrderExpiryDate: currentDate,
            limitOrderStartDate: currentDate,
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

      it('should delete a InvoiceLineItem', () => {
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
