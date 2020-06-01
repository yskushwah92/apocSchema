import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AppTestModule } from '../../../test.module';
import { InvoiceHeaderComponent } from 'app/entities/invoice-header/invoice-header.component';
import { InvoiceHeaderService } from 'app/entities/invoice-header/invoice-header.service';
import { InvoiceHeader } from 'app/shared/model/invoice-header.model';

describe('Component Tests', () => {
  describe('InvoiceHeader Management Component', () => {
    let comp: InvoiceHeaderComponent;
    let fixture: ComponentFixture<InvoiceHeaderComponent>;
    let service: InvoiceHeaderService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [InvoiceHeaderComponent],
      })
        .overrideTemplate(InvoiceHeaderComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(InvoiceHeaderComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(InvoiceHeaderService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new InvoiceHeader(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.invoiceHeaders && comp.invoiceHeaders[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
