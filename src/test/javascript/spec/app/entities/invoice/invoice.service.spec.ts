import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { InvoiceService } from 'app/entities/invoice/invoice.service';
import { IInvoice, Invoice } from 'app/shared/model/invoice.model';
import { Priority } from 'app/shared/model/enumerations/priority.model';
import { InvoiceType } from 'app/shared/model/enumerations/invoice-type.model';
import { InvoiceStatus } from 'app/shared/model/enumerations/invoice-status.model';

describe('Service Tests', () => {
  describe('Invoice Service', () => {
    let injector: TestBed;
    let service: InvoiceService;
    let httpMock: HttpTestingController;
    let elemDefault: IInvoice;
    let expectedResult: IInvoice | IInvoice[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(InvoiceService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Invoice(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        Priority.HIGH,
        InvoiceType.PURCHASE_ORDER,
        currentDate,
        'AAAAAAA',
        0,
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        false,
        InvoiceStatus.ACCEPTED,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
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
            dateOfCreation: currentDate.format(DATE_TIME_FORMAT),
            createdAt: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Invoice', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dateOfCreation: currentDate.format(DATE_TIME_FORMAT),
            createdAt: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateOfCreation: currentDate,
            createdAt: currentDate,
          },
          returnedFromService
        );

        service.create(new Invoice()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Invoice', () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            serialNo: 'BBBBBB',
            invoiceNo: 'BBBBBB',
            referenceNo: 'BBBBBB',
            caseNo: 'BBBBBB',
            description: 'BBBBBB',
            priority: 'BBBBBB',
            type: 'BBBBBB',
            dateOfCreation: currentDate.format(DATE_TIME_FORMAT),
            sellerName: 'BBBBBB',
            amountFunctional: 1,
            amountReporting: 1,
            price: 'BBBBBB',
            issuer: 'BBBBBB',
            tax: 'BBBBBB',
            inclusiveOfTax: true,
            status: 'BBBBBB',
            gstn: 'BBBBBB',
            address: 'BBBBBB',
            category: 'BBBBBB',
            contactNo: 'BBBBBB',
            email: 'BBBBBB',
            prefferredModeOfCommunication: 'BBBBBB',
            pointOfContact: 'BBBBBB',
            bankAccDetails: 'BBBBBB',
            upiDetails: 'BBBBBB',
            preferredModeOfPayment: 'BBBBBB',
            createdAt: currentDate.format(DATE_TIME_FORMAT),
            createdBy: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateOfCreation: currentDate,
            createdAt: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Invoice', () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            serialNo: 'BBBBBB',
            invoiceNo: 'BBBBBB',
            referenceNo: 'BBBBBB',
            caseNo: 'BBBBBB',
            description: 'BBBBBB',
            priority: 'BBBBBB',
            type: 'BBBBBB',
            dateOfCreation: currentDate.format(DATE_TIME_FORMAT),
            sellerName: 'BBBBBB',
            amountFunctional: 1,
            amountReporting: 1,
            price: 'BBBBBB',
            issuer: 'BBBBBB',
            tax: 'BBBBBB',
            inclusiveOfTax: true,
            status: 'BBBBBB',
            gstn: 'BBBBBB',
            address: 'BBBBBB',
            category: 'BBBBBB',
            contactNo: 'BBBBBB',
            email: 'BBBBBB',
            prefferredModeOfCommunication: 'BBBBBB',
            pointOfContact: 'BBBBBB',
            bankAccDetails: 'BBBBBB',
            upiDetails: 'BBBBBB',
            preferredModeOfPayment: 'BBBBBB',
            createdAt: currentDate.format(DATE_TIME_FORMAT),
            createdBy: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateOfCreation: currentDate,
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

      it('should delete a Invoice', () => {
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
