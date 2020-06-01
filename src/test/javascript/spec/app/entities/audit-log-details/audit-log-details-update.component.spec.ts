import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { AuditLogDetailsUpdateComponent } from 'app/entities/audit-log-details/audit-log-details-update.component';
import { AuditLogDetailsService } from 'app/entities/audit-log-details/audit-log-details.service';
import { AuditLogDetails } from 'app/shared/model/audit-log-details.model';

describe('Component Tests', () => {
  describe('AuditLogDetails Management Update Component', () => {
    let comp: AuditLogDetailsUpdateComponent;
    let fixture: ComponentFixture<AuditLogDetailsUpdateComponent>;
    let service: AuditLogDetailsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [AuditLogDetailsUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(AuditLogDetailsUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(AuditLogDetailsUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AuditLogDetailsService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new AuditLogDetails(123);
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
        const entity = new AuditLogDetails();
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
