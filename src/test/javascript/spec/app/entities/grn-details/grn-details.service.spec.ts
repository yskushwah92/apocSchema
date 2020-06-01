import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { GRNDetailsService } from 'app/entities/grn-details/grn-details.service';
import { IGRNDetails, GRNDetails } from 'app/shared/model/grn-details.model';
import { GRNType } from 'app/shared/model/enumerations/grn-type.model';

describe('Service Tests', () => {
  describe('GRNDetails Service', () => {
    let injector: TestBed;
    let service: GRNDetailsService;
    let httpMock: HttpTestingController;
    let elemDefault: IGRNDetails;
    let expectedResult: IGRNDetails | IGRNDetails[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(GRNDetailsService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new GRNDetails(0, 'AAAAAAA', 'AAAAAAA', 0, GRNType.INVOICE, 'AAAAAAA', false);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a GRNDetails', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new GRNDetails()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a GRNDetails', () => {
        const returnedFromService = Object.assign(
          {
            grnNumber: 'BBBBBB',
            grnLineNumber: 'BBBBBB',
            grnQuantity: 1,
            grnType: 'BBBBBB',
            deliveryNote: 'BBBBBB',
            isOpen: true,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of GRNDetails', () => {
        const returnedFromService = Object.assign(
          {
            grnNumber: 'BBBBBB',
            grnLineNumber: 'BBBBBB',
            grnQuantity: 1,
            grnType: 'BBBBBB',
            deliveryNote: 'BBBBBB',
            isOpen: true,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a GRNDetails', () => {
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
