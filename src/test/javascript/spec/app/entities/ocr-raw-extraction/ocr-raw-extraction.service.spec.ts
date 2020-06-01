import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { OCRRawExtractionService } from 'app/entities/ocr-raw-extraction/ocr-raw-extraction.service';
import { IOCRRawExtraction, OCRRawExtraction } from 'app/shared/model/ocr-raw-extraction.model';
import { Language } from 'app/shared/model/enumerations/language.model';
import { DocumentType } from 'app/shared/model/enumerations/document-type.model';

describe('Service Tests', () => {
  describe('OCRRawExtraction Service', () => {
    let injector: TestBed;
    let service: OCRRawExtractionService;
    let httpMock: HttpTestingController;
    let elemDefault: IOCRRawExtraction;
    let expectedResult: IOCRRawExtraction | IOCRRawExtraction[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(OCRRawExtractionService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new OCRRawExtraction(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        Language.ENGLISH,
        DocumentType.INVOICE,
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

      it('should create a OCRRawExtraction', () => {
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

        service.create(new OCRRawExtraction()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a OCRRawExtraction', () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            fieldName: 'BBBBBB',
            capturedfieldValue: 'BBBBBB',
            actualFieldValue: 'BBBBBB',
            language: 'BBBBBB',
            documentType: 'BBBBBB',
            accuracy: 'BBBBBB',
            extractions: 'BBBBBB',
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

      it('should return a list of OCRRawExtraction', () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            fieldName: 'BBBBBB',
            capturedfieldValue: 'BBBBBB',
            actualFieldValue: 'BBBBBB',
            language: 'BBBBBB',
            documentType: 'BBBBBB',
            accuracy: 'BBBBBB',
            extractions: 'BBBBBB',
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

      it('should delete a OCRRawExtraction', () => {
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
