import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AppTestModule } from '../../../test.module';
import { PurchaseOrderLineComponent } from 'app/entities/purchase-order-line/purchase-order-line.component';
import { PurchaseOrderLineService } from 'app/entities/purchase-order-line/purchase-order-line.service';
import { PurchaseOrderLine } from 'app/shared/model/purchase-order-line.model';

describe('Component Tests', () => {
  describe('PurchaseOrderLine Management Component', () => {
    let comp: PurchaseOrderLineComponent;
    let fixture: ComponentFixture<PurchaseOrderLineComponent>;
    let service: PurchaseOrderLineService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [PurchaseOrderLineComponent],
      })
        .overrideTemplate(PurchaseOrderLineComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PurchaseOrderLineComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PurchaseOrderLineService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new PurchaseOrderLine(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.purchaseOrderLines && comp.purchaseOrderLines[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
