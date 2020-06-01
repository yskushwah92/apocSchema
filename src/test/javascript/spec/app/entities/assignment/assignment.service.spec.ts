import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { AssignmentService } from 'app/entities/assignment/assignment.service';
import { IAssignment, Assignment } from 'app/shared/model/assignment.model';
import { AssignmentMode } from 'app/shared/model/enumerations/assignment-mode.model';

describe('Service Tests', () => {
  describe('Assignment Service', () => {
    let injector: TestBed;
    let service: AssignmentService;
    let httpMock: HttpTestingController;
    let elemDefault: IAssignment;
    let expectedResult: IAssignment | IAssignment[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(AssignmentService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Assignment(
        0,
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        'AAAAAAA',
        0,
        AssignmentMode.MANUAL,
        currentDate,
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            assignmentDate: currentDate.format(DATE_TIME_FORMAT),
            setSLA: currentDate.format(DATE_TIME_FORMAT),
            createdAt: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Assignment', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            assignmentDate: currentDate.format(DATE_TIME_FORMAT),
            setSLA: currentDate.format(DATE_TIME_FORMAT),
            createdAt: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            assignmentDate: currentDate,
            setSLA: currentDate,
            createdAt: currentDate,
          },
          returnedFromService
        );

        service.create(new Assignment()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Assignment', () => {
        const returnedFromService = Object.assign(
          {
            assignmentDate: currentDate.format(DATE_TIME_FORMAT),
            assignedTo: 'BBBBBB',
            assignedBy: 'BBBBBB',
            setSLA: currentDate.format(DATE_TIME_FORMAT),
            assignmentStatus: 'BBBBBB',
            percentageCompleted: 1,
            assignmentMode: 'BBBBBB',
            createdAt: currentDate.format(DATE_TIME_FORMAT),
            createdBy: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            assignmentDate: currentDate,
            setSLA: currentDate,
            createdAt: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Assignment', () => {
        const returnedFromService = Object.assign(
          {
            assignmentDate: currentDate.format(DATE_TIME_FORMAT),
            assignedTo: 'BBBBBB',
            assignedBy: 'BBBBBB',
            setSLA: currentDate.format(DATE_TIME_FORMAT),
            assignmentStatus: 'BBBBBB',
            percentageCompleted: 1,
            assignmentMode: 'BBBBBB',
            createdAt: currentDate.format(DATE_TIME_FORMAT),
            createdBy: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            assignmentDate: currentDate,
            setSLA: currentDate,
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

      it('should delete a Assignment', () => {
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
