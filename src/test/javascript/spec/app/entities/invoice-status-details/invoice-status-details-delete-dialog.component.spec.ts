import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { AppTestModule } from '../../../test.module';
import { MockEventManager } from '../../../helpers/mock-event-manager.service';
import { MockActiveModal } from '../../../helpers/mock-active-modal.service';
import { InvoiceStatusDetailsDeleteDialogComponent } from 'app/entities/invoice-status-details/invoice-status-details-delete-dialog.component';
import { InvoiceStatusDetailsService } from 'app/entities/invoice-status-details/invoice-status-details.service';

describe('Component Tests', () => {
  describe('InvoiceStatusDetails Management Delete Component', () => {
    let comp: InvoiceStatusDetailsDeleteDialogComponent;
    let fixture: ComponentFixture<InvoiceStatusDetailsDeleteDialogComponent>;
    let service: InvoiceStatusDetailsService;
    let mockEventManager: MockEventManager;
    let mockActiveModal: MockActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [InvoiceStatusDetailsDeleteDialogComponent],
      })
        .overrideTemplate(InvoiceStatusDetailsDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(InvoiceStatusDetailsDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(InvoiceStatusDetailsService);
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
