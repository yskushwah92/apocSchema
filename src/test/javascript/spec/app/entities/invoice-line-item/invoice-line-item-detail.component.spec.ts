import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { InvoiceLineItemDetailComponent } from 'app/entities/invoice-line-item/invoice-line-item-detail.component';
import { InvoiceLineItem } from 'app/shared/model/invoice-line-item.model';

describe('Component Tests', () => {
  describe('InvoiceLineItem Management Detail Component', () => {
    let comp: InvoiceLineItemDetailComponent;
    let fixture: ComponentFixture<InvoiceLineItemDetailComponent>;
    const route = ({ data: of({ invoiceLineItem: new InvoiceLineItem(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [InvoiceLineItemDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(InvoiceLineItemDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(InvoiceLineItemDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load invoiceLineItem on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.invoiceLineItem).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
