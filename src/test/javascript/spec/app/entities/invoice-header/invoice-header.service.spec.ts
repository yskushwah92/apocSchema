import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { InvoiceHeaderService } from 'app/entities/invoice-header/invoice-header.service';
import { IInvoiceHeader, InvoiceHeader } from 'app/shared/model/invoice-header.model';
import { Currency } from 'app/shared/model/enumerations/currency.model';

describe('Service Tests', () => {
  describe('InvoiceHeader Service', () => {
    let injector: TestBed;
    let service: InvoiceHeaderService;
    let httpMock: HttpTestingController;
    let elemDefault: IInvoiceHeader;
    let expectedResult: IInvoiceHeader | IInvoiceHeader[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(InvoiceHeaderService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new InvoiceHeader(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        Currency.INDIAN_RUPEE,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        currentDate,
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        'AAAAAAA',
        currentDate,
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        false,
        'AAAAAAA',
        Currency.INDIAN_RUPEE,
        Currency.INDIAN_RUPEE,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        0,
        0,
        0,
        Currency.INDIAN_RUPEE,
        'AAAAAAA',
        currentDate,
        currentDate,
        0,
        currentDate,
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            scanDate: currentDate.format(DATE_TIME_FORMAT),
            receivedDate: currentDate.format(DATE_TIME_FORMAT),
            caseCreationDate: currentDate.format(DATE_TIME_FORMAT),
            slaExpirationDate: currentDate.format(DATE_TIME_FORMAT),
            deliveryDate: currentDate.format(DATE_TIME_FORMAT),
            deliveryNoteNumber: currentDate.format(DATE_TIME_FORMAT),
            postingDate: currentDate.format(DATE_TIME_FORMAT),
            termDate: currentDate.format(DATE_TIME_FORMAT),
            createdAt: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a InvoiceHeader', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            scanDate: currentDate.format(DATE_TIME_FORMAT),
            receivedDate: currentDate.format(DATE_TIME_FORMAT),
            caseCreationDate: currentDate.format(DATE_TIME_FORMAT),
            slaExpirationDate: currentDate.format(DATE_TIME_FORMAT),
            deliveryDate: currentDate.format(DATE_TIME_FORMAT),
            deliveryNoteNumber: currentDate.format(DATE_TIME_FORMAT),
            postingDate: currentDate.format(DATE_TIME_FORMAT),
            termDate: currentDate.format(DATE_TIME_FORMAT),
            createdAt: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            scanDate: currentDate,
            receivedDate: currentDate,
            caseCreationDate: currentDate,
            slaExpirationDate: currentDate,
            deliveryDate: currentDate,
            deliveryNoteNumber: currentDate,
            postingDate: currentDate,
            termDate: currentDate,
            createdAt: currentDate,
          },
          returnedFromService
        );

        service.create(new InvoiceHeader()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a InvoiceHeader', () => {
        const returnedFromService = Object.assign(
          {
            companyCode: 'BBBBBB',
            legalEntityName: 'BBBBBB',
            comments: 'BBBBBB',
            grossAmount: 'BBBBBB',
            netAmount: 'BBBBBB',
            currency: 'BBBBBB',
            country: 'BBBBBB',
            countryKey: 'BBBBBB',
            languageKey: 'BBBBBB',
            erpReferenceNumber: 'BBBBBB',
            poBoxCode: 'BBBBBB',
            scanDate: currentDate.format(DATE_TIME_FORMAT),
            receivedDate: currentDate.format(DATE_TIME_FORMAT),
            caseCreationDate: currentDate.format(DATE_TIME_FORMAT),
            numberOfScannedPages: 'BBBBBB',
            sla: 'BBBBBB',
            slaExpirationDate: currentDate.format(DATE_TIME_FORMAT),
            tradingPartner: 'BBBBBB',
            deliveryDate: currentDate.format(DATE_TIME_FORMAT),
            deliveryNoteNumber: currentDate.format(DATE_TIME_FORMAT),
            recepientEmail: 'BBBBBB',
            isCaseClose: 'BBBBBB',
            ocrRequired: true,
            barcode: 'BBBBBB',
            functionalCurrency: 'BBBBBB',
            reportingCurrency: 'BBBBBB',
            approverRequired: 'BBBBBB',
            siteCode: 'BBBBBB',
            sortCode: 'BBBBBB',
            discount: 1,
            additionalCharges: 1,
            sumLineAmount: 1,
            conversionRate: 1,
            paymentCurrency: 'BBBBBB',
            liabilityAccount: 'BBBBBB',
            postingDate: currentDate.format(DATE_TIME_FORMAT),
            termDate: currentDate.format(DATE_TIME_FORMAT),
            shippingAmount: 1,
            createdAt: currentDate.format(DATE_TIME_FORMAT),
            createdBy: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            scanDate: currentDate,
            receivedDate: currentDate,
            caseCreationDate: currentDate,
            slaExpirationDate: currentDate,
            deliveryDate: currentDate,
            deliveryNoteNumber: currentDate,
            postingDate: currentDate,
            termDate: currentDate,
            createdAt: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of InvoiceHeader', () => {
        const returnedFromService = Object.assign(
          {
            companyCode: 'BBBBBB',
            legalEntityName: 'BBBBBB',
            comments: 'BBBBBB',
            grossAmount: 'BBBBBB',
            netAmount: 'BBBBBB',
            currency: 'BBBBBB',
            country: 'BBBBBB',
            countryKey: 'BBBBBB',
            languageKey: 'BBBBBB',
            erpReferenceNumber: 'BBBBBB',
            poBoxCode: 'BBBBBB',
            scanDate: currentDate.format(DATE_TIME_FORMAT),
            receivedDate: currentDate.format(DATE_TIME_FORMAT),
            caseCreationDate: currentDate.format(DATE_TIME_FORMAT),
            numberOfScannedPages: 'BBBBBB',
            sla: 'BBBBBB',
            slaExpirationDate: currentDate.format(DATE_TIME_FORMAT),
            tradingPartner: 'BBBBBB',
            deliveryDate: currentDate.format(DATE_TIME_FORMAT),
            deliveryNoteNumber: currentDate.format(DATE_TIME_FORMAT),
            recepientEmail: 'BBBBBB',
            isCaseClose: 'BBBBBB',
            ocrRequired: true,
            barcode: 'BBBBBB',
            functionalCurrency: 'BBBBBB',
            reportingCurrency: 'BBBBBB',
            approverRequired: 'BBBBBB',
            siteCode: 'BBBBBB',
            sortCode: 'BBBBBB',
            discount: 1,
            additionalCharges: 1,
            sumLineAmount: 1,
            conversionRate: 1,
            paymentCurrency: 'BBBBBB',
            liabilityAccount: 'BBBBBB',
            postingDate: currentDate.format(DATE_TIME_FORMAT),
            termDate: currentDate.format(DATE_TIME_FORMAT),
            shippingAmount: 1,
            createdAt: currentDate.format(DATE_TIME_FORMAT),
            createdBy: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            scanDate: currentDate,
            receivedDate: currentDate,
            caseCreationDate: currentDate,
            slaExpirationDate: currentDate,
            deliveryDate: currentDate,
            deliveryNoteNumber: currentDate,
            postingDate: currentDate,
            termDate: currentDate,
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

      it('should delete a InvoiceHeader', () => {
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
