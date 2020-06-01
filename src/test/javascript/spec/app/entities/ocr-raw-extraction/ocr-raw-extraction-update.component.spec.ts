import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { OCRRawExtractionUpdateComponent } from 'app/entities/ocr-raw-extraction/ocr-raw-extraction-update.component';
import { OCRRawExtractionService } from 'app/entities/ocr-raw-extraction/ocr-raw-extraction.service';
import { OCRRawExtraction } from 'app/shared/model/ocr-raw-extraction.model';

describe('Component Tests', () => {
  describe('OCRRawExtraction Management Update Component', () => {
    let comp: OCRRawExtractionUpdateComponent;
    let fixture: ComponentFixture<OCRRawExtractionUpdateComponent>;
    let service: OCRRawExtractionService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [OCRRawExtractionUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(OCRRawExtractionUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(OCRRawExtractionUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(OCRRawExtractionService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new OCRRawExtraction(123);
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
        const entity = new OCRRawExtraction();
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
