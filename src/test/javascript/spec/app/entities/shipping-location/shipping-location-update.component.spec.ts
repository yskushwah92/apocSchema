import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { ShippingLocationUpdateComponent } from 'app/entities/shipping-location/shipping-location-update.component';
import { ShippingLocationService } from 'app/entities/shipping-location/shipping-location.service';
import { ShippingLocation } from 'app/shared/model/shipping-location.model';

describe('Component Tests', () => {
  describe('ShippingLocation Management Update Component', () => {
    let comp: ShippingLocationUpdateComponent;
    let fixture: ComponentFixture<ShippingLocationUpdateComponent>;
    let service: ShippingLocationService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [ShippingLocationUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ShippingLocationUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ShippingLocationUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ShippingLocationService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ShippingLocation(123);
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
        const entity = new ShippingLocation();
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
