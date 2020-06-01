import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { VendorService } from 'app/entities/vendor/vendor.service';
import { IVendor, Vendor } from 'app/shared/model/vendor.model';

describe('Service Tests', () => {
  describe('Vendor Service', () => {
    let injector: TestBed;
    let service: VendorService;
    let httpMock: HttpTestingController;
    let elemDefault: IVendor;
    let expectedResult: IVendor | IVendor[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(VendorService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Vendor(
        0,
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
            createdAt: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Vendor', () => {
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

        service.create(new Vendor()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Vendor', () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            displayName: 'BBBBBB',
            vendorNumber: 'BBBBBB',
            paymentTerms: 'BBBBBB',
            type: 'BBBBBB',
            iln: 'BBBBBB',
            taxIdNO: 'BBBBBB',
            gstRegistrationNumber: 'BBBBBB',
            gstStatus: 'BBBBBB',
            vatIdNo: 'BBBBBB',
            clerkId: 'BBBBBB',
            status: 'BBBBBB',
            vatRegistrationNumber: 'BBBBBB',
            prefferredModeOfCommunication: 'BBBBBB',
            pointOfContact: 'BBBBBB',
            preferredModeOfPayment: 'BBBBBB',
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

      it('should return a list of Vendor', () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            displayName: 'BBBBBB',
            vendorNumber: 'BBBBBB',
            paymentTerms: 'BBBBBB',
            type: 'BBBBBB',
            iln: 'BBBBBB',
            taxIdNO: 'BBBBBB',
            gstRegistrationNumber: 'BBBBBB',
            gstStatus: 'BBBBBB',
            vatIdNo: 'BBBBBB',
            clerkId: 'BBBBBB',
            status: 'BBBBBB',
            vatRegistrationNumber: 'BBBBBB',
            prefferredModeOfCommunication: 'BBBBBB',
            pointOfContact: 'BBBBBB',
            preferredModeOfPayment: 'BBBBBB',
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

      it('should delete a Vendor', () => {
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
