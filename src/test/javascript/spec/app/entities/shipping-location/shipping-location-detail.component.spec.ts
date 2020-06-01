import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { ShippingLocationDetailComponent } from 'app/entities/shipping-location/shipping-location-detail.component';
import { ShippingLocation } from 'app/shared/model/shipping-location.model';

describe('Component Tests', () => {
  describe('ShippingLocation Management Detail Component', () => {
    let comp: ShippingLocationDetailComponent;
    let fixture: ComponentFixture<ShippingLocationDetailComponent>;
    const route = ({ data: of({ shippingLocation: new ShippingLocation(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [ShippingLocationDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ShippingLocationDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ShippingLocationDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load shippingLocation on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.shippingLocation).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
