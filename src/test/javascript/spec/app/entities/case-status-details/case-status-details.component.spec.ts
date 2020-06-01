import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AppTestModule } from '../../../test.module';
import { CaseStatusDetailsComponent } from 'app/entities/case-status-details/case-status-details.component';
import { CaseStatusDetailsService } from 'app/entities/case-status-details/case-status-details.service';
import { CaseStatusDetails } from 'app/shared/model/case-status-details.model';

describe('Component Tests', () => {
  describe('CaseStatusDetails Management Component', () => {
    let comp: CaseStatusDetailsComponent;
    let fixture: ComponentFixture<CaseStatusDetailsComponent>;
    let service: CaseStatusDetailsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [CaseStatusDetailsComponent],
      })
        .overrideTemplate(CaseStatusDetailsComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CaseStatusDetailsComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CaseStatusDetailsService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new CaseStatusDetails(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.caseStatusDetails && comp.caseStatusDetails[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
