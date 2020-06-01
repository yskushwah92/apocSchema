import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { ModelInfoUpdateComponent } from 'app/entities/model-info/model-info-update.component';
import { ModelInfoService } from 'app/entities/model-info/model-info.service';
import { ModelInfo } from 'app/shared/model/model-info.model';

describe('Component Tests', () => {
  describe('ModelInfo Management Update Component', () => {
    let comp: ModelInfoUpdateComponent;
    let fixture: ComponentFixture<ModelInfoUpdateComponent>;
    let service: ModelInfoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [ModelInfoUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ModelInfoUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ModelInfoUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ModelInfoService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ModelInfo(123);
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
        const entity = new ModelInfo();
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
