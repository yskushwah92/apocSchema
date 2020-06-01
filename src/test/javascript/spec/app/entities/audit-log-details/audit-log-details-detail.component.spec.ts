import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { AuditLogDetailsDetailComponent } from 'app/entities/audit-log-details/audit-log-details-detail.component';
import { AuditLogDetails } from 'app/shared/model/audit-log-details.model';

describe('Component Tests', () => {
  describe('AuditLogDetails Management Detail Component', () => {
    let comp: AuditLogDetailsDetailComponent;
    let fixture: ComponentFixture<AuditLogDetailsDetailComponent>;
    const route = ({ data: of({ auditLogDetails: new AuditLogDetails(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [AuditLogDetailsDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(AuditLogDetailsDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(AuditLogDetailsDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load auditLogDetails on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.auditLogDetails).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
