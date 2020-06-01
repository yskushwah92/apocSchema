import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { AppTestModule } from '../../../test.module';
import { MockEventManager } from '../../../helpers/mock-event-manager.service';
import { MockActiveModal } from '../../../helpers/mock-active-modal.service';
import { InvoiceHeaderTaxDetailsDeleteDialogComponent } from 'app/entities/invoice-header-tax-details/invoice-header-tax-details-delete-dialog.component';
import { InvoiceHeaderTaxDetailsService } from 'app/entities/invoice-header-tax-details/invoice-header-tax-details.service';

describe('Component Tests', () => {
  describe('InvoiceHeaderTaxDetails Management Delete Component', () => {
    let comp: InvoiceHeaderTaxDetailsDeleteDialogComponent;
    let fixture: ComponentFixture<InvoiceHeaderTaxDetailsDeleteDialogComponent>;
    let service: InvoiceHeaderTaxDetailsService;
    let mockEventManager: MockEventManager;
    let mockActiveModal: MockActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [InvoiceHeaderTaxDetailsDeleteDialogComponent],
      })
        .overrideTemplate(InvoiceHeaderTaxDetailsDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(InvoiceHeaderTaxDetailsDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(InvoiceHeaderTaxDetailsService);
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
