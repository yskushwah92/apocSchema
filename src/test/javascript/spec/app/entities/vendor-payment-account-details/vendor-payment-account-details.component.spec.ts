import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AppTestModule } from '../../../test.module';
import { VendorPaymentAccountDetailsComponent } from 'app/entities/vendor-payment-account-details/vendor-payment-account-details.component';
import { VendorPaymentAccountDetailsService } from 'app/entities/vendor-payment-account-details/vendor-payment-account-details.service';
import { VendorPaymentAccountDetails } from 'app/shared/model/vendor-payment-account-details.model';

describe('Component Tests', () => {
  describe('VendorPaymentAccountDetails Management Component', () => {
    let comp: VendorPaymentAccountDetailsComponent;
    let fixture: ComponentFixture<VendorPaymentAccountDetailsComponent>;
    let service: VendorPaymentAccountDetailsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [VendorPaymentAccountDetailsComponent],
      })
        .overrideTemplate(VendorPaymentAccountDetailsComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(VendorPaymentAccountDetailsComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(VendorPaymentAccountDetailsService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new VendorPaymentAccountDetails(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.vendorPaymentAccountDetails && comp.vendorPaymentAccountDetails[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
