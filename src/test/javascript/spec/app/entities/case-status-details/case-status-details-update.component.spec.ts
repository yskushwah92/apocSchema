import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { CaseStatusDetailsUpdateComponent } from 'app/entities/case-status-details/case-status-details-update.component';
import { CaseStatusDetailsService } from 'app/entities/case-status-details/case-status-details.service';
import { CaseStatusDetails } from 'app/shared/model/case-status-details.model';

describe('Component Tests', () => {
  describe('CaseStatusDetails Management Update Component', () => {
    let comp: CaseStatusDetailsUpdateComponent;
    let fixture: ComponentFixture<CaseStatusDetailsUpdateComponent>;
    let service: CaseStatusDetailsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [CaseStatusDetailsUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(CaseStatusDetailsUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CaseStatusDetailsUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CaseStatusDetailsService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new CaseStatusDetails(123);
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
        const entity = new CaseStatusDetails();
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
