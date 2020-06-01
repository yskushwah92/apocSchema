import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { InvoiceStatusDetailsDetailComponent } from 'app/entities/invoice-status-details/invoice-status-details-detail.component';
import { InvoiceStatusDetails } from 'app/shared/model/invoice-status-details.model';

describe('Component Tests', () => {
  describe('InvoiceStatusDetails Management Detail Component', () => {
    let comp: InvoiceStatusDetailsDetailComponent;
    let fixture: ComponentFixture<InvoiceStatusDetailsDetailComponent>;
    const route = ({ data: of({ invoiceStatusDetails: new InvoiceStatusDetails(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [InvoiceStatusDetailsDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(InvoiceStatusDetailsDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(InvoiceStatusDetailsDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load invoiceStatusDetails on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.invoiceStatusDetails).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
