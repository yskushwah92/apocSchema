import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AppTestModule } from '../../../test.module';
import { AuditLogDetailsComponent } from 'app/entities/audit-log-details/audit-log-details.component';
import { AuditLogDetailsService } from 'app/entities/audit-log-details/audit-log-details.service';
import { AuditLogDetails } from 'app/shared/model/audit-log-details.model';

describe('Component Tests', () => {
  describe('AuditLogDetails Management Component', () => {
    let comp: AuditLogDetailsComponent;
    let fixture: ComponentFixture<AuditLogDetailsComponent>;
    let service: AuditLogDetailsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [AuditLogDetailsComponent],
      })
        .overrideTemplate(AuditLogDetailsComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(AuditLogDetailsComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AuditLogDetailsService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new AuditLogDetails(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.auditLogDetails && comp.auditLogDetails[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
