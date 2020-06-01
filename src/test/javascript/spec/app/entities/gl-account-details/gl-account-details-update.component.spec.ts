import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { GLAccountDetailsUpdateComponent } from 'app/entities/gl-account-details/gl-account-details-update.component';
import { GLAccountDetailsService } from 'app/entities/gl-account-details/gl-account-details.service';
import { GLAccountDetails } from 'app/shared/model/gl-account-details.model';

describe('Component Tests', () => {
  describe('GLAccountDetails Management Update Component', () => {
    let comp: GLAccountDetailsUpdateComponent;
    let fixture: ComponentFixture<GLAccountDetailsUpdateComponent>;
    let service: GLAccountDetailsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [GLAccountDetailsUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(GLAccountDetailsUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(GLAccountDetailsUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(GLAccountDetailsService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new GLAccountDetails(123);
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
        const entity = new GLAccountDetails();
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
