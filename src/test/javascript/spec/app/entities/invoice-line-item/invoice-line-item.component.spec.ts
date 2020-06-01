import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AppTestModule } from '../../../test.module';
import { InvoiceLineItemComponent } from 'app/entities/invoice-line-item/invoice-line-item.component';
import { InvoiceLineItemService } from 'app/entities/invoice-line-item/invoice-line-item.service';
import { InvoiceLineItem } from 'app/shared/model/invoice-line-item.model';

describe('Component Tests', () => {
  describe('InvoiceLineItem Management Component', () => {
    let comp: InvoiceLineItemComponent;
    let fixture: ComponentFixture<InvoiceLineItemComponent>;
    let service: InvoiceLineItemService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [InvoiceLineItemComponent],
      })
        .overrideTemplate(InvoiceLineItemComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(InvoiceLineItemComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(InvoiceLineItemService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new InvoiceLineItem(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.invoiceLineItems && comp.invoiceLineItems[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
