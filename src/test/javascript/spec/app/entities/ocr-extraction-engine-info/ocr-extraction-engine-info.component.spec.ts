import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AppTestModule } from '../../../test.module';
import { OCRExtractionEngineInfoComponent } from 'app/entities/ocr-extraction-engine-info/ocr-extraction-engine-info.component';
import { OCRExtractionEngineInfoService } from 'app/entities/ocr-extraction-engine-info/ocr-extraction-engine-info.service';
import { OCRExtractionEngineInfo } from 'app/shared/model/ocr-extraction-engine-info.model';

describe('Component Tests', () => {
  describe('OCRExtractionEngineInfo Management Component', () => {
    let comp: OCRExtractionEngineInfoComponent;
    let fixture: ComponentFixture<OCRExtractionEngineInfoComponent>;
    let service: OCRExtractionEngineInfoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [OCRExtractionEngineInfoComponent],
      })
        .overrideTemplate(OCRExtractionEngineInfoComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(OCRExtractionEngineInfoComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(OCRExtractionEngineInfoService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new OCRExtractionEngineInfo(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.oCRExtractionEngineInfos && comp.oCRExtractionEngineInfos[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
