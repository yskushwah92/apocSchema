import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { TaxCodeDetailComponent } from 'app/entities/tax-code/tax-code-detail.component';
import { TaxCode } from 'app/shared/model/tax-code.model';

describe('Component Tests', () => {
  describe('TaxCode Management Detail Component', () => {
    let comp: TaxCodeDetailComponent;
    let fixture: ComponentFixture<TaxCodeDetailComponent>;
    const route = ({ data: of({ taxCode: new TaxCode(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [TaxCodeDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(TaxCodeDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TaxCodeDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load taxCode on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.taxCode).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
