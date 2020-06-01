import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { PurchaseOrderHeaderDetailComponent } from 'app/entities/purchase-order-header/purchase-order-header-detail.component';
import { PurchaseOrderHeader } from 'app/shared/model/purchase-order-header.model';

describe('Component Tests', () => {
  describe('PurchaseOrderHeader Management Detail Component', () => {
    let comp: PurchaseOrderHeaderDetailComponent;
    let fixture: ComponentFixture<PurchaseOrderHeaderDetailComponent>;
    const route = ({ data: of({ purchaseOrderHeader: new PurchaseOrderHeader(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [PurchaseOrderHeaderDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(PurchaseOrderHeaderDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PurchaseOrderHeaderDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load purchaseOrderHeader on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.purchaseOrderHeader).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
