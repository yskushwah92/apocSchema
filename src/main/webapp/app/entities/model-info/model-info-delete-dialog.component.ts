import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IModelInfo } from 'app/shared/model/model-info.model';
import { ModelInfoService } from './model-info.service';

@Component({
  templateUrl: './model-info-delete-dialog.component.html',
})
export class ModelInfoDeleteDialogComponent {
  modelInfo?: IModelInfo;

  constructor(protected modelInfoService: ModelInfoService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.modelInfoService.delete(id).subscribe(() => {
      this.eventManager.broadcast('modelInfoListModification');
      this.activeModal.close();
    });
  }
}
