import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IFileSourceDetails } from 'app/shared/model/file-source-details.model';
import { FileSourceDetailsService } from './file-source-details.service';

@Component({
  templateUrl: './file-source-details-delete-dialog.component.html',
})
export class FileSourceDetailsDeleteDialogComponent {
  fileSourceDetails?: IFileSourceDetails;

  constructor(
    protected fileSourceDetailsService: FileSourceDetailsService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.fileSourceDetailsService.delete(id).subscribe(() => {
      this.eventManager.broadcast('fileSourceDetailsListModification');
      this.activeModal.close();
    });
  }
}
