import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { GRNDetailsUpdateComponent } from 'app/entities/grn-details/grn-details-update.component';
import { GRNDetailsService } from 'app/entities/grn-details/grn-details.service';
import { GRNDetails } from 'app/shared/model/grn-details.model';

describe('Component Tests', () => {
  describe('GRNDetails Management Update Component', () => {
    let comp: GRNDetailsUpdateComponent;
    let fixture: ComponentFixture<GRNDetailsUpdateComponent>;
    let service: GRNDetailsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [GRNDetailsUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(GRNDetailsUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(GRNDetailsUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(GRNDetailsService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new GRNDetails(123);
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
        const entity = new GRNDetails();
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
