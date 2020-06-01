import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { VendorPaymentAccountDetailsService } from 'app/entities/vendor-payment-account-details/vendor-payment-account-details.service';
import { IVendorPaymentAccountDetails, VendorPaymentAccountDetails } from 'app/shared/model/vendor-payment-account-details.model';
import { PaymentAccountType } from 'app/shared/model/enumerations/payment-account-type.model';
import { PaymentMethod } from 'app/shared/model/enumerations/payment-method.model';

describe('Service Tests', () => {
  describe('VendorPaymentAccountDetails Service', () => {
    let injector: TestBed;
    let service: VendorPaymentAccountDetailsService;
    let httpMock: HttpTestingController;
    let elemDefault: IVendorPaymentAccountDetails;
    let expectedResult: IVendorPaymentAccountDetails | IVendorPaymentAccountDetails[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(VendorPaymentAccountDetailsService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new VendorPaymentAccountDetails(
        0,
        'AAAAAAA',
        PaymentAccountType.BYBANKTRANSFERINADVANCE,
        PaymentMethod.BANK,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        false,
        currentDate,
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            createdAt: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a VendorPaymentAccountDetails', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            createdAt: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            createdAt: currentDate,
          },
          returnedFromService
        );

        service.create(new VendorPaymentAccountDetails()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a VendorPaymentAccountDetails', () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            type: 'BBBBBB',
            method: 'BBBBBB',
            accountNumber: 'BBBBBB',
            accountName: 'BBBBBB',
            accountCode: 'BBBBBB',
            accountKey: 'BBBBBB',
            bankAccountType: 'BBBBBB',
            iban: 'BBBBBB',
            isActive: true,
            createdAt: currentDate.format(DATE_TIME_FORMAT),
            createdBy: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            createdAt: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of VendorPaymentAccountDetails', () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            type: 'BBBBBB',
            method: 'BBBBBB',
            accountNumber: 'BBBBBB',
            accountName: 'BBBBBB',
            accountCode: 'BBBBBB',
            accountKey: 'BBBBBB',
            bankAccountType: 'BBBBBB',
            iban: 'BBBBBB',
            isActive: true,
            createdAt: currentDate.format(DATE_TIME_FORMAT),
            createdBy: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
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

      it('should delete a VendorPaymentAccountDetails', () => {
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
