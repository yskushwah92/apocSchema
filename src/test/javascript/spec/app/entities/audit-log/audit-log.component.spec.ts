import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AppTestModule } from '../../../test.module';
import { AuditLogComponent } from 'app/entities/audit-log/audit-log.component';
import { AuditLogService } from 'app/entities/audit-log/audit-log.service';
import { AuditLog } from 'app/shared/model/audit-log.model';

describe('Component Tests', () => {
  describe('AuditLog Management Component', () => {
    let comp: AuditLogComponent;
    let fixture: ComponentFixture<AuditLogComponent>;
    let service: AuditLogService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [AuditLogComponent],
      })
        .overrideTemplate(AuditLogComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(AuditLogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AuditLogService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new AuditLog(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.auditLogs && comp.auditLogs[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
