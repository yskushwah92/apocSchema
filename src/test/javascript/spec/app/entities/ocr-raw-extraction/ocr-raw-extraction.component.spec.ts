import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AppTestModule } from '../../../test.module';
import { OCRRawExtractionComponent } from 'app/entities/ocr-raw-extraction/ocr-raw-extraction.component';
import { OCRRawExtractionService } from 'app/entities/ocr-raw-extraction/ocr-raw-extraction.service';
import { OCRRawExtraction } from 'app/shared/model/ocr-raw-extraction.model';

describe('Component Tests', () => {
  describe('OCRRawExtraction Management Component', () => {
    let comp: OCRRawExtractionComponent;
    let fixture: ComponentFixture<OCRRawExtractionComponent>;
    let service: OCRRawExtractionService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [OCRRawExtractionComponent],
      })
        .overrideTemplate(OCRRawExtractionComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(OCRRawExtractionComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(OCRRawExtractionService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new OCRRawExtraction(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.oCRRawExtractions && comp.oCRRawExtractions[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
