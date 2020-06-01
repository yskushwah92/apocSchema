import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { OCRExtractionEngineInfoDetailComponent } from 'app/entities/ocr-extraction-engine-info/ocr-extraction-engine-info-detail.component';
import { OCRExtractionEngineInfo } from 'app/shared/model/ocr-extraction-engine-info.model';

describe('Component Tests', () => {
  describe('OCRExtractionEngineInfo Management Detail Component', () => {
    let comp: OCRExtractionEngineInfoDetailComponent;
    let fixture: ComponentFixture<OCRExtractionEngineInfoDetailComponent>;
    const route = ({ data: of({ oCRExtractionEngineInfo: new OCRExtractionEngineInfo(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [OCRExtractionEngineInfoDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(OCRExtractionEngineInfoDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(OCRExtractionEngineInfoDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load oCRExtractionEngineInfo on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.oCRExtractionEngineInfo).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
