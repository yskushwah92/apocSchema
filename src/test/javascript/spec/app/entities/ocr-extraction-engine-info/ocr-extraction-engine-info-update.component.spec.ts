import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { OCRExtractionEngineInfoUpdateComponent } from 'app/entities/ocr-extraction-engine-info/ocr-extraction-engine-info-update.component';
import { OCRExtractionEngineInfoService } from 'app/entities/ocr-extraction-engine-info/ocr-extraction-engine-info.service';
import { OCRExtractionEngineInfo } from 'app/shared/model/ocr-extraction-engine-info.model';

describe('Component Tests', () => {
  describe('OCRExtractionEngineInfo Management Update Component', () => {
    let comp: OCRExtractionEngineInfoUpdateComponent;
    let fixture: ComponentFixture<OCRExtractionEngineInfoUpdateComponent>;
    let service: OCRExtractionEngineInfoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [OCRExtractionEngineInfoUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(OCRExtractionEngineInfoUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(OCRExtractionEngineInfoUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(OCRExtractionEngineInfoService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new OCRExtractionEngineInfo(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new OCRExtractionEngineInfo();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
