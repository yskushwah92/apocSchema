import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AppTestModule } from '../../../test.module';
import { PaymentInfoComponent } from 'app/entities/payment-info/payment-info.component';
import { PaymentInfoService } from 'app/entities/payment-info/payment-info.service';
import { PaymentInfo } from 'app/shared/model/payment-info.model';

describe('Component Tests', () => {
  describe('PaymentInfo Management Component', () => {
    let comp: PaymentInfoComponent;
    let fixture: ComponentFixture<PaymentInfoComponent>;
    let service: PaymentInfoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [PaymentInfoComponent],
      })
        .overrideTemplate(PaymentInfoComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PaymentInfoComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PaymentInfoService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new PaymentInfo(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.paymentInfos && comp.paymentInfos[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
