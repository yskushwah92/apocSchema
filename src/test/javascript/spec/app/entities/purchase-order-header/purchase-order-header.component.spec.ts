import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AppTestModule } from '../../../test.module';
import { PurchaseOrderHeaderComponent } from 'app/entities/purchase-order-header/purchase-order-header.component';
import { PurchaseOrderHeaderService } from 'app/entities/purchase-order-header/purchase-order-header.service';
import { PurchaseOrderHeader } from 'app/shared/model/purchase-order-header.model';

describe('Component Tests', () => {
  describe('PurchaseOrderHeader Management Component', () => {
    let comp: PurchaseOrderHeaderComponent;
    let fixture: ComponentFixture<PurchaseOrderHeaderComponent>;
    let service: PurchaseOrderHeaderService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [PurchaseOrderHeaderComponent],
      })
        .overrideTemplate(PurchaseOrderHeaderComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PurchaseOrderHeaderComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PurchaseOrderHeaderService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new PurchaseOrderHeader(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.purchaseOrderHeaders && comp.purchaseOrderHeaders[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
