import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { PurchaseOrderHeaderService } from 'app/entities/purchase-order-header/purchase-order-header.service';
import { IPurchaseOrderHeader, PurchaseOrderHeader } from 'app/shared/model/purchase-order-header.model';
import { POStatus } from 'app/shared/model/enumerations/po-status.model';

describe('Service Tests', () => {
  describe('PurchaseOrderHeader Service', () => {
    let injector: TestBed;
    let service: PurchaseOrderHeaderService;
    let httpMock: HttpTestingController;
    let elemDefault: IPurchaseOrderHeader;
    let expectedResult: IPurchaseOrderHeader | IPurchaseOrderHeader[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(PurchaseOrderHeaderService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new PurchaseOrderHeader(
        0,
        'AAAAAAA',
        0,
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        false,
        POStatus.CLOSED,
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
            creationDate: currentDate.format(DATE_TIME_FORMAT),
            createdAt: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a PurchaseOrderHeader', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            creationDate: currentDate.format(DATE_TIME_FORMAT),
            createdAt: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            creationDate: currentDate,
            createdAt: currentDate,
          },
          returnedFromService
        );

        service.create(new PurchaseOrderHeader()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a PurchaseOrderHeader', () => {
        const returnedFromService = Object.assign(
          {
            serialNo: 'BBBBBB',
            amount: 1,
            type: 'BBBBBB',
            requestedBy: 'BBBBBB',
            creationDate: currentDate.format(DATE_TIME_FORMAT),
            receiptRequired: true,
            status: 'BBBBBB',
            companyCode: 'BBBBBB',
            currencyCode: 'BBBBBB',
            createdAt: currentDate.format(DATE_TIME_FORMAT),
            createdBy: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            creationDate: currentDate,
            createdAt: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of PurchaseOrderHeader', () => {
        const returnedFromService = Object.assign(
          {
            serialNo: 'BBBBBB',
            amount: 1,
            type: 'BBBBBB',
            requestedBy: 'BBBBBB',
            creationDate: currentDate.format(DATE_TIME_FORMAT),
            receiptRequired: true,
            status: 'BBBBBB',
            companyCode: 'BBBBBB',
            currencyCode: 'BBBBBB',
            createdAt: currentDate.format(DATE_TIME_FORMAT),
            createdBy: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            creationDate: currentDate,
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

      it('should delete a PurchaseOrderHeader', () => {
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
