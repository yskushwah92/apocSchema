import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { GRNDetailsDetailComponent } from 'app/entities/grn-details/grn-details-detail.component';
import { GRNDetails } from 'app/shared/model/grn-details.model';

describe('Component Tests', () => {
  describe('GRNDetails Management Detail Component', () => {
    let comp: GRNDetailsDetailComponent;
    let fixture: ComponentFixture<GRNDetailsDetailComponent>;
    const route = ({ data: of({ gRNDetails: new GRNDetails(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [GRNDetailsDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(GRNDetailsDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(GRNDetailsDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load gRNDetails on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.gRNDetails).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
