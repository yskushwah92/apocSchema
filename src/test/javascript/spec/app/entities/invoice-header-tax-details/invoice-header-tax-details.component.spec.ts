import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AppTestModule } from '../../../test.module';
import { InvoiceHeaderTaxDetailsComponent } from 'app/entities/invoice-header-tax-details/invoice-header-tax-details.component';
import { InvoiceHeaderTaxDetailsService } from 'app/entities/invoice-header-tax-details/invoice-header-tax-details.service';
import { InvoiceHeaderTaxDetails } from 'app/shared/model/invoice-header-tax-details.model';

describe('Component Tests', () => {
  describe('InvoiceHeaderTaxDetails Management Component', () => {
    let comp: InvoiceHeaderTaxDetailsComponent;
    let fixture: ComponentFixture<InvoiceHeaderTaxDetailsComponent>;
    let service: InvoiceHeaderTaxDetailsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [InvoiceHeaderTaxDetailsComponent],
      })
        .overrideTemplate(InvoiceHeaderTaxDetailsComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(InvoiceHeaderTaxDetailsComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(InvoiceHeaderTaxDetailsService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new InvoiceHeaderTaxDetails(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.invoiceHeaderTaxDetails && comp.invoiceHeaderTaxDetails[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
