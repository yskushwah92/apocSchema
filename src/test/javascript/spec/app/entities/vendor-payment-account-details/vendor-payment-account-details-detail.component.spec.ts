import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { VendorPaymentAccountDetailsDetailComponent } from 'app/entities/vendor-payment-account-details/vendor-payment-account-details-detail.component';
import { VendorPaymentAccountDetails } from 'app/shared/model/vendor-payment-account-details.model';

describe('Component Tests', () => {
  describe('VendorPaymentAccountDetails Management Detail Component', () => {
    let comp: VendorPaymentAccountDetailsDetailComponent;
    let fixture: ComponentFixture<VendorPaymentAccountDetailsDetailComponent>;
    const route = ({ data: of({ vendorPaymentAccountDetails: new VendorPaymentAccountDetails(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [VendorPaymentAccountDetailsDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(VendorPaymentAccountDetailsDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(VendorPaymentAccountDetailsDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load vendorPaymentAccountDetails on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.vendorPaymentAccountDetails).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
