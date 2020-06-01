import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { AppTestModule } from '../../../test.module';
import { MockEventManager } from '../../../helpers/mock-event-manager.service';
import { MockActiveModal } from '../../../helpers/mock-active-modal.service';
import { OCRRawExtractionDeleteDialogComponent } from 'app/entities/ocr-raw-extraction/ocr-raw-extraction-delete-dialog.component';
import { OCRRawExtractionService } from 'app/entities/ocr-raw-extraction/ocr-raw-extraction.service';

describe('Component Tests', () => {
  describe('OCRRawExtraction Management Delete Component', () => {
    let comp: OCRRawExtractionDeleteDialogComponent;
    let fixture: ComponentFixture<OCRRawExtractionDeleteDialogComponent>;
    let service: OCRRawExtractionService;
    let mockEventManager: MockEventManager;
    let mockActiveModal: MockActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [OCRRawExtractionDeleteDialogComponent],
      })
        .overrideTemplate(OCRRawExtractionDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(OCRRawExtractionDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(OCRRawExtractionService);
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
