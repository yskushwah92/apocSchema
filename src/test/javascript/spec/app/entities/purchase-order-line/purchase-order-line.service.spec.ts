import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { PurchaseOrderLineService } from 'app/entities/purchase-order-line/purchase-order-line.service';
import { IPurchaseOrderLine, PurchaseOrderLine } from 'app/shared/model/purchase-order-line.model';
import { Currency } from 'app/shared/model/enumerations/currency.model';

describe('Service Tests', () => {
  describe('PurchaseOrderLine Service', () => {
    let injector: TestBed;
    let service: PurchaseOrderLineService;
    let httpMock: HttpTestingController;
    let elemDefault: IPurchaseOrderLine;
    let expectedResult: IPurchaseOrderLine | IPurchaseOrderLine[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(PurchaseOrderLineService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new PurchaseOrderLine(
        0,
        'AAAAAAA',
        'AAAAAAA',
        0,
        0,
        0,
        'AAAAAAA',
        Currency.INDIAN_RUPEE,
        0,
        0,
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        0,
        false,
        0,
        0,
        0,
        currentDate,
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            deliveryDate: currentDate.format(DATE_TIME_FORMAT),
            createdAt: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a PurchaseOrderLine', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            deliveryDate: currentDate.format(DATE_TIME_FORMAT),
            createdAt: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            deliveryDate: currentDate,
            createdAt: currentDate,
          },
          returnedFromService
        );

        service.create(new PurchaseOrderLine()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a PurchaseOrderLine', () => {
        const returnedFromService = Object.assign(
          {
            country: 'BBBBBB',
            articleNo: 'BBBBBB',
            qty: 1,
            receivedQty: 1,
            billedQty: 1,
            unitOfMeasurement: 'BBBBBB',
            currency: 'BBBBBB',
            lineAmount: 1,
            lineAmountExclTax: 1,
            unitPrice: 1,
            costCentre: 'BBBBBB',
            costCentreid: 'BBBBBB',
            articleDescription: 'BBBBBB',
            deliveryReceiptNo: 'BBBBBB',
            deliveryDate: currentDate.format(DATE_TIME_FORMAT),
            hsnCode: 'BBBBBB',
            tolerance: 'BBBBBB',
            tolerancePrice: 1,
            receiptReqd: true,
            acceptedQty: 1,
            amountReceived: 1,
            availableQty: 1,
            createdAt: currentDate.format(DATE_TIME_FORMAT),
            createdBy: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            deliveryDate: currentDate,
            createdAt: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of PurchaseOrderLine', () => {
        const returnedFromService = Object.assign(
          {
            country: 'BBBBBB',
            articleNo: 'BBBBBB',
            qty: 1,
            receivedQty: 1,
            billedQty: 1,
            unitOfMeasurement: 'BBBBBB',
            currency: 'BBBBBB',
            lineAmount: 1,
            lineAmountExclTax: 1,
            unitPrice: 1,
            costCentre: 'BBBBBB',
            costCentreid: 'BBBBBB',
            articleDescription: 'BBBBBB',
            deliveryReceiptNo: 'BBBBBB',
            deliveryDate: currentDate.format(DATE_TIME_FORMAT),
            hsnCode: 'BBBBBB',
            tolerance: 'BBBBBB',
            tolerancePrice: 1,
            receiptReqd: true,
            acceptedQty: 1,
            amountReceived: 1,
            availableQty: 1,
            createdAt: currentDate.format(DATE_TIME_FORMAT),
            createdBy: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            deliveryDate: currentDate,
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

      it('should delete a PurchaseOrderLine', () => {
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
