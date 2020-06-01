import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { PaymentInfoUpdateComponent } from 'app/entities/payment-info/payment-info-update.component';
import { PaymentInfoService } from 'app/entities/payment-info/payment-info.service';
import { PaymentInfo } from 'app/shared/model/payment-info.model';

describe('Component Tests', () => {
  describe('PaymentInfo Management Update Component', () => {
    let comp: PaymentInfoUpdateComponent;
    let fixture: ComponentFixture<PaymentInfoUpdateComponent>;
    let service: PaymentInfoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [PaymentInfoUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(PaymentInfoUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PaymentInfoUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PaymentInfoService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new PaymentInfo(123);
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
        const entity = new PaymentInfo();
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
