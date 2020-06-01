import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { AuditLogService } from 'app/entities/audit-log/audit-log.service';
import { IAuditLog, AuditLog } from 'app/shared/model/audit-log.model';

describe('Service Tests', () => {
  describe('AuditLog Service', () => {
    let injector: TestBed;
    let service: AuditLogService;
    let httpMock: HttpTestingController;
    let elemDefault: IAuditLog;
    let expectedResult: IAuditLog | IAuditLog[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(AuditLogService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new AuditLog(
        0,
        'AAAAAAA',
        currentDate,
        currentDate,
        currentDate,
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
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
            activityCreationDate: currentDate.format(DATE_TIME_FORMAT),
            activityStartTime: currentDate.format(DATE_TIME_FORMAT),
            activityEndTime: currentDate.format(DATE_TIME_FORMAT),
            assignedDate: currentDate.format(DATE_TIME_FORMAT),
            completeOn: currentDate.format(DATE_TIME_FORMAT),
            createdAt: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a AuditLog', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            activityCreationDate: currentDate.format(DATE_TIME_FORMAT),
            activityStartTime: currentDate.format(DATE_TIME_FORMAT),
            activityEndTime: currentDate.format(DATE_TIME_FORMAT),
            assignedDate: currentDate.format(DATE_TIME_FORMAT),
            completeOn: currentDate.format(DATE_TIME_FORMAT),
            createdAt: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            activityCreationDate: currentDate,
            activityStartTime: currentDate,
            activityEndTime: currentDate,
            assignedDate: currentDate,
            completeOn: currentDate,
            createdAt: currentDate,
          },
          returnedFromService
        );

        service.create(new AuditLog()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a AuditLog', () => {
        const returnedFromService = Object.assign(
          {
            activityName: 'BBBBBB',
            activityCreationDate: currentDate.format(DATE_TIME_FORMAT),
            activityStartTime: currentDate.format(DATE_TIME_FORMAT),
            activityEndTime: currentDate.format(DATE_TIME_FORMAT),
            assignedDate: currentDate.format(DATE_TIME_FORMAT),
            user: 'BBBBBB',
            status: 'BBBBBB',
            reason: 'BBBBBB',
            comments: 'BBBBBB',
            completeOn: currentDate.format(DATE_TIME_FORMAT),
            duration: 'BBBBBB',
            createdAt: currentDate.format(DATE_TIME_FORMAT),
            createdBy: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            activityCreationDate: currentDate,
            activityStartTime: currentDate,
            activityEndTime: currentDate,
            assignedDate: currentDate,
            completeOn: currentDate,
            createdAt: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of AuditLog', () => {
        const returnedFromService = Object.assign(
          {
            activityName: 'BBBBBB',
            activityCreationDate: currentDate.format(DATE_TIME_FORMAT),
            activityStartTime: currentDate.format(DATE_TIME_FORMAT),
            activityEndTime: currentDate.format(DATE_TIME_FORMAT),
            assignedDate: currentDate.format(DATE_TIME_FORMAT),
            user: 'BBBBBB',
            status: 'BBBBBB',
            reason: 'BBBBBB',
            comments: 'BBBBBB',
            completeOn: currentDate.format(DATE_TIME_FORMAT),
            duration: 'BBBBBB',
            createdAt: currentDate.format(DATE_TIME_FORMAT),
            createdBy: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            activityCreationDate: currentDate,
            activityStartTime: currentDate,
            activityEndTime: currentDate,
            assignedDate: currentDate,
            completeOn: currentDate,
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

      it('should delete a AuditLog', () => {
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
