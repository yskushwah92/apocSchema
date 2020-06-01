import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { FileInfoService } from 'app/entities/file-info/file-info.service';
import { IFileInfo, FileInfo } from 'app/shared/model/file-info.model';
import { FileSource } from 'app/shared/model/enumerations/file-source.model';
import { FileType } from 'app/shared/model/enumerations/file-type.model';
import { DocumentType } from 'app/shared/model/enumerations/document-type.model';
import { DocumentCategory } from 'app/shared/model/enumerations/document-category.model';

describe('Service Tests', () => {
  describe('FileInfo Service', () => {
    let injector: TestBed;
    let service: FileInfoService;
    let httpMock: HttpTestingController;
    let elemDefault: IFileInfo;
    let expectedResult: IFileInfo | IFileInfo[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(FileInfoService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new FileInfo(
        0,
        FileSource.MAIL,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        FileType.PDF,
        DocumentType.INVOICE,
        DocumentCategory.INVOICE,
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

      it('should create a FileInfo', () => {
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

        service.create(new FileInfo()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a FileInfo', () => {
        const returnedFromService = Object.assign(
          {
            source: 'BBBBBB',
            sourceDetails: 'BBBBBB',
            senderMailId: 'BBBBBB',
            filePath: 'BBBBBB',
            fileName: 'BBBBBB',
            reason: 'BBBBBB',
            fileExtension: 'BBBBBB',
            type: 'BBBBBB',
            documentType: 'BBBBBB',
            documentCategory: 'BBBBBB',
            clientId: 'BBBBBB',
            clientDetails: 'BBBBBB',
            protocol: 'BBBBBB',
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

      it('should return a list of FileInfo', () => {
        const returnedFromService = Object.assign(
          {
            source: 'BBBBBB',
            sourceDetails: 'BBBBBB',
            senderMailId: 'BBBBBB',
            filePath: 'BBBBBB',
            fileName: 'BBBBBB',
            reason: 'BBBBBB',
            fileExtension: 'BBBBBB',
            type: 'BBBBBB',
            documentType: 'BBBBBB',
            documentCategory: 'BBBBBB',
            clientId: 'BBBBBB',
            clientDetails: 'BBBBBB',
            protocol: 'BBBBBB',
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

      it('should delete a FileInfo', () => {
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
