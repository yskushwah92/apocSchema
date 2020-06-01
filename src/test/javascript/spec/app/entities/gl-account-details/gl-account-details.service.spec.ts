import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { GLAccountDetailsService } from 'app/entities/gl-account-details/gl-account-details.service';
import { IGLAccountDetails, GLAccountDetails } from 'app/shared/model/gl-account-details.model';

describe('Service Tests', () => {
  describe('GLAccountDetails Service', () => {
    let injector: TestBed;
    let service: GLAccountDetailsService;
    let httpMock: HttpTestingController;
    let elemDefault: IGLAccountDetails;
    let expectedResult: IGLAccountDetails | IGLAccountDetails[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(GLAccountDetailsService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new GLAccountDetails(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        false,
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

      it('should create a GLAccountDetails', () => {
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

        service.create(new GLAccountDetails()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a GLAccountDetails', () => {
        const returnedFromService = Object.assign(
          {
            accountNo: 'BBBBBB',
            country: 'BBBBBB',
            chartOfAccounts: 'BBBBBB',
            categoryId: 'BBBBBB',
            accountType: 'BBBBBB',
            isBalanceSheet: true,
            textForCriterion: 'BBBBBB',
            someText: 'BBBBBB',
            taxCatTaxCode: 'BBBBBB',
            currencykey: 'BBBBBB',
            activeCOA: 'BBBBBB',
            activeCoCode: 'BBBBBB',
            postingWithoutTaxAllowed: 'BBBBBB',
            postingBlock: 'BBBBBB',
            postAtProfitCentre: 'BBBBBB',
            postAtCostCentre: 'BBBBBB',
            postAtSegment: 'BBBBBB',
            postAtInternalOrder: 'BBBBBB',
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

      it('should return a list of GLAccountDetails', () => {
        const returnedFromService = Object.assign(
          {
            accountNo: 'BBBBBB',
            country: 'BBBBBB',
            chartOfAccounts: 'BBBBBB',
            categoryId: 'BBBBBB',
            accountType: 'BBBBBB',
            isBalanceSheet: true,
            textForCriterion: 'BBBBBB',
            someText: 'BBBBBB',
            taxCatTaxCode: 'BBBBBB',
            currencykey: 'BBBBBB',
            activeCOA: 'BBBBBB',
            activeCoCode: 'BBBBBB',
            postingWithoutTaxAllowed: 'BBBBBB',
            postingBlock: 'BBBBBB',
            postAtProfitCentre: 'BBBBBB',
            postAtCostCentre: 'BBBBBB',
            postAtSegment: 'BBBBBB',
            postAtInternalOrder: 'BBBBBB',
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

      it('should delete a GLAccountDetails', () => {
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
