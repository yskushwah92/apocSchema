import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { InvoiceHeaderTaxDetailsDetailComponent } from 'app/entities/invoice-header-tax-details/invoice-header-tax-details-detail.component';
import { InvoiceHeaderTaxDetails } from 'app/shared/model/invoice-header-tax-details.model';

describe('Component Tests', () => {
  describe('InvoiceHeaderTaxDetails Management Detail Component', () => {
    let comp: InvoiceHeaderTaxDetailsDetailComponent;
    let fixture: ComponentFixture<InvoiceHeaderTaxDetailsDetailComponent>;
    const route = ({ data: of({ invoiceHeaderTaxDetails: new InvoiceHeaderTaxDetails(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [InvoiceHeaderTaxDetailsDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(InvoiceHeaderTaxDetailsDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(InvoiceHeaderTaxDetailsDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load invoiceHeaderTaxDetails on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.invoiceHeaderTaxDetails).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
