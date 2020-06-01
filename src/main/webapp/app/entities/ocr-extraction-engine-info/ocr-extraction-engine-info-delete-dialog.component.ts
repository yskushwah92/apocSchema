import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IOCRExtractionEngineInfo } from 'app/shared/model/ocr-extraction-engine-info.model';
import { OCRExtractionEngineInfoService } from './ocr-extraction-engine-info.service';

@Component({
  templateUrl: './ocr-extraction-engine-info-delete-dialog.component.html',
})
export class OCRExtractionEngineInfoDeleteDialogComponent {
  oCRExtractionEngineInfo?: IOCRExtractionEngineInfo;

  constructor(
    protected oCRExtractionEngineInfoService: OCRExtractionEngineInfoService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.oCRExtractionEngineInfoService.delete(id).subscribe(() => {
      this.eventManager.broadcast('oCRExtractionEngineInfoListModification');
      this.activeModal.close();
    });
  }
}
