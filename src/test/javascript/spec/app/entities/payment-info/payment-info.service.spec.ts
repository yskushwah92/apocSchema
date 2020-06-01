import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { PaymentInfoService } from 'app/entities/payment-info/payment-info.service';
import { IPaymentInfo, PaymentInfo } from 'app/shared/model/payment-info.model';
import { PaymentMode } from 'app/shared/model/enumerations/payment-mode.model';
import { PaymentStatus } from 'app/shared/model/enumerations/payment-status.model';
import { PaymentType } from 'app/shared/model/enumerations/payment-type.model';
import { ApprovalStatus } from 'app/shared/model/enumerations/approval-status.model';
import { Currency } from 'app/shared/model/enumerations/currency.model';

describe('Service Tests', () => {
  describe('PaymentInfo Service', () => {
    let injector: TestBed;
    let service: PaymentInfoService;
    let httpMock: HttpTestingController;
    let elemDefault: IPaymentInfo;
    let expectedResult: IPaymentInfo | IPaymentInfo[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(PaymentInfoService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new PaymentInfo(
        0,
        'AAAAAAA',
        'AAAAAAA',
        PaymentMode.CASH,
        currentDate,
        PaymentStatus.SUCCESS,
        'AAAAAAA',
        PaymentType.FORWARD,
        ApprovalStatus.APPROVED,
        currentDate,
        currentDate,
        'AAAAAAA',
        currentDate,
        'AAAAAAA',
        0,
        currentDate,
        'AAAAAAA',
        0,
        'AAAAAAA',
        currentDate,
        'AAAAAAA',
        0,
        0,
        0,
        Currency.INDIAN_RUPEE,
        0,
        currentDate,
        0,
        'AAAAAAA',
        currentDate,
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            dueDate: currentDate.format(DATE_TIME_FORMAT),
            dateOfApproval: currentDate.format(DATE_TIME_FORMAT),
            dateOfPayment: currentDate.format(DATE_TIME_FORMAT),
            checkDate: currentDate.format(DATE_TIME_FORMAT),
            earlyPaymentDate: currentDate.format(DATE_TIME_FORMAT),
            paymentDocDate: currentDate.format(DATE_TIME_FORMAT),
            clearedDate: currentDate.format(DATE_TIME_FORMAT),
            createdAt: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a PaymentInfo', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dueDate: currentDate.format(DATE_TIME_FORMAT),
            dateOfApproval: currentDate.format(DATE_TIME_FORMAT),
            dateOfPayment: currentDate.format(DATE_TIME_FORMAT),
            checkDate: currentDate.format(DATE_TIME_FORMAT),
            earlyPaymentDate: currentDate.format(DATE_TIME_FORMAT),
            paymentDocDate: currentDate.format(DATE_TIME_FORMAT),
            clearedDate: currentDate.format(DATE_TIME_FORMAT),
            createdAt: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dueDate: currentDate,
            dateOfApproval: currentDate,
            dateOfPayment: currentDate,
            checkDate: currentDate,
            earlyPaymentDate: currentDate,
            paymentDocDate: currentDate,
            clearedDate: currentDate,
            createdAt: currentDate,
          },
          returnedFromService
        );

        service.create(new PaymentInfo()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a PaymentInfo', () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            terms: 'BBBBBB',
            mode: 'BBBBBB',
            dueDate: currentDate.format(DATE_TIME_FORMAT),
            status: 'BBBBBB',
            paymentChannel: 'BBBBBB',
            type: 'BBBBBB',
            approvalStatus: 'BBBBBB',
            dateOfApproval: currentDate.format(DATE_TIME_FORMAT),
            dateOfPayment: currentDate.format(DATE_TIME_FORMAT),
            paymentReferenceNumber: 'BBBBBB',
            checkDate: currentDate.format(DATE_TIME_FORMAT),
            checkNumber: 'BBBBBB',
            checkAmount: 1,
            earlyPaymentDate: currentDate.format(DATE_TIME_FORMAT),
            earlyPaymentDiscount: 'BBBBBB',
            earlyPaymentAmount: 1,
            earlyPaymentRemarks: 'BBBBBB',
            paymentDocDate: currentDate.format(DATE_TIME_FORMAT),
            paymentDocNo: 'BBBBBB',
            paymentAmount: 1,
            discountTaken: 1,
            discountLost: 1,
            paymentCurrency: 'BBBBBB',
            invoiceBaseAmount: 1,
            clearedDate: currentDate.format(DATE_TIME_FORMAT),
            clearedAmount: 1,
            transactionId: 'BBBBBB',
            createdAt: currentDate.format(DATE_TIME_FORMAT),
            createdBy: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dueDate: currentDate,
            dateOfApproval: currentDate,
            dateOfPayment: currentDate,
            checkDate: currentDate,
            earlyPaymentDate: currentDate,
            paymentDocDate: currentDate,
            clearedDate: currentDate,
            createdAt: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of PaymentInfo', () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            terms: 'BBBBBB',
            mode: 'BBBBBB',
            dueDate: currentDate.format(DATE_TIME_FORMAT),
            status: 'BBBBBB',
            paymentChannel: 'BBBBBB',
            type: 'BBBBBB',
            approvalStatus: 'BBBBBB',
            dateOfApproval: currentDate.format(DATE_TIME_FORMAT),
            dateOfPayment: currentDate.format(DATE_TIME_FORMAT),
            paymentReferenceNumber: 'BBBBBB',
            checkDate: currentDate.format(DATE_TIME_FORMAT),
            checkNumber: 'BBBBBB',
            checkAmount: 1,
            earlyPaymentDate: currentDate.format(DATE_TIME_FORMAT),
            earlyPaymentDiscount: 'BBBBBB',
            earlyPaymentAmount: 1,
            earlyPaymentRemarks: 'BBBBBB',
            paymentDocDate: currentDate.format(DATE_TIME_FORMAT),
            paymentDocNo: 'BBBBBB',
            paymentAmount: 1,
            discountTaken: 1,
            discountLost: 1,
            paymentCurrency: 'BBBBBB',
            invoiceBaseAmount: 1,
            clearedDate: currentDate.format(DATE_TIME_FORMAT),
            clearedAmount: 1,
            transactionId: 'BBBBBB',
            createdAt: currentDate.format(DATE_TIME_FORMAT),
            createdBy: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dueDate: currentDate,
            dateOfApproval: currentDate,
            dateOfPayment: currentDate,
            checkDate: currentDate,
            earlyPaymentDate: currentDate,
            paymentDocDate: currentDate,
            clearedDate: currentDate,
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

      it('should delete a PaymentInfo', () => {
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
