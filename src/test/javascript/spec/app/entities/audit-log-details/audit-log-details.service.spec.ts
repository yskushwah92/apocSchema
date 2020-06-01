import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { AuditLogDetailsService } from 'app/entities/audit-log-details/audit-log-details.service';
import { IAuditLogDetails, AuditLogDetails } from 'app/shared/model/audit-log-details.model';

describe('Service Tests', () => {
  describe('AuditLogDetails Service', () => {
    let injector: TestBed;
    let service: AuditLogDetailsService;
    let httpMock: HttpTestingController;
    let elemDefault: IAuditLogDetails;
    let expectedResult: IAuditLogDetails | IAuditLogDetails[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(AuditLogDetailsService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new AuditLogDetails(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        false,
        'AAAAAAA',
        currentDate,
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            createdOn: currentDate.format(DATE_TIME_FORMAT),
            createdAt: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a AuditLogDetails', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            createdOn: currentDate.format(DATE_TIME_FORMAT),
            createdAt: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            createdOn: currentDate,
            createdAt: currentDate,
          },
          returnedFromService
        );

        service.create(new AuditLogDetails()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a AuditLogDetails', () => {
        const returnedFromService = Object.assign(
          {
            assignedBy: 'BBBBBB',
            actor: 'BBBBBB',
            status: 'BBBBBB',
            statusCode: 'BBBBBB',
            reason: 'BBBBBB',
            reasonCode: 'BBBBBB',
            createdOn: currentDate.format(DATE_TIME_FORMAT),
            comments: 'BBBBBB',
            delegate: 'BBBBBB',
            delegatedFlag: true,
            metSLA: 'BBBBBB',
            createdAt: currentDate.format(DATE_TIME_FORMAT),
            createdBy: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            createdOn: currentDate,
            createdAt: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of AuditLogDetails', () => {
        const returnedFromService = Object.assign(
          {
            assignedBy: 'BBBBBB',
            actor: 'BBBBBB',
            status: 'BBBBBB',
            statusCode: 'BBBBBB',
            reason: 'BBBBBB',
            reasonCode: 'BBBBBB',
            createdOn: currentDate.format(DATE_TIME_FORMAT),
            comments: 'BBBBBB',
            delegate: 'BBBBBB',
            delegatedFlag: true,
            metSLA: 'BBBBBB',
            createdAt: currentDate.format(DATE_TIME_FORMAT),
            createdBy: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            createdOn: currentDate,
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

      it('should delete a AuditLogDetails', () => {
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
