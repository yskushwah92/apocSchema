import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { VendorPaymentAccountDetailsUpdateComponent } from 'app/entities/vendor-payment-account-details/vendor-payment-account-details-update.component';
import { VendorPaymentAccountDetailsService } from 'app/entities/vendor-payment-account-details/vendor-payment-account-details.service';
import { VendorPaymentAccountDetails } from 'app/shared/model/vendor-payment-account-details.model';

describe('Component Tests', () => {
  describe('VendorPaymentAccountDetails Management Update Component', () => {
    let comp: VendorPaymentAccountDetailsUpdateComponent;
    let fixture: ComponentFixture<VendorPaymentAccountDetailsUpdateComponent>;
    let service: VendorPaymentAccountDetailsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [VendorPaymentAccountDetailsUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(VendorPaymentAccountDetailsUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(VendorPaymentAccountDetailsUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(VendorPaymentAccountDetailsService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new VendorPaymentAccountDetails(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new VendorPaymentAccountDetails();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
