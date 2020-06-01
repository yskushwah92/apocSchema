import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { PaymentInfoDetailComponent } from 'app/entities/payment-info/payment-info-detail.component';
import { PaymentInfo } from 'app/shared/model/payment-info.model';

describe('Component Tests', () => {
  describe('PaymentInfo Management Detail Component', () => {
    let comp: PaymentInfoDetailComponent;
    let fixture: ComponentFixture<PaymentInfoDetailComponent>;
    const route = ({ data: of({ paymentInfo: new PaymentInfo(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [PaymentInfoDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(PaymentInfoDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PaymentInfoDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load paymentInfo on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.paymentInfo).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
