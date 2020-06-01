import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { PurchaseOrderHeaderUpdateComponent } from 'app/entities/purchase-order-header/purchase-order-header-update.component';
import { PurchaseOrderHeaderService } from 'app/entities/purchase-order-header/purchase-order-header.service';
import { PurchaseOrderHeader } from 'app/shared/model/purchase-order-header.model';

describe('Component Tests', () => {
  describe('PurchaseOrderHeader Management Update Component', () => {
    let comp: PurchaseOrderHeaderUpdateComponent;
    let fixture: ComponentFixture<PurchaseOrderHeaderUpdateComponent>;
    let service: PurchaseOrderHeaderService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [PurchaseOrderHeaderUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(PurchaseOrderHeaderUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PurchaseOrderHeaderUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PurchaseOrderHeaderService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new PurchaseOrderHeader(123);
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
        const entity = new PurchaseOrderHeader();
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
