import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { AppTestModule } from '../../../test.module';
import { MockEventManager } from '../../../helpers/mock-event-manager.service';
import { MockActiveModal } from '../../../helpers/mock-active-modal.service';
import { OCRExtractionEngineInfoDeleteDialogComponent } from 'app/entities/ocr-extraction-engine-info/ocr-extraction-engine-info-delete-dialog.component';
import { OCRExtractionEngineInfoService } from 'app/entities/ocr-extraction-engine-info/ocr-extraction-engine-info.service';

describe('Component Tests', () => {
  describe('OCRExtractionEngineInfo Management Delete Component', () => {
    let comp: OCRExtractionEngineInfoDeleteDialogComponent;
    let fixture: ComponentFixture<OCRExtractionEngineInfoDeleteDialogComponent>;
    let service: OCRExtractionEngineInfoService;
    let mockEventManager: MockEventManager;
    let mockActiveModal: MockActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [OCRExtractionEngineInfoDeleteDialogComponent],
      })
        .overrideTemplate(OCRExtractionEngineInfoDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(OCRExtractionEngineInfoDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(OCRExtractionEngineInfoService);
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
