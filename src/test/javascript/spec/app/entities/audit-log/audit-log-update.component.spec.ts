import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { AuditLogUpdateComponent } from 'app/entities/audit-log/audit-log-update.component';
import { AuditLogService } from 'app/entities/audit-log/audit-log.service';
import { AuditLog } from 'app/shared/model/audit-log.model';

describe('Component Tests', () => {
  describe('AuditLog Management Update Component', () => {
    let comp: AuditLogUpdateComponent;
    let fixture: ComponentFixture<AuditLogUpdateComponent>;
    let service: AuditLogService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [AuditLogUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(AuditLogUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(AuditLogUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AuditLogService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new AuditLog(123);
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
        const entity = new AuditLog();
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
