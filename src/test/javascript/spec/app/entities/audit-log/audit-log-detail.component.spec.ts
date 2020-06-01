import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { AuditLogDetailComponent } from 'app/entities/audit-log/audit-log-detail.component';
import { AuditLog } from 'app/shared/model/audit-log.model';

describe('Component Tests', () => {
  describe('AuditLog Management Detail Component', () => {
    let comp: AuditLogDetailComponent;
    let fixture: ComponentFixture<AuditLogDetailComponent>;
    const route = ({ data: of({ auditLog: new AuditLog(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [AuditLogDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(AuditLogDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(AuditLogDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load auditLog on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.auditLog).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
