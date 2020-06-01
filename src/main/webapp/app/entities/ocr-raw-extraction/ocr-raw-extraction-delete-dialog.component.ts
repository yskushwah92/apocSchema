import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IOCRRawExtraction } from 'app/shared/model/ocr-raw-extraction.model';
import { OCRRawExtractionService } from './ocr-raw-extraction.service';

@Component({
  templateUrl: './ocr-raw-extraction-delete-dialog.component.html',
})
export class OCRRawExtractionDeleteDialogComponent {
  oCRRawExtraction?: IOCRRawExtraction;

  constructor(
    protected oCRRawExtractionService: OCRRawExtractionService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.oCRRawExtractionService.delete(id).subscribe(() => {
      this.eventManager.broadcast('oCRRawExtractionListModification');
      this.activeModal.close();
    });
  }
}
