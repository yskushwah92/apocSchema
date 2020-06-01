import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { OCRRawExtractionDetailComponent } from 'app/entities/ocr-raw-extraction/ocr-raw-extraction-detail.component';
import { OCRRawExtraction } from 'app/shared/model/ocr-raw-extraction.model';

describe('Component Tests', () => {
  describe('OCRRawExtraction Management Detail Component', () => {
    let comp: OCRRawExtractionDetailComponent;
    let fixture: ComponentFixture<OCRRawExtractionDetailComponent>;
    const route = ({ data: of({ oCRRawExtraction: new OCRRawExtraction(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [OCRRawExtractionDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(OCRRawExtractionDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(OCRRawExtractionDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load oCRRawExtraction on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.oCRRawExtraction).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
