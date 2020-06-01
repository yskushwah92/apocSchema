import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { AppTestModule } from '../../../test.module';
import { MockEventManager } from '../../../helpers/mock-event-manager.service';
import { MockActiveModal } from '../../../helpers/mock-active-modal.service';
import { PurchaseOrderHeaderDeleteDialogComponent } from 'app/entities/purchase-order-header/purchase-order-header-delete-dialog.component';
import { PurchaseOrderHeaderService } from 'app/entities/purchase-order-header/purchase-order-header.service';

describe('Component Tests', () => {
  describe('PurchaseOrderHeader Management Delete Component', () => {
    let comp: PurchaseOrderHeaderDeleteDialogComponent;
    let fixture: ComponentFixture<PurchaseOrderHeaderDeleteDialogComponent>;
    let service: PurchaseOrderHeaderService;
    let mockEventManager: MockEventManager;
    let mockActiveModal: MockActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [PurchaseOrderHeaderDeleteDialogComponent],
      })
        .overrideTemplate(PurchaseOrderHeaderDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PurchaseOrderHeaderDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PurchaseOrderHeaderService);
      mockEventManager = TestBed.get(JhiEventManager);
      mockActiveModal = TestBed.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.closeSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));

      it('Should not call delete service on clear', () => {
        // GIVEN
        spyOn(service, 'delete');

        // WHEN
        comp.cancel();

        // THEN
        expect(service.delete).not.toHaveBeenCalled();
        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
      });
    });
  });
});
