import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AppTestModule } from '../../../test.module';
import { ShippingLocationComponent } from 'app/entities/shipping-location/shipping-location.component';
import { ShippingLocationService } from 'app/entities/shipping-location/shipping-location.service';
import { ShippingLocation } from 'app/shared/model/shipping-location.model';

describe('Component Tests', () => {
  describe('ShippingLocation Management Component', () => {
    let comp: ShippingLocationComponent;
    let fixture: ComponentFixture<ShippingLocationComponent>;
    let service: ShippingLocationService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [ShippingLocationComponent],
      })
        .overrideTemplate(ShippingLocationComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ShippingLocationComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ShippingLocationService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ShippingLocation(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.shippingLocations && comp.shippingLocations[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
