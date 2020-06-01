import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AppTestModule } from '../../../test.module';
import { InvoiceStatusDetailsComponent } from 'app/entities/invoice-status-details/invoice-status-details.component';
import { InvoiceStatusDetailsService } from 'app/entities/invoice-status-details/invoice-status-details.service';
import { InvoiceStatusDetails } from 'app/shared/model/invoice-status-details.model';

describe('Component Tests', () => {
  describe('InvoiceStatusDetails Management Component', () => {
    let comp: InvoiceStatusDetailsComponent;
    let fixture: ComponentFixture<InvoiceStatusDetailsComponent>;
    let service: InvoiceStatusDetailsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [InvoiceStatusDetailsComponent],
      })
        .overrideTemplate(InvoiceStatusDetailsComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(InvoiceStatusDetailsComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(InvoiceStatusDetailsService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new InvoiceStatusDetails(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.invoiceStatusDetails && comp.invoiceStatusDetails[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
