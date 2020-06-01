import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { CaseStatusDetailsDetailComponent } from 'app/entities/case-status-details/case-status-details-detail.component';
import { CaseStatusDetails } from 'app/shared/model/case-status-details.model';

describe('Component Tests', () => {
  describe('CaseStatusDetails Management Detail Component', () => {
    let comp: CaseStatusDetailsDetailComponent;
    let fixture: ComponentFixture<CaseStatusDetailsDetailComponent>;
    const route = ({ data: of({ caseStatusDetails: new CaseStatusDetails(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [CaseStatusDetailsDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(CaseStatusDetailsDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CaseStatusDetailsDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load caseStatusDetails on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.caseStatusDetails).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
